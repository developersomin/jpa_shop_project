package shop.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.jpashop.dto.ItemFormDto;
import shop.jpashop.entity.Item;
import shop.jpashop.entity.ItemImg;
import shop.jpashop.repository.ItemRepository;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {

        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0) {
                itemImg.setThumbnailImg("Y");
            } else {
                itemImg.setThumbnailImg("N");
            }
            itemImgService.saveItemImg(itemImg,itemImgFileList.get(i));
        }

        return item.getId();
    }
}
