public interface CalcBehavior{
	void doSomething();
	String getSymbol();
}

class BaseBehavior implements CalcBehavior{
	private Calculator calc;
	private String symbol;
	
	public String getSymbol(){
		return symbol;
	}
	public Calculator getCalc(){
		return calc;
	}
	public BaseBehavior(Calculator calc, String symbol){
		this.calc = calc;
		this.symbol = symbol;
	}
	public void doSomething(){}
}
class Multiply extends BaseBehavior{
	public Multiply(Calculator calc, String symbol){
		super(calc, symbol);
	}
	public String getSymbol(){
		return super.getSymbol();
	}
	public void doSomething(){
		getCalc().multiply();
	}
}

class Add extends BaseBehavior{
	public Add(Calculator calc, String symbol){
		super(calc, symbol);
	}
	public String getSymbol(){
		return super.getSymbol();
	}
	public void doSomething(){
		getCalc().add();
	}
}

class Subtract extends BaseBehavior{
	public Subtract(Calculator calc, String symbol){
		super(calc, symbol);
	}
	public String getSymbol(){
		return super.getSymbol();
	}
	public void doSomething(){
		getCalc().subtract();
	}
}

class Divide extends BaseBehavior{
	public Divide(Calculator calc, String symbol){
		super(calc, symbol);
	}
	public String getSymbol(){
		return super.getSymbol();
	}
	public void doSomething(){
		getCalc().divide();
	}
}

class Power extends BaseBehavior{
	public Power(Calculator calc, String symbol){
		super(calc, symbol);
	}
	public String getSymbol(){
		return super.getSymbol();
	}
	public void doSomething(){
		getCalc().power();
	}
}