$(document).ready(function() {

            $("#activate_user_checkbox").click(function () {
                  $('input:checkbox').not(this).prop('checked', this.checked);
            });

            $("#category_user_checkbox").click(function () {
                  $('input:checkbox').not(this).prop('checked', this.checked);
            });

            $("#bnk48_member_checkbox").click(function () {
                  $('input:checkbox').not(this).prop('checked', this.checked);
            });

            $("#delivery_checkbox").click(function () {
                  $('input:checkbox').not(this).prop('checked', this.checked);
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


});
