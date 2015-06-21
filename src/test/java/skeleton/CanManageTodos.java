package skeleton;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CanManageTodos {
    FirefoxDriver wd;

    @Given("^i have added learn cucumber to the list$")
    public void i_have_added_learn_cucumber_to_the_list() throws Throwable {
        File pathToBinary = new File("/opt/homebrew-cask/Caskroom/firefox/37.0.2/Firefox.app/Contents/MacOS/firefox");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        wd = new FirefoxDriver(ffBinary,firefoxProfile);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://todomvc.com/examples/angularjs/");
        wd.findElement(By.id("new-todo")).click();
        wd.findElement(By.id("new-todo")).clear();
        wd.findElement(By.id("new-todo")).sendKeys("Learn cucumber");
    }

    @When("^i mark the todo deletable$")
    public void i_mark_the_todo_deletable() throws Throwable {
        wd.findElement(By.id("new-todo")).sendKeys("\n");
        if (!wd.findElement(By.xpath("//div[@class='view']/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='view']/input")).click();
        }
    }

    @Then("^i can clear the todo$")
    public void i_can_clear_the_todo() throws Throwable {
        wd.findElement(By.id("clear-completed")).click();
        wd.findElement(By.cssSelector("html")).click();
    }

    @After
    public void tearDown() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
