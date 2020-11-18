package suheee.baguniguba.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductCategory {

    NONE("PCG00","미분류"),
    FRUITS("PCG01","과일"),
    RICE_MIXRICE("PCG02","쌀/잡곡"),
    NUT("PCG03","견과"),
    VEGETABLE("PCG04","야채"),
    CONFECTIONERY("PCG05","과자"),
    BEVERAGES("PCG06","음료"),
    FISH("PCG07","생선"),
    CONDIMENTS("PCG08","조미료/장류"),
    BAKERY_JAM("PCG09","베이커리/잼"),
    INSTANTFOODS("PCG10","즉석식품"),
    CANNEDGOODS("PCG11","통조림"),
    NOODLES("PCG12","면류"),
    SIDEDISH("PCG13","반찬"),
    CLOTHES("PCG14","의류"),
    HOBBY_GAMES("PCG15","취미/게임"),
    ELECTRONICS("PCG16","전자제품"),
    HEALTHFOODS("PCG17","건강식품"),
    ;


    private final String code;
    private final String title;

    ProductCategory(final String code, final String title){
        this.code = code;
        this.title = title;
    }


    public static ProductCategory findAny(final String codeOrTitle){
        return Arrays.stream(ProductCategory.values())
                .filter(e -> e.code.equals(codeOrTitle) || e.title.equals(codeOrTitle))
                .findAny()
                .orElse(NONE);
    }



}
