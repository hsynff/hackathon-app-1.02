$(document).ready(function () {


    $(".addNewUserInfoWrapFirstButton").click(function () {
        $(".addNewUserInfoWrapFirst").hide();
        $(".addNewUserInfoWrapSecond").show();
    });
    $(".addNewUserInfoWrapSecondButton").click(function () {
        $(".addNewUserInfoWrapSecond").hide();
        $(".addNewUserInfoWrapThird").show();
    });
    $(".addNewUserInfoWrapThirdButton").click(function () {
        $(".addNewUserInfoWrapThird").hide();
        $(".addNewUserInfoWrapFourth").show();
    });
    $(".chooseRepairerButton").click(function () {
        $(".addNewUserInfoWrapFourth").hide();
        $(".addNewUserInfoWrapFifth").show();
    });

    $(".addNewUserButtonPrint").click(function () {
        window.print();
    })

    var qrcode = new QRCode("qrcode");
    qrcode.makeCode("http://barama.az");
    


});