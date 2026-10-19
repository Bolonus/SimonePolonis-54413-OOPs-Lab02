package kirk;

public class OverflowCounter implements Counter {
	  int counter = 0;
	  
	  public void increase() {
		  if(counter==10) {
			  counter=0;
		  }
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
		  return "Overflow Counter";
	  } 
}
