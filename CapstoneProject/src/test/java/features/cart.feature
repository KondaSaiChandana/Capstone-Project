Feature:JPetStore Cart Functionality

Scenario:verify that adding removing items and updating cart
   Given The user is on the JPetStore SignInpage
   When The user signs in with username and password
   And The user clicks on the cart image
   Then The user should be redirected to the view cart page
   And The cart page display Your cart is empty message when there is no items in cart
   When The user clicks on Fish
   Then The user should redirected to the fish category page
   When The user clicks on product id
   Then The user should be redirected to the items page of that product
   When The user clicks on add to cart button which is next to item price
   Then The product should be added to the cart and redirected to view cart page
   And Add items to cart again
   And set quantity of item and click updateCart
   Then Click on Check out process button
   And Close Browser
   