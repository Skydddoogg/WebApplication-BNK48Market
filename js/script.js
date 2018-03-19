$(document).ready(function() {

  function h(e) {
    $(e).css({
      'height': '40px',
      'overflow-y': 'hidden'
    }).height(e.scrollHeight);
  }
  $('textarea').each(function() {
    h(this);
  }).on('input', function() {
    h(this);
  });


$('.new_post').click(function(){
  $(".overlay").fadeIn(500);
});

$(".overlay").not(".text").click(function(){
  $(".overlay").fadeOut(500);
});

$('.text_area_new_post').keyup(function(){
  var text_length = $(this).val().length;


  if(text_length < 100){
    $(this).css({fontSize:26  });
  }else{
    $(this).css({fontSize:16});
  }
});

});
