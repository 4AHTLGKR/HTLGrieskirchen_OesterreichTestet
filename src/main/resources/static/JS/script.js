console.log("fdfagwre");

let birthdate;

let screeningStationId;


$('#birthdate').on('input', function(){
    birthdate=$(this).val();
    console.log($(this).val());
});

$('.screen-place li').click(function() {
    $(".selected-screeningstation").html($(this).text());
    $('#selectedScreeningStationId').val($(this).children('input').first().val());
    console.log($('#selectedScreeningStationId').val());
    console.log($(this).children('input').first().val());
    $(this).toggleClass('active').siblings().removeClass('active');
});

$('.time-list li').click(function() {
    $(this).toggleClass('active').siblings().removeClass('active');
    changeDateTime();
    console.log($('#selectedTimeSlot').val());
});

$('#dateSlot').on('input', function(){
    changeDateTime();
    console.log($('#selectedTimeSlot').val());
});

function changeDateTime()
{
    $("#selectedTimeSlot").val($('#dateSlot').val() + " " + $(".active").text());

}