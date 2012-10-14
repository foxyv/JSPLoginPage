/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function submitLogin(publicKey)
{
    username = document.getElementById('user').value;
    password = document.getElementById('pass').value;
    
    //Code from W3C for multi-browser support of AJAX
    var LoginXMLRequestAndHttpResponse;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        LoginXMLRequestAndHttpResponse=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        LoginXMLRequestAndHttpResponse=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    LoginXMLRequestAndHttpResponse.open("POST","LoginSession.jsp",true);
    LoginXMLRequestAndHttpResponse.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    LoginXMLRequestAndHttpResponse.send("username=" + username + "&password=" + password);
    
    var opts = {
        lines: 5, // The number of lines to draw
        length: 7, // The length of each line
        width: 4, // The line thickness
        radius: 5, // The radius of the inner circle
        corners: 1, // Corner roundness (0..1)
        rotate: 0, // The rotation offset
        color: '#900000', // #rgb or #rrggbb
        speed: 1, // Rounds per second
        trail: 60, // Afterglow percentage
        shadow: false, // Whether to render a shadow
        hwaccel: false, // Whether to use hardware acceleration
        className: 'spinner', // The CSS class to assign to the spinner
        zIndex: 2e9, // The z-index (defaults to 2000000000)
        top: 'auto', // Top position relative to parent in px
        left: 'auto' // Left position relative to parent in px
    };
    
    var target = document.getElementById('LoginStatus');
    var spinner = new Spinner(opts).spin(target);
    
            
    LoginXMLRequestAndHttpResponse.onreadystatechange=function()
    {
        
        if(LoginXMLRequestAndHttpResponse.readyState == 4 && LoginXMLRequestAndHttpResponse.status==200)
        {
            document.getElementById("LoginResult").innerHTML=LoginXMLRequestAndHttpResponse.responseText;
            
            spinner.stop();
        }
    }    
}