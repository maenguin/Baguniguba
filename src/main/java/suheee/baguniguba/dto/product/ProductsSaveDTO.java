package suheee.baguniguba.dto.product;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.domain.Product;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.enums.ProductEventType;
import suheee.baguniguba.enums.ProductStatus;

import java.util.List;

@Getter
public class ProductsSaveDTO {

    @ApiModelProperty(notes = "상점코드")
    private String storeCode;

    @ApiModelProperty(notes = "상품들")
    private List<ProductSaveDTO> products;


}
