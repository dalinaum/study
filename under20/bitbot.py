#!/usr/bin/env python

import time, datetime
import requests, json
import sys
import base64, hmac, hashlib
import logging

def setup_file_log_hanlder():
    now = datetime.datetime.now()
    global handler
    if 'handler' in globals():
        logger.removeHandler(handler)
    handler = logging.FileHandler(f"log-bitbot_{now.year}-{now.month}-{now.day}.log")
    handler.setLevel(logging.INFO)
    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    handler.setFormatter(formatter)
    logger.addHandler(handler)

if not 'logger' in globals():
    logger = logging.getLogger(__name__)
    logger.setLevel(logging.INFO)
    logger.addHandler(logging.StreamHandler())
    setup_file_log_hanlder()

API_HOST = "https://api.gopax.co.kr"
API_KEY = "XXX"
SECRET_KEY = "YYY"

def calculate_yesterday():
    global yesterday
    yesterday = datetime.datetime.now() - datetime.timedelta(days = 1)
    global yesterday_begin
    yesterday_begin = datetime.datetime(yesterday.year, yesterday.month, yesterday.day,0,0,0,0)
    global yesterday_begin_time
    yesterday_begin_time = int(time.mktime(yesterday_begin.timetuple()) * 1000.0)
    global yesterday_end
    yesterday_end = datetime.datetime(yesterday.year, yesterday.month, yesterday.day,23,59,59,999)
    global yesterday_end_time
    yesterday_end_time = int(time.mktime(yesterday_end.timetuple()) * 1000.0)

calculate_yesterday()

def logResponse(response):
    logger.info("status_code: " + str(response.status_code))
    logger.info("\nheaders: " + str(response.headers))
    logger.info("\ntext: " + response.text)

def assets():
    url = API_HOST + "/assets"
    response = requests.get(url)
    if response.status_code != 200:
        logResponse(response)
        return
    return json.loads(response.text)

def begin_time():
    return yesterday_begin_time

def end_time():
    return yesterday_end_time

def candles(pair_name, start, end, interval = 30):
    url = f"{API_HOST}/trading-pairs/{pair_name}/candles?start={start}&end={end}&interval={interval}"
    response = requests.get(url)
    if response.status_code != 200:
        logResponse(response)
        return
    parsed = json.loads(response.text)
    result = { 'start' : parsed[0][0], 'end' : parsed[-1][0], 'open' : parsed[0][3], 'close' : parsed[-1][4] }
    min = sys.maxsize
    max = -sys.maxsize - 1

    for item in parsed:
        item_min = item[1]
        item_max = item[2]
        if item_min < min:
            min = item_min
        if item_max > max:
            max = item_max

    result['low'] = min
    result['high'] = max
    result['range'] = max - min
    result['signal_range'] = abs(result['close'] - result['open'])
    #result['noise'] = 1 - (result['signal_range'] / result['range'])
    result['noise'] = 0.5
    result['breakout'] = (result['range'] * result['noise']) + result['close']
    result['volatility'] = result['range'] / result['close']
    return result

def prices(pair_name):
    url = f"{API_HOST}/trading-pairs/{pair_name}/book"
    response = requests.get(url)
    if response.status_code != 200:
        logResponse(response)
        return
    return json.loads(response.text)

def prices_ask(pair_name):
    return prices(pair_name)['ask']

def prices_bid(pair_name):
    return prices(pair_name)['bid']

def price_lowest_ask(pair_name):
    (id, price, volume) = prices_ask(pair_name)[0]
    return { 'id' : id, 'price' : price, 'volume' : volume }

def price_highest_ask(pair_name):
    (id, price, volume) = prices_ask(pair_name)[3]
    return { 'id' : id, 'price' : price, 'volume' : volume }

def price_highest_bid(pair_name):
    (id, price, volume) = prices_bid(pair_name)[0]
    return { 'id' : id, 'price' : price, 'volume' : volume }

def price_lowest_bid(pair_name):
    (id, price, volume) = prices_bid(pair_name)[3]
    return { 'id' : id, 'price' : price, 'volume' : volume }

def generate_signature(nonce, method, request_path, body = ''):
    what = nonce + method + request_path + body
    key = base64.b64decode(SECRET_KEY)
    signature = hmac.new(key, what.encode("utf8"), hashlib.sha512)
    return base64.b64encode(signature.digest())

