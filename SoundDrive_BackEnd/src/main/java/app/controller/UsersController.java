package app.controller;

import app.buisness.*;
import app.domain.Request.CreateUserRequest;
import app.domain.Request.DeleteUserRequest;
import app.domain.Request.GetUserBasedOnAccessTokenRequest;
import app.domain.Request.UpdateUserPasswordRequest;
import app.domain.Response.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final GetUsersUseCase getUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUserBasedoOnAccessTokenUseCase getUserBasedAtUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserPasswordUseCase updateUserPasswordUseCase;

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse response = getUsersUseCase.getUsers();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createUser")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/getUser")
    public ResponseEntity<GetUserResponse> getUser(@RequestBody @Valid GetUserBasedOnAccessTokenRequest request){
        System.out.println(request.getAccessToken());
        GetUserResponse response = getUserBasedAtUseCase.getUser(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<DeleteUserResponse> deleteUser(@RequestBody @Valid DeleteUserRequest request){
        DeleteUserResponse response = deleteUserUseCase.deleteUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UpdateUserPasswordResponse> updatePassword(@RequestBody @Valid UpdateUserPasswordRequest request){
        UpdateUserPasswordResponse response = updateUserPasswordUseCase.updateUser(request);
        return ResponseEntity.ok(response);
    }
}
