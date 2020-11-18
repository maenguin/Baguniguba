package suheee.baguniguba.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoresReqDTO {

    private String accountID;

    public StoresReqDTO(String accountID) {
        this.accountID = accountID;
    }
}
