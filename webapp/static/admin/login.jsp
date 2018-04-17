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

    <body>

        <div id="login"> 


            <div class="loginInner">
                <div class="logoLogin">
                    <h1>MARKET48</h1>
                </div>
                <div class="card">
                    <c:if test="${ not empty error }">
                        <div class="alert alert-danger" role="alert">
                            ${error.message}
                        </div>
                    </c:if>
                    <div class="basic-form">
                        <form action="${ctx}/admin-auth" method="POST">
                            <div class="form-group">
                                <h5 style="color: #72777c;">Username</h5>
                                <input type="text" name="username" class="form-control input-default " required>
                            </div>
                            <div class="form-group">
                                <h5 style="color: #72777c;">Password</h5>
                                <input type="password" name="password" class="form-control input-default " required>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Login" class="button_login">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


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