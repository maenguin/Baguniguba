package suheee.baguniguba.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.dto.account.AccountSaveDTO;
import suheee.baguniguba.dto.account.AccountSignInDTO;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;
import suheee.baguniguba.repository.AccountRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean signUp(final AccountSaveDTO accountSaveDTO){

        if (accountRepository.findByEmail(accountSaveDTO.getEmail()).isPresent())
            throw new BusinessException(ResultCode.ACCOUNT_DUPLICATION);

        Account account = accountSaveDTO.toEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public boolean signIn(final AccountSignInDTO accountSignInDTO){

        Account account = accountRepository.findByEmail(accountSignInDTO.getEmail())
                .orElseThrow(() -> new BusinessException((ResultCode.ID_OR_PASS_NOT_MATCHED)));

        if (!bCryptPasswordEncoder.matches(accountSignInDTO.getPassword(),account.getPassword())){
            throw new BusinessException((ResultCode.ID_OR_PASS_NOT_MATCHED));
        }
        return true;
    }
}
