import 'package:flutter/material.dart';
import 'package:flutter_application/models/user.dart';
import 'package:flutter_application/services/auth.dart';
import 'package:flutter_application/widgets/failure_dialog.dart';
import 'package:flutter_application/widgets/success_dialog.dart';
import 'package:grpc/grpc.dart';

class SignupScreen extends StatefulWidget {
  const SignupScreen({super.key});

  @override
  State<StatefulWidget> createState() => _SignupScreenState();
}

class _SignupScreenState extends State<SignupScreen> {
  final _fullNameController = TextEditingController();
  final _signupUsernameController = TextEditingController();
  final _signupPasswordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  final _signupFormKey = GlobalKey<FormState>();
  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Signup'),
      ),
      body: isLoading
          ? const Center(
              child: CircularProgressIndicator(),
            )
          : Form(
              key: _signupFormKey,
              child: ListView(
                padding: const EdgeInsets.all(16.0),
                children: <Widget>[
                  TextFormField(
                    controller: _fullNameController,
                    decoration: const InputDecoration(
                      labelText: 'Full name',
                      border: OutlineInputBorder(),
                    ),
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please enter your full name';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  TextFormField(
                    controller: _signupUsernameController,
                    decoration: const InputDecoration(
                      labelText: 'Username',
                      border: OutlineInputBorder(),
                    ),
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please enter a username';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  TextFormField(
                    controller: _signupPasswordController,
                    decoration: const InputDecoration(
                      labelText: 'Password',
                      border: OutlineInputBorder(),
                    ),
                    obscureText: true,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please enter a password';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  TextFormField(
                    controller: _confirmPasswordController,
                    decoration: const InputDecoration(
                      labelText: 'Confirm Password',
                      border: OutlineInputBorder(),
                    ),
                    obscureText: true,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please confirm your password';
                      } else if (value != _signupPasswordController.text) {
                        return 'Passwords do not match';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  ElevatedButton(
                    onPressed: () async {
                      if (_signupFormKey.currentState!.validate()) {
                        try {
                          isLoading = true;
                          setState(() {});
                          UserModel? user = await AuthService.signup(
                              _signupUsernameController.text,
                              _signupPasswordController.text,
                              _fullNameController.text);
                          if (user != null) {
                            if (mounted) {
                              showSuccessDialog(
                                context,
                                "Account created for ${user.username}. Login again",
                                () {
                                  Navigator.pop(context);
                                },
                              );
                            }
                          }
                        } on GrpcError catch (error) {
                          if (mounted) {
                            showErrorDialog(
                                context, error.message.toString(), () {});
                          }
                        } finally {
                          if (mounted) {
                            setState(() {
                              isLoading = false;
                            });
                          }
                        }
                      }
                    },
                    child: const Text('Signup'),
                  )
                ],
              ),
            ),
    );
  }

  @override
  void dispose() {
    _fullNameController.dispose();
    _signupUsernameController.dispose();
    _signupPasswordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }
}
