var qrcode = new QRCode("qrcode");
$(document).ready(function () {


    $(".addNewUserInfoWrapFirstButton").click(function () {
        var fin = $('#fin').val();

        $.ajax({
            url: '/getUserByFin',
            data: 'fin='+fin,
            dataType: 'html',
            success: function (data) {
                $(".addNewUserInfoWrapSecond").html(data);
                $(".addNewUserInfoWrapSecond").show();
            }

        });

        $(".addNewUserInfoWrapFirst").hide();
        // $(".addNewUserInfoWrapSecond").show();
    });


    $(".chooseRepairerButton").click(function () {

    });

    $(".addNewUserButtonPrint").click(function () {
        window.print();
    })



    


});

function processToFinal(id, fullName, contactNumb) {


    $.ajax({
        url: '/generateQR',
        dataType: 'text',
        success: function (data) {
            var url = window.location.href;
            var arr = url.split("/");
            var result = arr[0] + "//" + arr[2];
            qrcode.makeCode(result+'/user/track?t='+data);
            $(".trackingNumb").text(data);
            $('[name="trackingNumber"]').val(data);
            $(".repairerName").text(fullName);
            $(".repairerPhone").text(contactNumb);
            $(".title").text($('[name="title"]').val());
            $(".device").text($( "#selectBrand option:selected" ).text()+' '+$( "#selectDevice option:selected" ).text());
            $(".startDate").text((new Date().getMonth()+1)+'-'+new Date().getDate()+'-'+new Date().getFullYear());
            $(".price").text($('[name="price"]').val());
            $(".user").text($('[name="fullName"]').val());
            $(".email").text($('#email').val());
            $(".fin").text($('[name="fin"]').val());
            $(".phone").text($('[name="phone"]').val());
            $(".address").text($('[name="address"]').val());
            $(".addNewUserInfoWrapFourth").hide();
            $(".addNewUserInfoWrapFifth").show();
            $('[name="idRepairer"]').val(id);

        }
    });


}