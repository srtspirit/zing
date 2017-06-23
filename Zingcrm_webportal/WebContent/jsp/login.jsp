
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!--  <meta http-equiv="X-UA-Compatible" content="IE=7">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="X-UA-Compatible" content="IE=9"> -->
    <meta http-equiv="X-UA-Compatible" content="Edge">

<title>Zing CRM - Login</title>
<link type="text/css" href=<c:url value="/resources/css/login.css"/> rel="stylesheet" media="all" />
<link href='http://fonts.googleapis.com/css?family=Archivo+Black' rel='stylesheet' type='text/css'>
<!--[if IE 9]>
<style type="text/css">

#login{
    behavior:url(../resources/css/PIE.htc);
    margin:40px;
    background:#fff;
    border:5px solid #45538d;
    width:50px;
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    border-radius:5px;
    -moz-box-shadow:0 0 10px #4e707c;
    -webkit-box-shadow:0 0 10px #4e707c;
    /*box-shadow:0 0 10px #4e707c;*/
    text-align:left;
    position:relative;
    border-collapse: separate; /* For IE and earlier */
    }


#login {
    behavior:url(../resources/css/PIE.htc);
    background: none repeat scroll 0 0 #FFFFFF;
    border: 5px solid #45538D;
    border-collapse: separate;
    border-radius: 5px 5px 5px 5px;
    margin: 120px auto;
    position: relative;
    text-align: left;
    width: 500px;
}


.container {
    behavior:url(../resources/css/PIE.htc);
    position: relative;
    width: 100%;
}

.wom {
    behavior:url(../resources/css/PIE.htc);
    margin: -69px 0 9px -58px;
}

#login h1{
    behavior:url(../resources/css/PIE.htc);
    background:#32A6DC;
    color:#000;
    font-family:roboto;
    /*text-shadow:#007dab 0 1px 0;*/
    padding-left:67px;
    font-size:22px;
    padding:25px 23px;
    margin-bottom:3.2em;
    border-bottom:1px solid #007dab;
    }

.button{
    border:0;
    height:50px;
    background:url('/resources/images/loginbtn.png') no-repeat;
    width:150px;
    cursor:pointer;
    }

.button:hover{
    opacity:1;
    filter:alpha(opacity=80); /* For IE8 and earlier */
    }
    </style>
<![endif]-->
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
<script type="text/javascript" src=<c:url value="/resources/js/jquery-min.js"/>></script>
<script type="text/javascript" src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>></script>
<script type="text/javascript" >window.history.forward();function noBack() {
	window.history.forward();}</script> 
</head>
<!-- <body style="background: #e8ebff url(../resources/images/bg.png) repeat top left;"> -->
<body onpageshow="if (event.persisted) noBack();">

<table width="100%" border="0" cellspacing=0 cellpadding=0>
    <tr><td>
    <div class="headblck"></div>
    <div class="logoheadbg">
    <a href="/">
                    <span class="logo"></span>
                </a>
    </div>
    <div class="container">

        <form id="login" name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

            <h1><p class="signin">Sign-In</p></h1>

            <c:if test="${not empty error}">
                    <p class="errorblock">
 ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </p>
            </c:if>

            <div>
                <label for="login_username">Username</label>
                <input type="text" name="j_username" id="login_username" class="field" placeholder="userid" onfocus="javascript: if(this.value == 'someone@example.com'){ this.value = ''; }" onblur="javascript: if(this.value==''){this.value='someone@example.com';}" />
            </div>

            <div>
                <label for="login_password">Password</label>
             <input type="password" name="j_password" id="login_password" class="field"><br>
             <p>


                 <a href="forgotpwd">Forgot password?</a>

                 </p>
            </div>

            
            <div style="width:98%; text-align:center; margin:auto; left:0px; right:0px;"> <input type="submit" name="submit" id="submit" value="" class="button" onclick="javascript:log();"/> </div>
            <div class="submit">
                 
                 <p class="back"><label for="_spring_security_remember_me">Remember Me</label>
                <input id="remMe" name="_spring_security_remember_me" type="checkbox" checked="checked"/></p>

            </div>
            

        </form>


    </div>
    </td></tr>
    </table>

        <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script>
        <script type="text/javascript" src=<c:url value="/resources/js/rememberMe.js"/>></script>
        <script type="text/javascript">
        function log(){

            if (document.getElementById("remMe").checked) {
                toMem();
            }
            document.getElementById("login").submit();
        }

</script>

</body>
</html>
