package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//java 17 부터 사용 불가
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    /**
     * Bean Validation 메세지 찾는 순서
     * 1. 생성된 메세지 코드 순서대로 messageSource 에서 찾기
     * @NotBlank
     * NotBlank.item.itemName
     * NotBlank.itemName
     * NotBlank.java.lang.String
     * NotBlank
     * @Range
     * Range.item.price
     * Range.price
     * Range.java.lang.Integer
     * Range
     * 2. 어노테이션의 message 속성 사용
     * 3. 라이브러리가 제공하는 기본 값 사용
     */

    //빈값 + 공백만 있는 경우를 허용하지 않는다
    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    //null 을 허용하지 않는다
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    //범위 안의 값이어야 한다
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class}) //최대 9999까지 허용한다
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
