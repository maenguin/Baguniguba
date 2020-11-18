package suheee.baguniguba.dto.store;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.enums.StoreStatus;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class StoreSaveDTO {

    @ApiModelProperty(notes = "상점명",required = true)
    private String name;
    @ApiModelProperty(notes = "점주명",required = false)
    private String ownerName;
    @ApiModelProperty(notes = "계정 아이디",required = true)
    private String accountID;


    public StoreSaveDTO(String name, String ownerName, String accountID) {
        this.name = name;
        this.ownerName = ownerName;
        this.accountID = accountID;
    }

    public Store toEntity(final Account account){
        return Store.builder()
                .name(name)
                .status(StoreStatus.OPEN.getCode())
                .ownerName(ownerName)
                .account(account)
                .build();
    }
}
