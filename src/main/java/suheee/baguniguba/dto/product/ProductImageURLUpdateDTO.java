package suheee.baguniguba.dto.product;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductImageURLUpdateDTO {

    private String barcode;
    private String storeCode;
    private String imageURL;


    public ProductImageURLUpdateDTO(String barcode, String storeCode, String imageURL) {
        this.barcode = barcode;
        this.storeCode = storeCode;
        this.imageURL = imageURL;
    }
}
