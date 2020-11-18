package suheee.baguniguba.dto.product;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductEventTypeUpdateDTO {

    private String barcode;
    private String storeCode;
    private String eventType;


    public ProductEventTypeUpdateDTO(String barcode, String storeCode, String eventType) {
        this.barcode = barcode;
        this.storeCode = storeCode;
        this.eventType = eventType;
    }
}