def get_nonce():
    return str(time.time())

def generate_headers(method, request_path, body = ''):
    nonce = get_nonce()
    signature = generate_signature(nonce, method, request_path, body)
    return {
        'API-KEY': API_KEY,
        'SIGNATURE': signature,
        'NONCE': nonce
    }

def balances():
    method = 'GET'
    request_path = '/balances'
    headers = generate_headers(method, request_path)
    url = f"{API_HOST}{request_path}"
    response = requests.get(url, headers=headers)
    if response.status_code != 200:
        logResponse(response)
        return
    return json.loads(response.text)

def balance(pair_name):
    method = 'GET'
    request_path = f"/balances/{pair_name}"
    headers = generate_headers( method, request_path)
    url = f"{API_HOST}{request_path}"
    response = requests.get(url, headers=headers)
    if response.status_code != 200:
        logResponse(response)
        return
    return json.loads(response.text)

def order(pair_name, side, price, amount):
    method = 'POST'
    # type market ?????????. ?????? ?????????
    body = {
        'type' : 'limit',
        'side' : side,
        'price' : price,
        'amount' : amount,
        'tradingPairName' : pair_name
    }
    data = json.dumps(body)
    request_path = "/orders"
    headers = generate_headers(method, request_path, data)
    url = f"{API_HOST}{request_path}"
    response = requests.post(url, headers=headers, data=data)
    if response.status_code != 200:
        logResponse(response)
        return
    return json.loads(response.text)

def buy(pair_name, price, amount):
    return order(pair_name, 'buy', price, amount)

def sell(pair_name, price, amount):
    return order(pair_name, 'sell', price, amount)

def buy_market(pair_name, sum):
    price_map = price_lowest_ask(pair_name)
    price = price_map['price']
    amount = sum / price
    logger.info(f"price: {price} / amount: {amount} / sum : {sum}")
    return buy(pair_name, price, amount)

def sell_market_amount(pair_name, amount):
    price_map = price_lowest_bid(pair_name)
    price = price_map['price']
    logger.info(f"{pair_name} ?????? / ?????? {price} / ??? {amount}")
    return sell(pair_name, price, amount)

def sell_market(name):
    amount = balance(name)['avail']
    if amount < 0.0001:
        logger.info(f"0.0001 ????????? ????????? ??? ????????????: {name} / {amount}")
        return
    pair_name = name + '-KRW'
    return sell_market_amount(pair_name, amount)

def sell_multiple_market(names):
    for name in names:
        logger.info(sell_market(name))

def explain_candles(name, candles):
    logger.info(f"{name}\n\t?????? ?????????:{candles['open']} / ??????:{candles['close']} / ??????:{candles['low']} / ??????:{candles['high']}")
    logger.info(f"\t?????????:{candles['range']} / ?????????:{candles['noise']}  / ????????????:{candles['breakout']} / ?????????:{candles['volatility']}")

