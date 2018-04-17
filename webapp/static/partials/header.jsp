<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<header>
    <div class="container" style="max-width:1000px;">
        <div class="row">
            <div class="col">
                <div id="logo">
                    <a href="${ctx}/feed">
                        <h2>MARKET48</h2>
                    </a>
                </div>
            </div>
            <!--        <div class="col">
                      <div class="header-search">
                        <input type="text" name="search" placeholder="ค้นหา">
                      </div>
                    </div>-->
            <div class="col">
                <div class="profile_nav">
                    <a href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <div class="profile_icon" aria-expanded="false">
                            <img src="/resources/profile_picture/${empty sessionScope.user.profile_picture ? 'default.png' : sessionScope.user.profile_picture}" alt="">
                        </div>
                    </a>
                    <div class="profile_dropdown dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <ul>
                            <c:if test="${not empty sessionScope.user}">
                                <li>
                                    <a href="${ctx}/user/profile?id=${ sessionScope.user.id }">โปรไฟล์ส่วนตัว</a>

                                </li>

                                <li>
                                    <a href="${ctx}/user/edit-profile">แก้ไขโปรไฟล์</a>
                                </li>

                                <li>
                                    <a href="${ctx}/user/manage-salepost">จัดการโพสต์</a>
                                </li>

                                <li>
                                    <a href="${ctx}/user/saved-post">รายการสินค้าที่ต้องการ</a>
                                </li>
                                <c:if test="${ sessionScope.user.activate_status == 'PENDING' && sessionScope.user.national_id == null || sessionScope.user.activate_status == 'REJECTED' }">
                                    <li>
                                        <a href="${ctx}/user/account-identification">ยืนยันตัวตน</a>
                                    </li>
                                </c:if>
                                <li>
                                    <a href="${ctx}/user/logout">ออกจากระบบ</a>
                                </li>
                            </c:if>
                            <c:if test="${ empty sessionScope.user }">
                                <li>
                                    <a href="${ctx}/login">เข้าสู่ระบบ</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
</header>
