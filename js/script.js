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


  $('.new_post').click(function() {
    var filename = $('#image-upload').val();
    $(".overlay").fadeIn(200);
    $(".new_post_field").show();
    $(".new_post_button").show();
    $('.bottom_new_post > .separator').show();
    $('.overlay').css({
      'z-index': 2,
    })
    if (filename == "") {
      $("#image-preview").hide()
    } else {
      $("#image-preview").show();
    }
  });

  $(".overlay").not(".text").click(function() {
    $('.exit_thumbnail_popup').hide();
    $(".overlay").fadeOut(200);
    $(".new_post_field").hide();
    $('#set_area_category').hide();
    $('#main_category_type').hide();
    $('.bottom_new_post > .separator').hide();
    $(".new_post_button").hide();
    $("#image-preview").hide();
    $(".post_thumbnail_popup").hide();
    $('body').css({
      'overflow-y': 'scroll',
    })
  });

  //Hidden New Post Field
  $(".new_post_field").hide();
  $(".new_post_button").hide();
  $('.bottom_new_post > .separator').hide();
  $("#image-preview").hide();

  $('.text_area_new_post').keyup(function() {
    var text_length = $(this).val().length;


    if (text_length < 100) {
      $(this).css({
        fontSize: 26
      });
    } else {
      $(this).css({
        fontSize: 16
      });
    }
  });


  // CATEGORY Ajax
  function updateCategorySet() {
    $('.category_type').empty();
    $.ajax({
      url: 'http://market48.toppy.in.th/api/category',
      dataType: 'json',
      type: 'GET',
      // This is query string i.e. country_id=123
      data: {
        super_id: $('#category').val()
      },
      success: function(data) {
        if (data.length == 0) {
          $('#set_area_category').hide();
          $(".overlay").not(".text").click(function() {
            $('#set_area_category').hide();
          });

          $('.new_post').click(function() {
            $('#set_area_category').hide();
          });
        } else {
          $('#set_area_category').show();
          $('.new_post').click(function() {
            $('#set_area_category').show();
          });
        }

        $('#category_set').empty(); // clear the current elements in select box
        for (row in data) {
          // console.log(row)
          $('#category_set').append($('<option></option>').attr('value', data[row].id).text(data[row].name));
        }
        updateCategoryType();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert(errorThrown);
      }
    });
  }

  function updateCategoryType() {
    $.ajax({
      url: 'http://market48.toppy.in.th/api/category',
      dataType: 'json',
      type: 'GET',
      // This is query string i.e. country_id=123
      data: {
        super_id: $('#category_set').val()
      },
      success: function(data) {
        $('.category_type').empty(); // clear the current elements in select box

        if (data.length == 0) {
          $('#main_category_type').hide();

          $(".overlay").not(".text").click(function() {
            $('#main_category_type').hide();
          });

          $('.new_post').click(function() {
            $('#main_category_type').hide();
          });

        } else {
          $('#main_category_type').show();
          $('.new_post').click(function() {
            $('#main_category_type').show();
          });
        }
        for (row in data) {
          var newListItem = "<li><input id='new_post_" + data[row].id + "' type='checkbox' name='type' value='" + data[row].id + "'/><label for='new_post_" + data[row].id + "'>" + data[row].name + "</label></li>";
          $('.category_type').append(newListItem);
        }
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert(errorThrown);
      }
    });
  }


  $('#category').change(updateCategorySet);
  $('#category_set').change(updateCategoryType);








  $('#filter_category').change(function() {
    $.ajax({
      url: 'http://market48.toppy.in.th/api/category',
      dataType: 'json',
      type: 'GET',
      // This is query string i.e. country_id=123
      data: {
        super_id: $('#filter_category').val()
      },
      success: function(data) {
        if (data.length == 0) {
          $("#filter_set_area_category").hide();
        }else{
          $("#filter_set_area_category").show();
        }
        $('#filter_set_area_category_input').empty(); // clear the current elements in select box
        for (row in data) {
          // console.log(row)
          $('#filter_set_area_category_input').append($('<option></option>').attr('value', data[row].id).text(data[row].name));
        }
        updateFilterCategoryType();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert("w");
      }
    });
  });


  function updateFilterCategoryType() {
    $.ajax({
      url: 'http://market48.toppy.in.th/api/category',
      dataType: 'json',
      type: 'GET',
      // This is query string i.e. country_id=123
      data: {
        super_id: $('#filter_set_area_category_input').val()
      },
      success: function(data) {
        $('.filter_category_type').empty(); // clear the current elements in select box

        if (data.length == 0) {
          $("#filter_main_category_type").hide();
        } else {
          $("#filter_main_category_type").show();
        }
        for (row in data) {
          var newListItem = "<li><input id='filter_" + data[row].id + "' type='checkbox' name='type' value='" + data[row].id + "'/><label for='filter_" + data[row].id + "'>" + data[row].name + "</label></li>";
          $('.filter_category_type').append(newListItem);
        }
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert(errorThrown);
      }
    });
  }


  $('#filter_set_area_category_input').change(updateFilterCategoryType);
  $("#filter_set_area_category").hide();






  var filename = $('#image-upload').val();
  if (filename == "") {
    $("#image-preview").hide()
  }
  $('#image-upload').change(function() {
    var filename = $('#image-upload').val();
    if (filename != "") {
      $("#image-preview").show()
    } else {
      $("#image-preview").hide()
    }
  });





  // Limit post
  var minimized_elements = $('p.minimize');

  minimized_elements.each(function() {
    var t = $(this).text();
    if (t.length < 200) return;

    $(this).html(
      t.slice(0, 200) + '<span> </span><br><a href="#" class="more">ดูเพิ่มเติม</a>' +
      '<span style="display:none;">' + t.slice(100, t.length) + ' <br><a href="#" class="less">แสดงน้อยลง</a></span>'
    );

  });

  $('a.more', minimized_elements).click(function(event) {
    event.preventDefault();
    $(this).hide().prev().hide();
    $(this).next().show();
  });

  $('a.less', minimized_elements).click(function(event) {
    event.preventDefault();
    $(this).parent().hide().prev().show().prev().show();
  });


  //Scroll
  // console.log($("div#post").length);

  $('.contact_button').click(function(){
    var contactSelector = "#post_id_"+$(this).attr("post-id")+"_contact";
    $(contactSelector).toggle();
  });



