package shop.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.jpashop.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);
}
