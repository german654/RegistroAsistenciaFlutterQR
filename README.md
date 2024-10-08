# eAttendance System Using QR Code

Welcome to the eAttendance System repository! This system is designed to streamline the attendance process using QR codes, ensuring real-time and accurate attendance tracking.

## Features

- **QR Code Generation:** Faculty members can generate a QR code representing a session. The QR code is displayed to students for attendance.

- **Customizable Timeout:** Faculty members have the flexibility to set the duration for which the QR code will be active. Once the set time elapses, the QR code becomes invalid.

- **Real-time Attendance:** The system facilitates real-time attendance tracking, allowing faculty to record attendance for up to 100 students within a minute.

- **Mobile App Integration:** Students can use the dedicated mobile app developed for both Android and iOS platforms using Flutter. The app enables seamless scanning of QR codes for attendance purposes.

- **Anti-Tampering Measures:** To prevent fake attendance, students must remain in the app and connected to the network after scanning the QR code. Any attempt to switch apps or change the network during the session will result in attendance not being marked.

## Getting Started

### Prerequisites

- [Flutter](https://flutter.dev/) installed for app development.
- [Java 17](https://openjdk.java.net/projects/jdk/17/) for Attendance Backend.
- [STS (Spring Tool Suite)](https://spring.io/tools) recommended as the IDE for backend development.
- MySQL database with a database named `attendance_system`. Configure port and credentials in `application.properties`.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/smit-joshi814/eAttendance.git
   ```

2. **Backend (Java 17 & STS):**
   - Import the backend project into STS.
   - Set up the database configuration in `application.properties`.
   - Run the project as a Spring Boot Application.

3. **Flutter (Frontend):**
   - Navigate to the `eattendance_student` directory.
   - Run `flutter pub get` to install dependencies.
   - Connect a device or use an emulator.
   - Run `flutter run` to launch the app.

## Configuration

- **Backend Configuration:**
  - The backend API is built using Spring Boot.
  - Configure the MySQL database in `application.properties`.
  - Set up the appropriate port for the backend.

- **Frontend Configuration:**
  - The mobile app is developed using Flutter.
  - Use `flutter doctor` to ensure all dependencies are installed.
  - Update the Flutter SDK if necessary.

## Contribution

We welcome contributions to enhance the eAttendance System. Please follow the [contribution guidelines](CONTRIBUTING.md) for more details.

### Contributors

<a href="https://github.com/smit-joshi814/eAttendance/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=smit-joshi814/eAttendance&max=5" />
</a>


## Contact

For any inquiries or support, please contact [https://www.linkedin.com/in/smit-joshi814/].

Take Attendance with ease🎉! 🚀
