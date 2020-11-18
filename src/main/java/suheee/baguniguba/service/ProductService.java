package suheee.baguniguba.service;


import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import suheee.baguniguba.domain.Product;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.dto.product.*;
import suheee.baguniguba.dto.store.StoreReqDTO;
import suheee.baguniguba.dto.store.StoreResDTO;
import suheee.baguniguba.enums.ProductCategory;
import suheee.baguniguba.enums.ProductEventType;
import suheee.baguniguba.enums.ProductStatus;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;
import suheee.baguniguba.repository.ProductRepository;
import suheee.baguniguba.repository.StoreRepository;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {

    final private ProductRepository productRepository;
    final private StoreRepository storeRepository;

    @Transactional
    public ProductResDTO findProduct(final ProductReqDTO productReqDTO){

        final Product product = productRepository.findByStoreCodeAndBarcode(productReqDTO.getStoreCode(), productReqDTO.getBarcode())
                .orElseThrow( () -> new BusinessException(ResultCode.ENTITY_NOT_FOUND) );
        return new ProductResDTO(product);
    }

    @Transactional
    public List<ProductResDTO> findAllProducts(final ProductsReqDTO productsReqDTO){

        final List<Product> products = productRepository.findAllByStore_Code(productsReqDTO.getStoreCode());
        if (products.isEmpty()){
            throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
        }

        return products.stream()
                .map(product -> new ProductResDTO(product))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean saveProduct(final ProductSaveDTO productSaveDTO){

        final Optional<Product> product = productRepository.findByStoreCodeAndBarcode(productSaveDTO.getStoreCode(), productSaveDTO.getBarcode());
        if (product.isPresent()){
            throw new BusinessException(ResultCode.PRODUCT_DUPLICATION);
        }

        final Store store = storeRepository.findByCode(productSaveDTO.getStoreCode())
                .orElseThrow(() -> new BusinessException(ResultCode.STORE_NOT_FOUND));

        productRepository.save(productSaveDTO.toEntity(store));
        return true;
    }

    @Transactional
    public boolean saveProducts(final ProductsSaveDTO productsSaveDTO){


        final Store store = storeRepository.findByCode(productsSaveDTO.getStoreCode())
                .orElseThrow(() -> new BusinessException(ResultCode.STORE_NOT_FOUND));


        StringBuilder stringBuilder = new StringBuilder();

        for (ProductSaveDTO productSaveDTO: productsSaveDTO.getProducts()) {
            final Optional<Product> product = productRepository.findByStoreCodeAndBarcode(productSaveDTO.getStoreCode(), productSaveDTO.getBarcode());
            if (product.isPresent()){
                System.out.println(productSaveDTO.getName()+"(등록되어있음)");
                //stringBuilder.append(productSaveDTO.getName()+"(등록되어있음)");
                continue;
            }

            productRepository.save(productSaveDTO.toEntity(store));
        }


        return true;
    }


    @Transactional
    public boolean updateProduct(final ProductUpdateDTO productUpdateDTO){

        Product product = productRepository.findById(productUpdateDTO.getId()
        )
                .orElseThrow(() -> new BusinessException(ResultCode.PRODUCT_NOT_FOUND));
        product.update(productUpdateDTO);
        return true;
    }


    @Transactional
    public boolean updateProductEventType(final ProductEventTypeUpdateDTO productEventTypeUpdateDTO){

        Product product = productRepository.findByStoreCodeAndBarcode(productEventTypeUpdateDTO.getStoreCode(), productEventTypeUpdateDTO.getBarcode())
                .orElseThrow(() -> new BusinessException(ResultCode.PRODUCT_NOT_FOUND));


        product.updateEventType(productEventTypeUpdateDTO.getEventType());
        return true;
    }


    @Transactional
    public List<ProductCategoryResDTO> findAllProductCategories(){

        return Arrays.stream(ProductCategory.values())
                .map(e -> new ProductCategoryResDTO(e.getCode(),e.getTitle()))
                .collect(Collectors.toList());
    }



//    public void dd(String query) {
//        String url = "https://msearch.shopping.naver.com/search/all";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//                .queryParam("query", query);
//        System.out.println("url : "+builder.toUriString());
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE);
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//
//        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(5000); //타임아웃 설정 5초
//        factory.setReadTimeout(5000);//타임아웃 설정 5초
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//        String body = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, String.class).getBody();
//        System.out.println("body : "+body);
//    }


    public ProductReviewPageDTO findProductReviewURL(String query){


        try {
            final String encode = URLEncoder.encode(query, "UTF-8");
            String url = "https://msearch.shopping.naver.com/search/all?query="+ encode;
            Document document = Jsoup.connect(url).get();

            //System.out.println(document);
            //elements.stream().map(e->e.select("span._3YKw4ohkB_"))
            //Elements elements = document.select("ul._2klRMnwJXJ span._10DOeA8THj");
            Elements elements = document.select("ul._2klRMnwJXJ li");
            //System.out.println(elements);
            List<String> idList = elements.stream().filter(e -> e.select("span._10DOeA8THj").text().contains("판매처"))
                    .map(e -> e.attr("id").replace("_sr_lst_", ""))
                    .collect(Collectors.toList());

            idList.forEach(e-> System.out.println(e));

            if (idList.size() > 0){
                return new ProductReviewPageDTO("https://msearch.shopping.naver.com/catalog/"+idList.get(0)+"/reviews?fromWhere=CATALOG") ;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);

    }


    @Transactional
    public boolean updateProductImageURL(final ProductImageURLUpdateDTO productImageURLUpdateDTO){

        Product product = productRepository.findByStoreCodeAndBarcode(productImageURLUpdateDTO.getStoreCode(), productImageURLUpdateDTO.getBarcode())
                .orElseThrow(() -> new BusinessException(ResultCode.PRODUCT_NOT_FOUND));


        product.updateImageURL(productImageURLUpdateDTO.getImageURL());
        return true;
    }



}
