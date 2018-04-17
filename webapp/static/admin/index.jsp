<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <!-- Favicon icon -->
  <link rel="icon" type="image/png" sizes="16x16" href="${ctx}/static/admin/images/favicon.png">
  <title>Administration | MARKET48</title>
  <!-- Bootstrap Core CSS -->
  <link href="${ctx}/static/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
  <!-- Custom CSS -->
  <link href="${ctx}/static/admin/css/helper.css" rel="stylesheet">
  <link href="${ctx}/static/admin/css/style.css" rel="stylesheet">

  <!-- Font -->
  <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:** -->
  <!--[if lt IE 9]>
    <script src="https:**oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https:**oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header fix-sidebar">
  <!-- Preloader - style you can find in spinners.css -->
  <div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
      <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
  </div>
  <!-- Main wrapper  -->
  <div id="main-wrapper">
    <!-- header header  -->
    <jsp:include page="partials/header.jsp" />
    <!-- End header header -->
    <!-- Left Sidebar  -->
    <jsp:include page="partials/left-sidebar.jsp" />
    <!-- End Left Sidebar  -->
    <!-- Page wrapper  -->
    <div class="page-wrapper">
      <!-- Bread crumb -->
      <div class="row page-titles">
        <div class="col-md-5 align-self-center">
          <h3 class="text-primary">หน้าหลัก</h3>
        </div>
        <div class="col-md-7 align-self-center">
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="javascript:void(0)">หน้าหลัก</a>
            </li>
          </ol>
        </div>
      </div>
      <!-- End Bread crumb -->
      <!-- Container fluid  -->
      <div class="container-fluid">
          <!-- Start Page Content -->
          <div class="row">
              <div class="col-md-3">
                  <div class="card bg-primary p-20">
                      <div class="media widget-ten">
                          <div class="media-left meida media-middle">
                              <span><i class="ti-comment f-s-40"></i></span>
                          </div>
                          <div class="media-body media-text-right">
                              <h2 class="color-white">${post_count}</h2>
                              <p class="m-b-0">โพสต์</p>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="col-md-3">
                  <div class="card bg-pink p-20">
                      <div class="media widget-ten">
                          <div class="media-left meida media-middle">
                              <span><i class="fa fa-users f-s-40"></i></span>
                          </div>
                          <div class="media-body media-text-right">
                              <h2 class="color-white">${all_user_count}</h2>
                              <p class="m-b-0">สมาชิกทั้งหมด</p>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="col-md-3">
                  <div class="card bg-success p-20">
                      <div class="media widget-ten">
                          <div class="media-left meida media-middle">
                              <span><i class="fa fa-user f-s-40"></i></span>
                          </div>
                          <div class="media-body media-text-right">
                              <h2 class="color-white">${active_user_count}</h2>
                              <p class="m-b-0">สมาชิกที่ยืนยันตัวตนแล้ว</p>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="col-md-3">
                  <div class="card bg-danger p-20">
                      <div class="media widget-ten">
                          <div class="media-left meida media-middle">
                              <span><i class="fa fa-user-times f-s-40"></i></span>
                          </div>
                          <div class="media-body media-text-right">
                              <h2 class="color-white">${non_identify_user_count}</h2>
                              <p class="m-b-0">สมาชิกที่ยังไม่ได้ยืนยันตัวตน</p>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
