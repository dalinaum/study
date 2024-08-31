import 'package:flutter/material.dart';
import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

class FileApp extends StatefulWidget {
  const FileApp({super.key});

  @override
  State<StatefulWidget> createState() => _FileApp();
}

class _FileApp extends State<FileApp> {
  final List<String> _itemList = List.empty(growable: true);
  final _controller = TextEditingController();

  @override
  void initState() {
    super.initState();
    _initData(DefaultAssetBundle.of(context));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('File Example'),
      ),
      body: Center(
        child: Column(
          children: [
            TextField(
              controller: _controller,
              keyboardType: TextInputType.text,
            ),
            Expanded(
              child: ListView.builder(
                itemBuilder: (context, index) {
                  return Card(
                    child: Center(
                      child: Text(
                        _itemList[index],
                        style: const TextStyle(fontSize: 30),
                      ),
                    ),
                  );
                },
                itemCount: _itemList.length,
              ),
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          final fruit = _controller.value.text;
          _writeFruit(fruit);
          setState(() {
            _itemList.add(fruit);
          });
        },
        child: const Icon(Icons.add),
      ),
    );
  }

  void _initData(AssetBundle assetBundle) async {
    var result = await _readListFile(assetBundle);
    setState(() {
      _itemList.addAll(result);
    });
  }

  Future<List<String>> _readListFile(AssetBundle bundle) async {
    List<String> itemList = List.empty(growable: true);
    const key = 'first';
    final prefs = await SharedPreferences.getInstance();
    var firstCheck = prefs.getBool(key) ?? false;
    final dir = await getApplicationDocumentsDirectory();
    var fileExist = await File('${dir.path}/fruit.txt').exists();

    if (firstCheck == false || fileExist == false) {
      prefs.setBool(key, true);
      var fileContent = await bundle.loadString('repo/fruit.txt');
      File('${dir.path}/fruit.txt').writeAsStringSync(fileContent);
      var lines = fileContent.split('\n');
      for (final line in lines) {
        print(line);
        itemList.add(line);
      }
      return itemList;
    } else {
      final fileContent = await File('${dir.path}/fruit.txt').readAsString();
      final lines = fileContent.split('\n');
      for (final line in lines) {
        print(line);
        itemList.add(line);
      }
      return itemList;
    }
  }

  void _writeFruit(String fruit) async {
    final dir = await getApplicationDocumentsDirectory();
    final fruitFile = File('${dir.path}/fruit.txt');
    var fileContent = await fruitFile.readAsString();
    fileContent += '\n$fruit';
    fruitFile.writeAsStringSync(fileContent);
  }
}
