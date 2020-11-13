
$(function () {
    $('#shopping-cart').on("click", function () {
        window.location = "/cart"
    })
    // console.log(document.cookie)
})
$('.addbtn').each(function () {
    let btn = this;
    let id = btn.dataset.productid;
    btn.addEventListener('click', function () {
        console.log(decodeURIComponent(document.cookie));
        $.ajax({
            url: "/api/cart/add/" + id,
            type: "POST",
            success: function () {
                window.location.reload()
            }
        })
    })
})






