package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\DataDrivenTestData.xlsx"; // Path to the Excel file

        // Creating an object for ExcelUtility
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1"); // Get total rows
        int totalcols = xlutil.getCellCount("Sheet1", 1); // Get total columns

        // Create a two-dimensional array to store data
        String logindata[][] = new String[totalrows][totalcols];

        // Read data from Excel and store in the array
        for (int i = 1; i <= totalrows; i++) { // i = rows
            for (int j = 0; j < totalcols; j++) { // j = columns
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // Store cell data
            }
        }

        return logindata; // Return the two-dimensional array
    }
}
