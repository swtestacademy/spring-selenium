package com.udemy.spring.springselenium.visa;

import com.udemy.spring.springselenium.SpringBaseTestNGTest;
import com.udemy.spring.springselenium.entity.User;
import com.udemy.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.udemy.spring.springselenium.page.visa.VisaRegistrationPage;
import com.udemy.spring.springselenium.repository.UserRepository;
import java.sql.Date;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserVisaTest extends SpringBaseTestNGTest {

    private static final Logger logger = LoggerFactory.getLogger(UserVisaTest.class);

    @Autowired
    private VisaRegistrationPage registrationPage;

    @Autowired
    private UserRepository repository;

    @LazyAutowired
    private ApplicationContext applicationContext;

    @Test(dataProvider = "getData")
    public void visaTest(User u) {
        this.registrationPage.goTo();
        this.registrationPage.setNames(u.getFirstName(), u.getLastName());
        this.registrationPage.setCountryFromAndTo(u.getFromCountry(), u.getToCountry());
        this.registrationPage.setBirthDate(u.getDob().toLocalDate());
        this.registrationPage.setContactDetails(u.getEmail(), u.getPhone());
        this.registrationPage.setComments(u.getComments());
        this.registrationPage.submit();

        logger.info("Request confirmation # INFO : " + this.registrationPage.getConfirmationNumber());
        logger.warn("Request confirmation # WARN : " + this.registrationPage.getConfirmationNumber());
    }

    @AfterClass
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
    }

    @DataProvider
    public Object[][] getData(ITestContext context) {
        return this.repository.findByDobBetween(
                Date.valueOf(context.getCurrentXmlTest().getParameter("dobFrom")),
                Date.valueOf(context.getCurrentXmlTest().getParameter("dobTo"))
            )
            .stream()
            .map(user -> new Object[] { user })
            .limit(3)
            .toArray(Object[][]::new);
    }

}
