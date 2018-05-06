<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Müştərilər</title>
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
            $('.newTask').removeClass('mainLeftLiActive');
            $('.clients').addClass('mainLeftLiActive');
        </script>
        <div class="col-sm-10">
            <div class="mainRightBar">
                <div class="clientsWrap">
                    <br>
                    <h3>Müştərilər</h3>
                    <div class="tableWrap">
                        <table id="myTable" class="display">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Ad / Soyad</th>
                                    <th>Əlaqə</th>
                                    <th>Fin</th>
                                    <th>E-mail</th>
                                    <th>Tamamlanmış təmirlər</th>
                                    <th>Davam edən təmirlər</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td>${user.idUser}</td>
                                    <td>${user.fullName}</td>
                                    <td>${user.contactNumber}</td>
                                    <td>${user.fin}</td>
                                    <td>${user.email}</td>
                                    <td>${user.deactiveTasks}</td>
                                    <td>${user.activeTasks}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>





    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <!-- QR JavaScript -->
    <script src="qrcode.js"></script>
    <!-- QR JavaScript -->
    <!-- DataTable JavaScript -->
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/js/datatables.js"></script>
    <!-- DataTable JavaScript -->
    <script>
        $('#myTable').DataTable();
    </script>
    <!-- Custom JavaScript -->
    <script src="custom.js"></script>
    <!-- Custom JavaScript -->

</body>

</html>