import 'package:eattendance_student/models/token_manager.dart';

import '../../exceptions/auth_exceptions.dart';
import '../../models/auth_request.dart';
import '../../models/batch_model.dart';
import '../../models/course_model.dart';
import '../../utility/constants.dart';
import '../../models/student_model.dart';
import '../../screens/authentication/login.dart';
import '../../utility/utils.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../screens/screen_navigator.dart';

class AuthenticationRepository extends GetxController {
  static AuthenticationRepository get instance => Get.find();
  // final _auth = FirebaseAuth.instance;
  // late final Rx<User?> firebaseUser;

  late Rx<Student?> student = Rx<Student?>(null);
  late SharedPreferences prefs;

  @override
  void onReady() async {
    prefs = await SharedPreferences.getInstance();
    bool isLoggedIn = prefs.getBool("isLoggedIn") ?? false;
    if (isLoggedIn) {
      student.value = Student.fromJson(prefs.getString("student") ??
          Student(
            studentId: 0,
            enrollment: 'enrollment',
            username: 'username',
            email: 'email',
            division: 'division',
            course: Course(courseId: 0, courseName: 'courseName'),
            batch: Batch(id: 0, batchName: 'batchName'),
            token: 'token',
          ).toJson());
    }
    // firebaseUser = Rx<User?>(_auth.currentUser);
    //firebaseUser.bindStream(_auth.userChanges());
    // ever(firebaseUser, _setInitialScreen);

    // ever(student, _setInitialScreen);
    _setInitialScreen(student);
  }

  _setInitialScreen(Rx<Student?> user) {
    user.value == null
        ? Get.offAll(() => const LoginScreen())
        : Get.offAll(() => const ScreenNavigator());
  }

  // Future<User?> createUserWithNameEmailAndPassword(
  //     String email, String password) async {
  //   try {
  //     await _auth.createUserWithEmailAndPassword(
  //         email: email, password: password);

  //     if (firebaseUser.value != null) {
  //       Get.offAll(() => const ScreenNavigator());
  //     }
  //     return firebaseUser.value;
  //   } on FirebaseAuthException catch (e) {
  //     final ex = SignUpWithEmailAndPasswordFailure.code(e.code);
  //     showSnackkBar(
  //       message: ex.message,
  //       title: 'Try Again',
  //       icon: const Icon(Icons.error),
  //     );
  //     // print('FIREBASE AUTH EXCEPTION - ${ex.message}');
  //     throw ex;
  //   } catch (_) {
  //     final ex = SignUpWithEmailAndPasswordFailure();
  //     showSnackkBar(
  //       message: ex.message,
  //       title: 'Try Again',
  //       icon: const Icon(Icons.error),
  //     );
  //     // print('EXCEPTION - ${ex.message}');
  //     throw ex;
  //   }
  // }

  Future<void> loginUserWithNameEmailAndPassword(
      String email, String password) async {
    try {
      final response = await http.post(Uri.parse("$apiUrl/auth/login"),
          body: AuthRequest(username: email, password: password).toJson(),
          headers: {'Content-Type': 'application/json'});

      if (response.statusCode == 200) {
        student = Rx<Student?>(Student.fromJson(response.body));

        await prefs.setBool("isLoggedIn", true);
        await prefs.setString("student", student.value!.toJson());
        await TokenManager.saveToken(student.value!.token);
      }
      if (response.statusCode == 404) {
        throw SignUpWithEmailAndPasswordFailure.code("404");
      }
      // await _auth.signInWithEmailAndPassword(email: email, password: password);

      // Get.put(faculty);
      student.value != null
          ? Get.offAll(() => const ScreenNavigator())
          : Get.offAll(() => const LoginScreen());
    } catch (_) {
      final ex = SignUpWithEmailAndPasswordFailure();
      showSnackkBar(
        message: ex.message,
        title: 'Try Again',
        icon: const Icon(Icons.error),
      );

      // print('EXCEPTION - ${ex.message}');
    }
  }

  // Future<void> resetPassword(email) async {
  //   await _auth.sendPasswordResetEmail(email: email);
  //   Get.offAll(() => const LoginScreen());
  //   showSnackkBar(
  //     message: 'Password Reset Mail Send SuccessFully',
  //     title: 'Check Your Mail',
  //     icon: const Icon(Icons.done),
  //   );
  // }

  Future<void> logOut() async {
    prefs.clear();
    Get.offAll(() => const LoginScreen());
  }
}
