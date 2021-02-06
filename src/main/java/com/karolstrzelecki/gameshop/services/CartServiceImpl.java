package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.models.Copy.Copy;
import com.karolstrzelecki.gameshop.models.ShoppingCart;
import com.karolstrzelecki.gameshop.models.User;
import com.karolstrzelecki.gameshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CartRepository cartRepository;



    @Override
    public void addToCart(Long copyId) throws AccessDeniedException {
        User user = userService.getUser();
        ShoppingCart cart = user.getShoppingCart();

        if (Objects.nonNull(cart) && Objects.nonNull(cart.getCartItemList()) && !cart.getCartItemList().isEmpty()) {
        }

        if(Objects.isNull(cart)){
            cart = createCart(user);
        }


        Copy copy = productService.getCopy(copyId);
        copy.setShoppingCart(cart);

        if (Objects.isNull(cart.getCartItemList())) {
            cart.setCartItemList(new ArrayList<>());
        }

        cart.getCartItemList().add(copy);

        cart = cartRepository.save(cart);


    }

    @Override
    public List<ProductDao> showCart() throws AccessDeniedException {
        User user = userService.getUser();
        ShoppingCart cart = user.getShoppingCart();
        if (Objects.isNull(cart)){
            return null;
        }else{
            List<Copy> copies = cart.getCartItemList();
            List<ProductDao> daos = copies.stream().map(e->{
                ProductDao dao = productService.convert(e);
                return dao;
            }).filter(x -> x!=null).collect(Collectors.toList());
            return daos;
        }


    }

    private ShoppingCart createCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        return cart;
    }


    @Override
    public boolean deleteFromCart(Long id) {
        try {
            User user = userService.getUser();
            ShoppingCart cart = user.getShoppingCart();

            if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
                System.out.println("Nie ma koszyka/ przedmiotu");
                return false;
            }
            List<Copy> cartItemsList = cart.getCartItemList();
            Optional<Copy> cartItem = cart.getCartItemList()
                    .stream()
                    .filter(ci -> ci.getCopyId().equals(id)).findFirst();
            if (!cartItem.isPresent()) {
                System.out.println("Nie znaleziono produktu");
                return false;
            }

            Copy copy = cartItem.get();

            copy.setShoppingCart(null);
            cartItemsList.remove(copy);





//            if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//                System.out.println("Pusty wózek");
//                user.setShoppingCart(null);
//                userService.saveUser(user);
//                return true;
//            }

            cart.setCartItemList(cartItemsList);

            System.out.println("Nie wiem o co chodzi");
            cartRepository.save(cart);


//            getCartSum();





        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public String getCartSum() {
        try {
            User user = userService.getUser();
            ShoppingCart cart = user.getShoppingCart();
            List<Copy> list= cart.getCartItemList();
            if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
                return null;
            }

           BigDecimal sum = list.stream().map(e->{
                return e.getPrice();
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println(sum);

        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

