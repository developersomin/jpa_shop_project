package shop.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.jpashop.dto.OrderDto;
import shop.jpashop.dto.OrderHistoryDto;
import shop.jpashop.dto.OrderItemDto;
import shop.jpashop.entity.*;
import shop.jpashop.repository.ItemImgRepository;
import shop.jpashop.repository.ItemRepository;
import shop.jpashop.repository.MemberRepository;
import shop.jpashop.repository.OrderRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ItemImgRepository itemImgRepository;

    @Transactional
    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityExistsException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    public Page<OrderHistoryDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        for (Order order : orders) {

            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);

            List<OrderItem> orderItems = order.getOrderItems();

            for (OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndThumbnailImg(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }

            orderHistoryDtos.add(orderHistoryDto);

        }
        return new PageImpl<OrderHistoryDto>(orderHistoryDtos, pageable, totalCount);
    }
}
