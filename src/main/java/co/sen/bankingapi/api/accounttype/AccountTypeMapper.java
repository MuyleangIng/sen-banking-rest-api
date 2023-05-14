package co.sen.bankingapi.api.accounttype;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AccountTypeMapper {

    @SelectProvider(type = AccountTypeProvider.class , method = "buildSelectSql")
    List<AccountType> select();
}
