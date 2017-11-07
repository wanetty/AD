<%-- 
    Document   : index
    Created on : 31-oct-2017, 16:37:58
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>    <%-- start web service invocation --%><hr/>
    <%
    try {
	calculatorwsandtypeorg.me.Calculator_Service service = new calculatorwsandtypeorg.me.Calculator_Service();
	calculatorwsandtypeorg.me.Calculator port = service.getCalculatorPort();
	 // TODO initialize WS operation arguments here
	int i = 3;
	int j = 4;
	// TODO process result here
	int result = port.add(i, j);
	out.println("Result = "+result);
    } catch (Exception ex) {
	out.println("exception" + ex);
    }
    %>
    <%-- end web service invocation --%><hr/>
</h1>
    </body>
</html>
