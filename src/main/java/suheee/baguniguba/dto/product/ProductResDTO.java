package suheee.baguniguba.dto.product;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import suheee.baguniguba.domain.Product;
import suheee.baguniguba.dto.common.IResponseDTO;
import suheee.baguniguba.enums.ProductCategory;
import suheee.baguniguba.enums.ProductEventType;
import suheee.baguniguba.enums.ProductStatus;

import javax.persistence.Column;
import java.util.Optional;
import java.util.OptionalInt;

@NoArgsConstructor
@Getter
public class ProductResDTO implements IResponseDTO {


    @ApiModelProperty(notes = "아이디")
    private Long id;
    @ApiModelProperty(notes = "바코드")
    private String barcode;
    @ApiModelProperty(notes = "상품명")
    private String name;
    @ApiModelProperty(notes = "재고")
    private long amount;
    @ApiModelProperty(notes = "가격")
    private long price;
    @ApiModelProperty(notes = "상품상태")
    private String status;
    @ApiModelProperty(notes = "상품 이벤트 타입")
    private String eventType;
    @ApiModelProperty(notes = "태그")
    private String tag;
    @ApiModelProperty(notes = "카테고리")
    private String category;
    @ApiModelProperty(notes = "사은품")
    private String gift;
    @ApiModelProperty(notes = "할인율")
    private Integer discountRate;

    @ApiModelProperty(notes = "추천 여부")
    private boolean recommended;

    @ApiModelProperty(notes = "이미지 url")
    private String imageURL;

    public ProductResDTO(Product product) {
        this.id = product.getId();
        this.barcode = product.getBarcode();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.price = product.getPrice();
        this.status = ProductStatus.findAny(product.getStatus()).getTitle();
        this.eventType = ProductEventType.findAny(product.getEventType()).getTitle();
        this.tag = product.getTag();
        this.category = ProductCategory.findAny(product.getCategory()).getTitle();
        this.gift = product.getGift();
        this.recommended = product.isRecommended();
        this.imageURL = product.getImageURL();
        //글로벌 익셉션에 걸리드라;
        //this.discountRate = Optional.of(product.getDiscountRate()).orElse(0);
        this.discountRate = product.getDiscountRate() == null? 0 : product.getDiscountRate();
    }
}
