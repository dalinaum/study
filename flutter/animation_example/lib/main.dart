import 'package:flutter/material.dart';

import 'person.dart';

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
      home: const AnimationApp(),
    );
  }
}

class AnimationApp extends StatefulWidget {
  const AnimationApp({super.key});

  @override
  State<AnimationApp> createState() => _AnimationApp();
}

class _AnimationApp extends State<AnimationApp> {
  final List<Person> _people = List.empty(growable: true);
  Color _weightColor = Colors.blue;
  var _current = 0;
  var _opacity = 1.0;

  @override
  void initState() {
    super.initState();
    _people.add(Person('스미스', 180, 92));
    _people.add(Person('메리', 162, 55));
    _people.add(Person('존', 177, 75));
    _people.add(Person('바트', 130, 40));
    _people.add(Person('콘', 194, 140));
    _people.add(Person('디디', 100, 80));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Animation Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            AnimatedOpacity(
              opacity: _opacity,
              duration: const Duration(seconds: 1),
              child: SizedBox(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    SizedBox(
                      width: 100,
                      child: Text('이름: ${_people[_current].name}'),
                    ),
                    AnimatedContainer(
                      duration: const Duration(seconds: 2),
                      curve: Curves.bounceIn,
                      color: Colors.amber,
                      width: 50,
                      height: _people[_current].height,
                      child: Text(
                        '키 ${_people[_current].height}',
                        textAlign: TextAlign.center,
                      ),
                    ),
                    AnimatedContainer(
                      duration: const Duration(seconds: 2),
                      curve: Curves.easeInCubic,
                      color: _weightColor,
                      width: 50,
                      height: _people[_current].weight,
                      child: Text(
                        '몸무게 ${_people[_current].weight}',
                        textAlign: TextAlign.center,
                      ),
                    ),
                    AnimatedContainer(
                      duration: const Duration(seconds: 2),
                      curve: Curves.linear,
                      color: Colors.pinkAccent,
                      width: 50,
                      height: _people[_current].bmi,
                      child: Text(
                        'bmi ${_people[_current].bmi.toString().substring(0, 2)}',
                        textAlign: TextAlign.center,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            ElevatedButton(
              onPressed: () {
                setState(() {
                  if (_current < _people.length - 1) {
                    _current++;
                  }
                  _changeWeightColor(_people[_current].weight);
                });
              },
              child: const Text('다음'),
            ),
            ElevatedButton(
              onPressed: () {
                setState(() {
                  if (_current > 0) {
                    _current--;
                  }
                  _changeWeightColor(_people[_current].weight);
                });
              },
              child: const Text('이전'),
            ),
            ElevatedButton(
              onPressed: () {
                setState(() {
                  _opacity == 1 ? _opacity = 0 : _opacity = 1;
                });
              },
              child: const Text('사라지기')
            ),
          ],
        ),
      ),
    );
  }

  void _changeWeightColor(double weight) {
    if (weight < 40) {
      _weightColor = Colors.blueAccent;
    } else if (weight < 60) {
      _weightColor = Colors.indigo;
    } else if (weight < 80) {
      _weightColor = Colors.orange;
    } else {
      _weightColor = Colors.red;
    }
  }
}
