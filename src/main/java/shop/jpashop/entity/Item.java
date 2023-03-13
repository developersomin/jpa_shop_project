package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import shop.jpashop.dto.ItemFormDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private Long id;

    private String itemName;

    private int price;

    private int stockQuantity;

    @Lob
    private String itemDetail;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItemImg> itemImgList = new ArrayList<>();



    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    /*public void addItemImg(ItemImg itemImg){
        itemImgList.add(itemImg);
        itemImg.setItem(this);
    }*/

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockQuantity = itemFormDto.getStockQuantity();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
        }
}





