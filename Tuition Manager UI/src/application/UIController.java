package application;

import backend.Instate;
import backend.International;
import backend.Outstate;
import backend.StudentList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UIController {
	
	//UI fields
	public TextField fname;
	public TextField lname;
	public TextField credit;
	
	public RadioButton instate;
	public RadioButton outstate;
	public RadioButton international;
	
	public CheckBox funding;
	public CheckBox tristate;
	public CheckBox exchange;
	
	public TextField funds;
	
	public TextArea textArea;
	
	private ToggleGroup studentType;
	
	//Tuition Manager fields
	private StudentList list;
	
	
	
	public void initialize() {
		
		//set up the radiobuttons so that only 1 can be selected at a time.
		studentType = new ToggleGroup();
		
		instate.setToggleGroup(studentType);
		
		outstate.setToggleGroup(studentType);

		international.setToggleGroup(studentType);

		resetUI();
		
		//initialize a student list
		list = new StudentList();
	}
	
	public void selectInstate() {
		funding.setDisable(false);
		funds.setDisable(false);
		
		tristate.setSelected(false);
		tristate.setDisable(true);
		
		exchange.setSelected(false);
		exchange.setDisable(true);
	}
	
	public void selectOutstate() {
		tristate.setDisable(false);
		
		funding.setSelected(false);
		funding.setDisable(true);
		
		funds.setText("");
		funds.setDisable(true);
		
		exchange.setSelected(false);
		exchange.setDisable(true);
	}
	
	public void selectInternational() {
		exchange.setDisable(false);
		
		funding.setSelected(false);
		funding.setDisable(true);
		
		funds.setText("");
		funds.setDisable(true);
		
		tristate.setSelected(false);
		tristate.setDisable(true);
	}
	
	public void selectFunding() {
		if(funding.isSelected())
			funds.setDisable(false);
		else {
			funds.setDisable(true);
			funds.setText("");
		}
	}
	
	public void add() {
		
		if( instate.isSelected() )
			instateAdd();
		else if( outstate.isSelected() ) 
			outstateAdd();
		else if( international.isSelected() )
			internationalAdd();
	}
	
	private void instateAdd() {
		
		//check for invalid input. Such as characters in number text fields or empty fields
		if( !isValidInput() ) {
			textArea.appendText("Cannot Add: Invalid input!\n");
			resetUI();
			return;
		}
		
		String fn = fname.getText();
		String ln = lname.getText();
		int c = Integer.parseInt(credit.getText());
		
		//check if credit amount is above 1.
		if(c < 1) {
			textArea.appendText("Cannot Add: Instate students must take at least 1 credit!\n");
			return;
		}
		
		int f = (funding.isSelected())? Integer.parseInt(funds.getText()): 0;
		
		//check if funding is a negative value.
		if(f < 0) {
			textArea.appendText("Cannot Add: Cannot have negative amount for funding!\n");
			return;
		}
		
		//check if part time student is receiving funding.
		if(c < 12 && funding.isSelected()) {
			textArea.appendText("Cannot Add: Part time students do not qualify for funding!\n");
			return;
		}
		Instate student = new Instate(fn, ln, c, f);
		
		if(list.contains(student)) {
			textArea.appendText("Cannot Add: Student already in list!\n");
			return;
		}
		
		list.add(student);
		textArea.appendText("Student successfully added\n");
		
		resetUI();
	}
	
	private void outstateAdd() {
		
		if( !isValidInput() ) {
			textArea.appendText("Cannot Add: Invalid input!\n");
			resetUI();
			return;
		}
		
		String fn = fname.getText();
		String ln = lname.getText();
		int c = Integer.parseInt(credit.getText());
		
		if(c < 1) {
			textArea.appendText("Cannot Add: Outstate students must take at least 1 credit\n");
			return;
		}
		
		boolean e = tristate.isSelected();
		
		Outstate student = new Outstate(fn, ln, c, e);
		
		if(list.contains(student)) {
			textArea.appendText("Cannot Add: Student already in list!\n");
			return;
		}
		
		list.add(student);
		textArea.appendText("Student successfully added\n");
		
		resetUI();
	}
	
	private void internationalAdd() {
		
		if( !isValidInput() ) {
			textArea.appendText("Cannot Add: Invalid input!\n");
			resetUI();
			return;
		}
		
		String fn = fname.getText();
		String ln = lname.getText();
		int c = Integer.parseInt(credit.getText());
		
		if(c < 9) {
			textArea.appendText("Cannot Add: International students must take at least 9 credits!\n");
			return;
		}
		
		boolean e = exchange.isSelected();
		
		International student = new International(fn, ln, c, e);
		
		if(list.contains(student)) {
			textArea.appendText("Cannot Add: Student already in list!\n");
			return;
		}
		
		list.add(student);
		textArea.appendText("Student successfully added\n");
		
		resetUI();
	}
	
	public void remove() {
		String fn = fname.getText();
		String ln = lname.getText();
		
		if(fn.length() == 0 || ln.length() == 0) {
			textArea.appendText("Cannot Remove: Empty first name or last name fields!\n");
			return;
		}
		
		Instate student = new Instate(fn, ln, 12, 0);
		
		if(!list.contains(student)) {
			textArea.appendText("Cannot Remove: Student not in list!\n");
			return;
		}
		
		list.remove(student);
		textArea.appendText("Student succesfully removed\n");
		
		resetUI();
	}
	
	public void print() {
		if (list.isEmpty()){
			textArea.appendText("Cannot Print: List is empty!\n");
			return;
		}
		textArea.appendText("-- start of list --\n");
		textArea.appendText(list.toString());
		textArea.appendText("-- end of list --\n");
	}
	
	private boolean isValidInput() {
		int a;
		if(fname.getText().length() == 0 || lname.getText().length() == 0) {
			return false;
		}
		try {
			a = Integer.parseInt(credit.getText());
			a = (!funding.isSelected())? 0 : Integer.parseInt(funds.getText());
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	
	private void resetUI() {
		//set all textfields to empty strings and reset the radio buttons and check boxes
		instate.setSelected(true);
		funding.setSelected(false);
		tristate.setSelected(false);
		exchange.setSelected(false);
		fname.setText("");
		lname.setText("");
		credit.setText("");
		funds.setText("");
		funding.setDisable(false);
		tristate.setDisable(true);
		exchange.setDisable(true);
		funds.setDisable(true);
	}
	
}
