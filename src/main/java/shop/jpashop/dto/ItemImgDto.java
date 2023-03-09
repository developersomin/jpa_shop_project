package shop.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import shop.jpashop.entity.ItemImg;

@Getter
@Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriName;

    private String imgUrl;

    private String thumbnailImg;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
