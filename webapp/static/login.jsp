<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>MARKET48</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- BOOTSRAP -->
        <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">

        <!-- STYLE -->
        <link rel="stylesheet" href="${ctx}/static/css/style.css">
    </head>

    <body style="background-color: #fff;">

        <div class="container" style="max-width:100%;">
            <div class="row">
                <div class="col" style="padding:0px; overflow:hidden;">
                    <div class="bnk-login">
                    </div>
                </div>
                <div class="col" style="padding:0px;">
                    <div id="login">
                        <div id="login">
                            <c:if test="${ errorType == 'login' }">
                                <div class="alert alert-danger" role="alert">
                                    ${error.message}
                                </div>
                            </c:if>
                            <c:if test="${not empty sessionScope.error}">
                                <div class="alert alert-danger" role="alert">
                                    ${sessionScope.error.message}
                                </div>
                                <c:remove var="error" scope="session" />
                            </c:if>
                            <form action="${ctx}/user-auth" class="login-form" method="POST">
                                <div class="username-field-login">
                                    <h6>Username</h6>
                                    <input type="text" name="username" required>
                                    <div class="check_box_login">
                                        <input id="login_id_1" type="checkbox" name="type" value="111">
                                        <label for="login_id_1">
                                            <span>Keep Logged In</span>
                                        </label>
                                    </div>
                                </div>
                                <div class="password-field-login">
                                    <h6>Password</h6>
                                    <input name="password" type="password" required>
                                </div>
                                <div class="submit-field-login">
                                    <input type="submit" value="Sign In" class="white-button">
                                </div>
                            </form>
                        </div>

                        <div class="separator">
                        </div>


                        <div id="register">
                            <c:if test="${ errorType == 'register' }">
                                <div class="alert alert-danger" role="alert">
                                    ${error.message}
                                </div>
                            </c:if>
                            <h1>Sign Up</h1>
                            <h5>It’s free and always will be.</h5>
                            <form action="${ctx}/register" method="POST" class="register-form" >
                                <ul>
                                    <li>
                                        <h6>First Name : </h6>
                                        <input type="text" name="firstname" required>
                                    </li>

                                    <li>
                                        <h6>Last Name : </h6>
                                        <input type="text" name="lastname" required>
                                    </li>

                                    <li>
                                        <h6>Username : </h6>
                                        <input type="text" name="username" required>
                                    </li>

                                    <li>
                                        <h6>Password : </h6>
                                        <input type="password" name="password" required>
                                    </li>

                                    <li>
                                        <h6>Your Email : </h6>
                                        <input type="text" name="email" required>
                                    </li>

                                    <li>
                                        <h6>I am : </h6>
                                        <select name="sex">
                                            <option value="M">Male</option>
                                            <option value="F">Female</option>
                                        </select>
                                    </li>

                                    <li>
                                        <h6></h6>
                                        <div class="check_box_login" style="margin: 10px 0 10px 0;">
                                            <input id="login_id_2" type="checkbox" name="acceptTerms" value="true" required autofocus>
                                            <label for="login_id_2">
                                                <span>ยอมรับเงื่อนไขและข้อตกลง
                                                    <a href="#" data-toggle="modal" data-target="#myModal">อ่านข้อตกลง</a>
                                                </span>
                                            </label>
                                        </div>

                                        <!-- Modal -->
                                        <div class="custom_modal">
                                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">ข้อตกลง และข้อกำหนเในการใช้งาน</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            ...
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <input type="submit" style="margin-left:90px; width:320px;" class="pink-button" value="Sign Up">

                                    </li>
                            </form>

                            <li>
                                <form action="${ctx}/feed" method="get" class="register-form">
                                    <input type="submit" style="margin-left:90px; width:320px;" class="white-button" value="Login as guest">
                                </form>
                            </li>

                            </ul>


                        </div>

                        <div class="separator">
                        </div>

                        <div class="login-credit">
                            <span>MARKET48 is a part of project in Web Programming subject, IT@KMITL.
                                © 2018 MARKET48</span>
                        </div>

                    </div>
                </div>
            </div>




            <!-- JAVASCRIPT -->
            <script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
            <script type="text/javascript" src="${ctx}/static/js/bootstrap.js"></script>
            <!--modal-->
            <script>
                $(document).ready(function () {
                    $("#myModal").hide();
                });
            </script>
    </body>

</html>
