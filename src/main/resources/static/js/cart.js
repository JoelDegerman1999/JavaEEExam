const modal = document.querySelector("#cart-modal")
const modalBg = document.querySelector("#cart-modalbg")
const cart = document.querySelector("#shopping-cart")


cart.addEventListener("click", () => {
    modal.classList.toggle('is-active')
})

modalBg.addEventListener('click', () => {
    modal.classList.toggle('is-active')
})




