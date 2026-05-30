package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toUserDto(user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserNameDto> getUsersSimple() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserNameDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/email")
    public List<UserEmailDto> searchUsersByEmail(@RequestParam("email") String email) {
        return userProvider.searchUsersByEmail(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> searchUsersOlderThan(@PathVariable("time") LocalDate time) {
        return userProvider.findAllUsers().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .map(userMapper::toUserDto)
                .toList();
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userMapper.toEntity(userDto));
        return userMapper.toUserDto(updatedUser);
    }

    }