package suheee.baguniguba.enums;

import lombok.Getter;

@Getter
public enum StoreStatus {

    WAITING("SS001","WAITING", "대기"),
    PRE_OPEN("SS002","PRE_OPEN", "임시 오픈"),
    OPEN("SS003","OPEN", "개점"),
    ENDED("SS004","ENDED", "마감"),
    PAUSED("SS005","PAUSED", "일시정지"),
    CLOSED("SS006","CLOSED", "폐점"),
    ;


    private final String code;
    private final String title;
    private final String message;

    StoreStatus(final String code,final String title, final String message){
        this.code = code;
        this.title = title;
        this.message = message;
    }
}
