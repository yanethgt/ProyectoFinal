<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Servlet</title>
    </head>
    <body>
        <h1>Sample Servlet GET</h1>
        Call the <a href="${pageContext.request.contextPath}/sampleServlet"/>servlet</a>.

        <h1>Sample Servlet POST</h1>
        Call the <form action="${pageContext.request.contextPath}/sampleServlet" method="post"/><input type="submit" value="servlet"/></form>.
    </body>
</html>