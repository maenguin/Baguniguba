package suheee.baguniguba.enums;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("0","success"),
    UNHANDLED_ERROR("-1","지정되지 않은 에러"),
    ENTITY_NOT_FOUND("C001", "Entity Not Found"),
    INVALID_TYPE_VALUE("C002", "Invalid Type Value"),
    INVALID_INPUT_VALUE("C003", "Invalid Input Value"),


    ID_OR_PASS_NOT_MATCHED("A001","아이디나 비밀번호가 일치하지 않습니다."),
    ACCOUNT_DUPLICATION("A002","중복된 아이디가 존재합니다."),
    ACCOUNT_NOT_FOUND("A002","해당 계정이 존재하지 않습니다."),

    PRODUCT_NOT_FOUND("P001", "해당 상품이 존재하지 않습니다."),
    PRODUCT_DUPLICATION("P002", "해당 상품이 이미 존재합니다."),


    STORE_NOT_FOUND("S001", "해당 상점이 존재하지 않습니다."),
    STORE_DUPLICATION("S001", "해당 상점이 이미 존재합니다."),
    ;


    private final String code;
    private final String message;

    ResultCode(final String code, final String message){
        this.code = code;
        this.message = message;
    }

}
