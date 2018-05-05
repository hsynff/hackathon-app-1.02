<%@ page import="java.util.Collections" %>
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

</head>

<body class="">

    <c:import url="view_elements/navigation_repairer.jsp"/>

    <section class="main">
        <c:import url="view_elements/menu_repairer.jsp"/>
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
                                    İzləmə kodu:
                                </li>
                                <li>
                                    ${repair.trackingNumber}
                                </li>
                            </ul>
                            <ul class="moreUl">
                                <li>
                                    Başlama vaxtı
                                </li>
                                <li> ${repair.startDate}</li>
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
                                <li> ${repair.progresses.get(repair.progresses.size()-1).percent} %</li>
                            </ul>
                            <br>
                            <ul class="moreUl">
                                <li>
                                    <button class="moreButtons" type="button" data-toggle="modal" data-target="#myModal">Statusu yenilə</button>
                                </li>
                                <li>
                                    <button class="moreButtons" type="button" data-toggle="modal" data-target="#myModalSecond">Təmiri dayandır</button>
                                </li>
                            </ul>
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
                                    Tel.:
                                </li>
                                <li>
                                    ${repair.user.contactNumber}

                                </li>
                            </ul>
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



    <!-- Modal update -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Statusu yeniləyin</h4>
                </div>
                <div class="modal-body">
                    <form action="/updateStatus">
                        <div class="form-group">
                            <p>Status faizini daxil edin</p>
                            <select class="form-control" name="percent">
                                <option selected value="">0%</option>
                                <option value="10">10%</option>
                                <option value="20">20%</option>
                                <option value="30">30%</option>
                                <option value="40">40%</option>
                                <option value="50">50%</option>
                                <option value="60">60%</option>
                                <option value="70">70%</option>
                                <option value="80">80%</option>
                                <option value="90">90%</option>
                                <option value="100">100%</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <p>Rəyinizi daxil edin</p>
                            <textarea class="form-control" name="comment"></textarea>
                        </div>
                        <input type="hidden" name="id" value="${repair.idRepair}">
                        <c:choose>
                            <c:when test="${empty repair.user.email}">
                                <input type="hidden" name="userEmail" value="">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="userEmail" value="${repair.user.email}">
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${empty repair.user.contactNumber}">
                                <input type="hidden" name="userPhone" value="">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="userPhone" value="${repair.user.contactNumber}">
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group">
                            <button class="updateModalButton" type="submit">Yenilə</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Bağla</button>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal update -->
    <div id="myModalSecond" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Təmiri dayandırın</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-inline">
                            <p>Təmir dayandırılsın?</p>
                            <button class="updateModalButton" type="submit">Təsdiqlə</button>
                            <button class="updateModalButton" type="submit">Ləğv et </button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Bağla</button>
                </div>
            </div>

        </div>
    </div>


    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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