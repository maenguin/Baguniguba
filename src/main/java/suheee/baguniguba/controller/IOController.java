package suheee.baguniguba.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import suheee.baguniguba.dto.common.CommonResDTO;
import suheee.baguniguba.dto.io.FIleUploadResDTO;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;
import suheee.baguniguba.service.IOService;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("baguniguba")
public class IOController {

    private final IOService ioService;

    @PostMapping("/upload")
    public ResponseEntity<CommonResDTO> uploadFile(@RequestPart MultipartFile files){

        String url = ioService.uploadFile(files);
        if (url.equals("")){
            throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
        }
        FIleUploadResDTO fIleUploadResDTO = new FIleUploadResDTO(url);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(fIleUploadResDTO));
    }
    @PostMapping("/upload2")
    public ResponseEntity<CommonResDTO> uploadFile2(@RequestParam("file") MultipartFile file){

        String url = ioService.uploadFile(file);
        if (url.equals("")){
            throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
        }
        FIleUploadResDTO fIleUploadResDTO = new FIleUploadResDTO(url);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(fIleUploadResDTO));
    }


}
