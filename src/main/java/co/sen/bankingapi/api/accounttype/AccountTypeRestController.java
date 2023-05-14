package co.sen.bankingapi.api.accounttype;

import co.sen.bankingapi.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();
    }
}
