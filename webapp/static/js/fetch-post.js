var search_detected = false;
var timeout_id;

(function ($) {
    $.fn.loaddata = function (options) {// Settings
        var settings = $.extend({
            loading_gif_url: "/resources/system/ajax-loader.gif", //url to loading gif
            end_record_text: 'ไม่มีโพสต์ที่จะแสดง กลับขึ้นข้างบนสุด', //no more records to load
            data_url: 'posts', //url 
            search_data_url: 'posts/search', //url 
            start_page: 1, //initial page
            loading_animation: "<div class='loading-post'>" +
                    "<div class='panel-effect'>" +
                    "<div class='fake-effect fe-0'></div>" +
                    "<div class='fake-effect fe-1'></div>" +
                    "<div class='fake-effect fe-2'></div>" +
                    "<div class='fake-effect fe-3'></div>" +
                    "<div class='fake-effect fe-4'></div>" +
                    "<div class='fake-effect fe-5'></div>" +
                    "<div class='fake-effect fe-6'></div>" +
                    "<div class='fake-effect fe-7'></div>" +
                    "<div class='fake-effect fe-8'></div>" +
                    "<div class='fake-effect fe-9'></div>" +
                    "<div class='fake-effect fe-10'></div>" +
                    "<div class='fake-effect fe-11'></div>" +
                    "</div>" + "</div>"
        }, options);

        var el = this;
        loading = false;
        end_record = false;

        //-- initial data load
        if (!search_detected)
            contents(el, settings);
        else
            searchContents(el, settings);

        $(window).scroll(function () { //detact scroll
            if ($(window).scrollTop() + $(window).height() >= $(document).height()) { //scrolled to bottom of the page
                if (!search_detected)
                    contents(el, settings); //load normal content chunk 
                else
                    searchContents(el, settings); // load serach result
            }
        });
    };
    //Ajax load function
    function contents(el, settings) {
//        var load_img = $('<img/>').attr('src',settings.loading_gif_url).addClass('loading-image'); //create load image
        var load_img = $(settings.loading_animation);
        var record_end_txt = $('<div/>').text(settings.end_record_text).addClass('no_more_post'); //end record text

        if (loading == false && end_record == false) {
            loading = true; //set loading flag on
            el.append(load_img); //append loading image
            $.post(settings.data_url, {'page': settings.start_page}, function (data) { //jQuery Ajax post
                if (data.trim().length == 0) { //no more records
                    el.append(record_end_txt); //show end record text
                    load_img.remove(); //remove loading img
                    end_record = true; //set end record flag on
                    return; //exit
                }
                loading = false;  //set loading flag off
                load_img.remove(); //remove loading img 
                el.append(data);  //append content 
                settings.start_page++; //page increment
            })
        }
    }

    //Ajax load function
    function searchContents(el, settings) {
//        var load_img = $('<img/>').attr('src',settings.loading_gif_url).addClass('loading-image'); //create load image
        var load_img = $(settings.loading_animation);
        var record_end_txt = $('<div/>').text(settings.end_record_text).addClass('no_more_post'); //end record text

        if (loading == false && end_record == false) {

            loading = true; //set loading flag on
            el.append(load_img); //append loading image
            console.log("SEARCHING...");

            var doThis = function () {
                // read user query
                category_id = $("#filter_category option:selected").val();
                set_id = $("#filter_set_area_category_input option:selected").val();
                types_id = [];
                $("#filter_type input[name='type']:checked").each(function () {
                    types_id.push($(this).val());
                });
                member_id = $("#filter_member option:selected").val();
                deliveries_id = [];
                $(".filter_sub input[name='filter_delivery']:checked").each(function () {
                    deliveries_id.push($(this).val());
                });
                sorting = $("#filter_sorting option:selected").val();

                $.post(settings.search_data_url, {
                    'page': settings.start_page,
                    'category_id': category_id,
                    'set_id': set_id,
                    'types_id': types_id,
                    'member_id': member_id,
                    'deliveries_id': deliveries_id,
                    'sorting': sorting

                }, function (data) { //jQuery Ajax post
                    if (data.trim().length == 0) { //no more records
                        el.append(record_end_txt); //show end record text
                        load_img.remove(); //remove loading img
                        end_record = true; //set end record flag on
                        return; //exit
                    }
                    loading = false;  //set loading flag off
                    load_img.remove(); //remove loading img 
                    el.append(data);  //append content 
                    settings.start_page++; //page increment
                })
            }
            
            timeout_id = setTimeout(doThis, 1000); // wait 1 sec and do it! (onclick มันเร็วไปจนรับค่าไม่ทัน)
        }
    }

})(jQuery);

$("#all-posts").loaddata(); //load the results into element


//$("#search-button")
//  .click(function () {
//   search_detected = true; // set flag
//   $("#all-posts").empty(); // clear previous data
//   $("#all-posts").loaddata(); //load the SEARCH result into element
//   
// });
// 

$(document).on("change", "#filter", function () {
    
    // ถ้า user กดรัวๆ onchange ทำงานรัวๆ ให้เครียร์ queue เก่าที่ wait ไว้ทิ้งก่อน
    if(timeout_id != undefined)
        clearTimeout(timeout_id);
    
    search_detected = true; // set flag
    $("#all-posts").empty(); // clear previous data
    $("#all-posts").loaddata(); //load the SEARCH result into element

});