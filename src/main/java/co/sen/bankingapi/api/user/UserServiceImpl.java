package co.sen.bankingapi.api.user;

import co.sen.bankingapi.api.user.web.CreateUserDto;
import co.sen.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("User = {}", user.getId());
        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit) {
        //call repo
        PageInfo<User> userPageInfo = PageHelper.startPage(page , limit).doSelectPageInfo(userMapper::select);

        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existById(id);
        System.out.println(isExisted);
        if (isExisted) {
            //DELETE
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }

    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExisted = userMapper.existById(id);
        if (isExisted) {
            //DELETE
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
}

