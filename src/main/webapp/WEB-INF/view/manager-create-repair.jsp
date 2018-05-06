<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title class="pagePrintTitleRemover">Təmir detalları</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Font-awesome CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <!-- Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <!-- DataTable CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body class="">

    <c:import url="view_elements/navigation_manager.jsp"/>

    <section class="main">
        <c:import url="view_elements/menu_manager.jsp"/>
        <script type="text/javascript">
            $('.repairers').removeClass('mainLeftLiActive');
            $('.newRepairer').removeClass('mainLeftLiActive');
            $('.newTask').addClass('mainLeftLiActive');
            $('.clients').removeClass('mainLeftLiActive');
        </script>
        <form action="/createRepair" style="overflow: hidden">
        <div class="col-sm-10">
            <div class="mainRightBar">
                <div class="addNewUserWrap addNewUserInfoWrapFirst">
                    <br>
                    <h3>İstifadəçi məlumatları</h3>
                    <br>

                        <div class="form-group">
                            <p class="addNewUserLabel">Fin-kod</p>
                            <input name="fin" type="text" id="fin" class="form-control">
                        </div>
                        <div class="form-group">
                            <button class="addNewUserButton addNewUserInfoWrapFirstButton" type="button">Növbəti</button>
                        </div>

                </div>
                <div class="addNewUserWrap addNewUserInfoWrapSecond">
                    <%--loads from ajax call--%>
                </div>
                <div class="addNewUserWrap addNewUserInfoWrapThird">

                </div>
                <div class="addNewUserWrap addNewUserWrapForTable addNewUserInfoWrapFourth">
                    <br>
                    <h3>Təmirçi</h3>
                    <div class="tableWrap">
                        <table id="myTable" class="display">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Ad / Soyad</th>
                                    <th>Əlaqə</th>
                                    <th>İş sayı</th>
                                    <th>Seç</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${staffList}" var="staff">
                                <tr>
                                    <td>${staff.idStaff}</td>
                                    <td>${staff.fullName}</td>
                                    <td>${staff.contactNumber}</td>
                                    <td>${staff.activeTasks}</td>
                                    <td>
                                        <button class="chooseRepairerButton btn" type="button" onclick="processToFinal('${staff.idStaff}','${staff.fullName}','${staff.contactNumber}');">Seç</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="addNewUserInfoWrap addNewUserInfoWrapFifth">
                    <div class="col-sm-6 addNewUserInfoColMargin">
                        <div class="infoBorder infoBorderFirst">
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Problem:
                                </li>
                                <li class="title">
                                    Ekran təmiri
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Cihaz:
                                </li>
                                <li  class="device">
                                    Apple Iphone 5s
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Başlama vaxtı:
                                </li>
                                <li  class="startDate">
                                    05.05.2018
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl addNewUserInfoUlBig">
                                <li>
                                    Xidmət xərci(AZN):
                                </li>
                                <li class="price">85AZN</li>
                            </ul>
                            <ul class="addNewUserInfoUl addNewUserInfoUlBig">
                                <li>İzləmə kodu:</li>
                                <li class="trackingNumb">123-456-789</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-6 addNewUserInfoColMargin">
                        <div class="infoBorder infoBorderSecond">
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Müştəri:
                                </li>
                                <li class="user">
                                    Əli Məmmədov
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    E-mail:
                                </li>
                                <li class="email">
                                    ali_mammadov@gmail.com
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Fin-kod:
                                </li>
                                <li class="fin">
                                    AZ123456
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Telefon:
                                </li>
                                <li class="phone">
                                    050 555 55 55
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Ünvan:
                                </li>
                                <li class="address">
                                    Bakı ş, Yasamal ray, ev 15.
                                </li>
                            </ul>
                        </div>
                    </div>
                    <br>
                    <div class="col-sm-6 addNewUserInfoColMargin">
                        <div id="qrcode"></div>
                    </div>
                    <div class="col-sm-6 addNewUserInfoColMargin">
                        <div class="infoBorderRepairer infoBorderFirst">
                            <input name="idRepairer" type="hidden">
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Təmirçi adı:
                                </li>
                                <li class="repairerName">
                                    Əli Məmmədov
                                </li>
                            </ul>
                            <ul class="addNewUserInfoUl">
                                <li>
                                    Əlaqə:
                                </li>
                                <li class="repairerPhone">
                                    050 555 55 55
                                </li>
                            </ul>
                        </div>
                        <br>
                        <div class="form-inline addNewUserButtonsAlign">
                            <input type="hidden" name="trackingNumber">
                            <button class="addNewUserButton" type="submit">Yadda saxla</button>
                            <button class="addNewUserButton addNewUserButtonPrint" type="button">Çap et</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </section>


    <!-- jQuery library -->
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <!-- QR JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/qrcode.js"></script>
    <!-- QR JavaScript -->
    <!-- DataTable JavaScript -->
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/js/datatables.js"></script>
    <!-- DataTable JavaScript -->
    <script>
        $('#myTable').DataTable();
    </script>
    <!-- Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
    <!-- Custom JavaScript -->

</body>

</html>