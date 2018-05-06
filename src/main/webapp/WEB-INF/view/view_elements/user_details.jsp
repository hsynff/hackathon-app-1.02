<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 5/5/2018
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<h3>İstifadəçi məlumatları</h3>
<br>

<div class="form-group">
    <p class="addNewUserLabel">Ad / Soyad</p>
    <input name="fullName" type="text" class="form-control" value="${user.fullName}">
</div>
<div class="form-group">
    <p class="addNewUserLabel">E-mail</p>
    <input name="email" id="email" type="text" class="form-control" value="${user.email}">
</div>
<div class="form-group">
    <p class="addNewUserLabel">Əlaqə</p>
    <input name="phone" type="text" class="form-control" value="${user.contactNumber}">
</div>
<div class="form-group">
    <p class="addNewUserLabel">Ünvan</p>
    <input name="address" type="text" class="form-control" value="${user.address}">
    <input type="hidden" name="idUser" value="${user.idUser}">
    <input type="hidden" name="isReturning" value="${isReturning}">
</div>
<div class="form-group">
    <button class="addNewUserButton addNewUserInfoWrapSecondButton" type="button">Növbəti</button>
</div>

<script>
    $(".addNewUserInfoWrapSecondButton").click(function () {
        $(".addNewUserInfoWrapSecond").hide();

        $.ajax({
            url: '/getAllModel',
            dataType: 'html',
            success: function (data) {
                $(".addNewUserInfoWrapThird").html(data);
                $(".addNewUserInfoWrapThird").show();
            }
        });

    });
</script>