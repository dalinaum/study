defmodule MyList do
  def span(a, a), do: [a]
  def span(a, b) when a < b, do: [a | span(a + 1, b)]

  def prime(n) do
    for x <- span(2, n), x < 3 or Enum.all?(span(2, x - 1), &(rem(x, &1) != 0)), do: x
  end

  def total_amount() do
    tax_rates = [NC: 0.075, TX: 0.08]

    orders = [
      [id: 123, ship_to: :NC, net_amount: 100.00],
      [id: 124, ship_to: :OK, net_amount: 35.50],
      [id: 125, ship_to: :TX, net_amount: 24.00],
      [id: 126, ship_to: :TX, net_amount: 44.80],
      [id: 127, ship_to: :NC, net_amount: 25.80],
      [id: 128, ship_to: :MA, net_amount: 10.00],
      [id: 129, ship_to: :CA, net_amount: 102.00],
      [id: 130, ship_to: :NC, net_amount: 50.00]
    ]

    for order <- orders do
      if order[:ship_to] in Keyword.keys(tax_rates) do
        net_amount = order[:net_amount]
        tax_rate = tax_rates[order[:ship_to]]
        total_amount = net_amount + net_amount * tax_rate
        Keyword.put(order, :total_amount, total_amount)
      else
        order
      end
    end
  end
end
