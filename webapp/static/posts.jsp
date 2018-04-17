<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />




<c:forEach var="data" items="${allPostsData}">

    <!-- START of query post -->

    <div id="post" class="post">
        <div class="header_post clearfix post_id_${data.post_id[0]}">
            <div class="profile_post">
                <div class="profile_icon_post">
                    <a href="${ctx}/user/profile?id=${data.user_id[0]}"><img src="/resources/profile_picture/${empty data.profile_picture[0] ? 'default.png' : data.profile_picture[0] }" alt=""></a>
                </div>

                <div class="userdes_post">
                    <h6><a href="${ctx}/user/profile?id=${data.user_id[0]}">${fn:toUpperCase(data.username[0])}</a> <span> ${data.like_count[0]} Likes</span></h6>
                    <p class="date_time_posted">${data.timestamp[0]}</p>
                </div>
            </div>

            <div class="price_of_post">
                <h4>฿${data.price[0]} <span>/ ชิ้น</span></h4>
                <div class="amount_of_product">
                    คงเหลือ ${data.remaining[0]} ชิ้น
                </div>
            </div>
        </div>

        <div class="post_type">
            <div class="row" style="margin:0;">
                <div class="col post_type_list">
                    <div class="header_type">
                        ประเภท
                    </div>
                    <div class="sub_type">
                        <c:choose>
                            <c:when test="${not empty data.cat_level3[0]}">
                                ${data.cat_level3[0]}
                            </c:when>
                            <c:when test="${not empty data.cat_level2[0]}">
                                ${data.cat_level2[0]}
                            </c:when>
                            <c:when test="${not empty data.cat_level1[0]}">
                                ${data.cat_level1[0]}
                            </c:when>
                        </c:choose>

                    </div>
                </div>
                <c:if test="${not empty data.cat_level2[0]}">
                    <div class="col post_type_list">
                        <div class="header_type">
                            เซต
                        </div>

                        <div class="sub_type">
                            <c:choose>
                                <c:when test="${not empty data.cat_level3[0]}">
                                    ${data.cat_level2[0]}
                                </c:when>
                                <c:when test="${not empty data.cat_level2[0]}">
                                    ${data.cat_level1[0]}
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty data.cat_level3[0]}">
                    <div class="col post_type_list">
                        <div class="header_type">
                            เซตย่อย
                        </div>

                        <div class="sub_type">
                            <c:forEach var="type" items="${data.cat_level1}">
                                ${type}<br>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty data.member[0]}">
                    <div class="col post_type_list">
                        <div class="header_type">
                            สมาชิก
                        </div>

                        <div class="sub_type">
                            ${data.member[0]}
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <c:if test="${not empty data.location[0] || not empty data.delivery[0]}">
            <div class="post_type">
                <div class="row" style="margin:0;">
                    <c:if test="${not empty data.location[0]}">
                        <div class="col post_type_list">
                            <div class="position_field">
                                นัดรับ - ${data.location[0]}
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty data.delivery[0]}">
                        <div class="col post_type_list">
                            <div class="deliverly_field">
                                บริการขนส่ง -
                                <c:set var="flag" value="${false}" />
                                <c:forEach var="delivery" items="${data.delivery}">
                                    <c:if test="${flag}">
                                        ,
                                    </c:if>
                                    ${delivery}
                                    <c:set var="flag" value="${true}" />
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty data.payment_name[0]}">
            <div class="post_type">
                <div class="row" style="margin:0;">
                    <div class="col post_type_list">
                        <div class="credit_field">
                            ช่องทางชำระเงิน -
                            <ul>
                                <c:forEach var="paymentName" items="${data.payment_name}" varStatus="status">
                                    <li>
                                        <img src="/resources/payment_logo/${data.payment_logo[status.index]}" alt="${paymentName}">
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty data.description[0]}">
            <div class="description_post">
                <p class="minimize">${data.description[0]}</p>
            </div>
        </c:if>

        <div class="post_thumbnail">
            <c:if test="${not empty sessionScope.user}">
                <div post-id="${data.post_id[0]}" class="save_post_button ${data.is_saved[0] == 1 ? 'save_post_active' : ''}"></div>
            </c:if>
            <img src="/resources/post_thumbnail/${data.thumbnail[0]}" alt="">
            <div class="separator" style="margin-bottom: 0;"></div>
        </div>

        <div class="post_contact_button">
            <div id="post_id_${data.post_id[0]}_contact" class="contact_icon">
                <ul>
                    <c:if test="${not empty data.facebook_id[0]}">
                        <li>
                            <a href="https://www.facebook.com/${data.facebook_id[0]}" target="_blank">
                                <img src="static/images/contact/facebook.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${not empty data.line_id[0]}">
                        <li>
                            <a href="https://line.me/ti/p/~${data.line_id[0]}" target="_blank">
                                <img src="static/images/contact/line.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${not empty data.twitter_id[0]}">
                        <li>
                            <a href="https://www.twitter.com/${data.twitter_id[0]}" target="_blank">
                                <img src="static/images/contact/twitter.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${not empty data.email[0]}">
                        <li>
                            <a href="mailto:${data.email[0]}" target="_blank">
                                <img src="static/images/contact/email.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <button post-id="${data.post_id[0]}" class="contact_button">ติดต่อผู้ขาย</button>
        </div>

        <div class="clearfix"></div>
    </div>

    <!-- END of query post -->

</c:forEach>
