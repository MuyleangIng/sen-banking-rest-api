package co.sen.bankingapi.api.accounttype;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements  AccountTypeService {

    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
        /*List<AccountTypeDto> accountTypeDtoList = accountTypes.stream()
                .map(accountType -> new AccountTypeDto(accountType.getName()))
                .toList();*/
        return accountTypeMapStruct.toDto(accountTypes);
    }
}
