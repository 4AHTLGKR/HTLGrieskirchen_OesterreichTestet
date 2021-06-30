
$('.screen-place li').click(function() {
    $(".selected-screeningstation").html($(this).text());
    $('#selectedScreeningStationId').val($(this).children('input').first().val());
    $(this).toggleClass('active').siblings().removeClass('active');
});

$('.time-list li').click(function() {
    $(this).toggleClass('active').siblings().removeClass('active');
    changeDateTime();
});

$('#dateSlot').on('input', function(){
    changeDateTime();
});

function changeDateTime()
{
    $("#selectedTimeSlot").val($(".active").children('input').first().val());

}