import 'dart:convert';

class Faculty {
  int userId;
  String enrollment;
  String email;
  String userName;
  String password;

  Faculty({
    required this.userId,
    required this.enrollment,
    required this.email,
    required this.userName,
    required this.password,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'userId': userId,
      'enrollment': enrollment,
      'email': email,
      'userName': userName,
      'password': password,
    };
  }

  factory Faculty.fromMap(Map<String, dynamic> map) {
    return Faculty(
      userId: map['userId'] as int,
      enrollment: map['enrollment'] as String,
      email: map['email'] as String,
      userName: map['userName'] as String,
      password: map['password'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory Faculty.fromJson(Map<String, dynamic> json) {
    return Faculty(
      userId: json['userId'] as int,
      enrollment: json['enrollment'] as String,
      email: json['email'] as String,
      userName: json['userName'] as String,
      password: json['password'] as String,
    );
  }
}
