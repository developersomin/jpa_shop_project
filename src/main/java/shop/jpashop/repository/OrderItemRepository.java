package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
