package co.sen.bankingapi.api.user;

import co.sen.bankingapi.api.user.web.CreateUserDto;
import co.sen.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);
}
