import 'dart:convert';

class Faculty {
  int facultyId;
  String enrollment;
  String email;
  String username;
  String token;

  Faculty({
    required this.facultyId,
    required this.enrollment,
    required this.email,
    required this.username,
    required this.token,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'facultyId': facultyId,
      'enrollment': enrollment,
      'email': email,
      'username': username,
      'token': token,
    };
  }

  factory Faculty.fromMap(Map<String, dynamic> map) {
    return Faculty(
      facultyId: map['facultyId'] as int,
      enrollment: map['enrollment'] as String,
      email: map['email'] as String,
      username: map['username'] as String,
      token: map['token'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory Faculty.fromJson(String source) =>
      Faculty.fromMap(json.decode(source) as Map<String, dynamic>);
}
