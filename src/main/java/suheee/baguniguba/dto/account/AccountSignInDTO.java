package suheee.baguniguba.dto.account;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSignInDTO {

    private String email;
    private String password;

    public AccountSignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
