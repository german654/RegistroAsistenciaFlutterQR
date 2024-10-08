// ignore_for_file: public_member_api_docs, sort_constructors_first

import 'dart:convert';

import 'course_model.dart';
import 'faculty_model.dart';
import 'semester_model.dart';
import 'subject_model.dart';

class Mapping {
  int mapId;
  Course course;
  Subject subject;
  Semester semester;
  Faculty faculty;

  Mapping({
    required this.mapId,
    required this.course,
    required this.subject,
    required this.semester,
    required this.faculty,
  });

  factory Mapping.fromJson(Map<String, dynamic> json) {
    return Mapping(
      mapId: json['mapId'] as int,
      course: Course.fromJson(
          json['course'] as Map<String, dynamic>), // Parse the nested object
      subject: Subject.fromJson(
          json['subject'] as Map<String, dynamic>), // Parse the nested object
      semester: Semester.fromJson(
          json['semester'] as Map<String, dynamic>), // Parse the nested object
      faculty: Faculty.fromJson(
          json['faculty'] as Map<String, dynamic>), // Parse the nested object
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'mapId': mapId,
      'course': course.toMap(),
      'subject': subject.toMap(),
      'semester': semester.toMap(),
      'faculty': faculty.toMap(),
    };
  }

  factory Mapping.fromMap(Map<String, dynamic> map) {
    return Mapping(
      mapId: map['mapId'] as int,
      course: Course.fromMap(map['course'] as Map<String, dynamic>),
      subject: Subject.fromMap(map['subject'] as Map<String, dynamic>),
      semester: Semester.fromMap(map['semester'] as Map<String, dynamic>),
      faculty: Faculty.fromMap(map['faculty'] as Map<String, dynamic>),
    );
  }

  String toJson() => json.encode(toMap());
}
