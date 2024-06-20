package cart;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src//test//java//features//cart.feature",
		glue="stepDef",
		plugin= {"pretty","html:target/cart_Reports.html"},
		monochrome=true
        )
public class CartRunner {

}