// $(window).scroll(function(){
//   var windowScroll = $(window).scrollTop();
//   var post = $(".post");
//   for(var i = 0; i < post.length; i++){
//     // console.log(post[i]);
//     var sHeight = post[i].offsetHeight;
//     var offsets = post[i].offsetTop;
//
//     if(windowScroll > offsets && windowScroll < offsets + sHeight) {
//       var postSelector = $(post[i]).attr('post-id');
//
//         $(post[i]).css({
//         "background-color": "#000",
//         "top": 0,
//
//       });
//     }else{
//       $(post[i]).css({
//         "background-color": "#fff",
//       })
//     }
//   }
//
// });



  //LIKE AND dislike
  var like = parseInt($(".like span").text());
  var dislike = parseInt($(".dislike span").text());

  function kFormatter(num) {
    if(num >= 1000 && num <= 999999){
      return num >= 1000 ? (num/1000).toFixed(1) + 'K' : num
    }else if(num >= 1000000){
      return num >= 1000000 ? (num/1000000).toFixed(1) + 'M' : num
    }

  }

  $(".like span").text(kFormatter(like));
  $(".dislike span").text(kFormatter(dislike));

  function likeTab(like , dislike){
    var sumLikeDislike = like + dislike;
    var likeTabWidth = (like/sumLikeDislike)*100;
    return  parseInt(likeTabWidth);

  }

  $(".like_tab").css({
    'width': likeTab(like, dislike)+'%',
  });


  // UPLOAD images
    $.extend({
      readURL : function (input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var input_name = "#"+$(input).attr('id')+"-preview";
        reader.onload = function(e) {
            $(input_name).css('background-image', 'url('+e.target.result +')');
            $(input_name).css("background-size", "cover");
            $(input_name).css("background-position", "center center");
            $(input_name).show();
            $(input_name+"> div > button").click(function(){
              $(input_name).hide();
              $(input).val("");
            });
        }

        reader.readAsDataURL(input.files[0]);
    }

    }

    });

$(".image-preview").hide();

$('.post_thumbnail img').click(function(){
  var src = $(this).attr('src');
  $('.post_thumbnail_popup img').attr('src', src);
  $(".overlay").fadeIn(200);
  console.log($(window).scrollTop());
  $('.post_thumbnail_popup').show();
  $('.overlay').css({
    'z-index': 4,
  })
  $('.exit_thumbnail_popup').show();
  $('body').css({
    'overflow': 'hidden',
  })
  $('.post_thumbnail_popup').css({
    'top': ($(window).scrollTop()-50)+'px',
  });
});

$('.exit_thumbnail_popup').click(function(){
  $('.post_thumbnail_popup').hide();
  $(".overlay").fadeOut(200);
  $(this).hide();
});



});
