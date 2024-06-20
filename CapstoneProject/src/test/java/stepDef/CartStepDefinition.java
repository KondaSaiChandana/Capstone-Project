package stepDef;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import cart.CartPage;
import cart.CategoryPage;
import cart.HomePage;
import cart.ProductsPage;
import cart.ScreenshotBase;
import cart.ScrollDownBase;
import cart.SelectBrowserBase;
import cart.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartStepDefinition {
    WebDriver driver;
    HomePage homePage;
    SignInPage signInPage;
    CartPage cartPage;
    CategoryPage categoryPage;
    ProductsPage  productsPage;
    ScreenshotBase screenshotBase;
    ScrollDownBase scrollDownBase;
    SelectBrowserBase selectBrowserBase;
   
	
    @Given("The user is on the JPetStore SignInpage")
    public void userIsOnTheJPetStoreSignInpage() throws InterruptedException,WebDriverException, ScriptTimeoutException,JavascriptException{
    	selectBrowserBase=new SelectBrowserBase(driver);
    	driver=SelectBrowserBase.selectBrowser("edge");
    	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
    	signInPage=new SignInPage(driver);
    	screenshotBase=new ScreenshotBase(driver);
    	scrollDownBase=new ScrollDownBase(driver);
    	scrollDownBase.scrollDown();
    	
    	
    }
    
    @When("The user signs in with username and password")
    public void userSignsInWithUsernameAndPassword()throws InterruptedException {
    	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(10));
    	signInPage.clickOnSignIn();	
    	Thread.sleep(2000);
    	signInPage.enterUsername();
    	signInPage.enterPassword();
    	Thread.sleep(2000);
    	signInPage.clickLogin();	
    }
    
    @And("The user clicks on the cart image")
    public void clicksOnTheCartImage() throws InterruptedException, IOException {
    	homePage=new HomePage(driver);
    	Thread.sleep(2000);
    	WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage.clickOnCartIcon(); 
        scrollDownBase.scrollDown();
        
    }
    
    @Then("The user should be redirected to the view cart page")
    public void redirectedToViewCartPage() {
    	String currentUrl=driver.getCurrentUrl();
    	assert currentUrl.contains("viewCart");
    	screenshotBase.takeScreenshot("cartPage");
    }
    
    @And("The cart page display Your cart is empty message when there is no items in cart")
    public void cartIsEmptyMessage() {
    	 WebDriverWait wait4=new WebDriverWait(driver, Duration.ofSeconds(10));
    	 WebElement emptyCartMsg=driver.findElement(By.xpath("//td[normalize-space()='Your cart is empty.']"));
    	 String actualMsg=emptyCartMsg.getText();
    	 String expectedmsg="Your cart is empty.";
    	 assertEquals(expectedmsg,actualMsg);
    }
    
    @When("The user clicks on Fish")
    public void clicksOnFish() throws InterruptedException{
    	cartPage=new CartPage(driver);
    	Thread.sleep(2000);
    	WebDriverWait wait5=new WebDriverWait(driver, Duration.ofSeconds(10));
    	cartPage.clickOnFishCategory();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("FishCategory");
    }
    
    @Then("The user should redirected to the fish category page")
    public void  redirectedToFishCategory() {
    	String currentUrl=driver.getCurrentUrl();
    	assert currentUrl.contains("FISH");
    }
    
    @When("The user clicks on product id")
    public void clicksOnProductId() throws InterruptedException{
        categoryPage=new CategoryPage(driver);
    	WebDriverWait wait6=new WebDriverWait(driver, Duration.ofSeconds(10));
    	categoryPage.selectAngelfish();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("FishProducts");
    	
    }
    
    @Then("The user should be redirected to the items page of that product")
    public void redirectedToItemsPage() {
    	String currentUrl=driver.getCurrentUrl();
    	assert currentUrl.contains("FI-SW-01");
    }
    
    @When("The user clicks on add to cart button which is next to item price")
    public void clickOnAddToCart() throws InterruptedException, IOException {
    	productsPage=new ProductsPage(driver);
    	Thread.sleep(2000);
    	WebDriverWait wait7=new WebDriverWait(driver, Duration.ofSeconds(10));
    	productsPage.addLargeAngelfish();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("LargeAngelFishAddedToCart");  	
    }
    
    @Then("The product should be added to the cart and redirected to view cart page")
    public void redirectedToViewCart() throws InterruptedException{
    	String currentUrl=driver.getCurrentUrl();
    	assert currentUrl.contains("viewCart");
    	cartPage.clickOnFishCategory();
    	scrollDownBase.scrollDown();
    	categoryPage.selectAngelfish();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	productsPage.addSmallAngelfish();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("SmallAngelFishAddedToCart");
    	Thread.sleep(2000);
    	cartPage.clickOnFishCategory();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	categoryPage.selectAngelfish();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	productsPage.addSmallAngelfish();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("SameItemAddedMorethanOnce");
    	Thread.sleep(2000);
    	WebElement remove=driver.findElement(By.cssSelector(".button[hx-post='/cart/removeItemFromCart?cartItem=EST-1']"));
    	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(2000);
    	remove.click();
    	Thread.sleep(2000);
    	screenshotBase.takeScreenshot("singleItemRemoved");
        WebElement removeall=driver.findElement(By.cssSelector(".button[hx-post='/cart/removeAllItemsFromCart']"));
        Thread.sleep(2000);
        removeall.click();
        Thread.sleep(2000);
        screenshotBase.takeScreenshot("AllItemsremoved");
    }
    
   
    @And("Add items to cart again")
    public void addItemsAgain() throws InterruptedException {
    	Thread.sleep(2000);
    	cartPage.clickOnDogsCategory();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	categoryPage.selectBullDog();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	productsPage.addFemalePuppyBullDog();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	cartPage.clickOnDogsCategory();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	categoryPage.selectBullDog();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	productsPage.addFemalePuppyBullDog();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	cartPage.clickOnDogsCategory();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	categoryPage.selectBullDog();
    	scrollDownBase.scrollDown();
    	Thread.sleep(2000);
    	productsPage.addMaleAdultBullDog();
    	scrollDownBase.scrollDown();		
    }
   
    @And("set quantity of item and click updateCart")
    public void increaseQuantity() throws InterruptedException {
    	WebElement item1=driver.findElement(By.xpath("//input[@type='number'][@value='2']"));
    	item1.clear();
    	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(10));
    	item1.sendKeys("5");
    	screenshotBase.takeScreenshot("setQuantity");
    	Thread.sleep(2000);
    	WebElement item2=driver.findElement(By.xpath("//input[@type='number'][@value='1']"));
    	item2.clear();
    	item2.sendKeys("0");
    	Thread.sleep(2000);
    	WebElement updateCart=driver.findElement(By.xpath("//button[normalize-space()='Update Cart']"));
    	updateCart.click();
    }
    @Then("Click on Check out process button")
    public void checkOutProcess() throws InterruptedException{
    	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(2000);   	
    	driver.findElement(By.xpath("//a[normalize-space()='Proceed to Checkout']")).click();
    	WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(2000); 
    	driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("AddBillingDetails");
    	WebDriverWait wait12=new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(2000); 
    	driver.findElement(By.xpath("//button[normalize-space()='Confirm']")).click();
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("ConfirmDetails");
    	WebDriverWait wait13=new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement orderMsg=driver.findElement(By.xpath("//p[normalize-space()='Thank you, your order has been submitted.']"));
    	scrollDownBase.scrollDown();
    	screenshotBase.takeScreenshot("OrderPlaced");
    	String actualMsg=orderMsg.getText();
    	String expectedMsg="Thank you, your order has been submitted.";
    	assertEquals(expectedMsg,actualMsg);
    	
    }
    @And("Close Browser")
    public void closeBrowser() throws InterruptedException {
    	Thread.sleep(2000);
    	WebDriverWait wait14=new WebDriverWait(driver, Duration.ofSeconds(10));
    	driver.close();
    }
}