import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';

import 'todo.dart';

class AddTodoApp extends StatefulWidget {
  final Future<Database> database;

  const AddTodoApp({required this.database, super.key});

  @override
  State<StatefulWidget> createState() => _AddTodoApp();
}

class _AddTodoApp extends State<AddTodoApp> {
  final _titleController = TextEditingController();
  final _contentController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Todo 추가'),
      ),
      body: Center(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: _titleController,
                decoration: const InputDecoration(labelText: '제목'),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: _contentController,
                decoration: const InputDecoration(labelText: '할일'),
              ),
            ),
            ElevatedButton(
              onPressed: () {
                final todo = Todo(
                  title: _titleController.text,
                  content: _contentController.text,
                  active: 0,
                );
                Navigator.of(context).pop(todo);
              },
              child: const Text('저장하기'),
            )
          ],
        ),
      ),
    );
  }
}
