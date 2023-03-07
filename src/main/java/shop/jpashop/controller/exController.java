package shop.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.jpashop.dto.ItemDto;

import java.time.LocalDateTime;

@Controller
public class exController {

    @GetMapping("/thymeleaf/ex02")
    public String thymeleafExample02(Model model){

        ItemDto itemDto = new ItemDto();
        itemDto.setItemName("테스트상품");
        itemDto.setItemDetail("상품상세설명");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/ex02";
    }

    @GetMapping("/thymeleaf/ex07")
    public String thymeleafExample02(){

        return "thymeleafEx/ex07";
    }
}
