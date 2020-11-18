package suheee.baguniguba.dto.store;


import lombok.Getter;

@Getter
public class StoreReqDTO {

    private String code;

    public StoreReqDTO(String code) {
        this.code = code;
    }
}
