package app.controller;

import app.buisness.LoginUseCase;
import app.domain.Request.LoginRequest;
import app.domain.Response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    LoginUseCase loginUse;
    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request)
    {
        LoginResponse response = loginUse.login(request);
        return ResponseEntity.ok(response);
    }

}
