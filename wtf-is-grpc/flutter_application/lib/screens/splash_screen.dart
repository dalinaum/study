import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_application/screens/home_screen.dart';
import 'package:flutter_application/screens/login.dart';
import 'package:flutter_application/services/auth.dart';
import 'package:flutter_application/services/grpc_services.dart';
import 'package:flutter_application/services/notification.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<StatefulWidget> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    SchedulerBinding.instance.addPostFrameCallback((timeStamp) {
      updateAddress();
    });
    // initAsync();
  }

  void updateAddress() {
    showDialog(
        context: context,
        builder: (builder) {
          return AlertDialog(
            title: const Text("Add serer address"),
            content: TextFormField(
              initialValue: GrpcService.host,
              decoration: const InputDecoration(
                labelText: "Enter Server Address",
              ),
              onChanged: (value) {
                GrpcService.host = value;
              },
            ),
            actions: [
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: const Text("Next"),
              ),
              ElevatedButton(
                onPressed: () {
                  GrpcService.updateChannel();
                  Navigator.of(context).pop();
                },
                child: const Text("Update"),
              )
            ],
          );
        }).then((value) {
      initAsync();
    });
  }

  Future<void> initAsync() async {
    try {
      await NotificationServices.initializeService();
      final isAuth = await AuthService.isAuthAvailable();
      if (isAuth) {
        final user = await AuthService.getUser();
        if (user != null) {
          navigateToHome();
        } else {
          navigateToLogin();
        }
      } else {
        navigateToLogin();
      }
    } catch (e) {
      log(e.toString());
      navigateToLogin();
    }
  }

  void navigateToHome() {
    Navigator.pop(context);
    Navigator.push(
      context,
      MaterialPageRoute(builder: (builder) => const HomeScreen()),
    );
  }

  void navigateToLogin() {
    Navigator.pop(context);
    Navigator.push(
      context,
      MaterialPageRoute(builder: (builder) => const LoginScreen()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(child: CircularProgressIndicator()),
    );
  }
}
