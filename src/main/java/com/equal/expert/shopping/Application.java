package com.equal.expert.shopping;

import com.equal.expert.shopping.model.Cart;

public class Application {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.addProduct("cornflakes", 1);
        cart.addProduct("cornflakes", 1);
        cart.addProduct("weetabix", 1);

        System.out.println(cart.getPurchasedProducts());
        System.out.println(cart.getSubTotal());
        System.out.println(cart.getTax());
        System.out.println(cart.getTotalPayable());
    }


}
