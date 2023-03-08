package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
