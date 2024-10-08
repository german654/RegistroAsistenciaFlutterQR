import 'package:eattendance_faculty/models/token_manager.dart';

import '../../models/auth_request.dart';
import '../../models/faculty_model.dart';
import '../../screens/authentication/login.dart';
import '../../screens/screen_navigator.dart';
import '../../exceptions/auth/auth_exceptions.dart';
import '../../utility/constants.dart';
import '../../utility/utils.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class AuthenticationRepository extends GetxController {
  static AuthenticationRepository get instance => Get.find();
  late Rx<Faculty?> faculty = Rx<Faculty?>(null);
  late SharedPreferences prefs;

  @override
  void onReady() async {
    prefs = await SharedPreferences.getInstance();
    bool isLoggedIn = prefs.getBool("isLoggedIn") ?? false;
    if (isLoggedIn) {
      faculty.value = Faculty.fromJson(prefs.getString("faculty") ??
          Faculty(
            facultyId: 0,
            enrollment: 'enrollment',
            email: 'email',
            username: 'username',
            token: 'token',
          ).toJson());
    }
    _setInitialScreen(faculty);
  }

  _setInitialScreen(Rx<Faculty?> user) {
    user.value == null
        ? Get.offAll(() => const LoginScreen())
        : Get.offAll(() => const ScreenNavigator());
  }

  Future<void> loginUserWithNameEmailAndPassword(
      String email, String password) async {
    try {
      final response = await http.post(Uri.parse("$apiUrl/auth/login"),
          body: AuthRequest(username: email, password: password).toJson(),
          headers: {'Content-Type': 'application/json'});
      if (response.statusCode == 200) {
        faculty = Rx<Faculty?>(Faculty.fromJson(response.body));

        await prefs.setBool("isLoggedIn", true);
        await prefs.setString("faculty", faculty.value!.toJson());
        await TokenManager.saveToken(faculty.value!.token);
      }
      if (response.statusCode == 404) {
        throw SignUpWithEmailAndPasswordFailure.code("404");
      }

      faculty.value != null
          ? Get.offAll(() => const ScreenNavigator())
          : Get.offAll(() => const LoginScreen());
    } catch (_) {
      final ex = SignUpWithEmailAndPasswordFailure();
      showSnackkBar(
        message: ex.message,
        title: 'Try Again',
        icon: const Icon(Icons.error),
      );
    }
  }

  Future<void> resetPassword(email) async {
    Get.offAll(() => const LoginScreen());
    showSnackkBar(
      message: 'Password Reset Mail Send SuccessFully',
      title: 'Check Your Mail',
      icon: const Icon(Icons.done),
    );
  }

  Future<void> logOut() async {
    prefs.clear();
    Get.offAll(() => const LoginScreen());
  }
}
