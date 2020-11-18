package suheee.baguniguba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ProductEventType {

    NONE("PE000","NONE", "행사 없음"),
    ONE_PLUS_ONE("PE001","ONE_PLUS_ONE","1 + 1 행사 상품"),
    TWO_PLUS_ONE("PE002","TWO_PLUS_ONE","2 + 1 행사 상품"),
    FREE_GIFT("PE003","FREE_GIFT", "사은품"),
    DISCOUNT("PE004","DISCOUNT", "할인"),
    ;


    private final String code;
    private final String title;
    private final String message;

    ProductEventType(final String code,final String title, final String message){
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public static ProductEventType findAny(final String codeOrTitle){
        return Arrays.stream(ProductEventType.values())
                .filter(e -> e.code.equals(codeOrTitle) || e.title.equals(codeOrTitle))
                .findAny()
                .orElse(NONE);
    }
}
