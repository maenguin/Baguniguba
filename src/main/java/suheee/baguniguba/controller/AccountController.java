package suheee.baguniguba.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suheee.baguniguba.dto.AnnuityResDTO;
import suheee.baguniguba.dto.account.AccountSaveDTO;
import suheee.baguniguba.dto.account.AccountSignInDTO;
import suheee.baguniguba.dto.common.CommonResDTO;
import suheee.baguniguba.dto.LottoResDTO;
import suheee.baguniguba.service.AccountService;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("baguniguba")
public class AccountController {

    private final AccountService accountService;


    @ApiOperation(value = "회원가입", notes = "성공시 따로 반환되는 객체는 없습니다.")
    @PostMapping("/account/signup")
    public ResponseEntity<CommonResDTO> signUp(@RequestBody AccountSaveDTO accountSaveDTO){
        accountService.signUp(accountSaveDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }



    @GetMapping("/account/test")
    public ResponseEntity<CommonResDTO> test(@RequestParam("id")  String id){
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }


    @ApiOperation(value = "로그인", notes = "성공시 따로 반환되는 객체는 없습니다.")
    @PostMapping("/account/signin")
    public ResponseEntity<CommonResDTO> signIn(@RequestBody AccountSignInDTO accountSignInDTO){
        accountService.signIn(accountSignInDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }


    @GetMapping("lotto")
    public ResponseEntity<CommonResDTO> lotto(@RequestParam("seed")  long seed){

        Stack<Integer> lottoMachine = new Stack<>();
        Random rand = new Random(System.currentTimeMillis()+seed);
        final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());

        lottoMachine.addAll(numbers);
        for (int i = 0; i < lottoMachine.size(); i++) {
            int randIndex = rand.nextInt(45);
            int n1 = lottoMachine.elementAt(i);
            int n2 = lottoMachine.elementAt(randIndex);
            lottoMachine.set(i,n2);
            lottoMachine.set(randIndex,n1);
        }

        LottoResDTO lottoResDTO = new LottoResDTO(
                 lottoMachine.pop()
                ,lottoMachine.pop()
                ,lottoMachine.pop()
                ,lottoMachine.pop()
                ,lottoMachine.pop()
                ,lottoMachine.pop()
        );

        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(lottoResDTO));
    }


    @GetMapping("annuity")
    public ResponseEntity<CommonResDTO> annuity(@RequestParam("seed")  long seed){

        Random rand = new Random(System.currentTimeMillis()+seed);

        AnnuityResDTO annuityResDTO = new AnnuityResDTO(
                rand.nextInt(5) + 1
                , rand.nextInt(10)
                , rand.nextInt(10)
                , rand.nextInt(10)
                , rand.nextInt(10)
                , rand.nextInt(10)
                , rand.nextInt(10)
        );

        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(annuityResDTO));
    }


}
