package suheee.baguniguba.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Entity

@Table(name = "ACCOUNT")
@NoArgsConstructor
public class Account extends BaseTimeEntity {




    @NotNull
    @Column(name = "ACCOUNT_EMAIL",unique = true)
    private String email;


    @NotNull
    @Column(name = "ACCOUNT_PASSWORD")
    private String password;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Builder
    public Account(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }
}
