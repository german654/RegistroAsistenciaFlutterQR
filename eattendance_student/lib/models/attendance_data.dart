// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'dart:convert';

class AttendanceData {
  String course;
  String subject;
  String sem;
  String dividion;
  String duration;
  String faculty;
  String createdAt;
  AttendanceData({
    required this.course,
    required this.subject,
    required this.sem,
    required this.dividion,
    required this.duration,
    required this.faculty,
    required this.createdAt,
  });

  AttendanceData copyWith({
    String? course,
    String? subject,
    String? sem,
    String? dividion,
    String? duration,
    String? faculty,
    String? createdAt,
  }) {
    return AttendanceData(
      course: course ?? this.course,
      subject: subject ?? this.subject,
      sem: sem ?? this.sem,
      dividion: dividion ?? this.dividion,
      duration: duration ?? this.duration,
      faculty: faculty ?? this.faculty,
      createdAt: createdAt ?? this.createdAt,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'course': course,
      'subject': subject,
      'sem': sem,
      'dividion': dividion,
      'duration': duration,
      'faculty': faculty,
      'createdAt': createdAt,
    };
  }

  factory AttendanceData.fromMap(Map<String, dynamic> map) {
    return AttendanceData(
      course: map['course'] as String,
      subject: map['subject'] as String,
      sem: map['sem'] as String,
      dividion: map['dividion'] as String,
      duration: map['duration'] as String,
      faculty: map['faculty'] as String,
      createdAt: map['createdAt'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory AttendanceData.fromJson(String source) =>
      AttendanceData.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() {
    return 'AttendanceData(course: $course, subject: $subject, sem: $sem, dividion: $dividion, duration: $duration, faculty: $faculty, createdAt: $createdAt)';
  }

  @override
  bool operator ==(covariant AttendanceData other) {
    if (identical(this, other)) return true;

    return other.course == course &&
        other.subject == subject &&
        other.sem == sem &&
        other.dividion == dividion &&
        other.duration == duration &&
        other.faculty == faculty &&
        other.createdAt == createdAt;
  }

  @override
  int get hashCode {
    return course.hashCode ^
        subject.hashCode ^
        sem.hashCode ^
        dividion.hashCode ^
        duration.hashCode ^
        faculty.hashCode ^
        createdAt.hashCode;
  }
}
