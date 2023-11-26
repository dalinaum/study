import 'package:flutter/material.dart';

class SecondDetail extends StatelessWidget {
  const SecondDetail({super.key});

  @override
  Widget build(BuildContext context) {
    final textEditingController = TextEditingController();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Second Page'),
      ),
      body: Center(
        child: Column(
          children: [
            TextField(
              controller: textEditingController,
              keyboardType: TextInputType.text,
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context)
                    .pop(textEditingController.text.toString());
              },
              child: const Text('저장하기'),
            )
          ],
        ),
      ),
    );
  }
}
