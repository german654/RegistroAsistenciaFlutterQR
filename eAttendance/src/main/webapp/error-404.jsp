<%@page import="com.attendance.system.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>


<title>eAttendance | Error</title>

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
                        <div class="error mx-auto" data-text="404">404</div>
                        <p class="lead text-gray-800 mb-5">Page Not Found</p>
                        <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>
                        <br/><br/>
                        <a class="btn btn-outline-primary" href="/home">← Back to Dashboard</a>
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
				var copyrightElement = document.getElementById('currentYear');
				copyrightElement.innerHTML = "Copyright &copy; eAttendance " + new Date().getFullYear();
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
	
	<script src="js/sb-admin-2.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#side-courses").addClass("active")
		});
	</script>
</body>
</html>
