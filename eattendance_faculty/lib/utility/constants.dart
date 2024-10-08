const String appName = "eAttendance Faculty";

const String appLogo = "assets/images/logo.png";

Map<String, String> createAuthorizationHeaders(String? token) {
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://b348-2406-b400-d11-87eb-58c6-324e-a9f1-1203.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
