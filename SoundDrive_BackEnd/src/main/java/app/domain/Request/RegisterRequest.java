package app.domain.Request;

public class RegisterRequest {
    public String email;
    public String name;
    public String password;

    public RegisterRequest(String email, String password, String name)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
