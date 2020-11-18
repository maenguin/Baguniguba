package suheee.baguniguba.dto;

import lombok.Getter;
import suheee.baguniguba.dto.common.IResponseDTO;

@Getter
public class AnnuityResDTO implements IResponseDTO {

    private int group;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;

    public AnnuityResDTO(int group, int first, int second, int third, int fourth, int fifth, int sixth) {
        this.group = group;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
    }
}
