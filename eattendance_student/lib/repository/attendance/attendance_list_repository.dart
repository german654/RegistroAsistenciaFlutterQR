import 'dart:convert';

import 'package:eattendance_student/models/attendance_model.dart';
import 'package:eattendance_student/models/token_manager.dart';
import 'package:eattendance_student/repository/auth/auth_repository.dart';
import 'package:eattendance_student/utility/constants.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class AttendanceListRepository {
  static AttendanceListRepository get instance => Get.find();
  final AuthenticationRepository authRepo = Get.find();

  Future<AttendanceListData?> getAttendanceList() async {
    try {
      final response = await http.get(
          Uri.parse(
              "$apiUrl/attendance/data/${authRepo.student.value!.studentId}"),
          headers: createAuthorizationHeaders(await TokenManager.getToken()));

      if (response.statusCode == 200) {
        final Map<String, dynamic> jsonData = jsonDecode(response.body);
        return AttendanceListData.fromMap(jsonData);
      } else {
        return null;
      }
    } catch (_) {
      return null;
    }
  }
}
