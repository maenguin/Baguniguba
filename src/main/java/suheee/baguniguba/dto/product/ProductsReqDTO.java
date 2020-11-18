package suheee.baguniguba.dto.product;


import lombok.Getter;

@Getter
public class ProductsReqDTO {
    private String storeCode;

    public ProductsReqDTO(String storeCode) {
        this.storeCode = storeCode;
    }
}
