package suheee.baguniguba.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.dto.account.AccountSaveDTO;
import suheee.baguniguba.dto.account.AccountSignInDTO;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;
import suheee.baguniguba.repository.AccountRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IOService {

    private final String fileDir = "C:\\Study\\baguniguba\\src\\main\\resources\\static";
    private final String baseURL = "http://maenguin.iptime.org:8080/resource_image.html";


    public String uploadFile(MultipartFile files){
        try{
            String filePath = fileDir+"\\"+files.getOriginalFilename();
            System.out.println("ddddddd "+filePath);
            files.transferTo(new File(filePath));
            return baseURL+"?url="+files.getOriginalFilename();
        } catch (IOException ioe){
            ioe.printStackTrace();
            return "";
        }
    }

}
