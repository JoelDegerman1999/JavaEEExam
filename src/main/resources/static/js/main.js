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

const changeCartItemQuantity = () => {
    $('.minus').each(function () {
        let minus = this
        let productId = minus.dataset.productid;
        minus.addEventListener("click", () => ajaxDecreaseQuantity(productId))
    })

    $('.plus').each(function () {
        let plus = this
        let productId = plus.dataset.productid;
        plus.addEventListener("click", () => ajaxIncreaseQuantity(productId))
    })
}

function ajaxDecreaseQuantity(id) {
    $.ajax({
        url: "/api/cart/decrease/" + id,
        type: "PUT",
        success: function () {
            location.reload();
        }
    })
}function ajaxIncreaseQuantity(id) {
    $.ajax({
        url: "/api/cart/increase/" + id,
        type: "PUT",
        success: function () {
            location.reload();
        }
    })
}
changeCartItemQuantity();






