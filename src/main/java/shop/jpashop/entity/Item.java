package shop.jpashop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import shop.jpashop.dto.ItemFormDto;
import shop.jpashop.execption.OutOfStockException;

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


    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockQuantity = itemFormDto.getStockQuantity();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
        }

    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0) {
            throw new OutOfStockException("상품 재고가 부족합니다. 현재 재고 : " + restStock);
        }
        this.stockQuantity = restStock;
    }
}





