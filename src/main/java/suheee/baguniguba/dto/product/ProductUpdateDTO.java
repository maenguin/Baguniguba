package suheee.baguniguba.dto.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.domain.Product;

@Getter
public class ProductUpdateDTO {

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
    @ApiModelProperty(notes = "행사 정보")
    private String eventType;
    @ApiModelProperty(notes = "상태")
    private String status;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
