import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'add_todo.dart';
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
                      return Card(
                        child: Column(
                          children: [
                            Text(todo.title),
                            Text(todo.content),
                            Text('${todo.active == 1}')
                          ],
                        ),
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
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final todo = await Navigator.of(context).pushNamed('/add');
          if (todo != null) {
            _insertTodo(todo as Todo);
          }
        },
        child: const Icon(Icons.add),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }

  void _insertTodo(Todo todo) async {
    final Database database = await widget.database;
    await database.insert('todos', todo.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
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
}
