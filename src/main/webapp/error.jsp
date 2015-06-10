<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Application Error</h1>
        <p><c:out value="${requestScope.commandResult.errorCode}"/>: <c:out value="${requestScope.commandResult.errorMessage}"/></p>
    </body>
</html>
