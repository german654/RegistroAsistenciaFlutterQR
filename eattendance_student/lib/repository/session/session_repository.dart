import 'dart:convert';

import '../auth/auth_repository.dart';
import '../../models/attendance_data.dart';
import '../../models/token_manager.dart';
import '../../models/mapping_model.dart';
import '../../utility/constants.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SessionRepository {
  static SessionRepository get instance => Get.find();
  final AuthenticationRepository authRepo = Get.find();

  Future<Mapping?> isSessionOpen() async {
    try {
      final response = await http.get(
          Uri.parse(
              "$apiUrl/session/isSession/${authRepo.student.value!.studentId}"),
          headers: createAuthorizationHeaders(await TokenManager.getToken()));

      // log(response.body);
      if (response.statusCode == 200) {
        final json = jsonDecode(response.body);
        return Mapping.fromJson(json);
      } else {
        return null;
      }
    } catch (_) {
      return null;
    }
  }

  Future<bool> fillAttendance(Mapping map, AttendanceData data) async {
    // remain to pass the AttendnceData in the Request
    final response = await http.post(
        Uri.parse(
            "$apiUrl/session/fillAttendance/${map.mapId}/${authRepo.student.value!.studentId}"),
        headers: createAuthorizationHeaders(await TokenManager.getToken(),
            contentType: true),
        body: data.toJson());
    if (response.statusCode == 200) {
      return bool.parse(response.body);
    } else {
      throw Exception("Unable To Fill Attendance");
    }
  }
}
