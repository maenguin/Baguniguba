package suheee.baguniguba.dto.product;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductReqDTO {
    private String barcode;
    private String storeCode;

    public ProductReqDTO(String barcode, String storeCode) {
        this.barcode = barcode;
        this.storeCode = storeCode;
    }
}
