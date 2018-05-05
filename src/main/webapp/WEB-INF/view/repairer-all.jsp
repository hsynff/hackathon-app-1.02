<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>İşlərim</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Font-awesome CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <!-- Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <!-- DataTable CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables.css">

</head>

<body class="">

    <c:import url="view_elements/navigation_repairer.jsp"/>

    <section class="main">
       <c:import url="view_elements/menu_repairer.jsp"/>
        <div class="col-sm-10">
            <div class="mainRightBar">
                <div class="tableWrap">
                    <table id="myTable" class="display">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Cihaz</th>
                                <th>Problem</th>
                                <th>Status</th>
                                <th>Xidmət xərci (AZN)</th>
                                <th>Başlama tarixi</th>
                                <th>Müştəri</th>
                                <th>Fin-kod</th>
                                <th>İzləmə kodu</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${repairList}" var="list">
                            <tr>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.idRepair}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.device.brand} ${list.device.model.model}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.title}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.progresses.get(list.progresses.size()-1)} %</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.price}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.startDate}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.user.fullName}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.user.fin}</a>
                                </td>
                                <td>
                                    <a href="/staff/repair/${list.idRepair}">${list.trackingNumber}</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- jQuery library -->
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <!-- DataTable JavaScript -->
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/js/datatables.js"></script>
    <!-- DataTable JavaScript -->
    <script>
        $('#myTable').DataTable();
    </script>

</body>

</html>