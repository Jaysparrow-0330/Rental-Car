<%-- 
    Document   : history
    Created on : Mar 7, 2021, 2:35:13 AM
    Author     : jack3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>NURA CAR - History</title>
        <meta name="description" content="">
        <!--
        
        Template 2079 Garage
        
        http://www.tooplate.com/view/2079-garage
        
        -->
        <meta name="author" content="Web Domus Italia">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="source/bootstrap-3.3.6-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="source/font-awesome-4.5.0/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="style/slider.css">
        <link rel="stylesheet" type="text/css" href="style/mystyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/fontawesome.min.css">
    </head>
    <body>
        <!-- Header -->
        <c:set var="user" value="${sessionScope.LOGIN_USER}"></c:set>
            <div class="allcontain">
                <div class="header">


                    <ul style="font-size: 20px" class="logreg">

                        <li><a href="#">${user.name}</span></a></li>
                    <li><a href="LogoutController"><span class="register">Logout</span></a></li>

                </ul>
            </div>
            <!-- Navbar Up -->
            <nav class="topnavbar navbar-default topnav">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed toggle-costume" data-toggle="collapse" data-target="#upmenu" aria-expanded="false">
                            <span class="sr-only"> Toggle navigaion</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand logo" href="#" ><img style="width: 200px;height: 200px" src="http://cm1.narvii.com/6299/f3c7f9a219f319b3e1f0cab29996593b0f1b2c76_00.jpg" alt="logo"></a>
                    </div>	 
                </div>
                <div  class="collapse navbar-collapse" id="upmenu">
                    <ul  class="nav navbar-nav" id="navbarontop">
                        <li class="active"><a href="SearchPageController">HOME</a> </li>
                        <li class="active"><a href="HistoryController">HISTORY</a> </li>
                        <li class="active"><a href="#">FEEDBACK</a> </li>


                        <button><span class="postnewcar"><a href="ToCartController">CART</a></span></button>
                    </ul>
                </div>
            </nav>
        </div>
        <!--_______________________________________ Carousel__________________________________ -->
        <div class="allcontain">
            <div id="carousel-up" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner " role="listbox">
                    <div class="item active">
                        <img src="https://uhdwallpapers.org/uploads/converted/18/04/23/rolls-royce-wraith-luminary-collection-1920x1080_65698-mm-90.jpg" alt="oldcar">

                    </div>
                    <div class="item">
                        <img src="https://wallpapercave.com/wp/mueu0Ld.jpg" alt="porche">

                    </div>
                    <div class="item">
                        <img src="https://i.pinimg.com/originals/b9/cd/ee/b9cdee0908e4e84747236b92c253d032.jpg" alt="benz">

                    </div>
                </div>
                <nav class="navbar navbar-default midle-nav">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed textcostume" data-toggle="collapse" data-target="#navbarmidle" aria-expanded="false">
                            <h1>SEARCH TEXT</h1>
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="navbarmidle">
                        <div class="searchtxt">

                        </div>
                        <c:set var="error" value="${requestScope.ERROR}"></c:set>
                        <c:if test="${error != null}">
                            <center style="color: red; font-size: 20px">
                                ${error.line1}<br>
                                ${error.line2}<br>
                                ${error.line3}<br>
                            </center>
                        </c:if>
                        <c:set var="currPage" value="${requestScope.PAGE_NUM}"></c:set>
                        <c:set var="list" value="${requestScope.LIST}"></c:set>
                            <form action="HistoryController"> 

                                <h1>SEARCH CREATE DATE</h1>
                                <div class="form-group">
                                    <input type="date" placeholder="Date" name="date" value="${param.date}">
                                <button type="submit" name="btn" value="date" class="searchbutton"><span class="glyphicon glyphicon-search ">SEARCH</span></button>
                            </div>



                            <div class="form-group">
                                <h1>SEARCH CAR NAME</h1>
                                <input type="text" placeholder="Name" name="name" value="${param.name}">
                                <button type="submit" name="btn" value="name" class="searchbutton"><span class="glyphicon glyphicon-search ">SEARCH</span></button>
                            </div>


                        </form>







                    </div>
                </nav>
            </div>
        </div>
        <!-- ____________________Featured Section ______________________________--> 
        <div class="allcontain">
            <div class="feturedsection">
                <h1 class="text-center"><span class="bdots">&bullet;</span>Y O U R<span class="carstxt">H I S T O R Y</span>&bullet;</h1>
            </div>
            <div class="feturedimage">
                <div class="row firstrow">
                    <c:forEach var="order" items="${list}">

                        <div class="col-lg-6 costumcol colborder1">
                            <div class="row costumrow">

                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 txt1colon ">
                                    <div class="featurecontant">
                                        <h1>Name : ${user.name}</h1>
                                        <h1>Order Date : ${order.orderDate}</h1>
                                        <h1>Order ID : ${order.orderID}</h1>
                                        <h1><c:if test="${order.discountPrice == 0}"> 
                                                Total Price: ${order.totalPrice}
                                            </c:if>
                                            <c:if test="${order.discountPrice != 0}"> 
                                                <del>Total Price: ${order.totalPrice}</del></br> 
                                                Discount Price: ${order.discountPrice}
                                            </c:if></h1>


                                        <button id="btnRM" ><a href="HistoryDetailController?orderID=${order.orderID}">VIEW DETAIL</a></button>
                                        <button id="btnRM"> <a href="DeleteOrderHistoryController?orderID=${order.orderID}&pageNum=${pageNum}&date=${param.date}&name=${param.name}"  onclick="return confirm('are you sure?')">Delete Order</a></button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </div>
            <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}"></c:set>
                <center>
                <c:if test="${totalPage > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${totalPage}" var="pageNum">
                                <li class="page-item <c:if test="${currPage == pageNum}">disabled</c:if>"><a class="page-link" href="HistoryController?pageNum=${pageNum}&date=${param.date}&name=${param.name}">${pageNum}</a>
                                    </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </center>
            <!-- ________________________LATEST CARS SECTION _______________________-->

            <!-- _______________________________News Letter ____________________-->

            <!-- ______________________________________________________Bottom Menu ______________________________-->
            <div class="bottommenu">



                <div class="footer">
                    <div class="copyright">
                        &copy; Copy right 2016 | <a href="#">Privacy </a>| <a href="#">Policy</a>
                    </div>
                    <div class="atisda">
                        Designed by <a href="http://www.webdomus.net/">Web Domus Italia - Web Agency </a> 
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
        <script type="text/javascript" src="source/js/isotope.js"></script>
        <script type="text/javascript" src="source/js/myscript.js"></script> 
        <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
        <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
    </body>
</html>
