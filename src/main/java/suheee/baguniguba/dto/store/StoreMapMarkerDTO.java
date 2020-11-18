package suheee.baguniguba.dto.store;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import suheee.baguniguba.dto.product.ProductSaveDTO;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreMapMarkerDTO {

    public StoreMapMarkerDTO(String x, String y, String title) {
        this.x = x;
        this.y = y;
        this.title = title;
    }

    private String x;
    private String y;
    private String title;
}
