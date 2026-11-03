package TestCases;

import org.testng.annotations.Test;

import BaseClass.TestBase;
import pages.Checkout;

public class TestCheckout extends TestBase{

	Checkout checkout;
	@Test(priority = 1)
	public void testCheckoutVegies() throws InterruptedException
	{
		 checkout=new Checkout(driver);
		
		checkout.cartbtn();
		checkout.removeCucumber();
		checkout.proceedCheckout();
	}
	
	@Test(priority = 2)
	public void testValidateProduct() throws InterruptedException
	{
		checkout.validateProduct();
		checkout.promoCodeApply();
	}
	
	@Test(priority=3)
	public void testOrder()
	{
		checkout.chooseCountry();
		checkout.validatesuccessMessage();
	}
	
	@Test(priority = 4)
	public void testHomePage()
	{
		checkout.homepageValidation();
	}
}
