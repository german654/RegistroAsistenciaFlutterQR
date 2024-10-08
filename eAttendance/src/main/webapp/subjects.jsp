<%@page import="com.attendance.system.model.Subject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>eAttendance | Manage Subjects</title>

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
						<!-- Page Heading -->
						<h1 class="h3 mb-4 text-gray-800">Add Subject</h1>
						<div class="col-md-6">
							<div class="p-5">
								<form class="user" id="addSubject">
									<div class="form-group">
										<input type="text" class="form-control form-control-user"
											id="subjectName" name="subjectName"
											placeholder="Enter Subject Name" />
									</div>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class="mt-2 mb-2" id="subjectResponse"></div>
									<button type="submit"
										class="btn btn-primary btn-user btn-block">Add
										Subject</button>
								</form>
							</div>
						</div>
					</div>
					<hr />
					<div class="row">
						<h1 class="h3 mb-4 text-gray-800">Available Subjects</h1>
						<div class="col-md-12">
							<div class="p-5">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>Subject Id</th>
												<th>Name</th>
												<th>Edit</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>Subject Id</th>
												<th>Name</th>
												<th>Edit</th>
												<th>Delete</th>
											</tr>
										</tfoot>
										<tbody>
											<%
											List<Subject> subjects = (List<Subject>) request.getAttribute("subjectList");
											for (Subject subject : subjects) {
											%>
											<tr>
												<td><%=subject.getSubjectId()%></td>
												<td><%=subject.getSubjectName()%></td>
												<td><button type="button"
														class="btn btn-warning updSubject"
														data-sid="<%=subject.getSubjectId()%>" data-toggle="modal"
														data-target="#editSubject">
														<i class="fas fa-edit"></i>
													</button></td>
												<td><button type="button"
														class="btn btn-danger delSubject" data-csrf="${_csrf.token}"
														data-sid="<%=subject.getSubjectId()%>">
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
				<!-- /.container-fluid -->
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
			$("#side-subjects").addClass("active")
		});
	</script>
</body>
</html>
