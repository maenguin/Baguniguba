package suheee.baguniguba.dto.store;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.dto.common.IResponseDTO;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class StoreResDTO implements IResponseDTO {

    @ApiModelProperty(notes = "상점코드")
    private String code;
    @ApiModelProperty(notes = "상점명")
    private String name;
    @ApiModelProperty(notes = "상점상태")
    private String status;
    @ApiModelProperty(notes = "점주명")
    private String ownerName;
    private String mapURL;

    public StoreResDTO(final Store store) {
        this.code = store.getCode();
        this.name = store.getName();
        this.status = store.getStatus();
        this.ownerName = store.getOwnerName();
        this.mapURL = store.getMapURL();
    }
}
