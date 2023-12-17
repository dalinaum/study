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
    );
  }

  Future<List<Todo>> _getClearList() async {
    final Database database = await widget.database;
    List<Map<String, dynamic>> maps = await database
        .rawQuery('select title, content, id, active from todos where active=1');

    print("maps: ${maps}");
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
}
