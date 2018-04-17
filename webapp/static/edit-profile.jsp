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
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300i" rel="stylesheet">
        <!-- STYLE -->
        <link rel="stylesheet" href="${ctx}/static/css/style.css">
        <!-- Awsome ICON -->
        <link rel="stylesheet" href="${ctx}/static/css/fontawesome-all.css">
        <!-- Script -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <!-- Ajax -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Privew Image Upload -->
        <script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="${ctx}/static/js/bootstrap.js"></script>
    </head>

    <body>

        <jsp:include page="/static/partials/header.jsp" />


        <div class="container bodyContainer" style="max-width:1000px;">
            <div class="row" style="margin-top:20px;">
                <div class="col-3 side_bar_menu">
                    <jsp:include page="/static/partials/side_bar_menu.jsp" >
                        <jsp:param name="menu_id" value="2" />
                    </jsp:include>
                </div>
                <div class="col-9" style="padding-left:0px;">
                    <div class="" id="body">
                        <c:if test="${not empty success}">
                            <div class="alert alert-success" role="alert">
                                ${ success.message }
                            </div>
                            <c:remove var="success" scope="session" />
                        </c:if>
                        <form action = "${ctx}/user/edit-profile/submit" method = "POST" enctype = "multipart/form-data">
                            <div class="row profile_header">
                                <div class="col">
                                    <div class="row">
                                        <div class="col" style="display:flex;">
                                            <div class="profile_image">
                                                <img src="/resources/profile_picture/${empty sessionScope.user.profile_picture ? 'default.png' : sessionScope.user.profile_picture}" alt="">
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col">

                                            <!-- UPLOAD IMAGE -->
                                            <div class="" id="upload_image">
                                                <div class=" fileContainer" style="margin:0;">
                                                    <input type="file" name="profile_picture" id="imageUpload-1" class="image-upload" >
                                                    <label for="imageUpload-1" class="white-button"><span><i class="far fa-image"></i> เพิ่มรูปภาพ</span></label>
                                                </div>


                                                <div id="imageUpload-1-preview" class="image-preview">
                                                    <div class="image-preview-hover" >
                                                        <button type="button" class="remove-image-privew"><i class="fas fa-times"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END UPLOAD IMAGE -->

                                            <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $("#imageUpload-1").change(function () {
                                                        $.readURL(this);
                                                    });

                                                });
                                            </script>
                                        </div>
                                    </div>
                                </div>

                            </div>




                            <div id="edit_profile">
                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Username</h6>
                                        </li>
                                        <li>
                                            <input type="text" disabled="true" placeholder="${sessionScope.user.username}">
                                        </li>
                                    </ul>
                                </div>

                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Password</h6>
                                        </li>
                                        <li>
                                            <input type="password" name="password">
                                        </li>
                                    </ul>
                                </div>


                            </div>
                            <div class="separator">

                            </div>

                            <div id="edit_profile">
                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>First Name</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="firstname" value="${sessionScope.user.firstname}" required>
                                        </li>
                                    </ul>
                                </div>

                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Last Name</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="lastname" value="${sessionScope.user.lastname}" required>
                                        </li>
                                    </ul>
                                </div>

                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Sex</h6>
                                        </li>

                                        <li>
                                            <select name="sex">
                                                <option value="M" <c:if test="${sessionScope.user.sex == 'M'}">selected</c:if>>Male</option>
                                                <option value="F" <c:if test="${sessionScope.user.sex == 'F'}">selected</c:if>>Female</option>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="edit_profile_layout">
                                        <ul>
                                            <li>
                                                <h6>Phone</h6>
                                            </li>
                                            <li>
                                                <input type="text" name="phone" value="${sessionScope.user.phone}">
                                        </li>
                                    </ul>
                                </div>



                            </div>


                            <div class="separator"></div>

                            <div id="edit_profile">
                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>E-mail</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="email" value="${sessionScope.user.email}" required>
                                        </li>
                                    </ul>
                                </div>

                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Facebook</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="facebook_id" value="${sessionScope.user.facebook_id}">
                                            <p>https://www.facebook.com/<b>pleng.prongpanot</b></p>
                                        </li>
                                    </ul>
                                </div>


                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>Twiter</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="twitter_id" value="${sessionScope.user.twitter_id}">

                                            <p>https://twitter.com/<b>PlengProngpanot</b></p>
                                        </li>
                                    </ul>
                                </div>

                                <div class="edit_profile_layout">
                                    <ul>
                                        <li>
                                            <h6>LINE ID</h6>
                                        </li>
                                        <li>
                                            <input type="text" name="line_id" value="${sessionScope.user.line_id}">
                                            <!--                          <p>bnk48official</p>-->
                                        </li>
                                    </ul>
                                </div>

                            </div>

                            <div class="separator"></div>
                            <div id="edit_profile">
                                <div class="edit_profile_layout checkbox_edit_profile_stlye">
                                    <ul><li>
                                            <h6>ช่องทางการชำระเงิน</h6>
                                        </li>
                                        <c:forEach var="payment" items="${payments}">
                                            <li>
                                                <input id="delivery_id_${payment.id}" type="checkbox" name="payment" value="${payment.id}" 
                                                       <c:forEach var="paymentSelected" items="${userPaymentSelected}">
                                                           <c:if test="${paymentSelected.id == payment.id}">checked</c:if>
                                                       </c:forEach>
                                                 >
                                                <label for="delivery_id_${payment.id}">${payment.name}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>


                            </div>

                            <div class="separator"></div>

                            <div id="edit_profile">
                                <div class="edit_profile_layout">
                                    <input type="submit" value="บันทึกข้อมูล" class="pink-button edit_profile_button">
                                </div></div>

                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="overlay"></div>

    <!-- JAVASCRIPT -->
    <script type="text/javascript" src="${ctx}/static/js/script.js"></script>

</body>

</html>
