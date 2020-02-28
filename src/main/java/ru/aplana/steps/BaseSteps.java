package ru.aplana.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.aplana.util.TestProperties;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

        protected static WebDriver driver;
        public static WebDriver getDriver(){
                return driver;
        }
        public static String baseUrl;
        public static Properties properties = TestProperties.getInstance().getProperties();

        @Before
        public static void setUp() throws Exception{
                System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                baseUrl = properties.getProperty("app.url");
                System.out.println(baseUrl);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(baseUrl);
        }

        @After
        public static void tearDown() throws Exception {
                driver.quit();
        }

        @Attachment (type = "text", value = "Text", fileExtension = ".txt")
        public byte[] saveToFileAllure(Map<String,Integer> map){
                String c = "";
                for (Map.Entry<String,Integer> entry: map.entrySet()) {
                         c += entry+"\n";
                }
                int maxValueInMap=(Collections.max(map.values()));
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue()==maxValueInMap) {
                                c+= "--------------------------------------------------\n"+
                                        "Товар с наибольшей ценой\n" +
                                        "--------------------------------------------------\n"+entry+"\n";
                        }
                }

                File file = new File(c);
          return file.toString().getBytes();
        }


}
