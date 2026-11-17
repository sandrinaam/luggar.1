package com.example.luggar.user;

import com.example.luggar.user.dto.UpdateUserCommand;
import com.example.luggar.user.dto.UserRequest;
import com.example.luggar.user.dto.UserResponse;
import com.example.luggar.user.services.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final CreateUserService createUserService;

    private final DeleteUserService deleteUserService;

    private final GetUsersService getUsersService;

    private final GetUserByIdService getUserByIdService;

    private final UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService,
                          DeleteUserService deleteUserService,
                          GetUsersService getUsersService,
                          GetUserByIdService getUserByIdService,
                          UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.deleteUserService = deleteUserService;
        this.getUsersService = getUsersService;
        this.getUserByIdService = getUserByIdService;
        this.updateUserService = updateUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser( @Valid @RequestBody UserRequest user){
        return createUserService.execute(user);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return getUsersService.execute(null);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return getUserByIdService.execute(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request){
        return updateUserService.execute(new UpdateUserCommand(id, request));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return deleteUserService.execute(id);
    }

}
