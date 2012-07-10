import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//36 is the display length before errors(need to add a clause to handle erasing display when >= 36)

/**
 *
 *  @author Kenny 
 *
 **/
public class CalcView{
	//A calculator which the gui manipulates
	private Calculator calc = Calculator.getCalculator();
	private GridBagLayout gridbag = new GridBagLayout(); //the gui uses a brid bag layout
	private JFrame frame = new JFrame(); //creates the main window
	private JPanel pane = new JPanel();  //also creates a panel which holds everything in the calculator
	/**
	 *	boolean variable which helps correctly set the calculators view
	 *	when clearView is true the numbers entered will reset the calculators view
	 *	if it is false it will append the number to the calculators view
	 **/
	private boolean clearView = true;
	
	private JTextField view; //The calculators view for calculations
	//The button that controls decimal
	private JButton decimalButton; 
	//the number buttons
	private JButton[] theButtons;
	//controls the + operator
	private JButton buttonPlus;    
	//controls the - operator
	private JButton buttonMinus;  
	//controls the * operator
	private JButton buttonTimes;   
	//controls the / operator
	private JButton buttonDivide;  
	//controls the =
	private JButton buttonEquals;  
	//controls percent conversion
	private JButton buttonPercent; 
	//controls squaring some number
	private JButton buttonSquare;  
	//controls square rooting some number
	private JButton buttonSqrt;    
	//returns the reciprocal of some number
	private JButton buttonReciprocal;
	//inverts the number(- becomes +, + becomes -)
	private JButton buttonInvert;  
	//number exponentiation
	private JButton buttonExponent;
	//clears one item on the calculators display and view
	private JButton buttonDisplayBack; 
	//clears the current item
	private JButton buttonClear;   
	//fully clears the calculator
	private JButton buttonFullClear;
	//button for the constant e
	private JButton buttonE;       
	//button for the constrant pi
	private JButton buttonPi;      
	//button to produces the factorial of some number
	private JButton buttonFact;    
	//the display of the calculator
	private JLabel displayLabel;
	
	/**
	 *	used to ease using gridbag layout
	 *  sets the contraints of a component
	 *  and adds it to the calculators panel.
	 *
	 *  @param a component to add, and all the constraints for grid bag layouts
	 **/
	private void addComponent(Component component, int gridx, int gridy,
        int gridwidth, int gridheight, int weightx, int weighty, int ipady, int fill,
        int anchor) {
    
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
		constraints.ipady = ipady;
        constraints.anchor = anchor;
        gridbag.setConstraints(component, constraints);
        pane.add(component);
    }

	/*************************************************************************\
	***************************************************************************
	********                      OPERATOR HANDLING                     *******
	***************************************************************************
	*         >>>>>>>>>  All operator handling is the same  <<<<<<<<<<        *
	***************************************************************************
	*  Parses the views current contents and stores it for later use. declare *
	*  a variable for a string and determnes whether to store the labels curr-*
	*  ent text. The string is set to be the display label text as long as the*
	*  display label has something stored there. If it doesn't have anything  *
	*  the string variable is set to "blah" which is just a placeholder to av-*
	*  oid string index out of bounds exceptions. The method then handles     *
	*  multiple do nothing cases. Each case determines if the last item in the*
	*  string variable is of some sort of operator, if it does, nothing should*
	*  be done in the method.                                                 *
	*  The next step adds the correct operator to the label, then it stores   *
	*  the views value as a double in the correct spot of the calculator with *
	*  the appropriate calculator method. Next a call to the correct method   *
	*  in the calculator class is called.                                     *
	*  + = calc.plus()                                                        *
	*  - = calc.minus()                                                       *
	*  * = calc.multiply()                                                    *
	*  / = calc.divide()                                                      *
	*  ^ = calc.power()                                                       *
	*  Finally the clearView variable is set to true                          *
	\*************************************************************************/
	private class OperatorHandling implements ActionListener{
		CalcBehavior behavior; //use of the strategy pattern  //composition
		
		public OperatorHandling(CalcBehavior behavior){
			this.behavior = behavior;
		}
		
