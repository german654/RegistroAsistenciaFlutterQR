<%@page import="com.attendance.system.model.Semester"%>
<%@page import="com.attendance.system.model.Subject"%>
<%@page import="com.attendance.system.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>eAttendance | Manage Attendance</title>

<jsp:include page="components/head-imports.jsp"></jsp:include>

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
	<jsp:include page="components/sidebar.jsp"></jsp:include>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<jsp:include page="components/topbar.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Outer Row -->

					<!-- Nested Row within Card Body -->
					<div class="row">
						<h1 class="h3 mb-4 text-gray-800">Attendance</h1>
						<div class="col-md-6">
							<div class="p-5">
								<form class="user" id="startAttendance" action="attendanceQR" method="POST">
									<div class="form-group">
										<select name="course" id="course"
											class="form-control custom-select" required>
											<option value>Select Course</option>
											<%
											List<Course> courses = (List<Course>) request.getAttribute("courses");
											for (Course course : courses) {
											%>
											<option value="<%=course.getCourseId()%>"><%=course.getCourseName()%></option>
											<%
											}
											%>
										</select>
									</div>
									<div class="form-group">
										<select name="subject" id="subject"
											class="form-control custom-select" required>
											<option value>Select Subject</option>
											<%
											List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
											for (Subject subject : subjects) {
											%>
											<option value="<%=subject.getSubjectId()%>"><%=subject.getSubjectName()%></option>
											<%
											}
											%>
										</select>
									</div>
									<div class="form-group">
										<select name="semester" id="semester"
											class="form-control custom-select" required>
											<option value>Select Semester</option>
											<%
											List<Semester> semesters = (List<Semester>) request.getAttribute("sams");
											for (Semester semester : semesters) {
											%>
											<option value="<%=semester.getSemesterId()%>"><%=semester.getSemesterName()%></option>
											<%
											}
											%>
										</select>
									</div>
									<div class="form-group">
										<select name="division" id="dividion"
											class="form-control custom-select" required>
											<option value>Select Division</option>
											<%
											List<String> divisions = (List<String>) request.getAttribute("divisions");
											for (String division : divisions) {
											%>
											<option value="<%=division%>"><%=division%></option>
											<%
											}
											%>
										</select>
									</div>
									<div class="form-group">
										<input type="number" class="form-control form-control-user"
											id="duration" name="duration" placeholder="Enter Duration" min="1" value="1" max="5"
											required />
									</div>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit"
										class="btn btn-primary btn-user btn-block">Generate QR</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="components/footer.jsp"></jsp:include>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

<jsp:include page="components/models.jsp"></jsp:include>

	<jsp:include page="components/js-imports.jsp"></jsp:include>	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#side-attendance").addClass("active")
		});
	</script>
</body>
</html>
