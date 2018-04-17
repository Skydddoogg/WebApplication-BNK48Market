<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<ul>
    <li>
        <a href="profile?id=${ sessionScope.user.id }" ${ param.menu_id == 1 ? 'class="side_bar_menu_active"' : ''}>โปรไฟล์ส่วนตัว</a>

    </li>

    <li>
        <a href="edit-profile" ${ param.menu_id == 2 ? 'class="side_bar_menu_active"' : ''}>แก้ไขโปรไฟล์</a>
    </li>

    <li>
        <a href="manage-salepost" ${ param.menu_id == 3 ? 'class="side_bar_menu_active"' : ''}>จัดการโพสต์</a>
    </li>

    <li>
        <a href="saved-post" ${ param.menu_id == 4 ? 'class="side_bar_menu_active"' : ''}>รายการสินค้าที่ต้องการ</a>
    </li>

    <c:if test="${ sessionScope.user.activate_status == 'PENDING' && sessionScope.user.national_id == null || sessionScope.user.activate_status == 'REJECTED' }">
    <li>
        <a href="account-identification" ${ param.menu_id == 5 ? 'class="side_bar_menu_active"' : ''}>ยืนยันตัวตน</a>
    </li>
    </c:if>

</ul>
