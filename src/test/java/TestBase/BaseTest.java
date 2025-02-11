package TestBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BaseTest {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;
    @BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws IOException {
        logger= LogManager.getLogger(this.getClass());
        //reading the data from the properties.file
        FileReader file=new FileReader("./src//test//resources//properties.file");
        p=new Properties();
        p.load(file); // loading the properties.file

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //for operating system
            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else {
                System.out.println("no matching os");
                return;
            }

            //for browser
            switch (br.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default:
                    System.out.println("no matching browser"); return;
            }

            driver=new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"),capabilities);
        }
        if (p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase())
            {
                case "chrome": driver=new ChromeDriver(); break;
                case "edge": driver=new EdgeDriver(); break;
                case "firefox": driver=new FirefoxDriver(); break;
                default:
                    System.out.println("invalid browser"); return;
            }
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL1"));

    }

    @AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public void close()
    {
        driver.quit();
    }

    public String randomemail ()
    {
        String randomstring= RandomStringUtils.randomAlphabetic(5);
        return randomstring;
    }

    //this method will generate the random 5 numeric digits in string format from 0-9
    public String randomphone ()
    {
        String randomphonenum= RandomStringUtils.randomNumeric(5);
        return randomphonenum;
    }

    public String randomalphanumeric()
    {
        String randomstring= RandomStringUtils.randomAlphabetic(5);
        String randomphonenum= RandomStringUtils.randomNumeric(5);
        return (randomstring+randomphonenum);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
    @AfterSuite
    public void sendEmail() {
        System.out.println("Preparing to send Extent Report via Email...");

        // Path to Extent Report
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
        File reportFile = new File(reportPath);

        if (!reportFile.exists()) {
            System.out.println("Report not found: " + reportPath);
            return;
        }

        try {
            // Create email message
            Email email = new MultiPartEmail();
            email.setHostName("smtp.googlemail.com"); // Gmail SMTP
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-password"));
            email.setSSLOnConnect(true);
            email.setFrom("your-email@gmail.com"); // Sender
            email.setSubject("Automation Test Report");
            email.setMsg("Please find the attached Extent Report.");
            email.addTo("hassainshaik347@gmail.com"); // Receiver

            // Attach the Extent Report
            ((MultiPartEmail) email).attach(reportFile);

            // Send the email
            email.send();
            System.out.println("Extent Report Sent Successfully!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
