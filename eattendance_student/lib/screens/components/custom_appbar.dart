import 'package:flutter/material.dart';

class CustomAppBar {
  PreferredSizeWidget customAppBar({title = 'eAttendance'}) {
    return AppBar(
      title: Text(
        title,
        style: const TextStyle(
          color: Colors.black,
          fontSize: 20,
        ),
      ),
      centerTitle: true,
      backgroundColor: Colors.transparent,
      forceMaterialTransparency: true,
    );
  }
}
