package shop.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import shop.jpashop.dto.ItemSearchDto;
import shop.jpashop.entity.Item;
import shop.jpashop.entity.ItemSellStatus;
import shop.jpashop.entity.QItem;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){

        LocalDateTime dateTime = LocalDateTime.now();

        /*switch (searchDateType) {
            case "1d":
                dateTime = dateTime.minusDays(1);
                break;
            case "1w":
                dateTime = dateTime.minusWeeks(1);
                break;
            case "1m":
                dateTime = dateTime.minusMonths(1);
                break;
            case "6m":
                dateTime = dateTime.minusMonths(6);
                break;
            default:
                return null;
        }*/

        if (searchDateType == "1d") {
            dateTime = dateTime.minusDays(1);
        } else if (searchDateType =="1w") {
            dateTime = dateTime.minusWeeks(1);
        }
        else if (searchDateType =="1m") {
            dateTime = dateTime.minusMonths(1);
        } else if (searchDateType == "6m") {
            dateTime = dateTime.minusMonths(6);
        } else {
            return null;
        }
        return QItem.item.createdDate.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        /*switch (searchBy) {
            case "itemName":
                return QItem.item.itemName.like("%" + searchQuery + "%");
            case "createBy":
                return QItem.item.createBy.like("%" + searchQuery + "%");
            default:
                return null;
        }*/

        if (searchBy == "itemName") {
            return QItem.item.itemName.like("%" + searchQuery + "%");
        } else if (searchBy == "createBy") {
            return QItem.item.createBy.like("%" + searchQuery + "%");
        } else {
            return null;
        }
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(QItem.item)
                .where(searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()),
                        regDtsAfter(itemSearchDto.getSearchDateType()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = content.size();

        return new PageImpl<>(content, pageable, total);

    }
}
