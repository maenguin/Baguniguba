package suheee.baguniguba.dto.store;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.dto.product.ProductSaveDTO;

import java.util.List;

@Getter
public class StoreMapMarkerUpdateDTO {

    private String storeCode;

    private List<StoreMapMarkerDTO> markers;
}
