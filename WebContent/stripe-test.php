stripe_test.php
<br />
<br />
<?php
echo "Form data received:<br />\n";
foreach ($_POST as $key => $value) {
    echo $key . " = " . $value . "<br />\n";
}

?>

<br />
<br />

<h2>Custom Button</h2>
<br />

<script src="https://checkout.stripe.com/checkout.js"></script>

<button id="customButton">Purchase</button>

<script>
var handler = StripeCheckout.configure({
  key: 'pk_test_fXV7CvLKY2vmEkalqQezZUPc'
});

document.getElementById('customButton').addEventListener('click', function(e) {
  // Open Checkout with further options:
  handler.open({
    name: 'Jansal Valley',
    description: 'Website Order',
    zipCode: true,
    billingAddress: true,
    amount: 2000,
    image: 'https://stripe.com/img/documentation/checkout/marketplace.png',
    locale: 'auto',
    token: function(token) {
      // You can access the token ID with `token.id`.
      // Get the token ID to your server-side code for use.
      var token_content = "";
      for (var key in token) {
          if (token.hasOwnProperty(key)) {
              console.log("token." + key + " -> " + token[key] + "\n");
              if (key === "card") {
                  for (var cardKey in token.card) {
                   console.log("token.card." + cardKey + " -> " + token.card[cardKey] + "\n");
                   if (cardKey === "metadata") {
                      for (var metaKey in token.card.metadata) {
                        console.log("token.card.metadata." + metaKey + " -> " + token.card.metadata[metaKey] + "\n");
                       }
                   }
                  }
              }
          }
      }
    }
  });
  e.preventDefault();
});

// Close Checkout on page navigation:
window.addEventListener('popstate', function() {
  handler.close();
});
</script>

<br />
<br />
<h2>Simple Button</h2>
<br />


<form action="stripe-test.php" method="POST">
	<script src="https://checkout.stripe.com/checkout.js"
		class="stripe-button" data-key="pk_test_fXV7CvLKY2vmEkalqQezZUPc"
		data-amount="999" data-name="Stripe.com"
		data-description="Example charge"
		data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
		data-locale="auto" data-zip-code="true">
  </script>
</form>




