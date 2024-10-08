import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

class LargeFileMain extends StatefulWidget {
  const LargeFileMain({super.key});

  @override
  State<StatefulWidget> createState() => _LargeFileMain();
}

class _LargeFileMain extends State<LargeFileMain> {
  var _downloading = false;
  var _progressString = '';
  var _file = '';
  final _editingController = TextEditingController(
      text: 'https://images.pexels.com/photos/240040/pexels-photo-240040.jpeg'
          '?auto=compress');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: TextField(
          controller: _editingController,
          keyboardType: TextInputType.text,
          decoration: const InputDecoration(hintText: 'url 입력하세요'),
        ),
      ),
      body: Center(
        child: _downloading
            ? SizedBox(
                height: 120.0,
                width: 200.0,
                child: Card(
                  color: Colors.black,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const CircularProgressIndicator(),
                      const SizedBox(
                        height: 20.0,
                      ),
                      Text(
                        'Downloading file: $_progressString',
                        style: const TextStyle(color: Colors.white),
                      )
                    ],
                  ),
                ),
              )
            : FutureBuilder(
                builder: (context, snapshot) {
                  switch (snapshot.connectionState) {
                    case ConnectionState.none:
                      print('none');
                      return const Text('데이터 없음');
                    case ConnectionState.waiting:
                      print('waiting');
                      return const CircularProgressIndicator();
                    case ConnectionState.active:
                      print('active');
                      return const CircularProgressIndicator();
                    case ConnectionState.done:
                      print('done');
                      if (snapshot.hasData) {
                        return snapshot.data as Widget;
                      }
                  }
                  print('end process');
                  return const Text('데이터 없음');
                },
                future: downloadWidget(_file),
              ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          downloadFile();
        },
        child: const Icon(Icons.file_download),
      ),
    );
  }

  Future<void> downloadFile() async {
    final dio = Dio();
    try {
      var dir = await getApplicationDocumentsDirectory();
      await dio.download(
        _editingController.value.text,
        '${dir.path}/myimage.jpg',
        onReceiveProgress: (rec, total) {
          print('Rec: $rec, Total: $total');
          _file = '${dir.path}/myimage.jpg';
          setState(() {
            _downloading = true;
            _progressString = '${((rec / total) * 100).toStringAsFixed(0)}%';
          });
        },
      );
    } catch (e) {
      print(e);
    }
    setState(() {
      _downloading = false;
      _progressString = 'Completed';
    });
    print('Download completed');
  }

  Future<Widget> downloadWidget(String filePath) async {
    final file = File(filePath);
    final exist = await file.exists();
    FileImage(file).evict();

    if (exist) {
      return Center(
        child: Column(
          children: [Image.file(File(filePath))],
        ),
      );
    } else {
      return const Text('No Data');
    }
  }
}
