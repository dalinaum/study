import 'package:flutter/material.dart';
import 'package:flutter_application/models/user.dart';
import 'package:flutter_application/screens/home_screen.dart';
import 'package:flutter_application/screens/signup.dart';
import 'package:flutter_application/services/auth.dart';
import 'package:flutter_application/widgets/failure_dialog.dart';
import 'package:flutter_application/widgets/success_dialog.dart';
import 'package:grpc/grpc.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<StatefulWidget> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Login'),
      ),
      body: isLoading
          ? const Center(
              child: CircularProgressIndicator(),
            )
          : Form(
              key: _formKey,
              child: ListView(
                padding: const EdgeInsets.all(16.0),
                children: <Widget>[
                  TextFormField(
                    controller: _usernameController,
                    decoration: const InputDecoration(
                      labelText: 'Username',
                      border: OutlineInputBorder(),
                    ),
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please enter your name';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  TextFormField(
                    controller: _passwordController,
                    decoration: const InputDecoration(
                      labelText: 'Password',
                      border: OutlineInputBorder(),
                    ),
                    obscureText: true,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Please enter your password';
                      }
                      return null;
                    },
                  ),
                  const SizedBox(height: 16.0),
                  ElevatedButton(
                    onPressed: () async {
                      if (_formKey.currentState!.validate()) {
                        setState(() {
                          isLoading = true;
                        });
                        try {
                          UserModel? user = await AuthService.login(
                              _usernameController.text,
                              _passwordController.text);
                          if (user != null && mounted) {
                            showSuccessDialog(context,
                                "Welcome ${user.fullname.toUpperCase()}", () {
                              Navigator.popUntil(context, (route) => false);
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (builder) => const HomeScreen(),
                                ),
                              );
                            });
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
                    child: const Text('Login'),
                  ),
                  const SizedBox(height: 16.0),
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => const SignupScreen(),
                      ));
                    },
                    child: const Text('Don\'t have an account? Sign up'),
                  ),
                ],
              ),
            ),
    );
  }

  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    super.dispose();
  }
}
