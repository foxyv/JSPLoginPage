<%-- 
    Document   : ActivityIndicatorTest
    Created on : Sep 25, 2012, 2:15:57 PM
    Author     : Sweord
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="spin.js"></script>
    </head>
    <body>
        <p id="foo">
            
        </p>
        <script type="text/javascript">
            var opts = {
                lines: 5, // The number of lines to draw
                length: 7, // The length of each line
                width: 4, // The line thickness
                radius: 5, // The radius of the inner circle
                corners: 1, // Corner roundness (0..1)
                rotate: 0, // The rotation offset
                color: '#000', // #rgb or #rrggbb
                speed: 1, // Rounds per second
                trail: 60, // Afterglow percentage
                shadow: false, // Whether to render a shadow
                hwaccel: false, // Whether to use hardware acceleration
                className: 'spinner', // The CSS class to assign to the spinner
                zIndex: 2e9, // The z-index (defaults to 2000000000)
                top: 'auto', // Top position relative to parent in px
                left: 'auto' // Left position relative to parent in px
            };
            var target = document.getElementById('foo');
            var spinner = new Spinner(opts).spin(target);
        </script>
    </body>
</html>
