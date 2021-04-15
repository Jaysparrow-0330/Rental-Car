<%-- 
    Document   : searchPage
    Created on : Mar 5, 2021, 11:17:02 PM
    Author     : jack3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>NURA CAR</title>
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
                        <li class="active"><a href="#">HOME</a> </li>
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
                            <h1>SEARCH FOR CAR</h1>
                        </div>
                        <c:set var="error" value="${requestScope.ERROR}"></c:set>
                        <c:if test="${error != null}">
                            <center style="color: red; font-size: 20px">
                                ${error.line1}<br>
                                ${error.line2}<br>
                                ${error.line3}<br>
                            </center>
                        </c:if>
                        <form class="navbar-form navbar-left searchformmargin" role="search" action="SearchController" method="POST">
                            <div class="form-group">
                                <input type="text" class="form-control searchform" name="name" value="${param.name}" placeholder="Enter Keyword">
                            </div>


                            <div class="col">
                                <c:set var="listCategory" value="${requestScope.LIST_CATEGORY}"></c:set>
                                    <select name="category">
                                        <option value=""></option>
                                    <c:forEach var="cate" items="${listCategory}">
                                        <option <c:if test="${cate.categoryID == param.category}">selected</c:if> value="${cate.categoryID}">${cate.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <select name="gogo">
                                    <option <c:if test="${param.gogo == 'go1'}">selected</c:if> value="go1">Search by Name</option>
                                    <option <c:if test="${param.gogo == 'go2'}">selected</c:if> value="go2">Search by Category</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <input type="date" placeholder="Start date" name="start" value="${param.start}" class="form-control searchform">
                            </div>
                            <div class="form-group">
                                <input type="date" placeholder="End date" name="end" value="${param.end}" class="form-control searchform">
                            </div> 
                            <div class="form-group">
                                <input type="number" class="form-control searchform" name="quant" value="${param.quant}" placeholder="Amount">
                            </div> 
                            <button type="submit" class="searchbutton"><span class="glyphicon glyphicon-search ">SEARCH</span></button>
                        </form>
                    </div>
                </nav>
            </div>
        </div>
        <!-- ____________________Featured Section ______________________________--> 
        <div class="allcontain">
            <div class="feturedsection">
                <h1 class="text-center"><span class="bdots">&bullet;</span>M E N U<span class="carstxt">C A R S</span>&bullet;</h1>
            </div>
            <div class="feturedimage">
                <div class="row firstrow">
                    <c:forEach var="car" items="${requestScope.LIST}">
                        <form action="AddToCartController" method="POST">
                            <div class="col-lg-6 costumcol colborder1">
                                <div class="row costumrow">
                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 img1colon">
                                        <img style="height: 600px" src="${car.link}" >
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 txt1colon ">
                                        <div class="featurecontant">
                                            <h1>${car.carName}</h1>
                                            <h1>${car.year}</h1>
                                            <p>${car.description} </p>
                                            <h2>Quantity : <c:if test="${car.quantity > 0}">${car.quantity}</c:if><c:if test="${car.quantity <= 0}">OUT OF STOCK</c:if></h2>

                                                    <h2>Price :${car.price} $ </h2>

                                            <br><br><br>
                                            <input type="hidden" name="carID" value="${car.carID}">
                                            <input type="hidden" name="name" value="${param.name}">
                                            <input type="hidden" name="quant" value="${param.quant}">
                                            <input type="hidden" name="category" value="${param.category}">
                                            <input type="hidden" name="start" value="${param.start}">
                                            <input type="hidden" name="end" value="${param.end}">
                                            <input type="hidden" name="gogo" value="${param.gogo}">
                                            <input type="hidden" name="pageNum" value="${currPage}">
                                            <c:if test="${car.quantity > 0}"><button id="btnRM" type="submit">ADD TO CART</button></c:if>
                                            <c:if test="${car.quantity <= 0}"><button id="btnRM">OUT OF CAR</button></c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                    </c:forEach>
                </div>
            </div>
            <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}"></c:set>
                <center>
                <c:if test="${totalPage > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${totalPage}" var="pageNum">
                                <li class="page-item <c:if test="${currPage == pageNum}">disabled</c:if>"><a class="page-link" href="SearchController?pageNum=${pageNum}&name=${param.name}&category=${param.category}&gogo=${param.gogo}&start=${param.start}&end=${param.end}&quant=${param.quant}">${pageNum}</a>
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
