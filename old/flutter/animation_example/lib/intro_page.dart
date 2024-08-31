import 'package:flutter/material.dart';
import 'package:animation_example/saturn_loading.dart';
import 'dart:async';
import 'main.dart';

class IntroPage extends StatefulWidget {
  const IntroPage({super.key});

  @override
  State<StatefulWidget> createState() => _IntroPage();
}

class _IntroPage extends State<IntroPage> {
  @override
  void initState() {
    super.initState();
    loadData();
  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('애니메이션 앱'),
            SizedBox(
              height: 20,
            ),
            SaturnLoading(),
          ],
        ),
      ),
    );
  }

  Future<Timer> loadData() async {
    return Timer(
      const Duration(seconds: 5),
      onDoneLoading,
    );
  }

  onDoneLoading() async {
    Navigator.of(context).pushReplacement(MaterialPageRoute(
      builder: (context) => const AnimationApp(),
    ));
  }
}
