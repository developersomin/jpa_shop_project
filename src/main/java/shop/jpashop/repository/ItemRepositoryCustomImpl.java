package shop.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import shop.jpashop.dto.ItemSearchDto;
import shop.jpashop.dto.MainItemDto;
import shop.jpashop.dto.QMainItemDto;
import shop.jpashop.entity.Item;
import shop.jpashop.entity.ItemSellStatus;
import shop.jpashop.entity.QItem;
import shop.jpashop.entity.QItemImg;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static shop.jpashop.entity.QItem.item;
import static shop.jpashop.entity.QItemImg.itemImg;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();
        if (StringUtils.equals("1d",searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w",searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }
        else if (StringUtils.equals("1m",searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m",searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        } else {
            return null;
        }
        return item.createdDate.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("itemName",searchBy)) {
            System.out.println("쿼리실행 직전");
            return item.itemName.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createBy",searchBy)) {
            System.out.println("쿼리실행 직전");
            return item.createBy.like("%" + searchQuery + "%");
        } else {
            return null;
        }
    }



    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(item)
                .where(searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())
                        )
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = queryFactory.select(Wildcard.count).from(item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne(); //wildcard 모든문자 찾을때
        return new PageImpl<>(content, pageable, total);
    }
    private BooleanExpression itemNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : item.itemName.like("%" + "searchQuery" + "%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<MainItemDto> content = queryFactory.
                select(new QMainItemDto(item.id, item.itemName, item.itemDetail, itemImg.imgUrl, item.price))
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.thumbnailImg.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = queryFactory.
                select(Wildcard.count)
                .from(itemImg)
                .join(itemImg.item, item)
                .orderBy(item.id.desc())
                .where(itemImg.thumbnailImg.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
