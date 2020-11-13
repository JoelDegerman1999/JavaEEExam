

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







