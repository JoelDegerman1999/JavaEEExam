$(function () {
    $.ajax({
        url: "/api/cart/size",
        type: 'GET',
        success: function (size) {
            if (size == 0) {
                disableButton();
            }
        }
    });


})

function deleteRow(id) {
    $.ajax({
        url: "cart/delete/" + id,
        type: 'DELETE',
        success: function () {
            location.reload()
        }
    });
}

function disableButton() {
    $("button.checkout-button").prop('disabled', true).addClass("checkout-button-disabled");
}