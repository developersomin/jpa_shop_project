package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long>,ItemRepositoryCustom {
}
