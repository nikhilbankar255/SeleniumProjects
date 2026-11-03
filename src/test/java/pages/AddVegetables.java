package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.WaitUtil;

public class AddVegetables{

	WebDriver driver;
	WaitUtil util;
	public AddVegetables(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		util= new WaitUtil();
	}
	
	@FindBy(xpath="//input[@type='search']")
	 WebElement searchbox;
	
	@FindBy(xpath="//a[@class='increment']")
	 WebElement increment;
	
	@FindBy(xpath="//button[text()='ADD TO CART']")
	 WebElement AddtoKart;
	
	@FindBy(xpath="//button[text()='✔ ADDED']")
	 WebElement addedMessageText;
	
	@FindBy(xpath = "//input[@type='number']")
	WebElement count;
	
	
	public void serachField()
	{
		searchbox.click();
		searchbox.sendKeys("cucumber");
	}
	
	public void addCucumbers() throws InterruptedException
	{
		increment.click();
		Thread.sleep(1000);
		increment.click();
	}
	public void addToKart()
	{
		AddtoKart.click();
	}
	public void successMessage()
	{
		if(addedMessageText.isDisplayed())
		{
			System.out.println("Cucumber added successfully..");
		}
		else {
			System.out.println("Failed to Add Vegetable cucumber..XXXXX");
		}
	}
	
	public void addBrocoli() throws InterruptedException
	{
		searchbox.clear();
		searchbox.sendKeys("Brocolli");
		increment.click();
		Thread.sleep(1000);
		
		increment.click();
		if(count.getAttribute("value")=="4")
		{
			System.out.println("Number of items added ->"+count.getAttribute("value"));
		}
		else {
			System.out.println("Less added==>"+count.getAttribute("value"));
		}
	}
	
	public void addKartBrocoli()
	{
		AddtoKart.click();
	}
	
	public void addedMessage()
	{
		if(addedMessageText.isDisplayed())
		{
			System.out.println("Brocoli added successfully..");
		}
		else {
			System.out.println("Failed to Add Vegetable Brocoli..XXXXX");
		}
	}
}
