package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item_img")
public class ItemImg extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_img_id")
    private Long id;

    private String imgName;

    private String oriName;

    private String imgUrl;

    private String thumbnailImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String imgName, String oriName, String imgUrl) {
        this.imgName = imgName;
        this.oriName = oriName;
        this.imgUrl = imgUrl;
    }

}
