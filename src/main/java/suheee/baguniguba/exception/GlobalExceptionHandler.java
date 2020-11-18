package suheee.baguniguba.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import suheee.baguniguba.dto.common.CommonResDTO;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResDTO> handleBusinessException(final BusinessException e){
        final CommonResDTO commonResDTO = CommonResDTO.of(e.getResultCode());
        return ResponseEntity.ok().body(commonResDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResDTO> handleException(final Exception e){
        final CommonResDTO commonResDTO = CommonResDTO.of(e.getMessage());
        return ResponseEntity.ok().body(commonResDTO);
    }
}
