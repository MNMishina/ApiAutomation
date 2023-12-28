package rest_assured;

public class RegistrationFail {
    private String error;

    public RegistrationFail(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }
}
