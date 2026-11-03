package TestCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import BaseClass.TestBase;
import pages.AddVegetables;

public class TestSearchAddVegies extends TestBase {

	AddVegetables vegies;
	
	@Test(priority=1)
	public void testSearchAddCucumber() throws InterruptedException {
		 vegies = new AddVegetables(driver);
		vegies.serachField();
		vegies.addCucumbers();
		vegies.addToKart();
		vegies.successMessage();
	}
	
	@Test(priority=2)
	public void testSearchAddBrocoli() throws InterruptedException
	{
		vegies = new AddVegetables(driver);
		vegies.addBrocoli();
		vegies.addKartBrocoli();
		vegies.addedMessage();
	}
}
