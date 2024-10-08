import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../repository/attendance/attendance_list_repository.dart';
import '../../models/attendance_model.dart';

class AttendanceList extends StatefulWidget {
  const AttendanceList({super.key});

  @override
  State<AttendanceList> createState() => _AttendanceListState();
}

class _AttendanceListState extends State<AttendanceList>
    with SingleTickerProviderStateMixin {
  late AnimationController _animationController;
  double totalPercentage = 0.0;
  AttendanceListRepository attendanceListRepo =
      Get.put(AttendanceListRepository());

  AttendanceListData? data = AttendanceListData(
      subjects: List.empty(), subjectAttendance: List.empty(), total: 0);

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 1),
    );
    _animationController.forward();
    totalPercentage = 0.0;
    _initializeData();
  }

  void _initializeData() async {
    data = await attendanceListRepo.getAttendanceList();
    double totalAttendance = 0.0;
    for (double attendance in data!.subjectAttendance) {
      totalAttendance += attendance;
    }
    setState(() {
      totalPercentage = totalAttendance;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: listView(),
    );
  }

  Widget listView() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[
        Column(
          children: [
            for (int i = 0; i < data!.subjects.length; i++)
              listViewItem(
                  data!.subjectAttendance[i], data!.subjects[i].subjectName),
          ],
        ),
        Container(
          alignment: Alignment.center,
          padding: const EdgeInsets.all(10),
          color: totalPercentageColor(),
          child: Text(
            'Total Attendance: ${totalPercentage.toStringAsFixed(2)}',
            style: const TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 20.0,
            ),
          ),
        ),
      ],
    );
  }

  Widget listViewItem(double subjectPercentage, String subjectName) {
    Color progressColor = Colors.green;

    if (subjectPercentage <= 33) {
      progressColor = Colors.red;
    } else if (subjectPercentage > 33 && subjectPercentage < 70) {
      progressColor = Colors.yellow;
    }

    return Container(
      decoration: const BoxDecoration(
        border: Border.symmetric(horizontal: BorderSide(color: Colors.black26)),
      ),
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 15),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: Container(
              margin: const EdgeInsets.only(left: 10),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0, top: 5.0),
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          subjectName,
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 20.0,
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(right: 8.0),
            child: AnimatedBuilder(
              animation: _animationController,
              builder: (context, child) {
                return CustomPaint(
                  // size: const Size(100, 100),
                  painter: CirclePainter(
                    progress: subjectPercentage * _animationController.value,
                    progressColor: progressColor,
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(5),
                    child: Center(
                      child: Text(
                        '${(subjectPercentage * _animationController.value).toStringAsFixed(0)}',
                        style: const TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 15.0,
                          color: Colors.black,
                        ),
                      ),
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }

  Color totalPercentageColor() {
    if (totalPercentage <= 33) {
      return Colors.red;
    } else if (totalPercentage > 33 && totalPercentage < 70) {
      return Colors.yellow;
    } else {
      return Colors.green;
    }
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }
}

class CirclePainter extends CustomPainter {
  final double progress;
  final Color progressColor;

  CirclePainter({required this.progress, required this.progressColor});

  @override
  void paint(Canvas canvas, Size size) {
    Paint paint = Paint()
      ..color = Colors.grey[300]!
      ..strokeCap = StrokeCap.round
      ..style = PaintingStyle.stroke
      ..strokeWidth = 2.0;

    canvas.drawCircle(size.center(Offset.zero), size.width / 2, paint);

    Paint progressPaint = Paint()
      ..color = progressColor
      ..strokeCap = StrokeCap.round
      ..style = PaintingStyle.stroke
      ..strokeWidth = 3.0;

    double sweepAngle = 360 * progress / 100;
    canvas.drawArc(
      Rect.fromCircle(center: size.center(Offset.zero), radius: size.width / 2),
      -90 * (3.1415926535897932 / 180),
      sweepAngle * (3.1415926535897932 / 180),
      false,
      progressPaint,
    );
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return true;
  }
}
