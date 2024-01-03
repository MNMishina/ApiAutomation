package excel_reader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExcelTest {
    @Test(dataProvider = "usersFromSheet1", dataProviderClass = ExcelDataProvider.class)
    public void test(String... params) {
        System.out.println("User " + params[1] + " " + params[2] + " with id - " + params[0]);
    }
    @Test(dataProvider = "credentialsFromSheet2", dataProviderClass = ExcelDataProvider.class)
    public void test2(String param1, String param2) {
        System.out.println("Логин " + param1);
        System.out.println("Пароль " + param2);
    }
    @Test(dataProvider = "usersForApi", dataProviderClass = ExcelDataProvider.class)
    public void getUsersForReqres(String... params) {
        int id = (int) Double.parseDouble(params[0]);
        Response response = given()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/" + id)
                .then().log().body()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
       // String email = jsonPath.getString("data.email");

        Assert.assertEquals(jsonPath.getDouble("data.id"), id);
        Assert.assertEquals(jsonPath.getString("data.email"), params[1]);
        Assert.assertEquals(jsonPath.getString("data.first_name"), params[2]);
        Assert.assertEquals(jsonPath.getString("data.last_name"), params[3]);
        Assert.assertEquals(jsonPath.getString("data.avatar"), params[4]);

    }

}
