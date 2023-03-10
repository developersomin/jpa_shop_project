package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import shop.jpashop.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long>, QuerydslPredicateExecutor<Item>,ItemRepositoryCustom {
}
