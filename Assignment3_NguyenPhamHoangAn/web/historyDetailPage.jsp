<%-- 
    Document   : historyDetailPage
    Created on : Mar 7, 2021, 2:22:13 PM
    Author     : jack3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="style/cart.css">
        <title>NURA CAR - Cart</title>

    </head>

    <body>
        <c:set var="user" value="${sessionScope.LOGIN_USER}"></c:set>
            <main>
            <c:set var="list" value="${sessionScope.CART.list}"></c:set>
            <c:set var="order" value="${requestScope.ORDER}"></c:set>
            <c:set var="list" value="${requestScope.LIST}"></c:set>


                <div class="basket">
                    <div class="basket-module">
                        <h3>This is your Order Detail : ${user.name}</h3>


                </div>
                <div class="basket-labels">
                    <ul>
                        <li class="item item-heading">Item</li>
                        <li class="price">Price</li>
                        <li class="quantity">Quantity</li>
                        <li class="quantity">Start Day</li>
                        <li class="quantity">End Day</li>
                        <li class="quantity">Total Day</li>
                        <li class="subtotal">Subtotal</li>
                    </ul>
                </div>
                <c:forEach items="${list}" var="cart" varStatus="counter">

                    <div class="basket-product">
                        <div class="item">
                            <div class="product-image">
                                <img src="${cart.link}" alt="Placholder Image 2" class="product-frame">
                            </div>

                        </div>
                        <div class="price">${cart.price}</div>
                        <div class="quantity">
                            ${cart.quantity}
                        </div>
                        <div class= "date">${cart.startDate}</div>
                        <div class="date">${cart.endDate}</div>   
                        <div class="date">${cart.quantityDay}</div>   
                        <div class="subtotal">${cart.priceDay}</div>






                    </div>
                </c:forEach>

                </div>    
                <aside>
                    <div class="summary">
                        <div class="summary-total-items"><span class="total-items"></span> Items in your Bag</div>
                        <div class="summary-subtotal">
                            <div class="subtotal-title">Subtotal</div>
                            <div class="subtotal-value final-value" id="basket-subtotal">${order.totalPrice}</div>

                        </div>

                        <div class="summary-total">
                            <div class="total-title">Total</div>
                            <div class="total-value final-value" id="basket-total"> 
                                <c:if test="${order.discountPrice == 0}"> 
                                Total Price: ${order.totalPrice}
                            </c:if>
                            <c:if test="${order.discountPrice != 0}"> 
                                <del> ${order.totalPrice}</del></br> 
                                ${order.discountPrice}
                            </c:if>
                        </div>
                    </div>

                </div>
            </aside> 
   

    </main>

    <c:set var="error" value="${requestScope.ERROR}"></c:set>
    <c:if test="${error != null}">
    <center style="color: red; font-size: 20px">
        ${error.line1}</br>
    </center>
</c:if>
<c:if test="${error == null}">
    <c:if test="${empty list}"><center><h1 style="color: red; font-size: 50px">EMPTY CART</h1></center></c:if>
    </c:if>
<center><button><a style="font-size: 30px; background-color: #666666; color: white; text-align: center" href="HistoryController" class="login-forgot-pass">Back to History Page</a></button></center>
</body>

</html>
