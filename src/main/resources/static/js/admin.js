$(function () {
    const deleteBtn = $('#delete')
    const id = deleteBtn.dataset.productid;

    deleteBtn.on("click", deleteProduct(id))
})

function deleteProduct(id) {
    $.ajax({
        url: "/api/admin/delete/" + id,
        type: "DELETE",
        success: function () {
            window.location.reload()
        }
    })
}