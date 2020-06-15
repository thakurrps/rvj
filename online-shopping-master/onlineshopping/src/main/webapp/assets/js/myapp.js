$(function() {

	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// dataTable code
	var $table = $('#productListTable');

	// execute this table when we have this table
	if ($table.length) {
		var jsonUrl = '';
		// console.log("inside table");
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [ [ 2, 3, 5, -1 ], [ '2', '3', '5', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImages"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red;">Out of Stock!</span>';
									}
									return data;
								}

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';

									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success" disabled><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}
									return str;
								}
							} ]
				});
	}

	// dissmissing alert box after some sec
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 2000)
	}
	
	
	// ---------------------------------
	// DataTable for Admin
	// ---------------------------------

	var $adminProductsTable = $('#adminProductsTable');

	// execute this table when we have this table
	if ($adminProductsTable.length) {
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10', '30', '50', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg" style="height:100px;width:100px"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red;">Out of Stock!</span>';
									}
									return data;
								}

							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},

							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '"/>';
									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '"/>';
									}

									str += '<div class="slider"></div></label>';
									return str;

								}

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'+window.contextRoot+'/manage/'
											+ data
											+ '/product" class="btn btn-warning">'

									str += '<span class="glyphicon glyphicon-pencil"></span></a>'
									return str;

								}
							} ],
							
					    initComplete: function(){
						var api = this.api();
						api.$('.switch input[type="checkbox"]')
						.on(
								'change',
								function() {
									var checkbox = $(this);
									var checked = checkbox.prop('checked');
									var value = checkbox.prop('value');
									var dMsg = (checked) ? 'Do you want to activate the product?'
											: 'Do you want to deactivate the product?';
									

									bootbox
											.confirm({
												size : 'medium',
												title : 'Confermation call...!',
												message : dMsg,
												callback : function(confirmed) {
													if (confirmed) {
														
													var activationUrl = window.contextRoot +'/manage/product/' + value + '/activation';
													$.post(activationUrl, function(data){
														bootbox
														.alert({
															size : 'medium',
															title : 'Information',
															message : data
													})
														

																});
													} else {
														checkbox.prop('checked', !checked);
													}
												}
											});
								});

					}
				});
	}

	// --------------validation of category-------------------

	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		
		$categoryForm.validate({
			 rules:{
				 name:{
					 required: {
					        depends:function(){
					            $(this).val($.trim($(this).val()));
					            return true;
					        }
					    },
					 minlength:2
				 },
				 description:{
					 required: {
					        depends:function(){
					            $(this).val($.trim($(this).val()));
					            return true;
					        }
					    }
				 }
			 },
			 messages:{
				 name:{
					 required:"Please add the category name",
					 minlength:"Category name should not be less than 2 character"
				 },
				 description:{
					 required:"Please enter the description about category!"
				 }
			 },
			 errorElement:'em',
			 errorPlacement:function(error,element){
				 //add the class of element tag (em)
				 error.addClass('help-block');
				 //add the error after the input element
				 error.insertAfter(element);
			 }
		});
	}
//-----------------------------------------
	
});
