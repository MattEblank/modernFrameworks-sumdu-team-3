<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subjects</title>
</head>
<body>
<h2>Subjects List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
<tr><th>ID</th><th>Name</th><th></th></tr>
<c:forEach var="subject" items="${subjects}">
 <tr><td>${subject.id}</td>
    <td>${subject.name}</td>
    <td>
    <a href='<c:url value="/edit?id=${subject.id}" />'>Edit</a> |
    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
        <input type="hidden" name="id" value="${subject.id}">
        <input type="submit" value="Delete">
    </form>
 </td></tr>
</c:forEach>
</table>
</body>
</html>