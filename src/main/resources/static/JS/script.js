console.log("fdfagwre");

let birthdate;

let screeningStationId;


$('#birthdate').on('input', function(){
    birthdate=$(this).val();
    console.log($(this).val());
});

$('.location-list li').click(function() {
    $(".selected-screeningstation").html($(this).text());
    $('#selectedScreeningStationId').val($(this).children('input').first().val());
    console.log($('#selectedScreeningStationId').val());
    console.log($(this).children('input').first().val());
    $(this).toggleClass('active').siblings().removeClass('active');
});

