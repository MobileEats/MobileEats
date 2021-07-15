// var slideIndex = 0;
// showSlides(slideIndex);
//
// // Next/previous controls
// function plusSlides(n) {
//     showSlides(slideIndex += n);
// }
//
// // Thumbnail image controls
// function currentSlide(n) {
//     console.log(n);
//     showSlides(slideIndex = n);
// }
//
// function showSlides(newIndex) {
//     var i;
//     var slides = document.getElementsByClassName("mySlides");
//     var dots = document.getElementsByClassName("dot");
//     if (newIndex > slides.length - 1) {
//         newIndex = 0
//     }
//     if (newIndex < 0) {
//         newIndex = slides.length - 1
//     }
//     slides[slideIndex].removeClass('active').fadeOut();
//     slides[newIndex].addClass('active').fadeIn();
//     // for (i = 0; i < slides.length; i++) {
//     //     slides[i].style.display = "none";
//     // }
//     // for (i = 0; i < dots.length; i++) {
//     //     dots[i].className = dots[i].className.replace(" active", "");
//     // }
//     slides[slideIndex].style.display = "block";
//     dots[slideIndex].className += " active";
// }

//using the slick jquery library for slideshows
$(document).ready(() => {
    $('.slideshow').slick({
        adaptiveHeight: true,
        draggable: true,
        arrows: false,
        dots: true,
        appendDots: $('.dots-container'),
        slidesToShow: 1,
        autoplay: true,
        centerMode:false,
        cssEase:'ease',
    });
})
