package suheee.baguniguba.dto.account;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.dto.common.IResponseDTO;

@Getter
@NoArgsConstructor
public class AccountSaveDTO implements IResponseDTO {

    @ApiModelProperty(notes = "아이디")
    private String email;
    @ApiModelProperty(notes = "패스워드")
    private String password;


    public AccountSaveDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account toEntity(PasswordEncoder passwordEncoder){
        return Account.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .build();
    }


}
