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

    if (filename == "") {
      $("#image-preview").hide()
    } else {
      $("#image-preview").show();
    }
  });

  $(".overlay").not(".text").click(function() {
    $(".overlay").fadeOut(200);
    $(".new_post_field").hide();
    $('#set_area_category').hide();
    $('#main_category_type').hide();
    $('.bottom_new_post > .separator').hide();
    $(".new_post_button").hide();
    $("#image-preview").hide();
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

  $('.remove-image-privew').click(function() {
    $("#image-preview").hide();
    $("#image-upload").val("");
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
  var filterDiv = $("#filter").offset().top;
  $(window).scroll(function() {
    //       // console.log($("div#post").length);
    //       var n = 0;
    //       if ($(window).scrollTop() > $("div#post").eq(n).offset().top){
    //         console.log(n);
    //       }else{
    //         n++;
    //       }
    if ($(window).scrollTop() > filterDiv + 10) {
      $('#filter').css({
        'position': 'fixed',
        'top': '10px',
        'z-index': '1',
        'width': '318px'
      });
    } else {
      $('#filter').css({
        'position': 'relative',
        'top': 'auto'
      });

      console.log("1");
    }
  });

  $(".contact_button").click(function(){
    $(".contact_icon").fadeToggle();
//     $(".contact_icon > ul > li").each(function(i) {
//     $(this).delay(100 * i).fadeIn(500);
// });
  });

});
