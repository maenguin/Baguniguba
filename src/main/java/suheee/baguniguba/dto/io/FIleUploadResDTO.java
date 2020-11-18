package suheee.baguniguba.dto.io;


import lombok.Getter;
import lombok.NoArgsConstructor;
import suheee.baguniguba.dto.common.IResponseDTO;

@NoArgsConstructor
@Getter
public class FIleUploadResDTO implements IResponseDTO {

    public FIleUploadResDTO(String url) {
        this.url = url;
    }

    private String url;


}
