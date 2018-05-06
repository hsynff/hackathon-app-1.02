<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Təmir Haqqında</title>
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

    <c:import url="view_elements/navigation_repairer.jsp"/>

    <section class="main">
        <c:import url="view_elements/menu_repairer.jsp"/>
        <script type="text/javascript">
            $('.current').removeClass('mainLeftLiActive');
            $('.history').addClass('mainLeftLiActive');
        </script>
        <div class="col-sm-10">
            <br>
            <br>
            <div class="mainRightBar moreWrap">
                <div class="col-sm-12">
                    <div class="row moreRow">
                        <div class="col-sm-6 moreCol">
                            <ul class="moreUl">
                                <li>
                                    Problem:
                                </li>
                                <li>
                                    ${repair.title}
                                </li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    Cihaz:
                                </li>
                                <li>
                                    ${repair.device.brand}  ${repair.device.model.model}
                                </li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    Başlama vaxtı
                                </li>
                                <li>${repair.startDate}</li>
                            </ul>

                            <ul class="moreUl">
                                <li>
                                    Bitmə vaxtı
                                </li>
                                <li>${repair.endDate}</li>
                            </ul>
                        </div>
                        <div class="col-sm-6 moreCol">
                            <ul class="moreUl">
                                <li>
                                    Xidmət xərci(AZN):
                                </li>
                                <li> ${repair.price}</li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    Təmir statusu
                                </li>
                                <li>${repair.progresses.get(repair.progresses.size()-1).percent}</li>
                            </ul>
                            <br>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="row moreRow">
                        <div class="col-sm-6 moreCol">
                            <ul class="moreUl">
                                <li>
                                    Ad:
                                </li>
                                <li>
                                    ${repair.user.fullName}
                                </li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    E-mail:
                                </li>
                                <li>
                                    ${repair.user.email}
                                </li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    tel:
                                </li>
                                <li>
                                    ${repair.user.contactNumber}
                                </li>
                            </ul>
                        </div>
                        <div class="col-sm-6 moreCol">
                            <br>
                            <br>
                            <br>
                            <ul class="moreUl">
                                    <li>
                                        Fin-kod:
                                    </li>
                                    <li>
                                        ${repair.user.fin}
                                    </li>
                                </ul>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <div class="col-sm-12">
                    <h3>Təmir tarixçəsi</h3>
                    <div class="row">
                        <c:forEach items="${repair.progresses}" var="progList">
                            <div class="col-md-12 moreUpdateCol">
                                <div class="col-md-6">
                                    <ul class="moreUpdateUL">
                                        <li>Təmir statusu:</li>
                                        <li>${progList.percent} %</li>
                                    </ul>
                                </div>
                                <div class="col-sm-6">
                                    <ul class="moreUpdateUL">
                                        <li>Yenilənmə tarixi:</li>
                                        <li>${progList.date}</li>
                                    </ul>
                                </div>
                                <div class="col-md-12">
                                    <ul class="moreUpdateULComment">
                                        <li>Rəy:</li>
                                        <li>${progList.comment} </li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>



    <!-- jQuery library -->
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <!-- DataTable JavaScript -->
    <script type="text/javascript" charset="utf8" src="datatables.js"></script>
    <!-- DataTable JavaScript -->
    <script>
        $('#myTable').DataTable();
    </script>

</body>

</html>