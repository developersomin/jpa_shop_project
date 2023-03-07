package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private Long id;

    private String itemName;

    private int price;

    private int stockQuantity;

    @Lob
    private String itemDetail;

    @Enumerated
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}