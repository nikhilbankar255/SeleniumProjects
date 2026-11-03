package Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectUtil {

	public void selectByVisibleText(WebElement ele, String text)
	{
		Select sel= new Select(ele);
		sel.selectByVisibleText(text);
	}
}

