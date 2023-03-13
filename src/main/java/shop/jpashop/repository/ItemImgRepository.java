package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    List<ItemImg> findItemImgListById(Long itemId);
}
