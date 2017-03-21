<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    HttpSession sesion = request.getSession(false);
 if (!sesion.getId().equals(sesion.getAttribute("idsession"))) {
 	response.sendRedirect("Inec.html");
}
%>
<html>
    <head>
        <meta name="gwt:property" content="locale=es_PE">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" language="javascript" src="Inec/Inec.nocache.js"></script>
        <title>Inec Administracion</title>
        <link type="text/css" rel="stylesheet" href="Inec.css">
    </head>
    <body>
        <!-- OPTIONAL: include this if you want history support -->
        <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>

        <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
        <noscript>
        <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
            Your web browser must have JavaScript enabled
            in order for this application to display correctly.
        </div>
        </noscript>        
    </body>
</html>