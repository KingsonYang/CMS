/**
 * Created by hs on 2019.4.16.
 */
$(function(){
    $(".nav li").click(function() {
        $(".active").removeClass('active');
        $(this).addClass("active");
    });
})

