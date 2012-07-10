import java.util.*;

public class Calculator{
	
	private double value1;  //the first value to be used by calculator
	private double value2;  //the second value to be used
	private String operator;//the operator(+, -, *, /)
	private double answer;  //the answer of the calclator
	private boolean val1;   //if true, set value 1, else set value2
	
	//private constructor, don't want anyone making new instances of a calculator
	private Calculator(){
		value1 = 0;  
		value2 = 0;
		operator = "+"; 
		answer = 0;
		val1 = true;
	}
	
	//public method to return a calculator for use
	public static Calculator getCalculator(){
		return new Calculator();
	}
	
	//getters for the values of calculator
	public double getVal1(){
		return value1;
	}
	public double getVal2(){
		return value2;
	}
	public String getOperator(){
		return operator;
	}
	public double getAnswer(){
		return answer;
	}
	public boolean getWhichVal(){
		return val1;
	}
	
	//values to set the calculator
	public void setVal1(double value1){
		this.value1 = value1;
	}
	public void setVal2(double value2){
		this.value2 = value2;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}
	public void setAnswer(double answer){
		this.answer = answer;
	}
	public void setWhichVal(boolean val1){
		this.val1 = val1;
	}
	
	//goal of this method is to make the computation based on current values
	public void evaluate(){
		if(operator.equals("+")){
			answer = value1 + value2;
		}
		else if(operator.equals("-")){
			answer = value1 - value2;
		}
		else if(operator.equals("*")){
			answer = value1 * value2;
		}
		else if(operator.equals("/")){
			answer = value1 / value2;
		}
		else if(operator.equals("pow")){
			answer = Math.pow(value1, value2);
		}
		//else if(operator.equals("!")){
		//	answer = fact();
		//}
		value1 = answer;
		val1 = false;
	}
	
	public double fact(double aValue){
		double x = 1;
		for(int i = 1; i <= aValue; i++){
			x *= i;
		}
		return x;
	}
	//adds two values, evaluates current as well
	public void add(){
		evaluate();
		operator = "+";
	}
	//evaluates current values and sets the operator to -
	public void subtract(){
		evaluate();
		operator = "-";
	}
	//evaluates current values and sets operator to *
	public void multiply(){
		evaluate();
		operator = "*";
	}
	//evaluates current values and sets operator to /
	public void divide(){
		evaluate();
		operator = "/";
	}
	//evaluates current values and sets operator to powe
	public void power(){
		evaluate();
		operator = "pow";
	}
	//clears everything to default
	public void fullClear(){
		value1 = 0;
		value2 = 0;
		operator = "+";
		answer = 0;
		val1 = true;
	}
	//sets a value to negative, uses the flag to determine which one to change
	public void setNegative(){
		if(val1){
			value1 = 0 - value1;
		} 
		else{
			value2 = 0 - value2;
		}
	}
	
	//find the square root of the number
	public void squareRoot(){
		if(val1){
			value1 = Math.sqrt(value1);
		}
		else{
			value2 = Math.sqrt(value2);
		}
	}
	
	//find the reciprocal of current number
	public void reciprocal(){
		if(val1){
			value1 = 1 / value1;
			//return value1;
		}
		else{
			value2 = 1 / value2;
			//return value2;
		}
	}
	
	//uses the vlag to set value
	public void setValue(double value){
		if(val1){
			value1 = value;
		}
		else{
			value2 = value;
		}
		if(val1){
			val1 = !val1;
		}
	}
	public static void main(String[] args){
		Calculator x = getCalculator();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.setVal1(10);
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.add();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.setVal2(100);
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.multiply();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.setValue(10);
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.evaluate();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.setValue(10);
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.divide();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.evaluate();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		Calculator y = getCalculator();
		y.setValue(4);
		//System.out.println(y.fact());
		x.fullClear();
		x.setValue(4);
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.power();
		x.setValue(2);
		//x.evaluate();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.divide();
		System.out.println(x.getVal1() + x.getOperator() + x.getVal2() + " = " + x.getAnswer());
		x.reciprocal();
		System.out.println(x.getVal2());
	}
}
