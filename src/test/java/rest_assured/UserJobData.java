package rest_assured;

public class UserJobData {
    private String name;
    private String job;

    public UserJobData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
