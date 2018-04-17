<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                <c:choose>
                    <c:when test="${ userQuery.id == sessionScope.user.id }">
                        <div class="col-3 side_bar_menu">
                            <jsp:include page="/static/partials/side_bar_menu.jsp" >
                                <jsp:param name="menu_id" value="1" />
                            </jsp:include>
                        </div>
                        <div class="col-9" style="padding-left:0px;">
                    </c:when>
        <c:otherwise>
            <div class="col" style="padding-left:0px;">
        </c:otherwise>
                </c:choose>
                    <div class="" id="body">
                        <div class="row profile_header">
                            <div class="col">
                                <div class="row">
                                    <div class="col-9">
                                        <div class="profile_image">
                                            <img src="/resources/profile_picture/${empty userQuery.profile_picture ? 'default.png' : userQuery.profile_picture }" alt="">
                                        </div>
                                        <div class="profile_description">
                                            <ul>
                                                <li>
                                                    <h6>${fn:toUpperCase(userQuery.username)}</h6>
                                                    <c:if test="${userQuery.activate_status == 'ACTIVE'}"><div class="verify-icon" aria-label-activate-profile="ยืนยันตัวตนแล้ว"><i class="far fa-check-circle"></i></div></c:if>
                                                    </li>
                                                    <li>
                                                        <div class="contact_icon" style="display: block;">
                                                            <ul style="margin:0;">
                                                            <c:if test="${not empty userQuery.facebook_id}">
                                                                <li><a href="https://www.facebook.com/${userQuery.facebook_id}" target="_blank"><img src="${ctx}/static/images/contact/facebook.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty userQuery.line_id}">
                                                                <li><a href="https://line.me/ti/p/~${userQuery.line_id}" target="_blank"><img src="${ctx}/static/images/contact/line.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty userQuery.twitter_id}">
                                                                <li><a href="https://www.twitter.com/${userQuery.twitter_id}" target="_blank"><img src="${ctx}/static/images/contact/twitter.svg" alt=""></a></li>
                                                                    </c:if>
                                                                    <c:if test="${not empty userQuery.email}">
                                                                <li><a href="mailto:${userQuery.email}"><img src="${ctx}/static/images/contact/email.svg" alt=""></a></li>
                                                                    </c:if>
                                                        </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-3">
                                <div class="like_dislike">
                                    <div class="like-button">
                                        <div user-id="${userQuery.id}" class="like ${voted_type == 'LIKE' ? 'voted' : ''}" aria-label="Like">
                                            <span>${voteCount.like}</span>
                                        </div>
                                        <div user-id="${userQuery.id}" class="dislike ${voted_type == 'DISLIKE' ? 'voted' : ''}" aria-label="Dislike">
                                            <span>${voteCount.dislike}</span>
                                        </div>
                                    </div>
                                    <div class="like_dislike_tab">
                                        <div class="like_tab">

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div class="post_type">
                            <div class="row" style="margin:0;">
                                <div class="col post_type_list">
                                    <div class="header_type">
                                        ชื่อ / สกุล
                                    </div>

                                    <div class="sub_type">
                                        ${userQuery.firstname} ${userQuery.lastname}
                                    </div>
                                </div>
                                <div class="col post_type_list">
                                    <div class="header_type">
                                        เพศ
                                    </div>

                                    <div class="sub_type">
                                        ${userQuery.sex == 'M' ? 'ชาย' : 'หญิง'}
                                    </div>
                                </div>
                                <div class="col post_type_list">
                                    <div class="header_type">
                                        เบอร์ติดต่อ
                                    </div>

                                    <div class="sub_type">
                                        ${empty userQuery.phone ? 'ไม่ระบุ' : userQuery.phone}
                                    </div>
                                </div>
                                <div class="col post_type_list">
                                    <div class="credit_field_profile">
                                        <div class="header_type credit_field">
                                            ช่องทางการชำระเงิน
                                        </div>
                                        <div class="sub_type credit_field_sub">
                                            <ul>
                                                <c:forEach var="payment" items="${allPaymentData}">
                                                    <li>
                                                        <img src="/resources/payment_logo/${payment.logo}" alt="${payment.name}">
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>




                        </div>

                        <div id="comment">
                            <form action="${ctx}/user/profile/comment/add" method="POST">
                                <div class="row">
                                    <div class="col">
                                        <div class="comment_header">
                                            <h6>
                                                ความคิดเห็น ${fn:length(allCommentData)} รายการ
                                            </h6>
                                        </div>
                                    </div>


                                </div>
                                <div class="row">
                                    <div class="col-1">
                                        <div class="commnet_profile_img">
                                            <img src="/resources/profile_picture/${empty sessionScope.user.profile_picture ? 'default.png' : sessionScope.user.profile_picture}" alt="">
                                        </div>
                                    </div>
                                    <div class="col-11">
                                        <div class="comment_field">
                                            <textarea class="text_area_new_post" name="comment" rows="10" cols="50" placeholder="${fn:toUpperCase(sessionScope.user.username)} คุณมีความคิดเห็นอย่างไร ?" style="height: 44px; overflow-y: hidden;" required></textarea>
                                            <input type="hidden" name="user_profile_id" value="${userQuery.id}">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="comment_button col">
                                        <ul>
                                            <li>
                                                <input type="submit" value="COMMENT" class="pink-button send_comment_button"></li>
                                            <!--<li> <input type="submit" value="CANCEL" class="cancel_comment_button">-->
                                            </li>
                                        </ul>

                                    </div>
                                </div>
                            </form>

                            <div class="row">
                                <div class="col sub_comment">
                                    <ul>
                                        <c:forEach var="commentData" items="${allCommentData}">
                                            <li>
                                                <div class="row">
                                                    <div class="col-1">
                                                        <div class="commnet_profile_img" style="margin-top:15px;">
                                                            <img src="/resources/profile_picture/${empty commentData.profile_picture ? 'default.png' : commentData.profile_picture}" alt="">
                                                        </div>
                                                    </div>
                                                    <div class="col-11">
                                                        <div class="sub_comment_description">
                                                            <h6><a href="${ctx}/user/profile?id=${commentData.src_user_id}">${fn:toUpperCase(commentData.username)}</a> <span> ${commentData.timestamp}</span></h6>
                                                            <div>${commentData.comment}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="overlay"></div>

    <!-- JAVASCRIPT -->
    <script type="text/javascript" src="${ctx}/static/js/script.js"></script>
    <script>

        function updateLikeDislikeBar(like, dislike) {

            $(".like span").text(kFormatter(like));
            $(".dislike span").text(kFormatter(dislike));

            $(".like_tab").css({
                'width': likeTab(like, dislike) + '%'
            });
        }

        $(".like").click(function () {
            var like = parseInt($(".like span").text());
            var dislike = parseInt($(".dislike span").text());

            $(this).toggleClass("voted");

            if ($(this).hasClass("voted")) {
                // -- Like

                if ($(".dislike").hasClass("voted")) {
                    // Biz: Undislike first!
                    dislike -= 1;
                    $(".dislike").toggleClass("voted");
                    
                }

                // Biz: Like!
                $.post("${ctx}/user/vote", {action: "like", dest_user_id: $(this).attr("user-id")})
                            .done(function () { 
                                like += 1; 
                                console.log("like");  
                                updateLikeDislikeBar(like, dislike);})
                            .fail(function () {
                                alert("มีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");})
            }   
             else {
                // -- Unlike

                // Biz: Unlike
                $.post("${ctx}/user/vote", {action: "unlike", dest_user_id: $(this).attr("user-id")})
                            .done(function () { 
                                like -= 1
                                console.log("unlike");
                                updateLikeDislikeBar(like, dislike);})
                            .fail(function () {
                                alert("มีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");});
            }});
          
        $(".dislike").click(function () {
            
            var like = parseInt($(".like span").text());
            var dislike = parseInt($(".dislike span").text());
            
            $(this).toggleClass("voted");

            if ($(this).hasClass("voted")) {
                // -- Dislike
                if ($(".like").hasClass("voted")) {
                    like -= 1;
                    $(".like").toggleClass("voted");
                }

                // Biz: Dislike
                $.post("${ctx}/user/vote", {action: "dislike", dest_user_id: $(this).attr("user-id")})
                            .done(function () { 
                                dislike += 1;
                                console.log("dislike");
                                updateLikeDislikeBar(like, dislike);})
                            .fail(function () {
                                alert("มีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");})
            }
            else {
                // -- Undislike
                
                // Biz: Undislike
                $.post("${ctx}/user/vote", {action: "undislike", dest_user_id: $(this).attr("user-id")})
                            .done(function () { 
                                dislike -= 1;
                                console.log("undislike");
                                updateLikeDislikeBar(like, dislike);})
                            .fail(function () {
                                alert("มีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");})


            }});


    </script>
</body>

</html>
