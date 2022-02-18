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


<div style="display:flex;">
	<div style="margin:50px;" >
	
		<h1>Create New Category</h1>
		<form:form action="/newCategory" method="post" modelAttribute="category">
					<p>
						<form:label path="name">Category Name</form:label>
						<form:errors path="name" class="text-danger"/>
						<form:input type="text" path="name" />
						<input type=submit value="Submit"/>
					</p>
		</form:form>
	
	
	
	</div>
	
	<div style="margin:50px;" >
	
		<h1>Create New Product</h1>
		<form:form action="/newProduct" method="post" modelAttribute="product">
					<p>
						<form:label path="name">Name</form:label>
						<form:errors path="name" class="text-danger"/>
						<form:input type="text" path="name" />
					</p>
					<p>
						<form:label path="description">Description</form:label>
						<form:errors path="description" class="text-danger"/>
						<form:input type="text" path="description" />
					</p>
					<p>
						<form:label path="price">Price</form:label>
						<form:errors path="price" class="text-danger"/>
						<form:input type="number" path="price" />
					</p>
						
					<p>
						<input type=submit value="Submit"/>
					</p>
					
					
		</form:form>
	
	
	
	</div>
	
</div>



</body>
</html>