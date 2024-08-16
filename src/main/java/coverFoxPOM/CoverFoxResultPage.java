package coverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResultPage 
{
	@FindBy(xpath = "//div[contains(text(),'Insurance Plans')]")
	private WebElement resultText;
	@FindBy(className = "plan-card-container")private List<WebElement> banners;
	
	@FindBy(xpath="//div[text()='Sort Plans']") private WebElement sortPlanFilter;
	
	
	public CoverFoxResultPage(WebDriver driver)
	{
	    PageFactory.initElements(driver, this);
	}
	
	public void validateBanners() {
		Reporter.log("getting String Result", true);
	String[] ar = resultText.getText().split(" ");
	int result = Integer.parseInt(ar[0]);
	Reporter.log("getting total banners Result", true);
	if (result == banners.size()) {
	System.out.println("Result is matching with banners, TC passed");
	} else {
	System.out.println("Result is not matching with banners, TC failed");
	}
	}
	
	public int getPlanNumbersFromString()
	{
		String ar[]=resultText.getText().split(" ");
		Reporter.log("getting get plan numbers from String", true);
		int result = Integer.parseInt(ar[0]);
		return result;
	}
	
	public int getPlanNumbersFromBanners()
	{
		int bannerSize = banners.size();
		Reporter.log("getting get plan numbers from banners", true);
		return bannerSize;
	}
	
	public boolean sortPlanFilterIsDisplayed()
	{
		Reporter.log("Checking if sort plan filter is displayed", true);
		boolean result = sortPlanFilter.isDisplayed();
		return result;
	}
	
	
	

}
