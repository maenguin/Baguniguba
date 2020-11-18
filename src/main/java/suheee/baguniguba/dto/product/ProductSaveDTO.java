package suheee.baguniguba.dto.product;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.domain.Product;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.enums.ProductEventType;
import suheee.baguniguba.enums.ProductStatus;

@Getter
public class ProductSaveDTO {

    @ApiModelProperty(notes = "상점코드")
    private String storeCode;
    @ApiModelProperty(notes = "바코드")
    private String barcode;
    @ApiModelProperty(notes = "상품명")
    private String name;
    @ApiModelProperty(notes = "재고")
    private long amount;
    @ApiModelProperty(notes = "가격")
    private long price;

    @ApiModelProperty(notes = "태그")
    private String tag;

    @ApiModelProperty(notes = "추천 여부")
    private boolean recommended;

    @ApiModelProperty(notes = "이미지 url")
    private String imageURL;

    public Product toEntity(Store store){
        return  Product.builder()
                .barcode(barcode)
                .name(name)
                .amount(amount)
                .price(price)
                .eventType(ProductEventType.NONE.getCode())
                .status(ProductStatus.ON_SALE.getCode())
                .store(store)
                .tag(tag)
                .recommended(recommended)
                .imageURL(imageURL)
                .build();
    }

}
