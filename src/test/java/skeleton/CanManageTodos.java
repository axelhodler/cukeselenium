package skeleton;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class CanManageTodos {
    FirefoxDriver wd;

    @Given("^i have added learn cucumber to the list$")
    public void i_have_added_learn_cucumber_to_the_list() throws Throwable {
        System.setProperty("webdriver.gecko.driver",
                           "/usr/local/share/geckodriver");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://todomvc.com/examples/angularjs/");
        wd.findElement(By.id("new-todo")).click();
        wd.findElement(By.id("new-todo")).clear();
        wd.findElement(By.id("new-todo")).sendKeys("Learn cucumber");
        wd.findElement(By.id("new-todo")).sendKeys(Keys.ENTER);
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
        wd.quit();
    }

}
