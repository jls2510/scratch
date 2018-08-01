stripe-test-ui.php
<br/>
<br/>

<?php

require_once('./stripe-config.php');


?>

<br/>
<br/>

<h2>Custom Button</h2>
<br/>

<script src="https://checkout.stripe.com/checkout.js"></script>

<button id="customButton">Purchase</button>

<script>

    function processToken(token) {
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
    } // processToken()


    var handler = StripeCheckout.configure({
        key: '<?php echo $stripe['publishable_key']; ?>'
    });

    document.getElementById('customButton').addEventListener('click', function (e) {
        // Open Checkout with further options:
        handler.open({
            name: 'Jansal Valley',
            description: 'Website Order',
            zipCode: true,
            billingAddress: true,
            amount: 2000,
            image: 'https://stripe.com/img/documentation/checkout/marketplace.png',
            locale: 'auto',
            token: function (token) {
                // You can access the token ID with `token.id`.
                // Get the token ID to your server-side code for use.
                processToken(token);
            }
        });
        e.preventDefault();
    });

    // Close Checkout on page navigation:
    window.addEventListener('popstate', function () {
        handler.close();
    });
</script>

<br/>
<br/>
<h2>Simple Button</h2>
<br/>


<form action="stripe-test-api.php" method="POST">
    <script src="https://checkout.stripe.com/checkout.js"
            class="stripe-button" data-key="<?php echo $stripe['publishable_key']; ?>"
            data-amount="999"
            data-name="Jansal Valley Website"
            data-description="Test Charge"
            data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
            data-locale="auto" data-zip-code="true">
    </script>
</form>


<br/>
<br/>
<h2>Simple Update Button</h2>
<br/>


<form action="stripe-test-api.php" method="POST">
    <script
            src="https://checkout.stripe.com/checkout.js" class="stripe-button"
            data-key="<?php echo $stripe['publishable_key']; ?>"
            data-name="Jansal Valley Website"
            data-panel-label="Update Card Details"
            data-label="Update Card Details"
            data-allow-remember-me=false
            data-locale="auto">
    </script>
</form>


