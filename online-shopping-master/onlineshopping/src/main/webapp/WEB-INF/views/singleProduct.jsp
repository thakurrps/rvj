<div class="container">

	<!-- breadcrumb -->
	<div class="row">
		<div class="col-xs-12">

			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>

			</ol>

		</div>

	</div>

	<div class="row">
		<!-- display the product image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail" id="product-image">
				<img src="${images}/${product.code}.jpg" class="img img-responsive" id="change" style=" width: 100%;
    height: auto;
    overflow: hidden;margin-top:25px;">

			</div>
			<div class="product-gallery">
				<img src="${images}/PRDPQR123WGTX.jpg"
					id="menuImg1" onclick="changeImage1()" alt="a"> 
					<img
					src="${images}/PRDB6D3555505.jpg" alt="b"
					id="menuImg2" onclick="changeImage2()" alt="c">
					 <img
					src="${images}/PRDDEF123DEFX.jpg" alt="d"
					id="menuImg3" onclick="changeImage3()">
					<img
					src="${images}/${product.code}.jpg" alt="d"
					id="menuImg4" onclick="changeImage4()">
			</div>

		</div>
		<!-- display the product description -->

		<div class="col-xs-12 col-sm-8">

			<h3>${product.name}</h3>
			<hr>

			<p>${product.description}</p>
			<hr>

			<h4>
				Price: <strong> &#8377; ${product.unitPrice}/-</strong>
			</h4>
			<hr>




			<c:choose>
				<c:when test="${product.quantity<1}">
					<h6>
						<span style="color: red;">Out of stock!</span>
					</h6>

					<a href="javascript:void(0)" class="btn btn-success" disabled><strike></strike><span
						class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike></a>
				</c:when>
				<c:otherwise>
					<h6>In Stock(${product.quantity})</h6>

					<a href="${contextRoot}/cart/add/${product.id}/product"
						class="btn btn-success"><span
						class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>
				</c:otherwise>
			</c:choose>


			<a href="${contextRoot}/show/all/products" class="btn btn-success">Back</a>
		</div>
	</div>

</div>
<!-- self coded java script -->
		<script language="javascript">
function changeImage1() {
    document.getElementById("change").src=document.getElementById("menuImg1").src;
}

function changeImage2(){
    document.getElementById("change").src=document.getElementById("menuImg2").src;
}
function changeImage3(){
    document.getElementById("change").src=document.getElementById("menuImg3").src;
}
function changeImage4(){
    document.getElementById("change").src=document.getElementById("menuImg4").src;
}

</script>
