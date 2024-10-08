<%
String qr = (String) request.getAttribute("qr");
String duration= (String) request.getAttribute("duration");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>eAttendance - Attendance QR</title>

<jsp:include page="components/head-imports.jsp" />

</head>
<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
					<div class=" mt-5 d-flex justify-content-center align-items-center rounded">
									<div class="mr-2" style="font-weight: bold">Time Left&nbsp;:</div>
									<div class="p-2">
										<div>
											<span style="font-weight: bold" class="text-danger" id="timer"></span>
										</div>
									</div>
								</div>
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col">
									<div class="d-flex justify-content-center">
									<img alt="" src="data:image/png;base64, <%=qr%>" width="450px" height="450px">
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>

	<jsp:include page="components/js-imports.jsp" />
	
	<script>
	$(document).ready(function() {

		// Set the initial time
		let minutes = 1 * <%=duration%>;
		let seconds = 01;
		
		// Set a timeout to stop the repetition after 10 minutes (600,000 milliseconds)
		var timeoutId = setTimeout(function() {
			clearInterval(intervalId);
			redirect();
		}, 600000); // 600,000 milliseconds = 10 minutes
		
		function redirect() {
			window.location.replace("https://eattendance-zfk0.onrender.com/");
		}
		
		// Update the timer every second
		setInterval(function() {
			seconds--;
			if (seconds === 0) {
				seconds = 59;
				minutes--;
				if (minutes < 0) {
					redirect();
				}
			}

			// Format the time with leading zeros
			let formattedTime = padNumber(minutes) + ':' + padNumber(seconds);

			// Update the content of the timer div
			$('#timer').html('<i class=" fas fa-clock"></i>&nbsp;' + formattedTime);
		}, 1000);

		// Helper function to pad single-digit numbers with leading zeros
		function padNumber(num) {
			return (num < 10 ? '0' : '') + num;
		}
	});
	
</script>
</body>
</html>