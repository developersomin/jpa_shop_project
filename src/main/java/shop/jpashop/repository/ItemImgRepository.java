package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemIdAndThumbnailImg(Long itemId, String thumbnailImg);
}
