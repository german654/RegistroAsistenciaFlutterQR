import 'package:flutter/material.dart';

class Buttondisable extends StatefulWidget {
  const Buttondisable({super.key});

  @override
  State<Buttondisable> createState() => _ButtondisableState();
}

class _ButtondisableState extends State<Buttondisable> {
  bool isButtonDisable= false;
void disableButton(){
    setState(() {
      isButtonDisable = true;
    });
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(child:
      Column(
        // mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          ElevatedButton(onPressed: isButtonDisable ? null: () {
            disableButton();
          }, child: const Text("fill the attendace"))
        ],
      )),
    );
  }
}