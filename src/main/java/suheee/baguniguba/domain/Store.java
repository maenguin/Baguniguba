package suheee.baguniguba.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "Store")
@NoArgsConstructor
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private long id;

    @NotNull
    @Column(name = "STORE_CODE", unique = true)
    private String code;

    @NotNull
    @Column(name = "STORE_NAME")
    private String name;

    @NotNull
    @Column(name = "STORE_STATUS")
    private String status;

    @Column(name = "STORE_OWNER_NAME")
    private String ownerName;

    @Column(name = "STORE_MAP_URL")
    private String mapURL;

    @Column(name = "STORE_MAP_Markers",length = 1000)
    private String mapMarkers;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;


    @Builder
    public Store(@NotNull String code, @NotNull String name, @NotNull String status, String ownerName,String mapURL,String mapMarkers, @NotNull Account account) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.ownerName = ownerName;
        this.mapURL = mapURL;
        this.mapMarkers = mapMarkers;
        this.account = account;
    }

    public void setMapURL(String mapURL) {this.mapURL = mapURL;}
    public void setMapMarkers(String mapMarkers) {this.mapMarkers = mapMarkers;}
    public void SetCode(String code)
    {
        this.code = code;
    }
}
