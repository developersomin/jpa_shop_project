package shop.jpashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.jpashop.dto.ItemSearchDto;
import shop.jpashop.entity.Item;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
