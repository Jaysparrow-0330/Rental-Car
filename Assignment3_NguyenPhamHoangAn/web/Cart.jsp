 <%-- 
    Document   : Cart
    Created on : Mar 6, 2021, 2:39:53 AM
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

            <c:if test="${list != null}">
                <c:if test="${not empty list}">
                    <div class="basket">
                        <div class="basket-module">
                            <h3>This is your cart : ${user.name}</h3>
                            <c:set var="code" value="${sessionScope.CART}"></c:set>
                            <c:set var="price" value="${sessionScope.CART}"></c:set>
                                <form action="DiscountController" method="POST">
                                    <label for="promo-code">Enter a promotional code</label>

                                    <input id="promo-code" type="text" name="discount" value="${code.discountID}" class="promo-code-field">
                                <button style="margin-right: 20px" class="promo-code-cta" type="submit">Apply</button>
                                <button><a style="font-size: 30px; background-color: #666666; color: white;" href="DeleteDiscountController" class="btn btn-primary stretched-link">Remove Code</a></button>
                            </form>
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
                            <form action="TaskController" method="POST">
                                <div class="basket-product">
                                    <div class="item">
                                        <div class="product-image">
                                            <img src="${cart.value.link}" alt="Placholder Image 2" class="product-frame">
                                        </div>

                                    </div>
                                    <div class="price">${cart.value.price}</div>
                                    <div class="quantity">
                                        <input name="quant" type="number" value="${cart.value.quantity}" min="1" class="quantity-field">
                                    </div>
                                    <div class= "date">${cart.value.startDate}</div>
                                    <div class="date">${cart.value.endDate}</div>   
                                    <div class="date">${cart.value.quantityDay}</div>   
                                    <div class="subtotal">${cart.value.priceDay}</div>

                                    <div class="remove">
                                        <input type="hidden" name="carID" value="${cart.value.carID}"/>
                                        <input type="hidden" name="start" value="${cart.value.startDate}"/>
                                        <input type="hidden" name="end" value="${cart.value.endDate}"/>
                                        <button type="submit" name="btnAction" value="DeleteOrderItem" onclick="return confirm('are you sure?')">Remove</button>
                                    </div>
                                    <div class="remove">
                                        <button type="submit" name="btnAction" value="UpdateOrder">Update</button>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                    <aside>
                        <div class="summary">
                            <div class="summary-total-items"><span class="total-items"></span> Items in your Bag</div>
                            <div class="summary-subtotal">
                                <div class="subtotal-title">Subtotal</div>
                                <div class="subtotal-value final-value" id="basket-subtotal">${price.totalPrice}</div>
                                <div class="summary-promo ">
                                    <div class="promo-title">Promotion</div>
                                    <div class="promo-value final-value" id="basket-promo"><c:if test="${price.discountID != null}">
                                        <c:if test="${not empty price.discountID}">
                                            ${(price.discountPrice/price.totalPrice)*100}%
                                        </c:if></c:if></div>
                                </div>
                            </div>

                            <div class="summary-total">
                                <div class="total-title">Total</div>
                                <div class="total-value final-value" id="basket-total"> 
                                    <c:if test="${price.discountID != null}">
                                        <c:if test="${not empty price.discountID}">
                                            ${price.discountPrice}
                                        </c:if></c:if>
                                    <c:if test="${price.discountID == null}">
                                        <c:if test="${ empty price.discountID}">
                                            ${price.totalPrice}
                                        </c:if></c:if>
                                    </div>
                                </div>
                                <div class="summary-checkout">
                                    <button class="checkout-cta"><a href="ConfirmController"  onclick="return confirm('are you sure?')">Confirm Order</a></button>
                                </div>
                            </div>
                        </aside>
                </c:if>
            </c:if>
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
    <center><button><a style="font-size: 30px; background-color: #666666; color: white; text-align: center" href="SearchPageController" class="login-forgot-pass">Back to Main Page</a></button></center>
</body>

</html>
