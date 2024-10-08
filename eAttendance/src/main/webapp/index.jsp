<%@page import="com.attendance.system.model.Attendance"%>
<%@page import="com.attendance.system.model.Subject"%>
<%@page import="com.attendance.system.model.Semester"%>
<%@page import="com.attendance.system.model.Course"%>
<%@page import="com.attendance.system.model.MappingWrapper"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MappingWrapper mappings = (MappingWrapper) request.getAttribute("mappingWrapper");
List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>eAttendance | Dashboard</title>

<jsp:include page="components/head-imports.jsp"></jsp:include>

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Side bar -->
		<jsp:include page="components/sidebar.jsp"></jsp:include>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">

				<jsp:include page="components/topbar.jsp"></jsp:include>

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
					</div>

					<%
					for (Course course : mappings.getCourses()) {
					%>

					<div class="row">
						<div class="col">
							<div class="text-dark font-weight-bold"><%=course.getCourseName()%></div>
						</div>
					</div>

					<%
					for (Semester sem : mappings.getSemesters()) {
					%>

					<div><%=sem.getSemesterName()%></div>

					<div class="row">
						<%
						for (Subject subject : mappings.getSubjects()) {
						%>
						<div class="col-md-3 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">
												<%=subject.getSubjectName()%></div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">											
												<%
												int count = 0;
												for (Attendance attendance : attendanceList) {
													if (attendance.getMapId().getCourse().equals(course) && attendance.getMapId().getSemester().equals(sem) && attendance.getMapId().getSubject().equals(subject)) {
														count++;
													}
												}
												%>
												<%=count%>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						}
						%>
					</div>

					<%
					}
					%>

					<!-- Content Row -->
					<div class="row">
						<div class="col-lg">
							<div class="card">
								<div class="card-body">
									<h3 class="card-title">Statistics</h3>
									<div id="chart-mentions" class="chart-lg"></div>
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

	<!-- Models -->
	<jsp:include page="components/models.jsp"></jsp:include>

	<jsp:include page="components/js-imports.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#side-dashbord").addClass("active")
		});
	</script>

	<script type="text/javascript">
		window.addEventListener("load", () => {
			if ("serviceWorker" in navigator) {
			navigator.serviceWorker.register("service-worker.js");
			}
		});

	</script>

	<script>
    document.addEventListener("DOMContentLoaded", function () {
        window.ApexCharts && (new ApexCharts(document.getElementById('chart-mentions'), {
            chart: {
                type:"line",
                fontFamily: 'inherit',
                height: 350,
                parentHeightOffset: 0,
                toolbar: {
                    show: true,
                },
                animations: {
                    enabled: true
                },
                stacked: false,
            },
            plotOptions: {
                bar: {
                    columnWidth: '80%',
                }
            },
            stroke: {
                width: [2, 0, 2, 0]
             },
            dataLabels: {
                enabled: false,
            },
            fill: {
                opacity: 1,
            },
            series: [
            {
                name: "A",
                type: "column",
                data: [30,20,30,10]
            },
            {
                name: "B",
                type: "column",
                data: [18,30,40,20]
            },
            {
                name: "C",
                type: "column",
                data: [19,20,30,50]
            },
            {
                name: "D",
                type: "column",
                data: [8,20,30,40]
            }
            ],
            tooltip: {
                fixed: {
                  enabled: true,
                  position: 'topLeft', // topRight, topLeft, bottomRight, bottomLeft
                  offsetY: 30,
                  offsetX: 60
                },
             },
            grid: {
                padding: {
                    top: 0,
                    right: 0,
                    left: 70,
                    bottom: 0
                },
                strokeDashArray: 4,
                xaxis: {
                    lines: {
                        show: true
                    }
                },
            },
            xaxis: {
            	labels: {
                    padding: 0, 
                },
                tooltip: {
                    enabled: false
                },
                axisBorder: {
                    show: true,
                },
           	 	title: {
           	        text: 'Divisions',
           	        offsetX: 0,
           	        offsetY: 0,
           	        style: {
           	            fontSize: '12px',
           	        },
           	    },
            },
            yaxis: {
                labels: {
                    show: true,
                    align: 'right', // Align y-axis labels to the right for better readability
                },
                title: {
                    text: 'Attendance',
                    rotate: -90,
                    offsetX: 0,
                    offsetY: 0,
                    style: {
                        fontSize: '12px',
                    },
                },
            },
            labels: ['SAM-1','SAM-2','SAM-3','SAM-4','SAM-5','SAM-6','SAM-7'],
            colors: ['#2E93fA', '#66DA26', '#546E7A', '#E91E63', '#FF9800'],
            legend: {
                show: true,
                position: 'bottom', // Change the legend position (top, right, bottom, left)
                horizontalAlign: 'center', // Align legend items horizontally
                fontSize: '14px',
            },
        })).render();
    });
</script>
</body>
</html>
