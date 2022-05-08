package com.webapp.bankapp;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    @Test
    void testClientsFilterByTypes() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.cssSelector("div.form-check:nth-child(4) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("div.form-check:nth-child(7) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("button.btn:nth-child(8)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 2 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.cssSelector("button.btn:nth-child(8)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testClientsFilterByDateRange() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("date_from")).sendKeys("2015-03-31");
        driver.findElement(By.name("date_to")).sendKeys("2022-03-31");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(2) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 9 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("date_from")).sendKeys("2022-03-31");
        driver.findElement(By.name("date_to")).sendKeys("2022-03-31");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(2) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testClientsFilterBySumRange() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("sum_from")).sendKeys("0");
        driver.findElement(By.name("sum_to")).sendKeys("50000");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(3) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 2 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("sum_from")).sendKeys("50000");
        driver.findElement(By.name("sum_to")).sendKeys("50000");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(3) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testClientsFilterByStr() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("str")).sendKeys("Виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 1 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("str")).sendKeys("виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testAccountsFilterByTypes() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.cssSelector("div.form-check:nth-child(4) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("div.form-check:nth-child(7) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("button.btn:nth-child(8)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 2 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.cssSelector("button.btn:nth-child(8)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testAccountsFilterByDateRange() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("date_from")).sendKeys("2015-03-31");
        driver.findElement(By.name("date_to")).sendKeys("2022-03-31");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(2) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 27 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("date_from")).sendKeys("2022-03-31");
        driver.findElement(By.name("date_to")).sendKeys("2022-03-31");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(2) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testAccountsFilterBySumRange() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("sum_from")).sendKeys("0");
        driver.findElement(By.name("sum_to")).sendKeys("50000");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(3) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 3 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("sum_from")).sendKeys("50000");
        driver.findElement(By.name("sum_to")).sendKeys("50000");
        driver.findElement(By.cssSelector("div.col-sm:nth-child(3) > form:nth-child(1) > div:nth-child(1) > button:nth-child(5)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testAccountsFilterByStr() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("str")).sendKeys("5");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 4 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("str")).sendKeys("виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testBranchesFilterByStr() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Отделения")).click();
        driver.findElement(By.name("str")).sendKeys("Головной офис");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 1 + 1);

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        driver.findElement(By.name("str")).sendKeys("виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 0 + 1);

        driver.quit();
    }

    @Test
    void testBranchChangeAddress() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Отделения")).click();

        // first branch
        driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) >" +
                " td:nth-child(6) > form:nth-child(1) > button:nth-child(2)")).click();

        WebElement input = driver.findElement(By.name("address"));
        input.clear();
        input.sendKeys("Новый адрес");

        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();

        // check whether it was changed

        driver.findElement(By.partialLinkText("Отделения")).click();
        assertEquals(driver.findElements(By.xpath("//*[text()='Новый адрес']")).size(), 1);

        driver.quit();
    }

    @Test
    void testAccountChangeType() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("str")).sendKeys("Виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 1 + 1);
        // open the accounts page
        driver.findElement(By.cssSelector("button.btn:nth-child(3)")).click();
        assertEquals("Счета", driver.getTitle());
        // open the account
        driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)")).click();
        WebElement input = driver.findElement(By.name("type"));
        input.clear();
        input.sendKeys("1");
        driver.findElementByCssSelector("button.btn:nth-child(8)").click();
        assertEquals("Счёт", driver.getTitle());

        //incorrect type:
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("str")).sendKeys("Виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 1 + 1);
        // open the accounts page
        driver.findElement(By.cssSelector("button.btn:nth-child(3)")).click();
        assertEquals("Счета", driver.getTitle());
        // open the account
        driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)")).click();
        input = driver.findElement(By.name("type"));
        input.clear();
        input.sendKeys("-1");
        driver.findElementByCssSelector("button.btn:nth-child(8)").click();
        assertEquals("Ошибка", driver.getTitle());
        driver.quit();
    }

    @Test
    void testClientAdd() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElementByCssSelector("button.btn:nth-child(1)").click();
        driver.findElementByName("type").sendKeys("1");
        driver.findElementByName("name").sendKeys("Андрей");
        driver.findElementByName("middle_name").sendKeys("Отчество");
        driver.findElementByName("surname").sendKeys("Фамилия");
        driver.findElementByName("address").sendKeys("Адрес");
        driver.findElementByName("phone_number").sendKeys("79148294422");
        driver.findElementByName("email").sendKeys("a@b.ru");
        driver.findElementByName("date_of_birth").sendKeys("1988-03-31");
        driver.findElementByCssSelector(".btn").click();
        assertEquals("Андрей", driver.getTitle());

        // incorrect input
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElementByCssSelector("button.btn:nth-child(1)").click();
        driver.findElementByCssSelector(".btn").click();
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }

    @Test
    void testClientRemove() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        int number_before = driver.findElements(By.tagName("tr")).size();
        // edit
        driver.findElementByCssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) " +
                "> td:nth-child(11) > form:nth-child(1) > button:nth-child(2)").click();
        // delete
        driver.findElementByCssSelector("button.btn:nth-child(2)").click();
        assertEquals("Клиенты", driver.getTitle());
        int number_after = driver.findElements(By.tagName("tr")).size();
        assertEquals(number_after, number_before - 1);

        driver.quit();
    }

    @Test
    void testBranchAdd() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Отделения")).click();
        // add branch
        driver.findElementByCssSelector("button.btn:nth-child(1)").click();
        driver.findElementByName("name").sendKeys("Название отделения");
        driver.findElementByName("address").sendKeys("Адрес");
        driver.findElementByCssSelector(".btn").click();
        assertEquals("Отделение", driver.getTitle());

        driver.quit();
    }

    @Test
    void testClientCloseAccount() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Клиенты")).click();
        driver.findElement(By.name("str")).sendKeys("Виктор");
        driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(), 1 + 1);
        // open the accounts page
        driver.findElement(By.cssSelector("button.btn:nth-child(3)")).click();
        assertEquals("Счета", driver.getTitle());
        // open the account
        driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)")).click();
        WebElement input = driver.findElement(By.name("is_active"));
        input.clear();
        input.sendKeys("0");
        driver.findElementByCssSelector("button.btn:nth-child(8)").click();
        assertEquals("Счёт", driver.getTitle());

        driver.quit();
    }

    @Test
    void testOperationChange() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        // open one
        driver.findElementByCssSelector(".table > tbody:nth-child(2) > " +
                "tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)").click();
        // change first operation
        driver.findElementByCssSelector(".table > tbody:nth-child(2) > " +
                "tr:nth-child(1) > td:nth-child(4) > form:nth-child(1) > button:nth-child(2)").click();
        WebElement input = driver.findElement(By.name("amount"));
        input.clear();
        input.sendKeys("500");
        // save
        driver.findElementByCssSelector("button.btn:nth-child(5)").click();
        assertEquals("Операция", driver.getTitle());

        // incorrect
        input = driver.findElement(By.name("amount"));
        input.clear();
        input.sendKeys("50000000000000000000000000000");
        // save
        driver.findElementByCssSelector("button.btn:nth-child(5)").click();
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }


    @Test
    void testOperationAdd() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        // open one
        driver.findElementByCssSelector(".table > tbody:nth-child(2) > " +
                "tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)").click();
        driver.findElementByCssSelector("button.btn:nth-child(1)").click();
        assertEquals("Добавить операцию", driver.getTitle());
        driver.findElementByName("amount").sendKeys("5000");
        driver.findElementByName("timestamp").sendKeys("2021-05-11 01:59:59.0");
        driver.findElementByCssSelector(".btn").click();
        assertEquals("Операция", driver.getTitle());

        // incorrect input

        driver.get("http://localhost:8080/");
        assertEquals("Банковская система", driver.getTitle());
        driver.findElement(By.partialLinkText("Счета")).click();
        // open one
        driver.findElementByCssSelector(".table > tbody:nth-child(2) > " +
                "tr:nth-child(1) > td:nth-child(8) > form:nth-child(1) > button:nth-child(2)").click();
        driver.findElementByCssSelector("button.btn:nth-child(1)").click();
        assertEquals("Добавить операцию", driver.getTitle());
        driver.findElementByName("amount").sendKeys("5000");
        driver.findElementByName("timestamp").sendKeys("today");
        driver.findElementByCssSelector(".btn").click();
        assertEquals("Ошибка", driver.getTitle());
        driver.quit();
    }

}
