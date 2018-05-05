<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 5/5/2018
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="mainNav">
    <div class="container mainNavWrap">
        <div class="row">
            <ul class="mainNavRepaierUl">
                <li>Təmirçi:</li>
                <li>${staff.fullName}</li>
            </ul>
            <ul class="mainNavBarUL">
                <li data-toggle="modal" data-target="#myModalSettings">
                    <i class="fa fa-cog"></i>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-power-off"></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Modal update -->
<div id="myModalSettings" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Şifrənni dəyiş</h4>
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="form-group">
                        <p>Köhnə şifrə</p>
                        <input type="password" class="form-control">
                    </div>
                    <div class="form-group">
                        <p>Təzə şifrə</p>
                        <input type="password" class="form-control">
                    </div>
                    <div class="form-group">
                        <button class="changePasswordButton btn">Dəyiş</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Bağla</button>
            </div>
        </div>

    </div>
</div>