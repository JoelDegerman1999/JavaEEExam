const openModal = () => {
    const modal = document.querySelector("#cart-modal")
    const modalBg = document.querySelector("#cart-modalbg")
    const cart = document.querySelector("#shopping-cart")
    cart.addEventListener("click", () => {
        modal.classList.toggle('is-active')
    })
    modalBg.addEventListener('click', () => {
        modal.classList.toggle('is-active')
    })
}
openModal();

const addToCart = () => {
    $(".cart-submit").each(function () {
        let button = this;
        let id = button.dataset.productid;
        button.addEventListener("click", () => ajaxAddToCart(id))
    })
}

function ajaxAddToCart(id) {
    console.log("hej")
    $.ajax({
        url: "/cart/add/" + id,
        type: "POST",
        success: function () {
            location.reload();
        }
    })
}
addToCart()


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







