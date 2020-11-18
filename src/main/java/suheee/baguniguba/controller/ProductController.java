package suheee.baguniguba.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import suheee.baguniguba.dto.common.CommonResDTO;
import suheee.baguniguba.dto.product.*;
import suheee.baguniguba.dto.store.StoreReqDTO;
import suheee.baguniguba.dto.store.StoreResDTO;
import suheee.baguniguba.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("baguniguba")
public class ProductController {

    private final ProductService productService;



    @ApiOperation(value = "바코드와 상점코드에 대응되는 상품 정보를 가져옵니다.", notes = "성공시 상품 정보 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeCode", value = "상점 코드", required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "barcode", value = "바코드", required = true, dataType = "string",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "success",response = ProductResDTO.class)
    })
    @GetMapping("/product")
    public ResponseEntity<CommonResDTO> findProduct(@RequestParam("storeCode") String storeCode, @RequestParam("barcode") String barcode ){
        ProductReqDTO productReqDTO = new ProductReqDTO(barcode,storeCode);
        ProductResDTO productResDTO = productService.findProduct(productReqDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(productResDTO));
    }




    @ApiOperation(value = "상점에 등록된 모든 상품의 정보를 가져옵니다.", notes = "성공시 상품 목록 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeCode", value = "상점코드", required = true, dataType = "string",paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "success",response = ProductResDTO.class)
    })
    @GetMapping("/products")
    public ResponseEntity<CommonResDTO> findAllProducts(@RequestParam("storeCode") String storeCode ){
        ProductsReqDTO productsReqDTO = new ProductsReqDTO(storeCode);
        List<ProductResDTO> allProducts = productService.findAllProducts(productsReqDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(allProducts));
    }




    @ApiOperation(value = "상점에 상품을 등록합니다.", notes = "성공시 반환 객체 없음")
    @PostMapping("/product/insert")
    public ResponseEntity<CommonResDTO> saveProduct(@RequestBody ProductSaveDTO productSaveDTO){
        productService.saveProduct(productSaveDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }
    @ApiOperation(value = "상점에 상품 여러개를 일괄 등록합니다.", notes = "성공시 반환 객체 없음")
    @PostMapping("/products/insert")
    public ResponseEntity<CommonResDTO> saveProducts(@RequestBody ProductsSaveDTO productsSaveDTO){
        productService.saveProducts(productsSaveDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }


    @ApiOperation(value = "상품정보를 수정합니다.", notes = "성공시 반환 객체 없음")
    @PutMapping("/product/update")
    public ResponseEntity<CommonResDTO> editProduct(@RequestBody ProductUpdateDTO productUpdateDTO){
        productService.updateProduct(productUpdateDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }



    @ApiOperation(value = "상품의 이벤트 타입을 수정합니다.", notes = "성공시 반환 객체 없음")
    @PutMapping("/product/update/eventType")
    public ResponseEntity<CommonResDTO> updateProductEventType(@RequestBody ProductEventTypeUpdateDTO productEventTypeUpdateDTO){
        productService.updateProductEventType(productEventTypeUpdateDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }



    @ApiOperation(value = "모든 상품 카테고리들을 가져옵니다.", notes = "성공시 카테고리 리스트 반환")
    @GetMapping("/product/categories")
    public ResponseEntity<CommonResDTO> findALLProductCategories(){
        final List<ProductCategoryResDTO> allProductCategories = productService.findAllProductCategories();
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(allProductCategories));
    }

    @ApiOperation(value = "상품의 리뷰페이지를 찾아서 URL을 반환 합니다. 상품 이름을 입력", notes = "성공시 string url반환")
    @GetMapping("/product/review")
    public ResponseEntity<CommonResDTO> findProductReviewURL(@RequestParam("query") String query){
        final ProductReviewPageDTO productReviewURL = productService.findProductReviewURL(query);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(productReviewURL));
    }

    @ApiOperation(value = "상품의 이미지 url을 수정합니다.", notes = "성공시 반환 객체 없음")
    @PutMapping("/product/update/imageUrl")
    public ResponseEntity<CommonResDTO> updateProductImageURL(@RequestBody ProductImageURLUpdateDTO productImageURLUpdateDTO){
        productService.updateProductImageURL(productImageURLUpdateDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }


}
