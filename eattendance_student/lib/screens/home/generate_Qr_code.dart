import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:simple_barcode_scanner/simple_barcode_scanner.dart';

import '../../models/attendance_data.dart';
import '../../models/mapping_model.dart';
import '../../repository/session/session_repository.dart';
import '../../utility/utils.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> with WidgetsBindingObserver {
  final SessionRepository sessionRepo = Get.put(SessionRepository());
  late bool _showBuffer;
  String result = '';
  Map<String, String> queryParameters = {};
  late OverlayEntry _overlayEntry;
  String? duration;
  String? createdAt;
  String overlayMessage = "Please wait while we mark your attendance.";

  bool overlayShown = false;
  bool isAppActive = true; // Flag to track if the app is active
  bool isAttendance = false; // Flag to track if attendance is filled

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    switch (state) {
      case AppLifecycleState.resumed:
        setState(() {
          isAppActive = true; // App is now active
        });
        break;
      case AppLifecycleState.paused:
        setState(() {
          isAppActive = false; // App is now inactive
        });
        break;
      default:
        break;
    }
  }

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _showBuffer = false;
    _overlayEntry = createOverlayEntry();
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  OverlayEntry createOverlayEntry() {
    return OverlayEntry(
      builder: (context) => Scaffold(
        body: Container(
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height,
          color: Colors.white,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const CircularProgressIndicator(),
                Text(
                  overlayMessage,
                  style: const TextStyle(fontSize: 14),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void removeOverlay() {
    setState(() {
      _overlayEntry.remove();
      overlayShown = false;
      if (isAttendance) {
        showSnackkBar(
          icon: const Icon(Icons.done),
          message: 'Attendance Filled Successfully',
        );
      } else if (!isAttendance) {
        showSnackkBar(
          icon: const Icon(Icons.not_interested),
          message:
              'Could not mark attendance because you have switched the application Or Out Of Time',
        );
      } else {
        showSnackkBar(
          icon: const Icon(Icons.not_interested),
          message: 'Oops! You Are Out Of Time',
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    var res = await Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const SimpleBarcodeScannerPage(),
                      ),
                    );

                    setState(() {
                      if (res is String) {
                        result = res;
                        Uri uri = Uri.parse(result);
                        queryParameters = uri.queryParameters;

                        duration = queryParameters['duration'];
                        createdAt = queryParameters['createdAt'];
                      }
                    });

                    if (!overlayShown &&
                        duration != null &&
                        duration!.isNotEmpty) {
                      showOverlay(res);
                    } else {
                      showSnackkBar(
                        icon: const Icon(Icons.not_interested),
                        message: 'Invalid QR Code',
                      );
                    }
                  },
                  child: const Text('Open Scanner'),
                ),
              ],
            ),
          ),
          _showBuffer ? _overlayEntry as Widget : const SizedBox(),
        ],
      ),
    );
  }

  void showOverlay(String res) async {
    DateTime currentDate = DateTime.now();
    DateTime createdAtTime = DateTime(
      currentDate.year,
      currentDate.month,
      currentDate.day,
      int.parse(createdAt!.split(":")[0]),
      int.parse(createdAt!.split(":")[1]),
      int.parse(createdAt!.split(":")[2]),
    );

    int durationInMinutes = int.tryParse(duration ?? '0') ?? 0;
    DateTime endTime = createdAtTime.add(Duration(minutes: durationInMinutes));

    Overlay.of(context).insert(_overlayEntry);

    Duration difference = currentDate.difference(endTime).abs();
    int remainingSeconds = difference.inSeconds;

    Future.delayed(Duration(seconds: remainingSeconds), () {
      removeOverlay();
    });

    Mapping? map = await sessionRepo.isSessionOpen();

    log(map.toString());

    if (map != null && isAppActive) {
      showSnackkBar(
        icon: const Icon(Icons.done),
        message: 'Session is Active Filling Attendance',
      );

      if (remainingSeconds > 30) {
        Future.delayed(Duration(seconds: remainingSeconds - 40), () async {
          isAttendance =
              await sessionRepo.fillAttendance(map, getAttendanceData(res));
        });
      } else {
        isAttendance =
            await sessionRepo.fillAttendance(map, getAttendanceData(res));
      }
    } else if (!isAppActive) {
      showSnackkBar(
        icon: const Icon(Icons.not_interested),
        message:
            'Could not mark attendance because you have switched the application',
      );
      overlayMessage = 'Please wait till session expires.';
    } else {
      showSnackkBar(
        icon: const Icon(Icons.not_interested),
        message: 'Oops! You Are Out Of Time',
      );
    }

    log(queryParameters.toString());
    overlayShown = true;
  }

  AttendanceData getAttendanceData(String res) {
    Uri uri = Uri.parse(res);
    return AttendanceData(
      course: uri.queryParameters['course']!,
      subject: uri.queryParameters['subject']!,
      sem: uri.queryParameters['sem']!,
      dividion: uri.queryParameters['division']!,
      faculty: uri.queryParameters['fid']!,
      duration: uri.queryParameters['duration']!,
      createdAt: uri.queryParameters['createdAt']!,
    );
  }
}
