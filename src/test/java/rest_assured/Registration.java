package rest_assured;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Registration {
    private String email;
    private String password;

    public Registration(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
