import 'dart:developer';

import 'package:eattendance_faculty/models/token_manager.dart';

import '../../utility/constants.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SessionRepository {
  static SessionRepository get instance => Get.find();

  Future<String> startSession(String? selectedCourse, String? selectedSubject,
      String? selectedSem, String? selectedDivision, int facultyId) async {
    final response = await http.get(
        Uri.parse(
            "$apiUrl/session/start/$selectedCourse/$selectedSubject/$selectedSem/$selectedDivision/$facultyId"),
        headers: createAuthorizationHeaders(await TokenManager.getToken()));
    if (response.statusCode == 200) {
      String result = response.body;
      return result;
    } else {
      throw Exception('Failed to Start Session');
    }
  }

  Future<String> stopSession(String? selectedCourse, String? selectedSubject,
      String? selectedSem, String? selectedDivision, int facultyId) async {
    final response = await http.get(
        Uri.parse(
            "$apiUrl/session/stop/$selectedCourse/$selectedSubject/$selectedSem/$selectedDivision/$facultyId"),
        headers: createAuthorizationHeaders(await TokenManager.getToken()));
    log(response.body.toString());
    if (response.statusCode == 200) {
      String result = response.body.toString();
      return result;
    } else {
      return response.body.toString();
    }
  }
}