		public void actionPerformed(ActionEvent e){
			double d = Double.parseDouble(view.getText());
			String s;
			if(displayLabel.getText().length() > 0){
				s = displayLabel.getText();
			}
			else{
				s = "blah"; //place holder
			}
			if(displayLabel.getText().length() == 0){
				displayLabel.setText(view.getText());
			}
			if(s.substring(s.length() - 1).equals("/")){}
			else if(s.substring(s.length() - 1).equals("-")){}
			else if(s.substring(s.length() - 1).equals("*")){}
			else if(s.substring(s.length() - 1).equals("+")){}
			else{
				displayLabel.setText(displayLabel.getText() + behavior.getSymbol());
				calc.setValue(d);
				behavior.doSomething();
				view.setText("" + calc.getAnswer());	
			}
			clearView = true;
		}
	}
	
	/************************************************************************\
	 *******************  NUMBER BUTTON DESCRIPTION  **************************
	 **************************************************************************
	 *         >>>>All number buttons function the same exact way<<<<         *
	 **************************************************************************
	 *  The button depends on the current value of clearView, when it is true *
	 *  simply sets the calculators view and display to show the appropriate  *
	 *  number, then it sets the clearView variable to false allowing         *
	 *  subsequent button calls to append the number.                         *
	 *  If clearView is false the button appends the number to the view		  *
	 **************************************************************************
	 \************************************************************************/
	private class NumberHandling implements ActionListener{
		String number;
		
		public NumberHandling(String number){
			this.number = number;
		}
		
		public void actionPerformed(ActionEvent e){
			if(displayLabel.getText().length() >= 31){
				displayLabel.setText("");
			}
			if(number.equals(Math.PI + "")){
				view.setText(number);
				displayLabel.setText(number);
				clearView = true;
			}
			else if(number.equals(Math.E + "")){
				view.setText(number);
				displayLabel.setText(number);
				clearView = true;
			}
			else if(number.equals("sqr")){
				double d = Double.parseDouble(view.getText());
				view.setText(d * d + "");
				displayLabel.setText(d * d + "");
				clearView = true;
			}
			else if(number.equals("sqrt")){
				double d = Double.parseDouble(view.getText());
				view.setText(Math.sqrt(d) + "");
				displayLabel.setText(Math.sqrt(d) + "");
				clearView = true;
			}
			else if(number.equals("recip")){
				double n = Double.parseDouble(view.getText());
				view.setText(1 / n + "");
				clearView = true;
			}
			else if(number.equals("fact")){
				double d = Double.parseDouble(view.getText());
				view.setText(calc.fact(d) + "");
				clearView = true;
			}
			else if(number.equals("%")){
				double n1 = Double.parseDouble(view.getText());
				view.setText(n1 / 100 + "");
				clearView = true;
			}
			else{
				if(clearView){
					view.setText(number);
					clearView = false;
				}
				else{
					StringBuilder str = new StringBuilder(view.getText());
					str.append(number);
					view.setText(str.toString());
				}
				displayLabel.setText(displayLabel.getText() + number);
			}
		}
	}
	
