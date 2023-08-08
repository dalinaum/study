import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Material Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MaterialFlutterApp(),
    );
  }
}

class MaterialFlutterApp extends StatefulWidget {
  const MaterialFlutterApp({super.key});

  @override
  State<MaterialFlutterApp> createState() => _MaterialFlutterAppState();
}

class _MaterialFlutterAppState extends State<MaterialFlutterApp> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Materail Design App'),
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () {},
      ),
      body: Container(
        child: const Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Icon(Icons.android),
              Text('Android'),
            ],
          ),
        ),
      ),
    );
  }
}
