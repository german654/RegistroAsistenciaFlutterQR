<%@page import="com.attendance.system.model.SiteUser"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
<%
SiteUser user = (SiteUser) pageContext.getRequest().getAttribute("siteUser");
%>
	<sec:authorize access="hasAnyRole('ADMIN','FACULTY')">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="home">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3" title="<%=user.getUserName()%>">
					<small>Welcome</small> <span><%=user.getUserName().length() > 5 ? user.getUserName().substring(0, 5) + ".." : user.getUserName()%></span>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item" id="side-dashbord"><a class="nav-link"
				href="home"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider" />

			<!-- Heading -->
			<div class="sidebar-heading">Attendance</div>

			<li class="nav-item" id="side-attendance"><a class="nav-link"
				href="attendance"> <i class="fas fa-fw fa-check-square"></i> <span>Attendance</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider" />



			<sec:authorize access="hasRole('ADMIN')">
				<!-- Heading -->
				<div class="sidebar-heading">Faculty</div>
				<!-- Nav Item - Charts -->
				<li class="nav-item" id="side-faculty"><a class="nav-link"
					href="faculty"> <i class="fas fa-fw fa-users"></i> <span>Manage
							Faculty</span></a></li>

				<!-- Divider -->
				<hr class="sidebar-divider" />
			</sec:authorize>

			<!-- Heading -->
			<div class="sidebar-heading">Students</div>
			<!-- Nav Item - Charts -->
			<li class="nav-item" id="side-students"><a class="nav-link"
				href="student"> <i class="fas fa-fw fa-graduation-cap"></i> <span>Manage
						Students</span>
			</a></li>


			<!-- Divider -->
			<hr class="sidebar-divider" />

			<sec:authorize access="hasRole('ADMIN')">

				<!-- Heading -->
				<div class="sidebar-heading">Course | Subjects | Semesters</div>
				<!-- Nav Item - Charts -->
				<li class="nav-item" id="side-courses"><a class="nav-link"
					href="course"> <i class="fas fa-fw fa-file-pdf"></i> <span>Manage
							courses</span></a></li>
				<li class="nav-item" id="side-subjects"><a class="nav-link"
					href="subjects"> <i class="fas fa-fw fa-book"></i> <span>Manage
							subjects</span>
				</a></li>
				<li class="nav-item" id="side-semester"><a class="nav-link"
					href="semester"> <i class="fas fa-fw fa-strikethrough"></i> <span>Manage
							semester</span>
				</a></li>
				<li class="nav-item" id="side-batch"><a class="nav-link"
					href="batch"> <i class="fas fa-fw fa-clock"></i> <span>Manage
							Batch</span>
				</a></li>

				<!-- Divider -->
				<hr class="sidebar-divider" />


				<!-- Heading -->
				<div class="sidebar-heading">Course Subjects Semesters Faculty
					Mapping</div>

				<li class="nav-item" id="side-manage"><a class="nav-link"
					href="mapping"> <i class="fas fa-fw fa-tasks"></i> <span>Mappings</span></a></li>

				<!-- Divider -->
				<hr class="sidebar-divider" />

			</sec:authorize>


			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

			<!-- Sidebar Message -->
		</ul>
	</sec:authorize>
</sec:authorize>
<!-- End of Sidebar -->