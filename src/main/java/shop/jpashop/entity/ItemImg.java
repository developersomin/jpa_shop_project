package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item_img")
public class ItemImg {

    @Id
    @GeneratedValue
    @Column(name = "item_img_id")
    private Long id;

}
