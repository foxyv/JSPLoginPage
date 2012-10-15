/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getXMLHttp()
{
    //Code from W3C for multi-browser support of AJAX
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        return new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function createSpinner(target,aColor){
    var opts = {
        lines: 5, // The number of lines to draw
        length: 7, // The length of each line
        width: 4, // The line thickness
        radius: 5, // The radius of the inner circle
        corners: 1, // Corner roundness (0..1)
        rotate: 0, // The rotation offset
        color: aColor, // #rgb or #rrggbb
        speed: 1, // Rounds per second
        trail: 60, // Afterglow percentage
        shadow: false, // Whether to render a shadow
        hwaccel: false, // Whether to use hardware acceleration
        className: 'spinner', // The CSS class to assign to the spinner
        zIndex: 2e9, // The z-index (defaults to 2000000000)
        top: 'auto', // Top position relative to parent in px
        left: 'auto' // Left position relative to parent in px
    };
    return new Spinner(opts).spin(target);
}

function submitLogin()
{
    //Get the values from the text field in the document
    username = document.getElementById('user').value;
    password = document.getElementById('pass').value;
    
    //Create a new XMLHttpRequest
    var LoginXMLRequestAndHttpResponse = getXMLHttp();
    
    //Initialize the spinner to show that something is happening.
    var target = document.getElementById('LoginStatus');
    var loginspinner = createSpinner(target,"#900000");
    
    //Add a listener for when the ready state of the AJAX session changes
    LoginXMLRequestAndHttpResponse.onreadystatechange=function()
    {        
        if(LoginXMLRequestAndHttpResponse.readyState == 4 && LoginXMLRequestAndHttpResponse.status==200)
        {
            //Use the response to populate the LoginResult paragraph
            document.getElementById("LoginResult").innerHTML=LoginXMLRequestAndHttpResponse.responseText;
            
            //Stop the spinner.
            loginspinner.stop();
        }
    }    
    
    //Open the connection to the appropriate JSP using POST send the necessary information
    LoginXMLRequestAndHttpResponse.open("POST","AJAXLoginSession.jsp",true);
    LoginXMLRequestAndHttpResponse.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    LoginXMLRequestAndHttpResponse.send("username=" + username + "&password=" + password);
   
}

function submitUser()
{
    //Get the appropriate text fields from the document
    username = document.getElementById('user').value;
    password = document.getElementById('pass').value;
    
    //Get an XMLHttpObject from the browser to power our AJAX awesomeness
    var LoginXMLRequestAndHttpResponse = getXMLHttp();
    
    
    //Initialize the spinner 
    var target = document.getElementById('UserCreateStatus');
    var createspinner = createSpinner(target,"#000090");
    
    //Assign a function to be called when the XMLHttp ready state changes.
    LoginXMLRequestAndHttpResponse.onreadystatechange=function()
    {
        
        if(LoginXMLRequestAndHttpResponse.readyState == 4 && LoginXMLRequestAndHttpResponse.status==200)
        {
            document.getElementById("UserCreateResult").innerHTML=LoginXMLRequestAndHttpResponse.responseText;
            createspinner.stop();
        }
    }
    
    //Open the connection to the appropriate JSP using POST send the necessary information
    LoginXMLRequestAndHttpResponse.open("POST","AJAXCreateUserEntry.jsp",true);
    LoginXMLRequestAndHttpResponse.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    LoginXMLRequestAndHttpResponse.send("username=" + username + "&password=" + password);
}

function userInfoForm(username){
    //Get the appropriate text fields from the document
    
    age = document.getElementById('age').value;
    email = document.getElementById('email').value;
    occupation = document.getElementById('occupation').value;
    firstname = document.getElementById('firstname').value;
    
    //Get an XMLHttpObject from the browser to power our AJAX awesomeness
    var LoginXMLRequestAndHttpResponse = getXMLHttp();
    
    
    //Initialize the spinner 
    var target = document.getElementById('spinner');
    var createspinner = createSpinner(target,"#009000");
    
    //Assign a function to be called when the XMLHttp ready state changes.
    LoginXMLRequestAndHttpResponse.onreadystatechange=function()
    {
        
        if(LoginXMLRequestAndHttpResponse.readyState == 4 && LoginXMLRequestAndHttpResponse.status==200)
        {
            document.getElementById("SubmitResult").innerHTML=LoginXMLRequestAndHttpResponse.responseText;
            createspinner.stop();
        }
    }
    
    
    //Open the connection to the appropriate JSP using POST send the necessary information
    LoginXMLRequestAndHttpResponse.open("POST","AJAXUpdateUserInfo.jsp",true);
    LoginXMLRequestAndHttpResponse.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    LoginXMLRequestAndHttpResponse.send("username=" + username + "&age=" + age + "&email=" + email + "&occupation=" + occupation + "&firstname=" + firstname);
}