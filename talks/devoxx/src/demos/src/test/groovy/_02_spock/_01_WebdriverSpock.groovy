package _02_spock

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class _01_WebdriverSpock extends Specification {

    @Shared WebDriver driver = new ChromeDriver()

    def cleanupSpec() {
        driver.quit()
    }

    def "can successfully login"() {
        when:
        driver.get("http://localhost:5050/login")

        then:
        driver.findElement(By.tagName("h1")).text == "Please sign in"

        when:
        driver.findElement(By.name("username")).sendKeys("devoxx")
        driver.findElement(By.name("password")).sendKeys("devoxx")
        driver.findElement(By.tagName("button")).click()

        then:
        driver.findElement(By.tagName("h1")).text == "Login Successful"
    }

    def "can unsuccessfully login"() {
        when:
        driver.get("http://localhost:5050/login")

        then:
        driver.findElement(By.tagName("h1")).text == "Please sign in"

        when:
        driver.findElement(By.name("username")).sendKeys("bad")
        driver.findElement(By.name("password")).sendKeys("user")
        driver.findElement(By.tagName("button")).click()

        then:
        driver.findElement(By.tagName("h1")).text == "Login Failed"
    }

}