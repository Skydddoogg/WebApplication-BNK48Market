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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <!-- Ajax -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Privew Image Upload -->
        <script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="${ctx}/static/js/bootstrap.js"></script>

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/static/partials/header.jsp" />

        <div class="container bodyContainer" style="max-width:1000px">
            <div class="row" style="margin-top:20px;">
                <div class="col-3 side_bar_menu">
                    <jsp:include page="/static/partials/side_bar_menu.jsp" >
                        <jsp:param name="menu_id" value="4" />
                    </jsp:include>
                </div>
                <div class="col-9" style="padding-left:0px;">
                    <form action="${ctx}/user/saved-post/delete" method="POST">
                        <div class="" id="body">
                        <c:if test="${not empty sessionScope.success}">
                            <div class="alert alert-success" role="alert">
                                ${ sessionScope.success.message }
                            </div>
                            <c:remove var="success" scope="session" />
                        </c:if>
                            <div class="manage_post">
                                <div class="manage_post_button">
                                    <ul>
                                        <li>
                                            <div class="delete_post_button">
                                                <input type="submit" value="ลบโพสต์" class="pink-button send_comment_button">
                                            </div>
                                        </li>

                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                            </div>

                            <div class="save_post_list">
                                <ul>

                                    <li>
                                        <div class="row">
                                            <div class="col-1 sub_save_post_list">
                                                <div class="check_box_save_post">
                                                    <input id="list_edit_header_checkbox" type="checkbox" name="type" value="111">
                                                    <label for="list_edit_header_checkbox"></label>
                                                </div>
                                            </div>
                                            <div class="col sub_save_post_list">
                                                <h5>ภาพสินค้า</h5>
                                            </div>
                                            <div class="col sub_save_post_list">
                                                <h5>ประเภท</h5>
                                            </div>
                                            <div class="col sub_save_post_list">
                                                <h5>โพสโดย</h5>
                                            </div>
                                            <div class="col sub_save_post_list">
                                                <h5>ราคา / จำนวนคงเหลือ</h5>
                                            </div>

                                        </div>
                                    </li>

                                    <c:forEach var="data" items="${allSavedPostData}">
                                        <li>
                                            <div class="row">
                                                <div class="col-1 sub_save_post_list">
                                                    <div class="check_box_save_post">
                                                        <input id="post_id_${data.post_id}" type="checkbox" name="deleteSavedPost" value="${data.post_id}">
                                                        <label for="post_id_${data.post_id}"></label>
                                                    </div>
                                                </div>

                                                <div class="col sub_save_post_list">
                                                    <div class="thumbnail_save_post">
                                                        <img src="/resources/post_thumbnail/${data.thumbnail}" alt="">
                                                    </div>
                                                </div>
                                                <div class="col sub_save_post_list">
                                                    <div class="main_type_save_post">

                                                        <c:if test="${not empty data.cat_level3}">
                                                            <h6>${data.cat_level3}</h6>
                                                        </c:if>

                                                        <c:if test="${not empty data.cat_level2}">
                                                            <h6>${data.cat_level2}</h6>
                                                        </c:if>


                                                        <c:if test="${not empty data.cat_level1}">
                                                            <h6>${data.cat_level1}</h6>
                                                        </c:if>


                                                        <c:if test="${not empty data.member_name}">
                                                            <h6>${data.member_name}</h6>
                                                        </c:if>

                                                    </div>
                                                </div>

                                                <div class="col sub_save_post_list">
                                                    <h6>
                                                        <a href="${ctx}/user/profile?id=${data.user_id}">${data.username}</a>
                                                    </h6>
                                                </div>
                                                <div class="col sub_save_post_list">
                                                    <div class="price_of_post">
                                                        <h4>฿${data.price}
                                                            <span>/ ชิ้น</span>
                                                        </h4>
                                                        <div class="amount_of_product">
                                                            คงเหลือ ${data.remaining} ชิ้น
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="contact_save_post">
                                                    <div class=" title_contact_save_post">
                                                        <h6>ติดต่อผู้ขาย</h6>
                                                    </div>
                                                    <div class="contact_icon_save_post" style="display: block;">
                                                        <ul>
                                                            <c:if test="${not empty data.facebook_id}">
                                                                <li><a href="https://www.facebook.com/${data.facebook_id}" target="_blank"><img src="${ctx}/static/images/contact/facebook.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty data.line_id}">
                                                                <li><a href="https://line.me/ti/p/~${data.line_id}" target="_blank"><img src="${ctx}/static/images/contact/line.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty data.twitter_id}">
                                                                <li><a href="https://www.twitter.com/${data.twitter_id}" target="_blank"><img src="${ctx}/static/images/contact/twitter.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty data.email}">
                                                                <li><a href="mailto:${data.email}"><img src="${ctx}/static/images/contact/email.svg" alt=""></a></li>
                                                                    </c:if>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>

                                    <div class="post_thumbnail_popup">
                                        <img src="" alt="">
                                        <button type="button" class="exit_thumbnail_popup">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </div>

                                </ul>
                            </div>
                            <script type="text/javascript">
                                $("#list_edit_header_checkbox").click(function () {
                                    $('input:checkbox').not(this).prop('checked', this.checked);
                                });
                            </script>

                        </div>
                    </form>                                    
                </div>
            </div>
        </div>

    </div>

    <div class="overlay"></div>

    <!-- JAVASCRIPT -->
    <script type="text/javascript" src="${ctx}/static/js/script.js"></script>
    <script>
                                $(".save_post_list ul li").hover(function () {
                                    $(this).find(".contact_save_post").show();

                                }, function () {
                                    $(this).find(".contact_save_post").hide();
                                });

                                    $(".contact_save_post").each(function() {
                                        $(this).css({
                                          'right':'-'+$(this).outerWidth()+'px'
                                        })
                                      });


                                console.log("rigth:" + "-" + $(".contact_save_post").outerWidth())
    </script>

</body>

</html>