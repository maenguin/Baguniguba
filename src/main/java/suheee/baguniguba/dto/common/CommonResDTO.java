package suheee.baguniguba.dto.common;


import lombok.Getter;
import suheee.baguniguba.enums.ResultCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class CommonResDTO {

    private String code;
    private String message;
    private List result;

    private CommonResDTO(final ResultCode resultCode){
        this(resultCode,new ArrayList());
    }
    private CommonResDTO(final ResultCode resultCode, final IResponseDTO result){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.result = new ArrayList(Arrays.asList(result));
    }
    private CommonResDTO(final ResultCode resultCode, final List results){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.result = results;
    }
    public CommonResDTO(final String message) {
        this.code = ResultCode.UNHANDLED_ERROR.getCode();
        this.message = message;
        this.result = new ArrayList();
    }


    public static CommonResDTO of(ResultCode resultCode) {
        return new CommonResDTO(resultCode);
    }
    public static CommonResDTO of(ResultCode resultCode, IResponseDTO result) {
        return new CommonResDTO(resultCode, result);
    }
    public static CommonResDTO of(ResultCode resultCode, List results) {
        return new CommonResDTO(resultCode, results);
    }
    public static CommonResDTO of(String message) {
        return new CommonResDTO(message);
    }

    public static CommonResDTO ofSuccess() {
        return new CommonResDTO(ResultCode.SUCCESS);
    }
    public static CommonResDTO ofSuccess(IResponseDTO result) {
        return new CommonResDTO(ResultCode.SUCCESS, result);
    }
    public static CommonResDTO ofSuccess(List results) {
        return new CommonResDTO(ResultCode.SUCCESS, results);
    }

}
