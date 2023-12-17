import 'package:flutter/material.dart';
import 'package:sqflite/sqlite_api.dart';

import 'todo.dart';

class ClearListApp extends StatefulWidget {
  Future<Database> database;

  ClearListApp({required this.database, super.key});

  @override
  State<StatefulWidget> createState() => _ClearListApp();
}

class _ClearListApp extends State<ClearListApp> {
  late Future<List<Todo>> _clearList;

  @override
  void initState() {
    super.initState();
    _clearList = _getClearList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('완료할 일'),
      ),
      body: Center(
        child: FutureBuilder(
          builder: (context, snapshot) {
            switch (snapshot.connectionState) {
              case ConnectionState.none:
                return const CircularProgressIndicator();
              case ConnectionState.waiting:
                return const CircularProgressIndicator();
              case ConnectionState.active:
                return const CircularProgressIndicator();
              case ConnectionState.done:
                if (snapshot.hasData) {
                  return ListView.builder(
                    itemBuilder: (context, index) {
                      final todo = (snapshot.data as List<Todo>)[index];
                      return ListTile(
                        title: Text(
                          todo.title,
                          style: const TextStyle(fontSize: 20),
                        ),
                        subtitle: Column(
                          children: [
                            Text(todo.content),
                            Container(
                              height: 1,
                              color: Colors.blue,
                            ),
                          ],
                        ),
                      );
                    },
                    itemCount: (snapshot.data as List<Todo>).length,
                  );
                }
                return const Text('No data');
            }
          },
          future: _clearList,
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final result = await showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: const Text('완료한 일 삭제'),
                content: const Text('완료한 일을 모두 삭제할까요?'),
                actions: [
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).pop(true);
                    },
                    child: const Text('예'),
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).pop(false);
                    },
                    child: const Text('아니요'),
                  ),
                ],
              );
            },
          );
          if (result) {
            _removeAllTodos();
          }
        },
        child: const Icon(Icons.remove),
      ),
    );
  }

  Future<List<Todo>> _getClearList() async {
    final Database database = await widget.database;
    List<Map<String, dynamic>> maps = await database.rawQuery(
        'select title, content, id, active from todos where active=1');

    return List.generate(maps.length, (i) {
      final map = maps[i];
      var todo = Todo(
          title: map['title'].toString(),
          content: map['content'].toString(),
          id: map['id'],
          active: map['active']);
      return todo;
    });
  }

  void _removeAllTodos() async {
    final database = await widget.database;
    database.rawDelete('delete from todos where active=1');
    setState(() {
      _clearList = _getClearList();
    });
  }
}
