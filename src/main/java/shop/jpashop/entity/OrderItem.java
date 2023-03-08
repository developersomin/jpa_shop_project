package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "oreder_item_id")
    private Long id;

    private int orderPrice;

    private int count;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "order_id")
    private Order order;
}
