// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'dart:convert';

class AuthRequest {
  String username;
  String password;
  AuthRequest({
    required this.username,
    required this.password,
  });

  AuthRequest copyWith({
    String? username,
    String? password,
  }) {
    return AuthRequest(
      username: username ?? this.username,
      password: password ?? this.password,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'username': username,
      'password': password,
    };
  }

  factory AuthRequest.fromMap(Map<String, dynamic> map) {
    return AuthRequest(
      username: map['username'] as String,
      password: map['password'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory AuthRequest.fromJson(String source) =>
      AuthRequest.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() => 'AuthRequest(username: $username, password: $password)';

  @override
  bool operator ==(covariant AuthRequest other) {
    if (identical(this, other)) return true;

    return other.username == username && other.password == password;
  }

  @override
  int get hashCode => username.hashCode ^ password.hashCode;
}
