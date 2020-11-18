package suheee.baguniguba.domain;


import com.sun.istack.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import suheee.baguniguba.dto.product.ProductSaveDTO;
import suheee.baguniguba.dto.product.ProductUpdateDTO;
import suheee.baguniguba.enums.ProductCategory;
import suheee.baguniguba.enums.ProductEventType;
import suheee.baguniguba.enums.ProductStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product extends BaseTimeEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @NotNull
    @Column(name = "PRODUCT_BARCODE")
    private String barcode;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String name;

    @NotNull
    @Column(name = "PRODUCT_AMOUNT")
    private long amount;

    @NotNull
    @Column(name = "PRODUCT_PRICE")
    private long price;

    @NotNull
    @Column(name = "PRODUCT_STATUS")
    private String status;

    @NotNull
    @Column(name = "PRODUCT_EVENT_TYPE")
    private String eventType;

    @Column(name = "PRODUCT_TAG")
    private String tag;

    @Column(name = "PRODUCT_CATEGORY")
    private String category;

    @Column(name = "PRODUCT_GITF")
    private String gift;

    @Column(name = "PRODUCT_DISCOUNT_RATE")
    private Integer discountRate;

    @NotNull
    @Column(name = "PRODUCT_RECOMMENDED")
    private boolean recommended;

    @Column(name = "PRODUCT_IMAGE_URL")
    private String imageURL;


//    @Column(name = "PRODUCT_EVENT_TYPE_DETAIL")
//    private ProductEventTypeDetail eventTypeDetail;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;


    @Builder
    public Product(@NotNull String barcode, @NotNull String name, @NotNull long amount, @NotNull long price, @NotNull String status, @NotNull String eventType, String tag, String category, String gift, Integer discountRate, @NotNull  boolean recommended, String imageURL, @NotNull Store store) {
        this.barcode = barcode;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.status = status;
        this.eventType = eventType;
        this.tag = tag;
        this.category = category;
        this.gift = gift;
        this.discountRate = discountRate;
        this.recommended = recommended;
        this.imageURL = imageURL;
        this.store = store;

        if(amount <= 0){
            this.status = ProductStatus.SOLD_OUT.getCode();
        }

    }



    public void update(ProductUpdateDTO productUpdateDTO){
        this.barcode = productUpdateDTO.getBarcode();
        this.name = productUpdateDTO.getName();
        this.amount = productUpdateDTO.getAmount();
        this.price = productUpdateDTO.getPrice();
        this.eventType = ProductEventType.findAny(productUpdateDTO.getEventType()).getCode() ;
        this.status = ProductStatus.findAny(productUpdateDTO.getStatus()).getCode() ;
        this.tag = productUpdateDTO.getTag();
        this.category = ProductCategory.findAny(productUpdateDTO.getCategory()).getCode();
        this.gift = productUpdateDTO.getGift();
        this.discountRate = productUpdateDTO.getDiscountRate();
        this.recommended = productUpdateDTO.isRecommended();
        this.imageURL = productUpdateDTO.getImageURL();
        if(amount <= 0){
            this.status = ProductStatus.SOLD_OUT.getCode();
        }
    }

    public void updateEventType(String eventType){
        this.eventType = eventType;
    }
    public void updateImageURL(String url){
        this.imageURL = url;
    }
}
