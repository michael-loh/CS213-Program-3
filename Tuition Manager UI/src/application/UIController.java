/**
 * This class is the controller class for UI.fxml, and it handles and processes inputs from the UI.
 * @author Michael Loh
 * @author Varun Vasudevan
 */
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
	
	
	/**
	 * This method is called when the UI is initially loaded in.
	 * It sets up the radio buttons for student type, so that only 1 can be selected at a time.
	 * It initializes a StudentList object that will be used for this program.
	 */
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
	
	/**
	 * This method will disable options that are not available and enable options that are available when Instate is selected.
	 */
	public void selectInstate() {
		funding.setDisable(false);
		funds.setDisable(false);
		
		tristate.setSelected(false);
		tristate.setDisable(true);
		
		exchange.setSelected(false);
		exchange.setDisable(true);
	}
	
	/**
	 * This method will disable options that are not available and enable options that are available when Outstate is selected.
	 */
	public void selectOutstate() {
		tristate.setDisable(false);
		
		funding.setSelected(false);
		funding.setDisable(true);
		
		funds.setText("");
		funds.setDisable(true);
		
		exchange.setSelected(false);
		exchange.setDisable(true);
	}
	
	/**
	 * This method will disable options that are not available and enable options that are available when International is selected.
	 */
	public void selectInternational() {
		exchange.setDisable(false);
		
		funding.setSelected(false);
		funding.setDisable(true);
		
		funds.setText("");
		funds.setDisable(true);
		
		tristate.setSelected(false);
		tristate.setDisable(true);
	}
	
	/**
	 * This method enables the funds TextBox so that the user can input funds for an Instate student
	 */
	public void selectFunding() {
		if(funding.isSelected())
			funds.setDisable(false);
		else {
			funds.setDisable(true);
			funds.setText("");
		}
	}
	
	/**
	 * This button will add a student to list using the inputs given by the user.
	 * It will call instateAdd(), outstateAdd(), or InternationalAdd() given the user input.
	 */
	public void add() {
		
		if( instate.isSelected() )
			instateAdd();
		else if( outstate.isSelected() ) 
			outstateAdd();
		else if( international.isSelected() )
			internationalAdd();
	}
	
	/**
	 * This method will add an Instate student to the list.
	 * It checks for correct input, initializes an Instate, and adds it to the list.
	 */
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
	
	/**
	 * This method will add an Outstate student to the list.
	 * It checks for correct input, initializes an Outstate, and adds it to the list.
	 */
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
	
	/**
	 * This method will add an International student to the list.
	 * It checks for correct input, initializes an International, and adds it to the list.
	 */
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
	
	/**
	 * This method takes in a first name and last name, searches the list for a Student with the same name, and then removes that student.
	 * It checks for correct input, initializes an Instate, and then searches the list for that student.
	 * If found, it will remove the student from the list, and if not found, it will output a fail message to the user.
	 */
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
	
	/**
	 * This method prints out a list of all the students and their information in the list.
	 * If the list is empty, it will send a fail message to the user.
	 */
	public void print() {
		if (list.isEmpty()){
			textArea.appendText("Cannot Print: List is empty!\n");
			return;
		}
		textArea.appendText("-- start of list --\n");
		textArea.appendText(list.toString());
		textArea.appendText("-- end of list --\n");
	}
	
	
	/**
	 * This method checks if the current input is valid when a user tries to add a student.
	 * @return true if the input is valid and false if the input is invalid.
	 */
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
	
	/**
	 * This method will reset the UI by clearing out all the input boxes and resetting the student type buttons.
	 */
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
