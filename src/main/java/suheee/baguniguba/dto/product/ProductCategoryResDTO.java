package suheee.baguniguba.dto.product;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductCategoryResDTO {

    public ProductCategoryResDTO(String code, String title) {
        this.code = code;
        this.title = title;
    }

    private String code;
    private String title;


}
