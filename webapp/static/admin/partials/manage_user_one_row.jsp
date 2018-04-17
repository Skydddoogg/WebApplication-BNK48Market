<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${requestScope.user}"/>

<tr>
    <td>${user.username}</td>
    <td>${user.firstname}</td>
    <td>${user.lastname}</td>
    <td>
        <select class="form-control" id="val-skill" name="${user.id}">
            <option value="normal" ${ user.activate_status == 'BANNED' ? '' : 'selected' }>ปกติ</option>
            <option value="banned" ${ user.activate_status == 'BANNED' ? 'selected' : '' }>ระงับการใช้งาน</option>
        </select>
    </td>
    <td>
        <c:choose>
            <c:when test="${user.activate_status == 'PENDING' && not empty user.national_id}">
                <!-- Button to Open the Modal -->
                <button type="button" class="btn btn-info m-b-10 m-l-5" data-toggle="modal" data-target="#myModal-${user.id}">
                    ข้อมูลยืนยันตัวตน
                </button>

                <!-- The Modal -->
                <div class="modal fade" id="myModal-${user.id}">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">คุณ ${user.firstname} ${user.lastname} (${user.username})</h4>

                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <div class="activate-user">
                                    <ul>
                                        <li>
                                            <h5>ภาพถ่ายคู่บัตรประชาชน</h5>
                                            <div class="activate-user-images">
                                                <a href="/resources/id_card_picture_selfie/${user.id_card_picture_selfie}" target="_blank">
                                                    <img src="/resources/id_card_picture_selfie/${user.id_card_picture_selfie}" alt="">
                                                </a>
                                            </div>
                                        </li>
                                        <li>
                                            <h5>ภาพบัตรประชาชน</h5>
                                            <div class="activate-user-images">
                                                <a href="/resources/id_card_picture/${user.id_card_picture}" target="_blank">
                                                    <img src="/resources/id_card_picture/${user.id_card_picture}" alt="">
                                                </a>
                                            </div>
                                        </li>
                                        <li>
                                            <h5>เลขบัตรประชาชน</h5>
                                            <div class="alert alert-info">
                                                ${user.national_id}
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <a class="btn btn-info m-b-10 m-l-5" href="${ctx}/admin/manage-user/activate?uid=${user.id}&action=activate" role="button">Activate</a>
                                <a class="btn btn-danger m-b-10 m-l-5" href="${ctx}/admin/manage-user/activate?uid=${user.id}&action=reject" role="button">Reject</a>
                                <button type="button" class="btn btn-link m-b-10 m-l-5" data-dismiss="modal" aria-label="Close">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${user.activate_status == 'ACTIVE'}">
                ยืนยันตัวตนแล้ว
            </c:when>
            <c:when test="${user.activate_status == 'REJECTED'}">
                ไม่ผ่านการยืนยันตัวตน
            </c:when>

        </c:choose>

    </td>
</tr>