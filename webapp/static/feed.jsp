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
        <link rel="stylesheet" href="static/css/bootstrap.min.css">
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300i" rel="stylesheet">
        <!-- STYLE -->
        <link rel="stylesheet" href="static/css/style.css">
        <!-- Awsome ICON -->
        <link rel="stylesheet" href="static/css/fontawesome-all.css">
        <!-- Script -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <!-- Ajax -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Privew Image Upload -->
        <script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="static/js/bootstrap.js"></script>

        <!-- Fancybox -->

    </head>

    <body>

        <jsp:include page="/static/partials/header.jsp" />

        <div class="container bodyContainer" style="max-width:1000px;cart_icon">
            <div class="row" style="margin-top:20px;">
                <div class="col-8">
                    <c:if test="${not empty sessionScope.success}">
                        <div class="alert alert-success" role="alert">
                            ${ sessionScope.success.message }
                        </div>
                        <c:remove var="success" scope="session" />
                    </c:if>
                    <c:if test="${not empty sessionScope.user && sessionScope.user.activate_status == 'ACTIVE'}">
                        <div class="new_post">
                            <c:if test="${not empty sessionScope.addPostError}">
                                <div class="alert alert-danger" role="alert">
                                    ${sessionScope.addPostError.message}
                                </div>
                                <c:remove var="addPostError"/>
                            </c:if>
                            <form action="${ctx}/user/new-post" method="POST" enctype="multipart/form-data">
                                <div class="top_new_post">
                                    <div class="new_post_icon">
                                        <img src="/resources/profile_picture/${empty sessionScope.user.profile_picture ? 'default.png' : sessionScope.user.profile_picture}" alt="">
                                    </div>
                                    <div class="description_new_post">

                                        <textarea class="text_area_new_post" name="description" rows="10" cols="50" placeholder="${sessionScope.user.firstname} คุณต้องการขายอะไร ?"></textarea>
                                    </div>
                                </div>

                                <div class="separator">
                                </div>

                                <!-- Category Selector -->
                                <div class="bottom_new_post">
                                    <div class="new_post_field" id="category-area">
                                        <h6>ประเภท</h6>
                                        <select name="category" id="category" required>
                                            <option value="0" disabled selected="selected">เลือกหมวดหมู่</option>
                                            <c:forEach var="category" items="${categories}">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:forEach>

                                        </select>

                                    </div>

                                    <div id="set_area_category" style="display:none;">
                                        <h6>เซต</h6>
                                        <select name="set" id="category_set">
                                        </select>
                                    </div>



                                    <div id="main_category_type" style="display:none;">
                                        <h6>เซตย่อย</h6>
                                        <!--name="type"-->
                                        <ul class="category_type"></ul>
                                    </div>

                                    <div id="member_category" class="new_post_field">
                                        <h6>สมาชิก BNK48</h6>
                                        <select name="member" required>
                                            <option value="0">ไม่ระบุ</option>
                                            <c:set var="currentGen" value="0" />
                                            <c:forEach var="member" items="${members}">
                                                <c:if test="${currentGen != member.generation}">
                                                    <c:if test="${currentGen != 0}"></optgroup></c:if>
                                                    <optgroup label="รุ่นที่ ${member.generation}">
                                                        <c:set var="currentGen" value="${member.generation}" />
                                                    </c:if>
                                                    <option value="${member.id}">${member.name}</option>
                                                </c:forEach>
                                            </optgroup>
                                        </select>
                                    </div>

                                    <!-- End Selector -->

                                    <!-- Price Field -->
                                    <div class="clearfix"></div>

                                    <!-- Category Selector -->
                                    <div id="price_field" class="new_post_field">
                                        <h6>ราคา</h6>
                                        <input type="text" name="price" required>
                                    </div>

                                    <!-- End Price Field -->
                                    <div class="clearfix"></div>
                                    <div class="separator">
                                    </div>

                                    <div class="clearfix"></div>
                                    <!-- Category Selector -->
                                    <div class="new_post_field">
                                        <h6>สถานที่นัดรับ</h6>
                                        <input type="text" name="location">
                                    </div>

                                    <div class="new_post_field">
                                        <h6>ประเภทการจัดส่ง</h6>
                                        <div class="deliverly_new_post">
                                            <ul>
                                                <c:forEach var="delivery" items="${deliveries}">
                                                    <li><input id="delivery_new_id_${delivery.id}" type="checkbox" name="delivery" value="${delivery.id}">
                                                        <label for="delivery_new_id_${delivery.id}">${delivery.name}</label></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>

                                    <!-- UPLOAD IMAGE -->
                                    <div class="" id="upload_image">
                                        <div class=" fileContainer" style="margin:0;">
                                            <input type="file" name="thumbnail" id="imageUpload-1" class="image-upload" required="">
                                            <label for="imageUpload-1" class="white-button"><span><i class="far fa-image"></i> เพิ่มรูปภาพ</span></label>
                                        </div>


                                        <div id="imageUpload-1-preview" class="image-preview">
                                            <div class="image-preview-hover">
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

                                    <div class="clearfix"></div>
                                    <div class="separator">
                                    </div>

                                    <div class="new_post_button">
                                        <input type="submit" id="add-post-btn" class="pink-button" value="สร้างโพสต์">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                            </form>
                        </div>
                    </c:if>
                    <!-- START of all post -->
                    <!-- post-id ให้ใส่เลข ID ของ Post -->
                    <div id="all-posts"></div>
                    <!-- END of all post -->

                </div>
                <div class="col-4" style="padding-left:0px;">
                    <div class="banner">
                        ${ads.html_code}
                    </div>

                    <div id="filter">

                        <ul>
                            <li>
                                <div class="filter_header">
                                    <h6>ประเภท</h6>
                                </div>
                                <div class="filter_sub">
                                    <select name="category" id="filter_category" required="">
                                        <option value="0">ประเภทใดก็ได้</option>
                                        <option value="0" disabled="" selected="selected">เลือกประเภท</option>
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </li>

                            <li>
                                <div id="filter_set_area_category" class="filter_header">
                                    <div class="filter_header">
                                        <h6>เซต</h6>
                                    </div>
                                    <div class="filter_sub">
                                        <select name="category" id="filter_set_area_category_input" required="">
                                        </select>
                                    </div>
                                </div>

                            </li>

                            <li>
                                <div id="filter_main_category_type" style="display:none;">
                                    <div class="filter_header" style="margin-bottom:10px;">
                                        <h6>เซตย่อย</h6>
                                    </div>
                                    <div class="filter_sub">
                                        <ul class="filter_category_type" id="filter_type"></ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div id="filter_member_category" class="filter_field" style="">
                                    <div class="filter_header">
                                        <h6>สมาชิก BNK48</h6>
                                    </div>
                                    <div class="filter_sub">
                                    <select id="filter_member" required>
                                        <option value="0">สมาชิกคนใดก็ได้</option>
                                        <c:set var="currentGen" value="0" />
                                        <c:forEach var="member" items="${members}">
                                            <c:if test="${currentGen != member.generation}">
                                                <c:if test="${currentGen != 0}"></optgroup></c:if>
                                                <optgroup label="รุ่นที่ ${member.generation}">
                                                    <c:set var="currentGen" value="${member.generation}" />
                                                </c:if>
                                                <option value="${member.id}">${member.name}</option>
                                            </c:forEach>
                                        </optgroup>
                                    </select>
                                    </div>
                                </div>
                            </li>

                            <div class="separator">

                            </div>

                            <li>
                                <div class="filter_header" style="margin-bottom:10px;">
                                    <h6>รูปแบบการรับสินค้า</h6>
                                </div>
                                <div class="filter_sub">
                                    <ul>
                                        <li><input id="delivery_id_1" type="checkbox" name="filter_delivery" value="Meeting">
                                            <label for="delivery_id_1">นัดรับ</label></li>
                                        <li><input id="delivery_id_2" type="checkbox" name="filter_delivery" value="PostMail">
                                            <label for="delivery_id_2">บริการขนส่ง</label></li>
                                    </ul>
                                </div>
                            </li>

                            <div class="separator">

                            </div>

                            <li>
                                <div id="filter_member_category" class="filter_field" style="">
                                    <div class="filter_header">
                                        <h6>เรียงตาม</h6>
                                    </div>
                                    <div class="filter_sub">
                                        <select required="" id="filter_sorting">
                                            <optgroup label="ราคา">
                                                <option value="price-min-to-max">ราคา น้อย - มาก</option>
                                                <option value="price-max-to-min">ราคา มาก - น้อย</option>
                                            </optgroup>
                                            <optgroup label="ไลค์">
                                                <option value="like-min-to-max">ไลค์ น้อย - มาก</option>
                                                <option value="like-max-to-min">ไลค์ มาก - น้อย</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>
                            </li>

