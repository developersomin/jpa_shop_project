package shop.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import shop.jpashop.entity.ItemSellStatus;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery="";
}
