package kirk;

public class DoubleCounter implements Counter {
	  int counter = 0;
	  public void increase() {
	    counter += 2;
	  }
	  
	  public int currentValue() {
	    return counter;
	  }

	  public int reset() {
		  counter=0;
		  return counter;
	  }
	  
	  public String toString() {
		  return "Double Counter";
	  }
	}