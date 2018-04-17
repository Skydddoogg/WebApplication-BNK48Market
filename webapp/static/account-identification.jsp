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
                        <jsp:param name="menu_id" value="5" />
                    </jsp:include>
                </div>
                <div class="col-9" style="padding-left:0px;">
                    <div class="" id="body">
                        <form action="${ctx}/user/account-identification/submit" method="POST" enctype="multipart/form-data">
                            <div id="account_identification">
                                <ul>
                                    <li>
                                        <div class="account_identification_img">
                                            <img src="${ctx}/static/images/identification/selfi-cherpang.png" alt="">
                                        </div>

                                        <!-- UPLOAD IMAGE -->
                                        <div class="" id="upload_image">
                                            <div class="account_identification_button">
                                                <div class=" fileContainer" style="margin:0;">
                                                    <input type="file" name="id_card_picture_selfie" id="imageUpload-1" class="image-upload" required="">
                                                    <label for="imageUpload-1" class="pink-button"><span><i class="far fa-image"></i> เพิ่มรูปภาพ</span></label>
                                                </div>
                                            </div>

                                            <div class="account_identification_image_preview">
                                                <div id="imageUpload-1-preview" class="image-preview">
                                                    <div class="image-preview-hover">
                                                        <button type="button" class="remove-image-privew"><i class="fas fa-times"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END UPLOAD IMAGE -->
                                    </li>
                                    <li></li>
                                </ul>

                                <div class="clearfix"></div>
                            </div>
                            <div class="separator"></div>





                            <div id="account_identification">
                                <ul>
                                    <li>
                                        <div class="account_identification_img">
                                            <img src="${ctx}/static/images/identification/selfe-music.png" alt="">
                                        </div>

                                    </li>
                                    <li>
                                        <div class="verify-suggestion">
                                            <ol type="1">
                                                <li>ต้องสามารถเห็นรายละเอียดชัดเจน โดยถ่ายรูปบัตร หรือ scan สีจากหลักฐานจริงเท่านั้น</li>
                                                <li>ต้องเห็นรูปหน้า และเลขประจำตัวประชาชนชัดเจน</li>
                                                <li>สามารถระบุประทับในหลักฐานว่า “ใช้เฉพาะการสมัครสมาชิก Market48.com”</li>
                                            </ol>
                                        </div>

                                    </li>

                                    <li style="text-align:center;">
                                        <img src="${ctx}/static/images/identification/card.png" alt="">
                                    </li>

                                    <li>
                                        <div class="identification_field">
                                            <h6><b>เลขบัตรประชาชน</b></h6>
                                            <input type="text" name="national_id" maxlength="13">
                                        </div>
                                    </li>

                                    <li>
                                        <!-- UPLOAD IMAGE -->
                                        <div class="" id="upload_image">
                                            <div class="account_identification_button">
                                                <div class=" fileContainer" style="margin:0;">
                                                    <input type="file" name="id_card_picture" id="imageUpload-2" class="image-upload" required="">
                                                    <label for="imageUpload-2" class="pink-button"><span><i class="far fa-image"></i> เพิ่มรูปภาพ</span></label>
                                                </div>
                                            </div>

                                            <div class="account_identification_image_preview">
                                                <div id="imageUpload-2-preview" class="image-preview">
                                                    <div class="image-preview-hover">
                                                        <button type="button" class="remove-image-privew"><i class="fas fa-times"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END UPLOAD IMAGE -->
                                    </li>
                                </ul>

                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        $("#imageUpload-1").change(function () {
                                            $.readURL(this);
                                        });

                                        $("#imageUpload-2").change(function () {
                                            $.readURL(this);
                                        });
                                    });
                                </script>

                                <div class="clearfix"></div>

                            </div>


                            <div class="separator"></div>


                            <div class="account_identification_page_button">
                                <input type="submit" class="pink-button" value="ส่งข้อมูลยืนยันตัวตน">
                            </div>
                        </form>
                        <div class="clearfix"></div>
                    </div>
                </div>

            </div>

            <div class="overlay"></div>

            <!-- JAVASCRIPT -->
            <script type="text/javascript" src="${ctx}/static/js/script.js"></script>

    </body>

</html>
