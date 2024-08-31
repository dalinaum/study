import 'package:flutter/material.dart';
import 'dart:math';

class SecondPage extends StatefulWidget {
  const SecondPage({super.key});

  @override
  State<StatefulWidget> createState() => _SecondPage();
}

class _SecondPage extends State<SecondPage>
    with SingleTickerProviderStateMixin {
  late final AnimationController _animationController;
  late final Animation _rotateAnimation;
  late final Animation _scaleAnimation;
  late final Animation _transAnimation;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 5),
    );
    _rotateAnimation =
        Tween<double>(begin: 0, end: pi * 10).animate(_animationController);
    _scaleAnimation =
        Tween<double>(begin: 1, end: 0).animate(_animationController);
    _transAnimation =
        Tween<Offset>(begin: const Offset(0, 0), end: const Offset(200, 200))
            .animate(_animationController);
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('Animation Example2')),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              AnimatedBuilder(
                animation: _rotateAnimation,
                builder: (context, child) {
                  return Transform.translate(
                    offset: _transAnimation.value,
                    child: Transform.rotate(
                      angle: _rotateAnimation.value,
                      child: Transform.scale(
                        scale: _scaleAnimation.value,
                        child: child,
                      ),
                    ),
                  );
                },
                child: const Hero(
                  tag: 'detail',
                  child: Icon(
                    Icons.cake,
                    size: 150,
                  ),
                ),
              ),
              ElevatedButton(
                onPressed: () {
                  _animationController.forward();
                },
                child: const Text('로테이션 시작하기'),
              )
            ],
          ),
        ));
  }
}
