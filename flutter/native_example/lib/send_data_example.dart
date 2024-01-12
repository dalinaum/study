import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class SendDataExample extends StatefulWidget {
  const SendDataExample({super.key});

  @override
  State<StatefulWidget> createState() => _SendDataExample();
}

class _SendDataExample extends State<SendDataExample> {
  final _controller = TextEditingController();
  String _changedText = 'Nothing';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Send data Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(
              controller: _controller,
              keyboardType: TextInputType.text,
            ),
            const SizedBox(
              height: 20,
            ),
            Text(
              _changedText,
              style: const TextStyle(
                fontSize: 20,
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _sendData(_controller.value.text);
        },
        child: const Text('변환'),
      ),
    );
  }

  Future<void> _sendData(String text) async {
    const platform = MethodChannel('com.flutter.dev/encrpyto');
    final String result = await platform.invokeMethod('getEncrypto', text);
    print(result);
    setState(() {
      _changedText = result;
    });
  }
}
