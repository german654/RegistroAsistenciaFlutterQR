<%@page import="com.attendance.system.model.SiteUser"%>
<%@page import="com.attendance.system.model.Mapping"%>
<%@page import="com.attendance.system.model.Semester"%>
<%@page import="com.attendance.system.model.Subject"%>
<%@page import="java.util.List"%>
<%@page import="com.attendance.system.model.Course"%>
<%@page import="com.attendance.system.model.MappingWrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>


<title>eAttendance | Manage</title>

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
						<%
						MappingWrapper mappingWrapper = (MappingWrapper) request.getAttribute("mappingList");
						%>
						<!-- Page Heading -->
						<h1 class="h3 mb-4 text-gray-800">Add Mapping</h1>
						<div class="col-md-6">
							<div class="p-5">
								<form class="user" id="addMapping">
									<div class="form-group">
										<select name="course" id="course"
											class="form-control custom-select" required>
											<option value>Select Course</option>
											<%
											List<Course> courses = mappingWrapper.getCourses();
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
											List<Subject> subjects = mappingWrapper.getSubjects();
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
											List<Semester> semesters = mappingWrapper.getSemesters();
											for (Semester semester : semesters) {
											%>
											<option value="<%=semester.getSemesterId()%>"><%=semester.getSemesterName()%></option>
											<%
											}
											%>
										</select>
									</div>
									<div class="form-group">
										<select name="faculty" id="faculty"
											class="form-control custom-select" required>
											<option value>Select Faculty</option>
											<%
											List<SiteUser> faculties = mappingWrapper.getFaculties();
											for (SiteUser faculty : faculties) {
											%>
											<option value="<%=faculty.getUserId()%>"><%=faculty.getUserName()%></option>
											<%
											}
											%>
										</select>
									</div>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class="mt-2 mb-2" id="mappingResponse"></div>
									<button type="submit"
										class="btn btn-primary btn-user btn-block">Add
										Mapping</button>
								</form>
							</div>
						</div>
					</div>
					<hr />
					<div class="row">
						<h1 class="h3 mb-4 text-gray-800">Available Mappings</h1>
						<div class="col-md-12">
							<div class="p-5">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>Id</th>
												<th>Course</th>
												<th>Subject</th>
												<th>Semester</th>
												<th>Faculty</th>
												<!-- <th>Edit</th>  -->
												<th>Delete</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>Id</th>
												<th>Course</th>
												<th>Subject</th>
												<th>Semester</th>
												<th>Faculty</th>
												<!-- <th>Edit</th>  -->
												<th>Delete</th>
											</tr>
										</tfoot>
										<tbody>
											<%
											List<Mapping> mappings = mappingWrapper.getMappings();
											for (Mapping mapping : mappings) {
											%>
											<tr>
												<td><%=mapping.getMapId()%></td>
												<td><%=mapping.getCourse().getCourseName()%></td>
												<td><%=mapping.getSubject().getSubjectName()%></td>
												<td><%=mapping.getSemester().getSemesterName()%></td>
												<td><%=mapping.getFaculty().getUserName()%></td>
												<!-- 
												
												// Update Button
												
												<td><button type="button"
														class="btn btn-warning updMapping"
														data-mid="<%=mapping.getMapId()%>" data-toggle="modal"
														data-target="#editMapping">
														<i class="fas fa-edit"></i>
													</button></td>
													
												-->

												<td><button type="button"
														class="btn btn-danger delMapping" data-csrf="${_csrf.token}"
														data-mid="<%=mapping.getMapId()%>">
														<i class="fas fa-trash"></i>
													</button></td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
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
			$("#side-manage").addClass("active")
		});
	</script>
</body>
</html>
