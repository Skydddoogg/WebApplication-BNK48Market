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
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
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
                        <h3 class="text-primary">จัดการรูปแบบของการจัดส่ง</h3> </div>
                    <div class="col-md-7 align-self-center">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="javascript:void(0)">หน้าหลัก</a></li>
                            <li class="breadcrumb-item active">จัดการรูปแบบของการจัดส่ง</li>
                        </ol>
                    </div>
                </div>
                <!-- End Bread crumb -->
                <!-- Container fluid  -->
                <div class="container-fluid">
                    <!-- Start Page Content -->
                    <div class="row">
                        <div class="col-5">
                            <c:if test="${not empty sessionScope.success}">
                                <div class="alert alert-success" role="alert">
                                    ${ sessionScope.success.message }
                                </div>
                                <c:remove var="success" scope="session" />
                            </c:if>
                            <div class="card card-outline-primary">
                                <form action="${ctx}/admin/manage-delivery/add" method="POST">
                                    <div class="form-body">
                                        <h3 class="card-title m-t-15">เพิ่มรูปแบบการรับสินค้า</h3>
                                        <hr>
                                        <div class="row p-t-20">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">ชื่อผู้ให้บริการ</label>
                                                    <input type="text" name="name" class="form-control" placeholder="ต.ย. EMS">
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-info m-b-10 m-l-5">  Add</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-7">
                            <form action="${ctx}/admin/manage-delivery/delete" method="POST">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="myTable" class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th style="text-align:center;"><input id="delivery_checkbox" type="checkbox" name="type" value="111">
                                                            <label for="delivery_checkbox"></label></th>
                                                        <th style="text-align:center;">ชื่อผู้ให้บริการ</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="delivery" items="${deliveries}">
                                                        <tr>
                                                            <td style="text-align:center;"><input id="delivery_checkbox_${delivery.id}" type="checkbox" name="delivery" value="${delivery.id}">
                                                                <label for="delivery_checkbox_${delivery.id}"></label></td>
                                                            <td style="text-align:center;">${delivery.name}</td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <input type="submit" value="Delete" class="btn btn-danger m-b-10 m-l-5">
                                    </div>
                                </div>
                        </div>
                        </form>
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
    <script src="${ctx}/static/admin/js/jquery.slimscroll.js"></script>
    <!--Menu sidebar -->
    <script src="${ctx}/static/admin/js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="${ctx}/static/admin/js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="${ctx}/static/admin/js/custom.min.js"></script>
    <!-- Script Mod -->
    <script src="${ctx}/static/admin/js/scripts-mod.js"></script>

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
