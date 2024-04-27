import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_application/screens/login.dart';
import 'package:flutter_application/services/auth.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<StatefulWidget> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Hello ${AuthService.user?.fullname}"),
        actions: [
          IconButton(
            onPressed: () async {
              try {
                await AuthService.logout();
                if (mounted) {
                  Navigator.popUntil(context, (route) => false);
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (builder) => const LoginScreen()));
                }
              } catch (e) {
                log(e.toString());
              }
            },
            icon: const Icon(Icons.logout),
          ),
        ],
      ),
    );
  }
}
