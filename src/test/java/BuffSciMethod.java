import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.CDATASection;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BuffSciMethod {
    WebDriver driver;
    Faker faker = new Faker();
    Actions actions;


    @BeforeMethod
    public void setDriver() {
        driver = WebDriverFactory.getDriver("Chrome");
        driver.get("https://www.buffsci.org/");
        driver.manage().window().maximize();


    }

    public void setClosePopUp() {
        WebElement closePopUp = closePopUp = driver.findElement(new By.ByXPath("//*[@id=\"popupmodal\"]/div/div/div/div[1]/div[2]/button/span"));
        closePopUp.click();

    }
    @AfterMethod
    public void quit() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

    }

    @Test
    public void setBannerPage() {
        WebElement applyNowButton = driver.findElement(By.linkText("Apply Now !"));
        applyNowButton.click();

        setClosePopUp();


        String mainHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            // System.out.println("Current Title = " + driver.getTitle());
        }

        String expectedEnrolBuffaloWebSite = "Enroll Buffalo Charters | SchoolMint";
        String actualEnrolBuffaloWebSite = driver.getTitle();

        Assert.assertEquals(actualEnrolBuffaloWebSite, expectedEnrolBuffaloWebSite, "Enroll Buffalo Charters | SchoolMint");
        System.out.println("Apply Button Works at the Banner");

        driver.switchTo().window(mainHandle);
        //System.out.println(driver.getTitle());


    }

    @Test
    public void itHelpDesk() {
        setClosePopUp();

        WebElement itHelpDeskButton = driver.findElement(By.cssSelector("#topbar > div > div > a:nth-child(1)"));
        itHelpDeskButton.click();

        String mainHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
        }


        String expectedITHelpDeskURL = "https://myschedule.buffsci.org/helpdesk";
        Assert.assertTrue(expectedITHelpDeskURL.equals(driver.getCurrentUrl()), "https://myschedule.buffsci.org/helpdesk");
        System.out.println("IT Help Desk Button Works");

        WebElement bestNumberToReachYou = driver.findElement(By.xpath("//*[@id=\"input-47\"]"));
        bestNumberToReachYou.sendKeys(faker.phoneNumber().cellPhone());

        WebElement message = driver.findElement(By.id("input-50"));
        message.sendKeys(faker.backToTheFuture().quote());

        WebElement sendMessage = driver.findElement(By.xpath("//*[@id=\"inspire\"]/div/div[1]/div/button/span"));
        sendMessage.click();

    }

    @Test
    public void sendUsAMessage() {
        setClosePopUp();
        Select messageTo = new Select(driver.findElement(By.name("to")));
        messageTo.selectByIndex(4);

        WebElement name = driver.findElement(By.cssSelector("#name"));
        name.sendKeys(faker.name().fullName());

        WebElement yourEmail = driver.findElement(By.cssSelector("#email"));
        yourEmail.sendKeys(faker.internet().emailAddress());

        WebElement subject = driver.findElement(By.cssSelector("#subject"));
        subject.sendKeys(faker.university().name());

        WebElement message = driver.findElement(By.name("message"));
        message.sendKeys(faker.gameOfThrones().quote());

        WebElement submit = driver.findElement(By.cssSelector("#footer > div.footer-top > div > div > div:nth-child(2) > div > form > div.row > div.col-sm-4 > button"));
        submit.click();


    }


    @Test
    public void aboutAndAchivementButonsMethod() throws InterruptedException {
        setClosePopUp();

        actions = new Actions(driver);

        WebElement aboutButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/nav/ul/li[2]/a"));
        actions.moveToElement(aboutButton).click(aboutButton).perform();

        WebElement aboutConfirmationText = driver.findElement(By.cssSelector("#about > div > div > div.col-lg-7.col-md-6 > div > h2"));
        Assert.assertEquals(aboutConfirmationText.getText(), "BuffSci is an Award Winning Tuition-free Charter School.", "BuffSci is an Award Winning Tuition-free Charter School.");
        System.out.println("About Button Works");

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        System.out.println(" ");

        WebElement achivementButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/nav/ul/li[3]/a"));
        actions.moveToElement(achivementButton).click(achivementButton).perform();

        WebElement achivementConfimationText = driver.findElement(By.xpath("//*[@id=\"services\"]/div/header/h3"));
        Assert.assertEquals(achivementConfimationText.getText(), "Achievements", "Achievements Text");
        System.out.println("Achievements Button Works");

        Thread.sleep(2000);
        System.out.println(" ");


        WebElement schoolButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/nav/ul/li[4]/a"));
        actions.moveToElement(schoolButton).perform();


    }


    @Test
    public void schoolsMenuMethod() {
        setClosePopUp();

        //*[@id="header"]/div[2]/nav/ul/li[4]/ul/li[1]/a
        actions = new Actions(driver);

        WebElement schoolButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/nav/ul/li[4]/a"));
        actions.moveToElement(schoolButton).perform();


    }


}