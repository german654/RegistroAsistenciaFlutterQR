<%@page import="com.attendance.system.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>eAttendance | Error</title>
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet" />

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- SideBar -->
		<jsp:include page="components/sidebar.jsp"></jsp:include>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<jsp:include page="components/topbar.jsp"></jsp:include>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- 404 Error Text -->
					<div class="text-center">
						<div class="error mx-auto" data-text="Opps!">Opps!</div>
						<p class="lead text-gray-800 mb-5 mt-5">Unknown Error</p>
						<p class="text-gray mb-0">
							<%=request.getAttribute("error") != null
		? request.getAttribute("error")
		: "We are sorry but our server encountered an internal error"%></p>

						<br /> <a class="btn btn-outline-primary" href="/home">← Back to
							Dashboard</a> <br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white mt-auto">
				<div class="container my-auto">
					<div class="copyright text-center my-auto" id="currentYear">
					</div>
				</div>
				<script>
					var copyrightElement = document
							.getElementById('currentYear');
					copyrightElement.innerHTML = "Copyright &copy; eAttendance "
							+ new Date().getFullYear();
				</script>
			</footer>

			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

</body>
</html>
