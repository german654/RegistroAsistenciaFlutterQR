<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">X</button>
			</div>
			<div class="modal-body">Select "Logout" below if you are ready
				to end your current session.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
				<form action="/logout" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-primary" type="submit">Logout</button>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Faculty Model -->
<div class="modal fade" id="editFaculty" tabindex="-1" role="dialog"
	aria-labelledby="editFaculty" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Faculty</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editFacultyBody">
				<form class="user" method="get" id="editFacultyForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updFacEnroll" name="updFacEnroll"
							placeholder="Enter faculty Enrollment Id" required />
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updFacEmail" name="updFacEmail"
							placeholder="Enter faculty Email Id" required />
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updFacName" name="updFacName"
							placeholder="Enter Faculty Name" required />
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							id=updFacPass name="updFacPass" placeholder="Temporary Password" />

					</div>
					<input type="hidden" id="hdnFid" />
					<div class="mt-2 mb-2" id="updFacRes">
						<!-- Response -->
					</div>

					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Faculty</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Course Model -->
<div class="modal fade" id="editCourse" tabindex="-1" role="dialog"
	aria-labelledby="editCourse" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Course</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editCourseBody">
				<form class="user" id="editCourseForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updCourse" name="updCourse" placeholder="Enter Course Name"
							required />
					</div>
					<input type="hidden" id="hdnCid" />
					<div class="mt-2 mb-2" id="updCourseRes">
						<!-- Response -->
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Course</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Subject Model -->
<div class="modal fade" id="editSubject" tabindex="-1" role="dialog"
	aria-labelledby="editSubject" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Subject</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editSubjectBody">
				<form class="user" id="editSubjectForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updSubject" name="updSubject"
							placeholder="Enter Subject Name" required />
					</div>
					<input type="hidden" id="hdnSid" />
					<div class="mt-2 mb-2" id="updSubjectRes">
						<!-- Response -->
					</div>

					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Subject</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Semester Model -->
<div class="modal fade" id="editSem" tabindex="-1" role="dialog"
	aria-labelledby="editSem" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<h5 class="modal-title" id="exampleModalLabel">Update Semester</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editSemBody">
				<form class="user" id="editSemForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"

							id="updSem" name="updSem" placeholder="Enter Sem Name" required />
					</div>
					<input type="hidden" id="hdnSemid" />
					<div class="mt-2 mb-2" id="updSemRes">
						<!-- Response -->
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Semester</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Batch Model -->
<div class="modal fade" id="editBatch" tabindex="-1" role="dialog"
	aria-labelledby="editBatch" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Course</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editBatchBody">
				<form class="user" id="editBatchForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updBatch" name="updBatch" placeholder="Enter Batch Name"
							required />
					</div>
					<input type="hidden" id="hdnBid" />
					<div class="mt-2 mb-2" id="updBatchRes">
						<!-- Response -->
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Course</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Mapping Model -->
<div class="modal fade" id="editMapping" tabindex="-1" role="dialog"
	aria-labelledby="editMapping" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Mapping</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="editMappingBody">
				<form class="user" id="editMappingForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="updMapping" name="updMapping" placeholder="Enter Sem Name"
							required />
					</div>
					<input type="hidden" id="hdnMappingid" />
					<div class="mt-2 mb-2" id="updMappingRes">
						<!-- Response -->
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary btn-user btn-block">Update
						Mapping</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
