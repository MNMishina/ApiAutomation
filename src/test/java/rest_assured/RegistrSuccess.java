package rest_assured;

public class RegistrSuccess {
    private Integer id;
    private String token;

    public RegistrSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
