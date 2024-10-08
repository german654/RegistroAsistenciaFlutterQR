/* Faculty Operations */

const successRes = function(data) {
	return "<p class='text-success'>" + data + "</p>";
}

const errorRes = function(data) {
	return "<p class='text-danger'>" + data + "</p>";
}

//addFaculty
$("#addFaculty").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "faculty/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addFaculty").trigger("reset");
			}
		},
		success: function(data) {
			$("#facultyResponce").hide();
			$("#facultyResponce").html(successRes(data));
			$("#facultyResponce").fadeIn("slow");
		}
	});
});

// getFaculty Model data
$(document).on("click", ".updFac", function() {
	let fId = $(this).data("fid");
	$.ajax({
		url: "faculty/getFacultyById/" + fId,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#editFacultyBody").html("<p class='text-danger'>No Data Found</p>")
			} else {
				$("#updFacEnroll").val(data.enrollment);
				$("#updFacEmail").val(data.email);
				$("#updFacName").val(data.userName);
				$("#hdnFid").val(data.userId);
			}
		}
	});
});
// updates Faculty Data
$("#editFacultyForm").on("submit", function(e) {
	e.preventDefault();
	let fId = $("#hdnFid").val();
	$.ajax({
		url: "faculty/updateFacultyById/" + fId,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updFacRes").hide();
			$("#updFacRes").html(successRes(data));
			$("#updFacRes").fadeIn("slow");
		}
	})
});

// Delete Faculty
$(document).on("click", ".delFac", function() {
	let fid = $(this).data("fid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "faculty/deleteFacultyById/" + fid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Faculty Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Faculty");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Course Operations */
//add Course
$("#addCourse").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "course/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addCourse").trigger("reset");
			}
		},
		success: function(data) {
			$("#courseResponce").hide();
			$("#courseResponce").html(successRes(data));
			$("#courseResponce").fadeIn("slow");
		}
	});
});

//getCourseData
$(document).on("click", ".updCourse", function() {
	let cid = $(this).data("cid");
	$.ajax({
		url: "course/get/" + cid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updCourseRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updCourse").val(data.courseName);
				$("#hdnCid").val(data.courseId);
			}
		}
	});
});

//updateCourse
$("#editCourseForm").on("submit", function(e) {
	e.preventDefault();
	let cid = $("#hdnCid").val();
	$.ajax({
		url: "course/update/" + cid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updCourseRes").hide();
			$("#updCourseRes").html(successRes(data));
			$("#updCourseRes").fadeIn("slow");
		}
	});
});

//Delete Course
$(document).on("click", ".delCourse", function() {
	let cid = $(this).data("cid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "course/delete/" + cid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Course Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Course");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});


/* Subject Operations */
//add Subject
$("#addSubject").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "subjects/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addSubject").trigger("reset");
			}
		},
		success: function(data) {
			$("#subjectResponse").hide();
			$("#subjectResponse").html(successRes(data));
			$("#subjectResponse").fadeIn("slow");
		}
	});
});

//getSubjectData
$(document).on("click", ".updSubject", function() {
	let sid = $(this).data("sid");
	$.ajax({
		url: "subjects/get/" + sid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updSubjectRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updSubject").val(data.subjectName);
				$("#hdnSid").val(data.subjectId);
			}
		}
	});
});

//update Subject
$("#editSubjectForm").on("submit", function(e) {
	e.preventDefault();
	let sid = $("#hdnSid").val();
	$.ajax({
		url: "subjects/update/" + sid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updSubjectRes").hide();
			$("#updSubjectRes").html(successRes(data));
			$("#updSubjectRes").fadeIn("slow");
		}
	});
});

//Delete Subject
$(document).on("click", ".delSubject", function() {
	let sid = $(this).data("sid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "subjects/delete/" + sid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Subject Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Subject");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});


/* Semester Operations */
//add Semester
$("#addSemester").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "semester/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addSemester").trigger("reset");
			}
		},
		success: function(data) {
			$("#semesterResponse").hide();
			$("#semesterResponse").html(successRes(data));
			$("#semesterResponse").fadeIn("slow");
		}
	});
});

