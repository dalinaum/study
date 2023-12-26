import 'package:flutter/material.dart';

class SecondPage extends StatefulWidget {
  const SecondPage({super.key});

  @override
  State<StatefulWidget> createState() => _SecondPage();
}

class _SecondPage extends State<SecondPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('Animation Example2')),
        body: const Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Hero(
                tag: 'detail',
                child: Icon(
                  Icons.cake,
                  size: 150,
                ),
              ),
            ],
          ),
        ));
  }
}