	/**
	 *
	 *  This method is the workhorse for my gui, all this can be
	 *  done in the constructor with the class extending JFrame
	 *  I, however; prefer this method of GUI instantiation.
	 *
	 *  This method also makes the buttons actually reference an object
	 *
	 **/
	public void go(){
		//create the text field
		view = new JTextField(20);
		//set the layout for the panel
		pane.setLayout(gridbag);
		//add the textfield
		addComponent(view, 0, 1, 9, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		//set the text field to be uneditable
		view.setEditable(false);
		
		//the display label at the top
		displayLabel = new JLabel(" ");
		//add the label
		addComponent(displayLabel, 0, 0, 9, 1, 3, 3, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		
		//create the bottom row of buttons
		buttonDisplayBack = new JButton("<-");
		/**
		 * Back button action handling
		 * simply sets a string builder to both the 
		 * calculators display and removes the last item 
		 * from each.
		 **/
		buttonDisplayBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(displayLabel.getText().length() == 0){
					StringBuilder str = new StringBuilder(view.getText());
					str.deleteCharAt(str.length() - 1);
					view.setText(str.toString());
				}
				else{
					StringBuilder str = new StringBuilder(view.getText());
					str.deleteCharAt(str.length() - 1);
					view.setText(str.toString());
					str = new StringBuilder(displayLabel.getText());
					str.deleteCharAt(str.length() - 1);
					displayLabel.setText(str.toString());
				}
			}
		});
		//Adds the back button to the panel
		addComponent(buttonDisplayBack, 0, 7, 1, 1, 0, 3, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		
		theButtons = new JButton[10];
		for(int i = 0; i < theButtons.length; i++){
			//creates the button
			theButtons[i] = new JButton("" + i);
			//adds the action listener
			theButtons[i].addActionListener(new NumberHandling(i + ""));
		}
		
		//add the 0 button
		addComponent(theButtons[0], 1, 7, 1, 1, 0, 3, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		
		//intialize decimal button
		decimalButton = new JButton(".");
		/**
		 *  Decimal button handling  //See number button description at line 169
		 **/
		decimalButton.addActionListener(new NumberHandling("."));
		//add decimal button
		addComponent(decimalButton, 2, 7, 1, 1, 0, 3, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		
		//initialize exponent button
		buttonExponent = new JButton("Exp");
		/**
		 *  Action Handling for exponent button //see operator handling line 110
		 **/
		buttonExponent.addActionListener(new OperatorHandling(new Power(calc, "^")));
		
		//add the exponent button
		addComponent(buttonExponent, 3, 7, 1, 1, 0, 3, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		
		//create the equals button
		buttonEquals = new JButton("=");
		/**
		 *  Equals action handling
		 *  parse the value from the calculators view
		 *  makes a call to calc.evaluate()
		 *  declare temp to be the calculators answer
		 *  if the view is empty, call evaluate and set the view to temp
		 *  else set the calculators value to d 
		 *  call evaluates to determine the answer
		 *  creates a string equal to value 1 and sets the view to value 1
		 *  clear the display of the calculator
		 *  set clearView variable to true and finally clear the calculator
		 **/
		buttonEquals.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double d = Double.parseDouble(view.getText());
				double temp = calc.getAnswer();
				if(view.getText().equals("")){
					calc.evaluate();
					view.setText("" + temp);
				}
				//else if(view.getText().equals(calc.getVal1() + "")){}
				else{
					//Double d = Double.parseDouble(view.getText());
					calc.setValue(d);
					calc.evaluate();
					String str = "" + calc.getVal1();
					view.setText(str);
					calc.setVal2(0);
				}
				displayLabel.setText("");
				clearView = true;
				calc.fullClear();
			}
		});
		//add the equals button
		addComponent(buttonEquals, 4, 6, 4, 0, 0, 10, 25, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER);
		
		//one row up from the bottom
		buttonClear = new JButton("C");
		/**
		 *  Action handling for the clear button
		 *  if display label is empty clear the view of the calculator
		 *  if the view and display have the same length clear both of them
		 *  for any other situation delete x items from the display, where x
		 *  is equal to the length of the calculators view. Clear the views
		 *  text and set the display to equal the truncated string
		 **/
		buttonClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringBuilder str = new StringBuilder(displayLabel.getText());
				if(displayLabel.getText().length() == 0){
					view.setText("");
				}
				else if(view.getText().length() == displayLabel.getText().length()){
					view.setText("");
					displayLabel.setText("");
				}
				else{
					str.delete(str.length() - view.getText().length(), str.length());
					view.setText("");
					displayLabel.setText(str.toString());
				}
			}
		});
		//add clear button
		addComponent(buttonClear, 0, 6, 1, 1, 0, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 1 button
		addComponent(theButtons[1], 1, 6, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the number 2 button
		addComponent(theButtons[2], 2, 6, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 3 button
		addComponent(theButtons[3], 3, 6, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//up another row allclear, 4, 5, 6, +
		//create the all clear button
		buttonFullClear = new JButton("AC");
		/**
		 *  Action handling for all clear button
		 *  clear the view, display, and calculator
		 **/
		buttonFullClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.setText("");
				displayLabel.setText("");
				calc.fullClear();
			}
		});
		//add the all clear button
		addComponent(buttonFullClear, 0, 5, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the four button
		addComponent(theButtons[4], 1, 5, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 5 button
		addComponent(theButtons[5], 2, 5, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add 6 button
		addComponent(theButtons[6], 3, 5, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create + operator button
		buttonPlus = new JButton("+");
		/**
		 *  + operator handling  //see + operator handling at approx line 110
		 **/
		 
		buttonPlus.addActionListener(new OperatorHandling(new Add(calc, "+")));
		
		//add plus operator button
		addComponent(buttonPlus, 4, 5, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//next row up, invert, 7, 8, 9, -
		//+/- button creation
		buttonInvert = new JButton("+/-");
		/**
		 * Action handling for the +/- button
		 * Parse the calculators view, and create a stringbuilder object with the
		 * display labels text, determine the variable length, with the views length
		 * if the displays length is less than the view determine the number in the view
		 * if the number is greater than 0, append a - to the front, if it is not
		 * set the views text to everything except the - sign.
		 * otherwise delete the views text from the display label, leaving the last
		 * clicked items and re-append with the proper sign
		 **/
		buttonInvert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double d = 0;
				StringBuilder str = new StringBuilder(displayLabel.getText());
				int len = view.getText().length();
				if(len == 0){
					return;
					//do nothing
				}
				if(len != 0){
					d = Double.parseDouble(view.getText());
				}
				if(displayLabel.getText().length() < len){
					if(d > 0){
						view.setText("-" + view.getText());
						displayLabel.setText(view.getText());
					}
					else{
						view.setText(view.getText().substring(1));
						displayLabel.setText(view.getText());
					}
				}
				else{
					str.delete(str.length() - len, str.length());
					if(d > 0){
						view.setText("-" + view.getText());
						displayLabel.setText(str.toString() + view.getText());
					}
					else{
						view.setText(view.getText().substring(1));
						displayLabel.setText(str.toString() + view.getText());
					}
				}
			}
		});
		//add the +/- button
		addComponent(buttonInvert, 0, 4, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 7 button
		addComponent(theButtons[7], 1, 4, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 8 button
		addComponent(theButtons[8], 2, 4, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the 9 button
		addComponent(theButtons[9], 3, 4, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create - operator button
		buttonMinus = new JButton("-");
		/**
		 *  Action handling for - operator  // see operator handling at  approx 110
		 **/
		 
		buttonMinus.addActionListener(new OperatorHandling(new Subtract(calc, "-")));
		
		//add - operator
		addComponent(buttonMinus, 4, 4, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//next row up, reciprocal, square, sqrt, /, X
		//create reciprocal button
		buttonReciprocal = new JButton("1/x");
		
		//number handling for recip
		buttonReciprocal.addActionListener(new NumberHandling("recip"));
		//add the reciprocal button
		addComponent(buttonReciprocal, 0, 3, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create the square button
		buttonSquare = new JButton("sqr");
		
		//number handling for square
		buttonSquare.addActionListener(new NumberHandling("sqr"));
		
		//add the square button
		addComponent(buttonSquare, 1, 3, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create the square root button
		buttonSqrt = new JButton("sqrt");
		
		//number handling for square root
		buttonSqrt.addActionListener(new NumberHandling("sqrt"));
		
		//add the square root button
		addComponent(buttonSqrt, 2, 3, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create division operator
		buttonDivide = new JButton("/");
		/***
		 *  Action handling for operators
		 *********
		 *  See operator action handling at approx line 110
		 ***/
		 
		buttonDivide.addActionListener(new OperatorHandling(new Divide(calc, "/")));
		
		//add the divide button
		addComponent(buttonDivide, 3, 3, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create multiplication button
		buttonTimes = new JButton("x");
		/***
		 *  Action handling for * operator
		 *********
		 *  See operator handling at approx line 210
		 ***/
		buttonTimes.addActionListener(new OperatorHandling(new Multiply(calc, "x")));
		
		//add the multiplication button
		addComponent(buttonTimes, 4, 3, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//next row up, %, pi, e
		//create percentage button
		buttonPercent = new JButton("%");
		
		//number handling for percentage
		buttonPercent.addActionListener(new NumberHandling("%"));
		
		//add percentage button
		addComponent(buttonPercent, 0, 2, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create pi button
		buttonPi = new JButton("pi");
		
		//uses number handling
		buttonPi.addActionListener(new NumberHandling(Math.PI + ""));
		
		//add pi button
		addComponent(buttonPi, 1, 2, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create e button
		buttonE = new JButton("e");
		
		//uses number handling
		buttonE.addActionListener(new NumberHandling(Math.E + ""));
		//add the e button
		addComponent(buttonE, 2, 2, 1, 1, 0, 10, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//create the factorial button
		buttonFact = new JButton("Fact");
		
		//number handling for factorial
		buttonFact.addActionListener(new NumberHandling("fact"));
		
		//add the factorial button
		addComponent(buttonFact, 3, 2, 0, 1, 0, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		
		//add the pane to the frame
		frame.add(pane);
		frame.setVisible(true); //set it to be visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close operation
		//frame.pack();
		frame.setSize(263, 240); //set the frames size    263, 240
		frame.setResizable(false);//set to not be resizable
		frame.setLocation(20, 20);//set the location
		//System.out.println(frame.getSize());
		//System.out.println(buttonThree.getSize());
	}
	public static void main(String[] args){
		//create an instance of the calc and call the work horse method go()
		new CalcView().go();
	}
}