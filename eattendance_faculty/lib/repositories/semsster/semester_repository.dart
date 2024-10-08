import 'dart:convert';

import 'package:eattendance_faculty/models/token_manager.dart';

import '../../models/semester_model.dart';
import '../../utility/constants.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SemesterRepository {
  static SemesterRepository get instance => Get.find();

  Future<List<Semester>> getSemesters() async {
    final response = await http.get(Uri.parse("$apiUrl/semester/getAll"),
        headers: createAuthorizationHeaders(await TokenManager.getToken()));
    if (response.statusCode == 200) {
      List<dynamic> semesterList = jsonDecode(response.body);
      List<Semester> semesters =
          semesterList.map((e) => Semester.fromJson(e)).toList();
      return semesters;
    } else {
      throw Exception("Failed To Load Semesters");
    }
  }

  Future<List<DropdownMenuItem<String>>> getSemestersDropdownItems() async {
    List<Semester> semesters = await getSemesters();
    return semesters
        .map((semester) => DropdownMenuItem(
              value: semester.semesterId
                  .toString(), // Assuming 'id' is the unique identifier for the course
              child: Text(semester
                  .semesterName), // Replace 'name' with the actual course name field
            ))
        .toList();
  }
}
