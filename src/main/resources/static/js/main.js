const cartCounter = document.querySelector("#cart-counter")
const loggedInUser = document.querySelector("#loggedInUser");
let cartAmount = 0;

$.ajaxSetup({
    beforeSend: function (xhr, settings) {
        if (settings.type == 'POST' || settings.type == 'PUT'
            || settings.type == 'DELETE') {
            if (!(/^http:.*/.test(settings.url) || /^https:.*/
                .test(settings.url))) {
                // Only send the token to relative URLs i.e. locally.
                xhr.setRequestHeader("X-XSRF-TOKEN",
                    Cookies.get('XSRF-TOKEN'));
            }
        }
    }
});

$(function () {
    if (loggedInUser) {
        getCartSizeAndSetCounterToSize()
    }
    highlightCategoryChosen()
    addToCart()
    getAllOrders()
    changeCartItemQuantity();
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

function highlightCategoryChosen() {
    let split = window.location.href.split("/");
    let category = split[split.length - 1];
    let categoryBtns = document.querySelectorAll("#category");
    categoryBtns.forEach(value => {
        if (value.dataset.categoryname === category) {
            value.classList.add("is-info")
        }
    })
}

function addToCart() {
    $('.addbtn').each(function () {
        let btn = this;
        let id = btn.dataset.productid;
        btn.addEventListener('click', function () {
            $.ajax({
                url: "/ajax/cart/add/" + id,
                type: "post",
                success: function () {
                    cartAmount++;
                    cartCounter.innerHTML = cartAmount.toString();
                }
            })
        })
    })
}

function getAllOrders() {
    $('#loadOrders').on('click', function () {
        $.ajax({
            url: "/ajax/order",
            type: "get",
            success: function (resp) {
                console.log(resp)
            }
        })
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
}

function ajaxIncreaseQuantity(id) {
    $.ajax({
        url: "/api/cart/increase/" + id,
        type: "PUT",
        success: function () {
            location.reload();
        }
    })
}






