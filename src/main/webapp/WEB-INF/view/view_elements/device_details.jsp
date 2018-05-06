<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 5/6/2018
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $('.selectModel').on('change', function() {
        $.ajax({
            url: '/getDeviceByModelId',
            data: 'id='+$(this).val(),
            dataType: 'html',
            success: function (data) {
                $('.selectDevice').html(data);
            }
        });
    });

    $(".addNewUserInfoWrapThirdButton").click(function () {
        $(".addNewUserInfoWrapThird").hide();
        $(".addNewUserInfoWrapFourth").show();
    });
</script>
<br>
<h3>Cihaz məlumatları</h3>
<br>

    <div class="form-group addNewUserSelectLeft">
        <p class="addNewUserLabel">Model</p>
        <select name="brand" class="form-control selectModel" id="selectBrand">
            <c:forEach items="${modelList}" var="model">
            <option value="${model.idModel}">${model.model}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <p class="addNewUserLabel">Marka</p>
        <select name="model" class="form-control selectDevice" id="selectDevice">
            <%--ajax data--%>
        </select>
    </div>
    <div class="form-group">
        <p class="addNewUserLabel">Problem</p>
        <textarea name="title" type="text" class="form-control"></textarea>
    </div>
    <div class="form-group">
        <p class="addNewUserLabel">Təmir xərci</p>
        <input name="price" type="number" class="form-control">
    </div>
    <div class="form-group">
        <button class="addNewUserButton addNewUserInfoWrapThirdButton" type="button">Növbəti</button>
    </div>