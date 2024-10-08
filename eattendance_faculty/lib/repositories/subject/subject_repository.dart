import 'dart:convert';

import 'package:eattendance_faculty/models/token_manager.dart';

import '../../models/subject_model.dart';
import '../../repositories/course/course_repository.dart';
import '../../utility/constants.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SubjectRepository {
  static CourseRepository get instance => Get.find();

  Future<List<Subject>> getSubjects() async {
    final response = await http.get(Uri.parse("$apiUrl/subjects/getAll"),
        headers: createAuthorizationHeaders(await TokenManager.getToken()));
    if (response.statusCode == 200) {
      List<dynamic> subjectList = jsonDecode(response.body);
      List<Subject> subjects =
          subjectList.map((e) => Subject.fromJson(e)).toList();
      return subjects;
    } else {
      throw Exception("Failed to Load Subjects");
    }
  }

  Future<List<DropdownMenuItem<String>>> getSubjectsDropdownItems() async {
    List<Subject> subjects = await getSubjects();
    return subjects
        .map((subject) => DropdownMenuItem(
              value: subject.subjectId
                  .toString(), // Assuming 'id' is the unique identifier for the course
              child: Text(subject
                  .subjectName), // Replace 'name' with the actual course name field
            ))
        .toList();
  }
}
