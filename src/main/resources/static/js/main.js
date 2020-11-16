const cartCounter = document.querySelector("#cart-counter")
const loggedInUser = document.querySelector("#loggedInUser");
let cartAmount = 0;
$(function () {


    if (loggedInUser) {
        getCartSizeAndSetCounterToSize()
    }


    $('.addbtn').each(function () {
        let btn = this;
        let id = btn.dataset.productid;
        btn.addEventListener('click', function () {
            console.log(decodeURIComponent(document.cookie));
            $.ajax({
                url: "/api/cart/add/" + id,
                type: "POST",
                success: function () {
                    cartAmount++;
                    cartCounter.innerHTML = cartAmount.toString();
                }
            })
        })
    })
})

function getCartSizeAndSetCounterToSize() {
    $.ajax({
        url: "/api/cart/size",
        type: "GET",
        success: function (e) {
            cartAmount = e;
            cartCounter.innerHTML = cartAmount.toString();

        }

    })
}






