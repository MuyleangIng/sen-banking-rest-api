package co.sen.bankingapi.api.user.web;


import co.sen.bankingapi.api.user.UserService;
import co.sen.bankingapi.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @PutMapping("/{id}")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody IsDeletedDto dto){
        Integer deletedId = userService.updateIsDeletedStatusById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
    Integer deletedId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id ){
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been created successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

}
