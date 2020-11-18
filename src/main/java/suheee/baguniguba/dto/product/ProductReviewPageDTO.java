package suheee.baguniguba.dto.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.dto.common.IResponseDTO;

@Getter
public class ProductReviewPageDTO implements IResponseDTO {

    @ApiModelProperty(notes = "리뷰 url")
    private String url;

    public ProductReviewPageDTO(String url) {
        this.url = url;
    }
}
