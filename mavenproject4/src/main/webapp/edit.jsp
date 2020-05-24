<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit subject</title>
</head>
<body>
<h3>Edit subject</h3>
<form method="post">
<input type="hidden" value="${subject.id}" name="id" />
<label>Name</label><br>
<input name="name" value="${subject.name}" /><br><br>
<input type="submit" value="Send" />
</form>
</body>
</html>