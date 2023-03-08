package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
