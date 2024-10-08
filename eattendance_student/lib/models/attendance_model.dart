// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'dart:convert';

import 'package:collection/collection.dart';
import 'package:eattendance_student/models/subject_model.dart';

class AttendanceListData {
  List<Subject> subjects;
  List<double> subjectAttendance;
  double total;
  AttendanceListData({
    required this.subjects,
    required this.subjectAttendance,
    required this.total,
  });

  AttendanceListData copyWith({
    List<Subject>? subjects,
    List<double>? subjectAttendance,
    double? total,
  }) {
    return AttendanceListData(
      subjects: subjects ?? this.subjects,
      subjectAttendance: subjectAttendance ?? this.subjectAttendance,
      total: total ?? this.total,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'subjects': subjects.map((x) => x.toMap()).toList(),
      'subjectAttendance': subjectAttendance,
      'total': total,
    };
  }

  factory AttendanceListData.fromMap(Map<String, dynamic> map) {
    final List<Subject> subjects = (map['subjects'] as List<dynamic>?)
            ?.map((x) => Subject.fromMap(x as Map<String, dynamic>))
            .toList() ??
        [];
    final List<double> subjectAttendance =
        (map['subjectAttendance'] as List<dynamic>?)
                ?.map<double>((value) => value.toDouble())
                .toList() ??
            [];
    final double total =
        (map['total'] as int).toDouble(); // Convert 'total' from int to double

    return AttendanceListData(
      subjects: subjects,
      subjectAttendance: subjectAttendance,
      total: total,
    );
  }

  String toJson() => json.encode(toMap());

  factory AttendanceListData.fromJson(String source) =>
      AttendanceListData.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() =>
      'AttendanceListData(subjects: $subjects, subjectAttendance: $subjectAttendance, total: $total)';

  @override
  bool operator ==(covariant AttendanceListData other) {
    if (identical(this, other)) return true;
    final listEquals = const DeepCollectionEquality().equals;

    return listEquals(other.subjects, subjects) &&
        listEquals(other.subjectAttendance, subjectAttendance) &&
        other.total == total;
  }

  @override
  int get hashCode =>
      subjects.hashCode ^ subjectAttendance.hashCode ^ total.hashCode;
}
