import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

class LargeFileMain extends StatefulWidget {
  const LargeFileMain({super.key});

  @override
  State<StatefulWidget> createState() => _LargeFileMain();
}

class _LargeFileMain extends State<LargeFileMain> {
  final imgUrl =
      'https://images.pexels.com/photos/240040/pexels-photo-240040.jpeg'
      '?auto=compress';
  var downloading = false;
  var progressString = '';
  var file = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Large File Example'),
      ),
      body: Center(
        child: downloading
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
                      Text('Downloading file: $progressString',
                          style: const TextStyle(
                            color: Colors.white,
                          ))
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
                future: downloadWidget(file),
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
      await dio.download(imgUrl, '${dir.path}/myimage.jpeg',
          onReceiveProgress: (rec, total) {
        print('Rec: $rec, Total: $total');
        file = '${dir.path}/myimage.jpeg';
        setState(() {
          downloading = true;
          progressString = '${((rec / total) * 100).toStringAsFixed(0)}%';
        });
      });
    } catch (e) {
      print(e);
    }
    setState(() {
      downloading = false;
      progressString = 'Completed';
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
