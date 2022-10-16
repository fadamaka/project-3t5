<script>
    let quantity = 0;
    function addToCart() {
      quantity = ++quantity;
    }
</script>

<div>Your shopping cart has {quantity} items.</div>
<button on:click="{addToCart}">Add To Cart</button>