def breakout():
    logger.info("????????? ????????? ??? ????????? ???????????????.")
    calculate_yesterday()
    print(begin_time())
    print(end_time())

    total_money = balance('KRW')['avail']
    money = total_money / 3
    logger.info(f"??????: {total_money} / ?????? ?????? ??????: {money}")

    buy_eth = False
    buy_btc = False
    buy_xrp = False

    eth = candles('ETH-KRW', begin_time(), end_time())
    btc = candles('BTC-KRW', begin_time(), end_time())
    xrp = candles('XRP-KRW', begin_time(), end_time())
    explain_candles('ETH', eth)
    explain_candles('BTC', btc)
    explain_candles('XRP', xrp)

    target_eth_price = eth['breakout']
    target_btc_price = btc['breakout']
    target_xrp_price = xrp['breakout']
    logger.info(f"?????? ????????? ETH: {target_eth_price} / BTC: {target_btc_price} / XRP: {target_xrp_price}")
    logger.info(f"?????? ???????????? ETH: {eth['volatility']} / BTC: {btc['volatility']} / XRP: {xrp['volatility']}")

    now = datetime.datetime.now()
    target_volatility = 0.02
    if eth['volatility'] > target_volatility:
        eth_ratio = target_volatility / eth['volatility']
    else:
        eth_ratio = 1
    if btc['volatility'] > target_volatility:
        btc_ratio = target_volatility / btc['volatility']
    else:
        btc_ratio = 1
    if xrp['volatility'] > target_volatility:
        xrp_ratio = target_volatility / xrp['volatility']
    else:
        xrp_ratio = 1
    sum_eth = eth_ratio * money
    sum_btc = btc_ratio * money
    sum_xrp = xrp_ratio * money
    logger.info(f"?????? ?????? ????????? ETH: {sum_eth} / BTC: {sum_btc} / XRP: {sum_xrp}")

    logger.info("?????? ?????? ????????? ???????????????.")
    while True:
        now = datetime.datetime.now()
        logger.info(now)

        if now.hour == 23 and now.minute == 57:
            logger.info("?????? ?????? ????????? ???????????? ????????? ???????????????.")
            break

        if buy_eth == True and buy_btc == True and buy_xrp == True:
            logger.info("??? ?????? ???????????? ????????????.")
            break

        if buy_eth == False:
            try:
                eth_price = price_lowest_ask('ETH-KRW')['price']
                gap = target_eth_price - eth_price
                ratio = eth_price / target_eth_price
                logger.info(f"ETH ??????: {eth_price} ??????: {target_eth_price} ??????: {gap}/{ratio}")
                if (eth_price >= target_eth_price):
                    if (eth_price / target_eth_price >= 1.02):
                        logger.info("????????? ?????? ?????? ???????????? ????????????.")
                        buy_eth = True
                    else:
                        logger.info("ETH ???????????????.")
                        logger.info(buy_market('ETH-KRW', sum_eth))
                        buy_eth = True
            except Exception as ex:
                logger.error('ETH ????????? ??????????????????.')
                logger.exception(ex)
                time.sleep(30)

        if buy_btc == False:
            try:
                btc_price = price_lowest_ask('BTC-KRW')['price']
                gap = target_btc_price - btc_price
                ratio = btc_price / target_btc_price
                logger.info(f"BTC ??????: {btc_price} ??????: {target_btc_price} ??????: {gap}/{ratio}")
                if (btc_price >= target_btc_price):
                    if (btc_price / target_btc_price >= 1.02):
                        logger.info("????????? ?????? ?????? ???????????? ????????????.")
                        buy_btc = True
                    else:
                        logger.info("BTC ???????????????.")
                        logger.info(buy_market('BTC-KRW', sum_btc))
                        buy_btc = True
            except Exception as ex:
                logger.error('BTC ????????? ??????????????????.')
                logger.exception(ex)
                time.sleep(30)

        if buy_xrp == False:
            try:
                xrp_price = price_lowest_ask('XRP-KRW')['price']
                gap = target_xrp_price - xrp_price
                ratio = xrp_price / target_xrp_price
                logger.info(f"XRP ??????: {xrp_price} ??????: {target_xrp_price} ??????: {gap}/{ratio}")
                if (xrp_price >= target_xrp_price):
                    if (xrp_price / target_xrp_price >= 1.02):
                        logger.info("????????? ?????? ?????? ???????????? ????????????.")
                        buy_xrp = True
                    else:
                        logger.info("XRP ???????????????.")
                        logger.info(buy_market('XRP-KRW', sum_xrp))
                        buy_xrp = True
            except Exception as ex:
                logger.error('XRP ????????? ??????????????????.')
                logger.exception(ex)
                time.sleep(30)

        time.sleep(3)

    logger.info("????????? ???????????????.")

    while True:
        now = datetime.datetime.now()

        eth_price = price_lowest_ask('ETH-KRW')['price']
        logger.info(f"ETH ??????: {eth_price} / {target_eth_price}")

        btc_price = price_lowest_ask('BTC-KRW')['price']
        logger.info(f"BTC ??????: {btc_price} / {target_btc_price}")

        xrp_price = price_lowest_ask('XRP-KRW')['price']
        logger.info(f"XRP ??????: {xrp_price} / {target_xrp_price}")

        if now.hour == 23 and now.minute == 59:
            logger.info("????????? ???????????????.")
            sell_multiple_market(['ETH', 'BTC', 'XRP'])
            logger.info("?????? ???????????? ???????????????.")

            time.sleep(61)
            setup_file_log_hanlder()
            break
        else:
            logger.info(f"23??? 59?????? ???????????????. ")
            logger.info(now)

        time.sleep(30)

def run():
    while True:
        logger.info("????????? ?????? ????????? ???????????????.")
        breakout()
        time.sleep(30)

if __name__ == "__main__":
    run()
