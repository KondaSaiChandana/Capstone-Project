package cart;

import org.junit.runner.JUnitCore;
import org.testng.annotations.Test;

public class CartRunnerWrapper {
   @Test
   public void runCart() {
	   JUnitCore.runClasses(CartRunner.class);
   }
}