<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<c:if test="${not empty message }">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissable">
					<button class="close" type="button" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>

		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 align="center">Product Management</h3>
				</div>
				<div class="panel-body">
					<!-- Form elements -->

					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Product
								Name:</label>
							<div class="col-md-8">
								<sf:input type="text" class="form-control" id="name" path="name"
									placeholder="Product Name" />
								<sf:errors path="name" cssClass="help-block" element="em" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Brand
								Name:</label>
							<div class="col-md-8">
								<sf:input type="text" class="form-control" id="brand"
									path="brand" placeholder="Brand Name" />
								<sf:errors path="brand" cssClass="help-block" element="em" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Product
								Description:</label>
							<div class="col-md-8">
								<sf:textarea class="form-control" id="description"
									path="description" placeholder="Write a Description" rows="4"></sf:textarea>
								<sf:errors path="description" cssClass="help-block" element="em" />

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit
								Price:</label>
							<div class="col-md-8">
								<sf:input type="number" class="form-control" id="unitPrice"
									path="unitPrice" placeholder="Unit Price in &#8377; " />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>

						<%-- <div class="form-group">
							<label class="control-label col-md-4" for="brand">Brand
								Name:</label>
							<div class="col-md-8">
								<sf:input type="text" class="form-control" id="brand"
									path="brand" placeholder="Brand Name" />

							</div>
						</div> --%>

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity
								Available:</label>
							<div class="col-md-8">
								<sf:input type="number" class="form-control" id="quantity"
									path="quantity" placeholder="Quantity Available" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select
								Image:</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" class="form-control" id="file" />
								<sf:errors path="file" cssClass="help-block" element="em" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select
								Category:</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id==0}">
									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add
											Category</button>
									</div>
								</c:if>
							</div>

						</div>


						<div class="form-group">
							<div class="col-sm-offset-4 col-md-8">
								<input type="submit" class="btn btn-primary" id="submit"
									name="submit" value="Submit" />

								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
							</div>
						</div>

					</sf:form>
				</div>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr>
		</div>
		<div class="col-xs-12">
			<div style="overflow: auto">

				<table class="table table-striped table-bordered"
					id="adminProductsTable">
					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>NAME</th>
							<th>BRAND</th>
							<th>Quantity</th>
							<th>PRICE</th>
							<th>Active</th>
							<th>Edit</th>

						</tr>
					</thead>

					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>NAME</th>
							<th>BRAND</th>
							<th>Quantity</th>
							<th>PRICE</th>
							<th>Active</th>
							<th>Edit</th>

						</tr>
					</tfoot>


				</table>

			</div>
		</div>
	</div>

	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">Add new Category</h4>
				</div>
				<div class="modal-body">
					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">
						<div class="form-group">
							<lablel for="category_name" class="control-label col-md-4">Category
							Name</lablel>
							<div class="col-md-8">
							<sf:input type="text" class="form-control" path="name" id="category_name"/>
							</div>
						</div>
						
						<div class="form-group">
							
							<lablel for="category_description" class="control-label col-md-4">Description</lablel>
							<div class="col-md-8">
							<sf:textarea rows="5" col="" class="form-control" path="description" id="category_description"/>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-4" col-md-8>
							 <input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>

					</sf:form>
				</div>
			</div>
		</div>
	</div>

</div>