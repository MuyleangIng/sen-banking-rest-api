package co.sen.bankingapi.api.user;

import co.sen.bankingapi.api.user.web.CreateUserDto;
import co.sen.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    PageInfo<UserDto> findAllUsers(int page , int limit);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById(Integer id,boolean status);
}
