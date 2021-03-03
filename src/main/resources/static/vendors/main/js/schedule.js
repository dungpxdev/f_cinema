$(document).ready(function() {
  $('#addFilm').click(function() {
    var name = $('#timetableFilm').val();
    var duration = parseInt($('#timetableDuration').val()) * (5 / 3); /*must be in mins*/
    var dur = $('#timetableDuration').val();
    var startTime = $('#timetableStart').val();

    /*get the hour*/
    var hour = parseInt($('#timetableStart').val()) * 100;
    /*get the minutes*/
    var min = $('#timetableStart').val();
    var res = min.split(":");
    var minute = parseInt(res[1]) / 60 * 100;
    var t = hour + parseInt(minute);
    var screen = parseInt($('#timetableScreen').val()) * 10;
    $('.timetable .layoutdesign').append("<span class='film' style='top:" + 5 + "vh;left:calc(10vw + " + t + "px); width:" + duration + "px' data-start='" + startTime + "'>" + name + " -"+dur+"mins</span>");
  });

  $('.layoutdesign').on("click", ".film", function() {
    $(this).toggleClass("activeFilm");
    $('#deleteFilm').show();
  });
  $('#deleteFilm').click(function() {
    $('.activeFilm').remove();
    $(this).hide();
  });
});
