package suheee.baguniguba.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .paths(PathSelectors.ant("/baguniguba/**")) // 그중 /api/** 인 URL들만 필터링
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "바구니구바 Rest API with Swagger"
                ,getApiDescription()
                ,"1.0"
                ,"http://swagger.io/terms/"
                ,"maeng"
                ,"Apache License 2.0"
                ,"Lhttp://www.apache.org/licenses/LICENSE-2.0"
        );
    }



//    NONE("PE000","NONE", "행사 없음"),
//    ONE_PLUS_ONE("PE001","ONE_PLUS_ONE","1 + 1 행사 상품"),
//    TWO_PLUS_ONE("PE002","TWO_PLUS_ONE","2 + 1 행사 상품"),
//    FREE_GIFT("PE003","FREE_GIFT", "사은품"),
//    DISCOUNT("PE004","DISCOUNT", "할인"),

    private String getApiDescription(){
        return "바구니구바 Rest Api의 Documentation입니다.\n\n" +
                "`result body (공통구조)`\n" +
                "{\n" +
                " code : string         (결과코드 참조)\n" +
                " message : string      \n" +
                " result : Object array (메소드별 response object 참조)\n" +
                "}\n\n" +
                "`결과코드`\n" +
                "- 0     : 성공   (message에 success 출력)\n" +
                "- -1    : 지정되지 않은 에러   (message에 Exception message 출력)\n" +
                "- 그외   : 지정된 에러         (message에 지정된 메시지 출력)\n\n" +
                "`Product EventType 코드`\n" +
                "NONE : 행사 없음\n" +
                "ONE_PLUS_ONE : 1 + 1 행사 상품\n" +
                "TWO_PLUS_ONE : 2 + 1 행사 상품\n" +
                "FREE_GIFT : 사은품 증정\n" +
                "DISCOUNT : 할인 상품\n\n" +
                "`Product Status 코드`\n" +
                "NONE : 비활성\n" +
                "ON_SALE : 판매중\n" +
                "SOLD_OUT : 매진\n" +
                "STOPPED : 판매중단\n\n"
                ;
    }

}




