import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'add_todo.dart';
import 'clear_list.dart';
import 'todo.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final database = _initDatabase();

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => DatabaseApp(database: database),
        '/add': (context) => AddTodoApp(database: database),
        '/clear': (context) => ClearListApp(database: database),
      },
    );
  }

  Future<Database> _initDatabase() async => openDatabase(
        join(await getDatabasesPath(), 'todo_database.db'),
        onCreate: (db, version) => db
            .execute("CREATE TABLE todos(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                "title TEXT, content TEXT, active INTEGER)"),
        version: 1,
      );
}

class DatabaseApp extends StatefulWidget {
  final Future<Database> database;

  const DatabaseApp({required this.database, super.key});

  @override
  State<DatabaseApp> createState() => _DatabaseApp();
}

class _DatabaseApp extends State<DatabaseApp> {
  late Future<List<Todo>> _todoList;

  @override
  void initState() {
    super.initState();
    _todoList = _getTodos();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Database Example'),
        actions: [
          TextButton(
            onPressed: () async {
              await Navigator.of(context).pushNamed('/clear');
              setState(() {
                _todoList = _getTodos();
              });
            },
            child: const Text('완료한 일'),
          )
        ],
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
                            Text('체크 : ${todo.active == 1 ? 'true' : 'false'}'),
                            Container(
                              height: 1,
                              color: Colors.blue,
                            )
                          ],
                        ),
                        onTap: () async {
                          final controller =
                              TextEditingController(text: todo.content);

                          final Todo result = await showDialog(
                            context: context,
                            builder: (context) {
                              return AlertDialog(
                                title: Text('${todo.id} : ${todo.title}'),
                                content: TextField(
                                  controller: controller,
                                  keyboardType: TextInputType.text,
                                ),
                                actions: [
                                  TextButton(
                                    onPressed: () {
                                      setState(() {
                                        todo.active = todo.active == 1 ? 0 : 1;
                                        todo.content = controller.text;
                                      });
                                      Navigator.of(context).pop(todo);
                                    },
                                    child: const Text('예'),
                                  )
                                ],
                              );
                            },
                          );
                          _updateTodo(result);
                        },
                        onLongPress: () async {
                          final Todo? result = await showDialog(
                            context: context,
                            builder: (context) {
                              return AlertDialog(
                                title: Text('${todo.id} : ${todo.title}'),
                                content: Text('${todo.content}를 삭제하겠습니까?'),
                                actions: [
                                  TextButton(
                                    onPressed: () {
                                      Navigator.of(context).pop(todo);
                                    },
                                    child: const Text('예'),
                                  ),
                                  TextButton(
                                      onPressed: () {
                                        Navigator.of(context).pop();
                                      },
                                      child: const Text("아니요")),
                                ],
                              );
                            },
                          );
                          if (result != null) {
                            _deleteTodo(result);
                          }
                        },
                      );
                    },
                    itemCount: (snapshot.data as List<Todo>).length,
                  );
                } else {
                  return const Text('No data');
                }
              default:
                return const CircularProgressIndicator();
            }
          },
          future: _todoList,
        ),
      ),
      floatingActionButton: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            onPressed: () async {
              final todo = await Navigator.of(context).pushNamed('/add');
              if (todo != null) {
                _insertTodo(todo as Todo);
              }
            },
            heroTag: null,
            child: const Icon(Icons.add),
          ),
          const SizedBox(
            height: 10,
          ),
          FloatingActionButton(
            onPressed: () async {
              _allUpdate();
            },
            heroTag: null,
            child: const Icon(Icons.update),
          ),
        ],
      ),
    );
  }

  void _insertTodo(Todo todo) async {
    final database = await widget.database;
    await database.insert(
      'todos',
      todo.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
    setState(() {
      _todoList = _getTodos();
    });
  }

  Future<List<Todo>> _getTodos() async {
    final database = await widget.database;
    final List<Map<String, dynamic>> maps = await database.query('todos');

    return List.generate(maps.length, (index) {
      final row = maps[index];
      return Todo(
          title: row['title'].toString(),
          content: row['content'].toString(),
          active: row['active'] ?? 0,
          id: row['id']);
    });
  }

  void _updateTodo(Todo todo) async {
    final database = await widget.database;
    await database.update(
      'todos',
      todo.toMap(),
      where: 'id = ?',
      whereArgs: [todo.id],
    );
    setState(() {
      _todoList = _getTodos();
    });
  }

  void _deleteTodo(Todo todo) async {
    final database = await widget.database;
    await database.delete('todos', where: 'id = ?', whereArgs: [todo.id]);
    setState(() {
      _todoList = _getTodos();
    });
  }

  void _allUpdate() async {
    final database = await widget.database;
    await database.rawUpdate('update todos set active = 1 where active = 0');
    setState(() {
      _todoList = _getTodos();
    });
  }
}
