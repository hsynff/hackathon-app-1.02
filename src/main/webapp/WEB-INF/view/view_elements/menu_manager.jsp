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
            <li class="repairers mainLeftLi mainLeftLiActive">
                <a href="/staffManager/main">
                    <i class="fa fa-user"></i> Təmirçilər
                </a>
            </li>
            <li class="newRepairer mainLeftLi">
                <a href="/staffManager/new-repairer">
                    <i class="fa fa-calendar"></i> Yeni təmirçi yarat
                </a>
            </li>
            <li class="newTask mainLeftLi">
                <a href="/staffManager/new-repair">
                    <i class="fa fa-calendar"></i> Yeni təmir işi yarat
                </a>
            </li>
            <li class="clients mainLeftLi">
                <a href="/staffManager/clients">
                    <i class="fa fa-calendar"></i> Müştərilər
                </a>
            </li>
        </ul>
    </div>
</div>

<c:if test="${message ne null}">
    <script type="text/javascript">
        alert('${message}');
    </script>
</c:if>