import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const HttpApp(),
    );
  }
}

class HttpApp extends StatefulWidget {
  const HttpApp({super.key});

  @override
  State<HttpApp> createState() => _HttpApp();
}

class _HttpApp extends State<HttpApp> {
  List data = List.empty(growable: true);
  final TextEditingController _editingController = TextEditingController();
  final ScrollController _scrollController = ScrollController();
  int page = 1;

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(() {
      if (_scrollController.offset >=
              _scrollController.position.maxScrollExtent &&
          !_scrollController.position.outOfRange) {
        print('bottom');
        page++;
        getJSONData();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: TextField(
          controller: _editingController,
          style: const TextStyle(color: Colors.white),
          keyboardType: TextInputType.text,
          decoration: const InputDecoration(hintText: '검색어를 입력하세요'),
        ),
      ),
      body: Center(
          child: data.isEmpty
              ? const Text(
                  '데이터가 없습니다.',
                  style: TextStyle(fontSize: 20),
                  textAlign: TextAlign.center,
                )
              : ListView.builder(
                  itemBuilder: (context, index) {
                    return Card(
                      child: Row(
                        children: [
                          Image.network(
                            data[index]['thumbnail'],
                            height: 100,
                            width: 100,
                            fit: BoxFit.contain,
                          ),
                          Column(
                            children: [
                              SizedBox(
                                width: MediaQuery.of(context).size.width - 150,
                                child: Text(
                                  data[index]['title'].toString(),
                                  textAlign: TextAlign.center,
                                ),
                              ),
                              Text('저자 : ${data[index]['authors'].toString()}'),
                              Text(
                                  '가격 : ${data[index]['sale_price'].toString()}'),
                              Text('판매중 : ${data[index]['status'].toString()}'),
                            ],
                          ),
                        ],
                      ),
                    );
                  },
                  itemCount: data.length,
                  controller: _scrollController,
                )),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          page = 1;
          data.clear();
          getJSONData();
        },
        tooltip: 'Increment',
        child: const Icon(Icons.download),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  Future<String> getJSONData() async {
    final url =
        'https://dapi.kakao.com/v3/search/book?target=title&page=$page&query=${_editingController.value.text}';
    final response = await http.get(Uri.parse(url),
        headers: {"Authorization": "KakaoAK e1c8431bcfdcdcae08a7a2f1ea2194fa"});
    setState(() {
      final dataConvertedToJSON = json.decode(response.body);
      final List result = dataConvertedToJSON['documents'];
      data.addAll(result);
    });
    return response.body;
  }
}