<div class="row">
              <!-- /# column -->
              <div class="col-lg-4">
                  <div class="card">
                      <div class="card-title">
                          <h4 style="font-family: 'Kanit', sans-serif;">สมาชิกใหม่ล่าสุด</h4>
                      </div>
                      <div class="card-body">
                          <div class="recent-meaasge">
                              <c:forEach var="user" items="${newlyUsersData}" varStatus="status">
                              <div class="media ${status.last ? 'no-border' : '' }">
                                  <div class="media-left">
                                      <a href="${ctx}/user/profile?id=${user.id}" target="_blank"><img alt="..." src="/resources/profile_picture/${user.profile_picture == null ? 'default.png' : user.profile_picture}" class="media-object"></a>
                                  </div>
                                  <div class="media-body">
                                        <h4 class="media-heading"><a href="${ctx}/user/profile?id=${user.id}" style="color:#0056b3;" target="_blank">@${user.username}</a></h4>
                                      <p class="f-s-12">${user.firstname} ${user.lastname}</p>
                                  </div>
                              </div>
                            </c:forEach>
                              
                          </div>
                      </div>
                  </div>
              </div>

              <div class="col-lg-4">
                  <div class="card">
                      <div class="card-title">
                          <h4 style="font-family: 'Kanit', sans-serif;">5 อันดับ Like มากที่สุด</h4>
                      </div>
                      <div class="card-body">
                          <div class="recent-meaasge">
                              <c:forEach var="user" items="${mostLikedUsersData}" varStatus="status">
                              <div class="media ${status.last ? 'no-border' : '' }">
                                  <div class="media-left">
                                      <a href="${ctx}/user/profile?id=${user.id}" target="_blank"><img alt="..." src="/resources/profile_picture/${user.profile_picture == null ? 'default.png' : user.profile_picture}" class="media-object"></a>
                                  </div>
                                  <div class="media-body">
                                        <h4 class="media-heading"><a href="${ctx}/user/profile?id=${user.id}" style="color:#0056b3;" target="_blank">@${user.username}</a></h4>
                                      <div class="meaasge-date">${user.likes == null ? 0 : user.likes} Likes</div>
                                      <p class="f-s-12">${user.firstname} ${user.lastname}</p>
                                  </div>
                              </div>
                              </c:forEach>
                          </div>
                      </div>
                  </div>
              </div>

              <div class="col-lg-4">
                  <div class="card">
                      <div class="card-title">
                          <h4 style="font-family: 'Kanit', sans-serif;">5 อันดับ Dislike มากที่สุด</h4>
                      </div>
                      <div class="card-body">
                          <div class="recent-meaasge">
                              <c:forEach var="user" items="${mostDislikedUsersData}" varStatus="status">
                              <div class="media ${status.last ? 'no-border' : '' }">
                                  <div class="media-left">
                                      <a href="${ctx}/user/profile?id=${user.id}" target="_blank"><img alt="..." src="/resources/profile_picture/${user.profile_picture == null ? 'default.png' : user.profile_picture}" class="media-object"></a>
                                  </div>
                                  <div class="media-body">
                                        <h4 class="media-heading"><a href="${ctx}/user/profile?id=${user.id}" style="color:#0056b3;" target="_blank">@${user.username}</a></h4>
                                      <div class="meaasge-date">${user.likes == null ? 0 : user.likes} Dislikes</div>
                                      <p class="f-s-12">${user.firstname} ${user.lastname}</p>
                                  </div>
                              </div>
                              </c:forEach>
                          </div>
                      </div>
                  </div>
              </div>
             

          </div>


          <!-- End PAge Content -->
      </div>
      <!-- End Container fluid  -->
      <!-- footer -->
      <footer class="footer"> MARKET48 © 2018 All rights reserved.</footer>
      <!-- End footer -->
    </div>
    <!-- End Page wrapper  -->
  </div>
  <!-- End Wrapper -->
  <!-- All Jquery -->
  <script src="${ctx}/static/admin/js/lib/jquery/jquery.min.js"></script>
  <!-- Bootstrap tether Core JavaScript -->
  <script src="${ctx}/static/admin/js/lib/bootstrap/js/popper.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/bootstrap/js/bootstrap.min.js"></script>
  <!-- slimscrollbar scrollbar JavaScript -->
  <!-- <script src="js/jquery.slimscroll.js"></script> -->
  <!--Menu sidebar -->
  <!-- <script src="js/sidebarmenu.js"></script> -->
  <!--stickey kit -->
  <script src="${ctx}/static/admin/js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
  <!--Custom JavaScript -->
  <script src="${ctx}/static/admin/js/custom.min.js"></script>


  <script src="${ctx}/static/admin/js/lib/datatables/datatables.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
  <script src="${ctx}/static/admin/js/lib/datatables/datatables-init.js"></script>
</body>

</html>