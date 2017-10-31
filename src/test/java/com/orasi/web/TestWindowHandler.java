package com.orasi.web;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestWindowHandler extends WebBaseTest {

    @BeforeTest(groups = { "regression", "utils", "dev" })
    public void setup() {
        setApplicationUnderTest("Test Site");
        setPageURL("http://google.com");
        testStart("TestWindowHandler");
    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResults) {
    }

    @AfterTest(groups = { "regression", "utils", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestWindowHandler", testResults);
    }

    @Features("Utilities")
    @Stories("WindowHandler")
    @Title("waitUntilNumberOfWindowsAre")
    @Test(groups = { "regression", "smoke" })
    public void waitUntilNumberOfWindowsAre() {
        getDriver().executeJavaScript("window.open('http://bluesourcestaging.herokuapp.com', 'BLAH', 'height=800,width=800');");
        Assert.assertTrue(WindowHandler.waitUntilNumberOfWindowsAre(getDriver(), 2));
    }

    @Features("Utilities")
    @Stories("WindowHandler")
    @Title("waitUntilWindowExistsWithTitle")
    @Test(groups = { "regression", "smoke" }, dependsOnMethods = "waitUntilNumberOfWindowsAre")
    public void waitUntilWindowExistsWithTitle() {
        Assert.assertTrue(WindowHandler.waitUntilWindowExistsWithTitle(getDriver(), "BlueSource"));
    }

    @Features("Utilities")
    @Stories("WindowHandler")
    @Title("waitUntilWindowExistsTitleContains")
    @Test(groups = { "regression", "smoke" }, dependsOnMethods = "waitUntilNumberOfWindowsAre")
    public void waitUntilWindowExistsTitleContains() {
        Assert.assertTrue(WindowHandler.waitUntilWindowExistsTitleContains(getDriver(), "Blue"));
    }

    @Features("Utilities")
    @Stories("WindowHandler")
    @Title("waitUntilWindowExistsTitleMatches")
    @Test(groups = { "regression", "smoke" }, dependsOnMethods = "waitUntilNumberOfWindowsAre")
    public void waitUntilWindowExistsTitleMatches() {
        Assert.assertTrue(WindowHandler.waitUntilWindowExistsTitleMatches(getDriver(), "(?i:.*source)"));
    }

}
