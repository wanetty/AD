<%-- 
    Document   : index
    Created on : 31-oct-2017, 16:37:25
    Author     : Papilomavirus
--%>

<%@page import="javax.xml.ws.WebServiceRef"%>
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
        
	org.me.calculator.CalculatorWS_Service service = new org.me.calculator.CalculatorWS_Service();
	org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
	 // TODO initialize WS operation arguments here
	int i = 3;
	int j = 7;
	// TODO process result here
	int result = port.Sumar(i, j);
	out.println("Result = "+result);
    } catch (Exception ex) {
	out.println("exception" + ex);
    }
    %>
    <%-- end web service invocation --%><hr/>
</h1>
    </body>
</html>
