class UserModel {
  late String? id;
  final String name;
  final String email;
  final String password;

  UserModel({
    this.id,
    required this.name,
    required this.email,
    required this.password,
  });

  toJson() {
    return {
      "Name": name,
      "email": email,
      "password": password,
    };
  }

  Map<String, dynamic> toFirestore() {
    return {
      "name": name,
      "email": email,
      "country": password,
      if (id != null) "id": id,
    };
  }
}
