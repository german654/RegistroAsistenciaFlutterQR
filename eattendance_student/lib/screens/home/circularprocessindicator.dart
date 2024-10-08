import 'package:flutter/material.dart';

class ProcessIndicator extends StatelessWidget {
  const ProcessIndicator({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
        child: Column(
          children: [CircularProgressIndicator(), Text('Please wait...')],
        ),
      ),
    );
  }
}
