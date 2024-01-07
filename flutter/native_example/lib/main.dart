import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'dart:io';

import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    if (Platform.isIOS) {
      return const CupertinoApp(
        title: 'Flutter Demo',
        home: CupertinoNativeApp(),
      );
    } else {
      return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        home: const NativeApp(),
      );
    }
  }
}

class CupertinoNativeApp extends StatefulWidget {
  const CupertinoNativeApp({super.key});

  @override
  State<StatefulWidget> createState() {
    return _NativeApp();
  }
}

class NativeApp extends StatefulWidget {
  const NativeApp({super.key});

  @override
  State<StatefulWidget> createState() => _NativeApp();
}

class _NativeApp extends State<NativeApp> {
  String _deviceInfo = 'Unknown info';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Native 통신 예제'),
      ),
      body: Center(
        child: Text(
          _deviceInfo,
          style: const TextStyle(fontSize: 30),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _getDeviceInfo();
        },
        child: const Icon(Icons.get_app),
      ),
    );
  }

  Future<void> _getDeviceInfo() async {
    String deviceInfo;
    const platform = MethodChannel('com.flutter.dev/info');
    try {
      final String result = await platform.invokeMethod('getDeviceInfo');
      deviceInfo = 'Device info: $result';
    } on PlatformException catch (e) {
      deviceInfo = 'Failed to get Device info: ${e.message}';
    }
    setState(() {
      _deviceInfo = deviceInfo;
    });
  }
}
