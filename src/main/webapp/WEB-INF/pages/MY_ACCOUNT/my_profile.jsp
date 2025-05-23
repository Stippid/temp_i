<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" align="center">
	<div class="card">
		<div class="card-header">
			<h5><i class="menu-icon fa fa-user"></i> My Profile</h5>
		</div>
		<div class="card-body card-block" style="font-weight: bold;font-size: 18px;">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-5">
							<label for="text-input">User Name</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-md-6">
							<label for="text-input">${mp.login_name}</label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-5">
							<label for="text-input">User ID</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-md-6">
							<label for="text-input">${mp.userName}</label>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${mp.army_no != null}">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-5">
							<label for="text-input">Army No</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-12 col-md-6">
							<label for="text-input">${mp.army_no}</label>
						</div>
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${mp.user_sus_no != null}">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-5">
							<label for="text-input">SUS No</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-12 col-md-6">
							<label for="text-input">${mp.user_sus_no}</label>
						</div>
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>
</div>
