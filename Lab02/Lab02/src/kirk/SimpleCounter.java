package kirk;

public class SimpleCounter implements Counter {
	  int counter = 0;
	  public void increase() {
	    ++counter;
	  }
	  
	  public int currentValue() {
	    return counter;
	  }
	  
	  public int reset() {
		  counter=0;
		  return counter;
	  }
	  
	  public String toString() {
		  return "Simple Counter";
	  }
}