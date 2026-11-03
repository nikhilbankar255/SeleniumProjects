package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utility.SelectUtil;
import Utility.WaitUtil;

public class Checkout {

	WebDriver driver;
	SelectUtil selUtil;
	WaitUtil waitUtil;
	
	public Checkout(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		 selUtil= new SelectUtil(); 
		  waitUtil=new WaitUtil();
	}
	
	@FindBy(xpath="//a[@class='cart-icon']")
	WebElement cartbtn;
	
	@FindBy(xpath="//ul[@class='cart-items']//li[1]//a[@class='product-remove']")
	WebElement removebtn;
	
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")
	WebElement checkoutBtn;
	
	@FindBy(xpath="//p[@class='product-name']")
	WebElement productname; 
	
	@FindBy(xpath="//p[@class='quantity']")
	WebElement productQuantity; 
	
	@FindBy(xpath="//input[@class='promoCode']")
	WebElement promoCode; 
	
	@FindBy(xpath="//button[text()='Apply']")
	WebElement applyBtn;  
	
	@FindBy(xpath="//span[@class='promoInfo']")
	WebElement validateMsg;  
	
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeOrderbtn; 
	
	@FindBy(xpath="//select[@style='width: 200px;']")
	WebElement dropdown; 
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement termsCon; 
	
	@FindBy(xpath="//button[text()='Proceed']")
	WebElement proceedbtn; 
	
	@FindBy(xpath="//span[text()='Thank you, your order has been placed successfully ']")
	WebElement successMsg;
	
	@FindBy(xpath="//a[contains(text(),'Flight Booking')]")
	WebElement homePage; 

	public void cartbtn() throws InterruptedException
	{
		Thread.sleep(1000);
		cartbtn.click();
	}
	public void removeCucumber()
	{
		removebtn.click();
	}
	
	public void proceedCheckout()
	{
		checkoutBtn.click();
	}
	
	public void validateProduct() throws InterruptedException
	{
		Thread.sleep(1000);
		if(productname.getText().contains("Brocolli - 1 Kg"))
		{
			System.out.println("Product matched....");
		}
		else {
			System.out.println("Product mismatch..XXXX"+productname.getText());
		}
		
		if(productQuantity.getText().contains("4"))
		{
			System.out.println("Quantity-->"+productQuantity.getText());
		}
		else{
			System.out.println("Quantity-->"+productQuantity.getText());
		}
	}
	
	public void promoCodeApply()
	{
		promoCode.sendKeys("Test");
		applyBtn.click();
		waitUtil.waitForElevisibility(driver, validateMsg);
		
		if(validateMsg.getText().contains("Invalid code ..!"))
		{
			System.out.println("Promo code is invalid....XX");
		}
		else if(validateMsg.getText().contains("Empty code ..!")){
			System.out.println("Empty code....XX");
		}
		
		waitUtil.waitForElevisibility(driver, placeOrderbtn);
		placeOrderbtn.click();
	}
	
	public void chooseCountry()
	{
		waitUtil.waitForElevisibility(driver, dropdown);
		selUtil.selectByVisibleText(dropdown, "India");
		termsCon.click();
		proceedbtn.click();
	}
	
	public void validatesuccessMessage()
	{
		if(successMsg.isDisplayed())
		{
			System.out.println("Order placed successfully...");
		}
		else 
		{
			System.out.println("Failed to place order..XXX");
		}
	}
	
	public void homepageValidation()
	{
		waitUtil.waitForElevisibility(driver, homePage);
		Assert.assertEquals(homePage.getText(), "Flight Booking");
		System.out.println("End to end flow completed...");
	}
	
}