//getSemesterData
$(document).on("click", ".updSem", function() {
	let sid = $(this).data("sid");
	$.ajax({
		url: "semester/get/" + sid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updSemRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updSem").val(data.semesterName);
				$("#hdnSemid").val(data.semesterId);
			}
		}
	});
});

//update Semester
$("#editSemForm").on("submit", function(e) {
	e.preventDefault();
	let sid = $("#hdnSemid").val();
	$.ajax({
		url: "semester/update/" + sid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updSemRes").hide();
			$("#updSemRes").html(successRes(data));
			$("#updSemRes").fadeIn("slow");
		}
	});
});

//Delete Semester
$(document).on("click", ".delSem", function() {
	let sid = $(this).data("sid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "semester/delete/" + sid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Semester Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Semester");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Mapping Operations */
//add Mapping
$("#addMapping").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "mapping/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addMapping").trigger("reset");
			}
		},
		success: function(data) {
			$("#mappingResponse").hide();
			$("#mappingResponse").html(successRes(data));
			$("#mappingResponse").fadeIn("slow");
		}
	});
});

//Delete Mapping
$(document).on("click", ".delMapping", function() {
	let mid = $(this).data("mid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "mapping/delete/" + mid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Mapping Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Mapping");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Batch Operations */
//add Batch
$("#addBatch").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "batch/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addBatch").trigger("reset");
			}
		},
		success: function(data) {
			$("#batchResponce").hide();
			$("#batchResponce").html(successRes(data));
			$("#batchResponce").fadeIn("slow");
		}
	});
});


// getBatchData
$(document).on("click", ".updBatch", function() {
	let bid = $(this).data("bid");
	$.ajax({
		url: "batch/get/" + bid,
		type: "GET",
		success: function(data) {
			console.log(data);
			if (data == null) {
				$("#updBatchRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updBatch").val(data.batchName);
				$("#hdnBid").val(data.id);
			}
		}
	});
});

// Update Batch
$("#editBatchForm").on("submit", function(e) {
	let bid = $("#hdnBid").val();
	e.preventDefault();
	$.ajax({
		url: "batch/update/" + bid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updBatchRes").hide();
			$("#updBatchRes").html(successRes(data));
			$("#updBatchRes").fadeIn("slow");
		}
	});
});

// Delete Batch
$(document).on("click", ".delBatch", function() {
	let bid = $(this).data("bid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "batch/delete/" + bid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('Batch Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Batch");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Student Operations */
//addStudent
$("#addStudent").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "student/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addStudent").trigger("reset");
			}
		},
		success: function(data) {
			$("#studentResponse").hide();
			$("#studentResponse").html(successRes(data));
			$("#studentResponse").fadeIn("slow");
		}
	});
});

// load update Student Model
$(document).on("click", ".updStudent", function() {
	let sid = $(this).data("sid");
	$.ajax({
		url: "student/getStudent/" + sid,
		type: "GET",
		success: function(data) {
			$("#updStuId").val(data.studentId);
			$("#updStuEnroll").val(data.user.enrollment);
			$("#updUserId").val(data.user.userId);
			$("#updStuName").val(data.user.userName);
			$("#updStuEmail").val(data.user.email);
			$("#updStuDivision").val(data.studentDivision);
			$('#updStuBatch  option[value="' + data.studentBatch.id + '"]').attr("selected", true);
			$('#updStuCourse  option[value="' + data.studentCourse.courseId + '"]').attr("selected", true);
		}
	});
});


// updates Student Data
$("#editStudentForm").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "student/updateStudent",
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updStuRes").hide();
			$("#updStuRes").html(successRes(data));
			$("#updStuRes").fadeIn("slow");
		},
		error: function(data) {
			// console.log(data);
			$("#updStuRes").html(errorRes(data));
		}
	});
});

// Delete Student
$(document).on("click", ".delStudent", function() {
	let sid = $(this).data("sid");
	let csrf = $(this).data("csrf");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "student/delete/" + sid,
					type: "DELETE",
					data: { _csrf: csrf },
					success: function(data) {
						if (data == 1) {
							$.alert('student Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Student");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});