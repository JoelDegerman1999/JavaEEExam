$('.addbtn').on("click", function () {
    console.log($(this).closest("div").parent().find("img"))
    $(this).closest("div")
        .parent()
        .find("img")
        .clone()
        .addClass("zoom")
        .appendTo('body')
    setTimeout(function () {
        $(".zoom").remove()
    }, 1000)

    $(".fa-shopping-cart").addClass("wiggle")
    setTimeout(function () {
        $('.fa-shopping-cart').removeClass("wiggle")
    },1000)
})