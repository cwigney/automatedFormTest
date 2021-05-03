package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilites.ReadExcelFile;

import utilites.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class testCase {

    @Test(dataProvider = "testData")
    public void test(String firstName, String surname, String email, String mobile, String address, String gender){

        System.setProperty("webdriver.chrome.driver",Constants.chromeDriverLocation);
        WebDriver driver = new ChromeDriver();
        driver.get(Constants.url);

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(surname);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        WebElement genderMale = driver.findElement(By.id("gender-radio-1"));
        WebElement genderFemale = driver.findElement(By.id("gender-radio-2"));
        WebElement other = driver.findElement(By.id("gender-radio-3"));

        JavascriptExecutor js =(JavascriptExecutor) driver;

        switch(gender){
            case "male":
                js.executeScript("arguments[0].click();", genderMale);
                break;
            case "female":
                js.executeScript("arguments[0].click();", genderFemale);
                break;
            case "other":
                js.executeScript("arguments[0].click();", other);
        }

        driver.findElement(By.id("userNumber")).sendKeys(mobile);
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        WebElement submit = driver.findElement(By.xpath("//*[@id='submit']"));
        js.executeScript("arguments[0].click();", submit);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assertTrue (driver.getPageSource().contains("Thanks for submitting the form"),"Test Passes");

        driver.quit();

       }

@DataProvider(name = "testData")
    public Object[][] testDataExample() throws IOException {

        ReadExcelFile formData = new ReadExcelFile();
        int rows = formData.getRowCount(0);

        Object[][] formInput = new Object[rows][6];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 6; j++) {
                formInput[i][j] = formData.getCellData(0,i,j);
            }
        }
        return formInput;
    }

}
