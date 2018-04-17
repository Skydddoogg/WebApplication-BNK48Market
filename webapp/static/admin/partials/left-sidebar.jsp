<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="left-sidebar">
   <!-- Sidebar scroll-->
   <div class="scroll-sidebar">
     <!-- Sidebar navigation-->
     <nav class="sidebar-nav">
       <ul id="sidebarnav">
         <li class="nav-devider"></li>

         <li class="nav-label">User</li>
         <li>
           <a href="${ctx}/admin/manage-user" aria-expanded="false">
             <i class="fa fa-user"></i>
             <span class="hide-menu">จัดการผู้ใช้</span>
           </a>
         </li>
         <li class="nav-label">Category</li>
         <li>
           <a href="${ctx}/admin/manage-category" aria-expanded="false">
             <i class="fa fa-th-large"></i>
             <span class="hide-menu">จัดการประเภทของสินค้า</span>
           </a>
         </li>
         <li>
           <a href="${ctx}/admin/manage-bnk48-member" aria-expanded="false">
             <i class="fa fa-users"></i>
             <span class="hide-menu">จัดการชื่อสมาชิก BNK48</span>
           </a>
         </li>

         <li>
           <a href="${ctx}/admin/manage-delivery" aria-expanded="false">
             <i class="fa fa-truck"></i>
             <span class="hide-menu">จัดการรูปแบบของการจัดส่ง</span>
           </a>
           <li>
             <a href="${ctx}/admin/manage-payment" aria-expanded="false">
               <i class="fa fa-credit-card"></i>
               <span class="hide-menu">จัดการรูปแบบช่องทางชำระเงิน</span>
             </a>
           </li>
           <li class="nav-label">Advertisement</li>
           <li>
             <a href="${ctx}/admin/manage-ads" aria-expanded="false">
               <i class="fa fa-list-alt"></i>
               <span class="hide-menu">จัดการโฆษณา</span>
             </a>
           </li>
       </ul>
     </nav>
     <!-- End Sidebar navigation -->
 </div>
 <!-- End Sidebar scroll-->
</div>