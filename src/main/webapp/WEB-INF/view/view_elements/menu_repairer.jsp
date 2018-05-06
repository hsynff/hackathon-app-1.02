<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 5/5/2018
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-2 mainLeftWrapCol">
    <div class="mainLeftWrap">
        <ul class="mainLeftUl">
            <li class="current mainLeftLi mainLeftLiActive">
                <a href="/staff/main">
                    <i class="fa fa-user"></i> Hazırkı təmirlər</a>
            </li>
            <li class="history mainLeftLi">
                <a href="/staff/history">
                    <i class="fa fa-calendar"></i> Tarixçə</a>
            </li>
        </ul>
    </div>
</div>

<c:if test="${message ne null}">
    <script type="text/javascript">
        alert('${message}');
    </script>
</c:if>