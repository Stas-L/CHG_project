package cucumber;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestUtils {

    public static String getCredentialsDataFromExcel(int vRow, int vColumn) {
        String value;
        Workbook wb = null;
        try {
            FileInputStream fis = new FileInputStream("src\\test\\resources\\testData.xlsx");
            wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(vRow);
            Cell cell = row.getCell(vColumn);
            value = cell.getStringCellValue();
            return value;
        } catch (IOException e) {
            System.err.println("NullPointerException occurred: " + e.getMessage());
            throw new RuntimeException("ERROR: You need to use correct data from Excel file : " + e.getMessage());
        }
    }

    public static void fillLoginPageFields(WebDriverWait wait, WebElement element, int vRow, int vColumn) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.toString().toLowerCase().contains("date") || element.getAttribute("defaultValue").contains("date")) {
            clickWhenVisibleAndClickable(wait, element);
        }
        if (!element.getAttribute("defaultValue").isEmpty()) {
            element.clear();
        }
        element.sendKeys(getCredentialsDataFromExcel(vRow, vColumn));
        wait.until(e -> element.getAttribute("defaultValue").equals(getCredentialsDataFromExcel(vRow, vColumn)));
    }

    public static void clickWhenVisibleAndClickable(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void captureScreenShot(WebDriver driver, String methodName, String className) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("target/ScreenshotsFailure/%s-%s.png", className, methodName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}