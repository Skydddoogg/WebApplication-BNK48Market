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

        <div class="container bodyContainer" style="max-width:1000px">
            <div class="row" style="margin-top:20px;">
                <div class="col-3 side_bar_menu">
                    <jsp:include page="/static/partials/side_bar_menu.jsp" >
                        <jsp:param name="menu_id" value="3" />
                    </jsp:include>
                </div>
                <div class="col-9" style="padding-left:0px;">
                    <div class="" id="body">
                        <c:if test="${not empty sessionScope.success}">
                            <div class="alert alert-success" role="alert">
                                ${ sessionScope.success.message }
                            </div>
                            <c:remove var="success" scope="session" />
                        </c:if>
                        <form action="${ctx}/user/manage-salepost/submit" method="POST">
                            <div class="manage_post">
                                <div class="manage_post_button">
                                    <ul>
                                        <li>
                                            <div class="delete_post_button">
                                                <input type="submit" name="action" value="บันทึกโพสต์" class="pink-button send_comment_button">
                                            </div>
                                        </li>
                                        <li>
                                            <div class="edit_psot_button">
                                                <input type="submit" name="action" value="ลบโพสต์" class="white-button send_comment_button">
                                            </div>
                                        </li>

                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                            </div>

                            <div class="list_edit_post">
                                <ul>

                                    <li>
                                        <div class="row">
                                            <div class="col-1 sub_list_edit_post">
                                                <input id="list_edit_header_checkbox" type="checkbox" name="type" value="111">
                                                <label for="list_edit_header_checkbox"></label></div>
                                            <div class="col sub_list_edit_post">
                                                <h5>ประเภท</h5></div>
                                            <div class="col sub_list_edit_post">
                                                <h5>เซต</h5></div>
                                            <div class="col sub_list_edit_post">
                                                <h5>เซตย่อย</h5></div>
                                            <div class="col sub_list_edit_post">
                                                <h5>สมาชิก</h5></div>
                                            <div class="col sub_list_edit_post">
                                                <h5>สถานะ</h5>
                                            </div>
                                            <div class="col sub_list_edit_post">
                                                <h5>
                                                    จำนวนสินค้า
                                                </h5></div>
                                        </div>
                                    </li>

                                    <c:forEach var="row" items="${allPostData}">
                                        <li>
                                            <div class="row">
                                                <div class="col-1 sub_list_edit_post">
                                                    <input id="post_id_${row.post_id}" type="checkbox" name="delete" value="${row.post_id}">
                                                    <label for="post_id_${row.post_id}"></label></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6>
                                                        <c:choose>
                                                            <c:when test="${not empty row.cat_level3}">
                                                                ${row.cat_level3}
                                                            </c:when>
                                                            <c:when test="${not empty row.cat_level2}">
                                                                ${row.cat_level2}
                                                            </c:when>
                                                            <c:when test="${not empty row.cat_level1}">
                                                                ${row.cat_level1}
                                                            </c:when>
                                                        </c:choose>
                                                    </h6></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6>
                                                        <c:choose>
                                                            <c:when test="${not empty row.cat_level3}">
                                                                ${row.cat_level2}
                                                            </c:when>
                                                            <c:when test="${not empty row.cat_level2}">
                                                                ${row.cat_level1}
                                                            </c:when>
                                                        </c:choose>
                                                    </h6></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6>
                                                        <c:choose>
                                                            <c:when test="${not empty row.cat_level3}">
                                                                ${row.cat_level1}
                                                            </c:when>
                                                        </c:choose>
                                                    </h6></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6>${not empty row.member_name ? row.member_name : '<i>ไม่ระบุ</i>'}</h6></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6><select class="" name="status_post_${row.post_id}">
                                                            <option value="SOLD" ${ row.status == 'SOLD' ? 'selected' : ''}>ขายแล้ว</option>
                                                            <option value="RESERVED" ${ row.status == 'RESERVED' ? 'selected' : ''}>ติดจอง</option>
                                                            <option value="ACTIVE" ${ row.status == 'ACTIVE' ? 'selected' : ''}>ขาย</option>
                                                        </select></h6></div>
                                                <div class="col sub_list_edit_post">
                                                    <h6>
                                                        <input type="text" name="remaining_post_${row.post_id}" value="${row.remaining}" placeholder="20">
                                                    </h6></div>
                                                <input type="hidden" name="post_id" value="${row.post_id}">
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </form>
                        <script type="text/javascript">
                            $("#list_edit_header_checkbox").click(function () {
                                $('input:checkbox').not(this).prop('checked', this.checked);
                            });
                        </script>

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
