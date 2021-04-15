<%-- 
    Document   : VerifyPage
    Created on : Mar 5, 2021, 8:33:29 PM
    Author     : jack3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><link type="text/css" rel="stylesheet" id="dark-mode-custom-link"><link type="text/css" rel="stylesheet" id="dark-mode-general-link"><style lang="en" type="text/css" id="dark-mode-custom-style"></style><style lang="en" type="text/css" id="dark-mode-native-style"></style><head>

        <meta charset="UTF-8">

        <link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
        <meta name="apple-mobile-web-app-title" content="CodePen">

        <link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">

        <link rel="mask-icon" type="" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">


        <title>NURA CAR - Verify Account</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" type="text/css" href="style/login.css">





        <script>
            window.console = window.console || function (t) {};
        </script>



        <script>
            if (document.location.search.match(/type=embed/gi)) {
                window.parent.postMessage("resize", "*");
            }
        </script>


    </head>

    <body translate="no" data-new-gr-c-s-check-loaded="14.997.0" data-gr-ext-installed="">
    <c:set var="error" value="${requestScope.ERROR}"></c:set>
    <form class="login-form" action="VerifyController" method="POST"> 
        <h1> <img src="http://cm1.narvii.com/6299/f3c7f9a219f319b3e1f0cab29996593b0f1b2c76_00.jpg" width="300px" height="300px"></h1>
        <p class="login-text">
            <span class="fa-stack fa-lg">
                <i class="fa fa-circle fa-stack-2x"></i>
                <i class="fa fa-lock fa-stack-1x"></i>
            </span>
        </p>
        <input type="text" name="email" value="${param.email}" class="login-username" autofocus="true" required="true" placeholder="Your Email">
        <input type="text"  name="code" value="${param.code}" class="login-username" autofocus="true" required="true" placeholder="Your Code">

        <input type="submit" name="btn" value="Verify" class="login-submit">
        <input type="submit" name="btn" value="Resend" class="login-submit">
        <div style="color: red">
            ${error.line1}
        </div>
    </form>


    <a href="SearchPageController" class="login-forgot-pass">Back to Main Page</a>

    <div class="underlay-photo"></div>
    <div class="underlay-black"></div>
