package suheee.baguniguba.exception.business;


import lombok.Getter;
import suheee.baguniguba.enums.ResultCode;

@Getter
public class BusinessException extends RuntimeException{


    private ResultCode resultCode;

    public BusinessException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }


}
