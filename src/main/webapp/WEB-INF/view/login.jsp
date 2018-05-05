<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Daxil ol</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Font-awesome CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <!-- Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body class="adminLoginBody">

<c:if test="${message ne null}">
    <script type="text/javascript">
        alert('${message}');
    </script>
</c:if>


    <div class="container">
        <div class="row ">
            <div class="col-sm-offset-3 col-sm-6 centerWrap">
                <br>
                <h4 class="adminLoginIntro">Daxil olun</h4>
                <br>
                <form action="/doLogin" method="post">
                    <div class="form-group">
                        <input placeholder="İstifadəçi adınızı daxil edin" type="text" class="form-control adminLoginUsername" name="username">
                    </div>
                    <br>
                    <div class="form-group">
                        <input placeholder="Şifrənizi daxil edin" type="password" class="form-control adminLoginPassword" name="pwd">
                    </div>
                    <br>
                    <div class="form-group adminLoginRadioWrap">
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="1">Təmirçi</label>
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="2">Menecer</label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="adminLoginButton btn">Daxil ol</button>
                    </div>
                </form>
            </div>
        </div>
    </div>







    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- jQuery library -->
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
</body>

</html>