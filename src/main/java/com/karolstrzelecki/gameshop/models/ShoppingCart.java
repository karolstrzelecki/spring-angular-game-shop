package com.karolstrzelecki.gameshop.models;

import com.karolstrzelecki.gameshop.models.Copy.Copy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long shoppingCartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Copy> cartItemList;


    public void removeCopy(Copy copy){
        if(copy != null){
            if(cartItemList.contains(copy)){
                cartItemList.remove(copy);
                copy.setComputerGame(null);
            }
        }

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +

//                ", cartItemList=" + cartItemList +
                '}' ;


    }


}
