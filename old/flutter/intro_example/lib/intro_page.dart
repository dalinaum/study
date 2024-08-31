import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

import 'main.dart';

class IntroPage extends StatefulWidget {
  const IntroPage({super.key});

  @override
  State<StatefulWidget> createState() => _IntroPage();
}

class _IntroPage extends State<IntroPage> {
  Widget logo = const Icon(
    Icons.info,
    size: 50,
  );

  @override
  void initState() {
    super.initState();
    _initData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            logo,
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).pushReplacement(MaterialPageRoute(
                    builder: (context) => const MyHomePage()));
              },
              child: const Text('다음으로 가기'),
            )
          ],
        ),
      ),
    );
  }

  void _initData() async {
    final dir = await getApplicationDocumentsDirectory();
    var myImageFile = File('${dir.path}/myimage.jpg');
    final fileExist = await myImageFile.exists();
    if (fileExist) {
      setState(() {
        logo = Image.file(
          myImageFile,
          height: 200,
          width: 200,
          fit: BoxFit.contain,
        );
      });
    }
  }
}
