package rest_assured;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.Clock;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReqresTestShortVersion {
    private final static String URL = "https://reqres.in";

    @Test
    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get("/rest_assured/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void successRegTest() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Registration user = new Registration("eve.holt@reqres.in", "pistol");
        RegistrSuccess regSuccess = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(RegistrSuccess.class);

        Assert.assertNotNull(regSuccess.getId());
        Assert.assertNotNull(regSuccess.getToken());
        Assert.assertEquals(id, regSuccess.getId());
        Assert.assertEquals(token, regSuccess.getToken());
    }

    @Test
    public void unsuccessRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Registration user = new Registration("sydney@fife", "");
        RegistrationFail regFail = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(RegistrationFail.class);
        Assert.assertEquals(regFail.getError(), "Missing password");
    }

    @Test
    public void sortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());  // actual
        System.out.println(years);
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());  // expected
        System.out.println(sortedYears);
        Assert.assertEquals(years, sortedYears);
    }

    @Test
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseUniqSpec(204));
        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }
    @Test
    public void matchTimeTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserJobData user = new UserJobData("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        //Assert.assertEquals(response.getUpdatedAt().replaceAll(regex, ""), currentTime);
        System.out.println(currentTime);
        System.out.println(response.getUpdatedAt().replaceAll(regex, ""));
    }

}
