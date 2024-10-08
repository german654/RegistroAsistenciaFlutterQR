// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'dart:convert';

import '../../models/batch_model.dart';
import '../../models/course_model.dart';

class Student {
  int studentId;
  String enrollment;
  String username;
  String email;
  String division;
  Course course;
  Batch batch;
  String token;
  Student({
    required this.studentId,
    required this.enrollment,
    required this.username,
    required this.email,
    required this.division,
    required this.course,
    required this.batch,
    required this.token,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'studentId': studentId,
      'enrollment': enrollment,
      'username': username,
      'email': email,
      'division': division,
      'course': course.toMap(),
      'batch': batch.toMap(),
      'token': token,
    };
  }

  factory Student.fromMap(Map<String, dynamic> map) {
    return Student(
      studentId: map['studentId'] as int,
      enrollment: map['enrollment'] as String,
      username: map['username'] as String,
      email: map['email'] as String,
      division: map['division'] as String,
      course: Course.fromMap(map['course'] as Map<String, dynamic>),
      batch: Batch.fromMap(map['batch'] as Map<String, dynamic>),
      token: map['token'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory Student.fromJson(String source) =>
      Student.fromMap(json.decode(source) as Map<String, dynamic>);

  Student copyWith({
    int? studentId,
    String? enrollment,
    String? username,
    String? email,
    String? division,
    Course? course,
    Batch? batch,
    String? token,
  }) {
    return Student(
      studentId: studentId ?? this.studentId,
      enrollment: enrollment ?? this.enrollment,
      username: username ?? this.username,
      email: email ?? this.email,
      division: division ?? this.division,
      course: course ?? this.course,
      batch: batch ?? this.batch,
      token: token ?? this.token,
    );
  }

  @override
  String toString() {
    return 'Student(studentId: $studentId, enrollment: $enrollment, username: $username, email: $email, division: $division, course: $course, batch: $batch, token: $token)';
  }

  @override
  bool operator ==(covariant Student other) {
    if (identical(this, other)) return true;

    return other.studentId == studentId &&
        other.enrollment == enrollment &&
        other.username == username &&
        other.email == email &&
        other.division == division &&
        other.course == course &&
        other.batch == batch &&
        other.token == token;
  }

  @override
  int get hashCode {
    return studentId.hashCode ^
        enrollment.hashCode ^
        username.hashCode ^
        email.hashCode ^
        division.hashCode ^
        course.hashCode ^
        batch.hashCode ^
        token.hashCode;
  }
}
