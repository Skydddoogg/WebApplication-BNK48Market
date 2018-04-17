<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="header">
   <nav class="navbar top-navbar navbar-expand-md navbar-light">
     <!-- Logo -->
     <div class="navbar-header">
       <a class="navbar-brand" href="${ctx}/admin/home">
         <!-- Logo icon -->
         <!--End Logo icon -->
         <!-- Logo text -->
         <span>MARKET48</span>
       </a>
     </div>
     <!-- End Logo -->
     <div class="navbar-collapse">
       <!-- toggle and nav items -->
       <ul class="navbar-nav mr-auto mt-md-0">
         <!-- This is  -->
         <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted  " href="javascript:void(0)"><i class="mdi mdi-menu"></i></a> </li>
         <li class="nav-item m-l-10"> <a class="nav-link sidebartoggler hidden-sm-down text-muted  " href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
         <!-- End Messages -->
       </ul>
       <!-- User profile and search -->
       <ul class="navbar-nav my-lg-0">




         <!-- Profile -->
         <li class="nav-item dropdown">
           <a class="nav-link dropdown-toggle text-muted  " href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${ctx}/static/admin/images/users/5.jpg" alt="user" class="profile-pic" /></a>
           <div class="dropdown-menu dropdown-menu-right animated zoomIn">
             <ul class="dropdown-user">
               <li><a href="${ctx}/admin/logout"><i class="fa fa-power-off"></i> Logout</a></li>
             </ul>
           </div>
         </li>
       </ul>
     </div>
   </nav>
 </div>