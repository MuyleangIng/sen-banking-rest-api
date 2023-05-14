package co.sen.bankingapi.api.user;

import co.sen.bankingapi.api.user.web.CreateUserDto;
import co.sen.bankingapi.api.user.web.UserDto;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById(Integer id,boolean status);
}
