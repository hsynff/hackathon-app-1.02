<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Təmirçi əlavə et</title>
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
            $('.newRepairer').addClass('mainLeftLiActive');
            $('.newTask').removeClass('mainLeftLiActive');
            $('.clients').removeClass('mainLeftLiActive');
        </script>

        <div class="col-sm-10">
            <div class="mainRightBar">
                <div class="addRepairerWrap">
                    <br>
                    <h3>Təmirçi Əlavə et</h3>
                    <form action="/add-repairer" method="post">
                        <div class="form-group">
                            <p>Ad / Soyad</p>
                            <input class="form-control" type="text" name="fullName">
                        </div>
                        <div class="form-group">
                            <p>Əlaqə nömrəsi</p>
                            <input class="form-control" type="text" name="contactNumber">
                        </div>
                        <div class="form-group">
                            <p>İstifadəçi adı</p>
                            <input class="form-control" type="text" name="username">
                        </div>
                        <div class="form-group">
                            <p>Şifrə</p>
                            <input class="form-control" type="password" name="pwd">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="addRepairerButton btn">Əlavə et</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <!-- jQuery library -->
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <!-- QR JavaScript -->
    <script src="qrcode.js"></script>
    <!-- QR JavaScript -->
    <!-- DataTable JavaScript -->
    <script type="text/javascript" charset="utf8" src="datatables.js"></script>
    <!-- DataTable JavaScript -->
    <script>
        $('#myTable').DataTable();
    </script>
    <!-- Custom JavaScript -->
    <script src="custom.js"></script>
    <!-- Custom JavaScript -->

</body>

</html>