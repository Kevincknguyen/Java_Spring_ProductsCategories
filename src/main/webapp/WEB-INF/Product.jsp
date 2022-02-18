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

<h2><c:out value="${product.name }" /></h2>

<div style="display:flex;">
	<div style="flex:1;">
		<h3>Current Categories</h3>
		<c:forEach var="category" items="${thisCategories}">
			<p><c:out value="${category.name }" /></p>
		</c:forEach>
	</div>
	<div style="flex:1;">
	<h3>Add a category</h3>
		<form:form action="/addCategoryToProduct/${product.id}" method="post" modelAttribute="product">
		<input type="hidden" name="_method" value="put">
		
			<form:label path="categories">Select Category</form:label>
			<form:select path="categories" >
				<form:option value=" " path="categories" >Choose an option</form:option>
				<c:forEach var="category" items="${notCategories}">

					<form:option value="${category.id}" path="categories">
						<c:out value="${category.name}"></c:out>
					</form:option>
					
				</c:forEach>
			</form:select>
			
			<input type=submit value="Submit"/>
		</form:form>
	
	</div>
</div>



</body>
</html>