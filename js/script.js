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
console.log(filename);
    $(".overlay").fadeIn(500);
    $(".new_post_field").show();
    $(".new_post_button").show();
    $('.bottom_new_post > .separator').show();

    if (filename == "") {
      $("#image-preview").hide()
    }else{
      $("#image-preview").show();
    }
  });

  $(".overlay").not(".text").click(function() {
    $(".overlay").fadeOut(500);
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
          var newListItem = "<li><input type='checkbox' name='type' value='" + data[row].id + "'/>" + data[row].name + "</li>";
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



  // Post

});
