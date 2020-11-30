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
    if (checkIfCartIsEmpty()) {
        $('.cart-blue-dot').hide()
    }
    checkIfCartIsEmpty()
    highlightCategoryChosen()
    checkIfCartIsEmpty()
    addToCart()
    getAllOrders()
    changeCartItemQuantity();
})

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

function checkIfCartIsEmpty() {
    $.ajax({
        url: "/ajax/cart/notempty",
        type: "get",
        success: function (isEmpty) {
            if(!isEmpty) {
                $('.cart-blue-dot').show()
            }
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
                    $('.cart-blue-dot').fadeIn()
                }
            })
        })
    })
}

function getAllOrders() {
    $('#loadOrders').on('click', function () {
        $(this).addClass('is-loading')
        $.ajax({
            url: "/ajax/order",
            type: "get",
            success: function (resp) {
                console.log(resp)
                $('#loadOrders').removeClass('is-loading').hide()
                $('#order-container').append('<div class="box">' + createOrderTable(resp) + '</div>')
            }
        })
    })
}

function createOrderTable(orderList) {
    let tableStart = '<table class="table is-center" style="margin: 0 auto">'
    let tableEnd = '</table>'
    let thead = '<thead>' +
        '<tr>' +
        '<th>Id</th>' +
        '<th>Item</th>' +
        '<th>Quantity</th>' +
        '<th>Grand Total</th>' +
        '</tr>' +
        '</thead>'

    let tbodyStart = '<tbody>'
    let tbodyEnd = '</tbody>'

    let tableRows = [];

    orderList.forEach(order => {
        tableRows.push(`<tr>
        <th>${order.id}</th>
        <th>${order.id}</th>
        <th>${order.id}</th>
        <th>${order.orderGrandTotal}</th>
        </tr>`)
    })

    let table = tableStart + thead + tbodyStart + tableRows + tbodyEnd + tableEnd

    return table


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
        url: "/ajax/cart/decrease/" + id,
        type: "PUT",
        success: function () {
            location.reload();
        }
    })
}

function ajaxIncreaseQuantity(id) {
    $.ajax({
        url: "/ajax/cart/increase/" + id,
        type: "PUT",
        success: function () {
            location.reload();
        }
    })
}






