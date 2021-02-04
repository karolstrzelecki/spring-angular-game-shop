package com.karolstrzelecki.gameshop.models.Copy;




import com.karolstrzelecki.gameshop.models.ComputerGame;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Copy {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long copyId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Platform platform;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "computerGameId")
    private ComputerGame computerGame;



    public Copy() {
    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long id) {
        this.copyId = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ComputerGame getComputerGame() {
        return computerGame;
    }

    public void setComputerGame(ComputerGame computerGame) {
        this.computerGame = computerGame;
    }

    public static Copy createPart(ComputerGame computerGame, Copy copy) throws Exception {
        if (computerGame == null) {
            throw new Exception("The given computer game does not exist!");
        }


        Copy tmpCopy = copy;

        // Add to the whole
        computerGame.addCopy(copy);

        return copy;
    }
}
