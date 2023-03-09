package shop.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import shop.jpashop.entity.Item;
import shop.jpashop.entity.ItemSellStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명을 입력하시오")
    private String itemName;

    @NotNull(message = "가격을 입력하시오")
    private int price;

    @NotBlank(message = "상품에 대한 설명을 작성하시오")
    private String itemDetail;

    @NotNull(message = "재고수량을 입력하시오")
    private int stockQuantity;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();


    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
