package excel_reader;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
    @DataProvider
    public Object[][] usersFromSheet1() throws Exception {
        String path = "src/main/resources/users.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getExcelSheetData();
    }

    @DataProvider
    public Object[][] credentialsFromSheet2() throws Exception {
        String path = "src/main/resources/users.xlsx";
        ExcelReader excelReader = new ExcelReader(path, "Лист2");
        return excelReader.getCustomExcelSheetData();
    }

    @DataProvider
    public Object[][] usersForApi() throws Exception {
        String path = "src/main/resources/usersForReqres.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getExcelSheetData();
    }
}
