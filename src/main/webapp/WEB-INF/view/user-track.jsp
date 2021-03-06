<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>İzləmə</title>
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
            <div class="col-sm-offset-1 col-sm-10 maskWrap">
                <br>
                <br>
                <form action="/user/track" class="maskForm">

                    <div class="col-sm-9 maskFormInputWrap">
                        <label class="maskLabel">İzləmə kodu</label>
                        <input placeholder="123-456-789" type="tel" class="form-control maskInput" name="t" class="">
                    </div>
                    <div class="col-sm-3">
                        <button type="submit" class="maskLoginButton btn">Axtar</button>
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
    <!-- MASK -->
    <script src="${pageContext.request.contextPath}/resources/dist/jquery.inputmask.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/inputmask/phone-codes/phone.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/inputmask/phone-codes/phone-be.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/inputmask/phone-codes/phone-ru.js"></script>
    <script>
        $(document).ready(function () {
            $(".maskInput").inputmask("999-999-999"); //static mask
        });
    </script>
    <!-- MASK -->
</body>

</html>