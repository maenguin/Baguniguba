package suheee.baguniguba.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductStatus {

    NONE("PS000","NONE", "비활성"),
    ON_SALE("PS001","ON_SALE", "판매중"),
    SOLD_OUT("PS002","SOLD_OUT", "매진"),
    STOPPED("PS003","STOPPED", "판매중단")
    ;


    private final String code;
    private final String title;
    private final String message;

    ProductStatus(final String code, final String title, final String message){
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public static ProductStatus findAny(final String codeOrTitle){
        return Arrays.stream(ProductStatus.values())
                .filter(e -> e.code.equals(codeOrTitle) || e.title.equals(codeOrTitle))
                .findAny()
                .orElse(NONE);
    }
}
