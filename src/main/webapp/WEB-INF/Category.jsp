<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<h1>Product information</h1>

<h2><c:out value="${category.name }" /></h2>

<div style="display:flex;">
	<div style="flex:1;">
		<h3>Current Products in category</h3>
		<c:forEach var="product" items="${thisProducts}">
			<p><c:out value="${product.name }" /></p>
		</c:forEach>
	</div>
	<div style="flex:1;">
	<h3>Add a product</h3>
		<form:form action="/addProductToCategory/${category.id}" method="post" modelAttribute="category">
		<input type="hidden" name="_method" value="put">
		
			<form:label path="products">Select Product</form:label>
			<form:select path="products" >
				<form:option value=" " path="products" >Choose an option</form:option>
				<c:forEach var="product" items="${notProducts}">

					<form:option value="${product.id}" path="products">
						<c:out value="${product.name}"></c:out>
					</form:option>
					
				</c:forEach>
			</form:select>
			
			<input type=submit value="Submit"/>
		</form:form>
	
	</div>
</div>

</body>
</html>