<!--                            <li>
                                <div class="search_button">
                                    <button type="submit" id="search-button" class=" pink-button">ค้นหา</button>
                                </div>
                            </li>-->
                        </ul>

                    </div>

                </div>
            </div>
        </div>


        <div class="overlay"></div>
        <div class="post_thumbnail_popup">
            <img src="" alt="">
            <button type="button" class="exit_thumbnail_popup">
                <i class="fas fa-times"></i>
            </button>
        </div>
        <!-- Saveed ! Post Success Message Alert Box -->
        <div class="save_post_alert">
            <div class="popup-success" role="alert">
                <h4 class="alert-heading">บันทึกโพสต์สำเร็จ ! <p>สามารถดูโพสต์ที่บันทึกได้ที่เมนู "รายการสินค้าที่ต้องการ"</p></h4>
            </div>
        </div>

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="static/js/script.js"></script>
        <script type="text/javascript" src="static/js/fetch-post.js"></script>
        <script type="text/javascript">
            var filterDiv = $("#filter").offset().top;
            $(window).scroll(function () {
                if ($(window).scrollTop() > filterDiv - 80) {
                    $('#filter').css({
                        'position': 'fixed',
                        'top': '80px',
                        'z-index': '1',
                        'width': '318px'
                    });
                } else {
                    $('#filter').css({
                        'position': 'relative',
                        'top': 'auto'
                    });
                }

            });
            
            var thumbnailImg = $('.post_thumbnail');
                $(thumbnailImg).each(function(){
                  if($(this).find('img').outerHeight() > 492){
                      $(this).css({
                        'height': 492+'px'
                      });
                  }else{
                     $(this).css({
                        'height':'auto'
                      });

                 }
             });
            $(document).on("click", ".no_more_post", function(){
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            });

            $(document).on("click", ".save_post_button", function () {

                $(this).toggleClass("save_post_active");
                if ($(this).hasClass("save_post_active")) {
                    // save
                    console.log("Save>>" + $(this).attr("post-id"));
                    $.post("${ctx}/user/save-post", {action: "save", post_id: $(this).attr("post-id")})
                            .done(function () {
                                $(".save_post_alert").addClass("save_post_alert_animation");
                                setTimeout(function () {
                                    $(".save_post_alert").removeClass("save_post_alert_animation");
                                }, 2500);
                                $(".save_post_alert").addClass("save_post_alert_animation");
                                setTimeout(function () {
                                    $(".save_post_alert").removeClass("save_post_alert_animation");
                                }, 2500);
                            })
                            .fail(function () {
                                alert("บันทึกโพสต์ไม่สำเร็จ เนื่องจากมีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");
                            })

                } else {
                    // unsave
                    console.log("Unsave>>" + $(this).attr("post-id"));
                            $.post("${ctx}/user/save-post", {action: "unsave", post_id: $(this).attr("post-id")})
                            .done(function () { /* do nothing */  })
                            .fail(function () {
                                alert("มีปัญหาด้านการเชื่อมต่ออินเทอร์เน็ต");
                            })
                }
            });
            

        $(document).ready(function () {
            $('#add-post-btn').click(function() {
              checked = $(".new_post input[name=type]:checked").length;
              total = $(".new_post input[name=type]").length;
              if(!checked && total > 0) {
                alert("สินค้าประเภทนี้มีเซตย่อย กรุณาเลือกเซตย่อยอย่างน้อย 1 เซต");
                return false;
              }

            });
        });
        </script>
    </body>

</html>