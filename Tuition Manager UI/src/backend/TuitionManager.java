/**
 * The TuitionManager class is the interface class which processes user commands.
 * @author Varun Vasudevan
 */
package backend;

import java.util.Scanner;

public class TuitionManager {
	Scanner stdin;
	StudentList list;
	/**
	 * This method initializes a scanner and goes through the commands inputed
	 * Command I calls instateAdd()
	 * Command O calls outstateAdd()
	 * Command N calls international()
	 * Command R calls remove()
	 * Command P calls print()
	 * Command Q calls quit() and ends the loop
	 * Defaults when the command is invalid and moves to the next line 
	 * 
	 */
	void run() {
		stdin = new Scanner(System.in);
		list = new StudentList();
		boolean done = false;
		
	      
		while ( !done )
		{
			String command = stdin.next();
			
			switch ( command )  
			{   
				case "I":  
					instateAdd();
					break; 
				case "O": 
					outstateAdd();
					break;
				case "N": 
					internationalAdd();
					break;
				case "R": 
					remove();
					break;
				case "P":
					print();
					break;
				case "Q":
					quitMessage();
					done = true;
				default: 
					System.out.println("Command '" + command + "' is not supported!");
					stdin.nextLine();
			}  
		}
	}
	/**
	 *This method adds an in-state student to the list of students
	 *It reads the student's first name, last name, number of credits, and amount of funds.
	 *It checks whether the amount of credits and  funds is valid and also checks if the student is already in the list
	 */
	private void instateAdd() {
		String fname = stdin.next();
		String lname = stdin.next();
		int credit = Integer.parseInt(stdin.next());
		int funds = Integer.parseInt(stdin.next());
		if (credit <1) {
			invalidCredit();
			return;
		}
		if (funds<0) {
			invalidFunds();
			return;
		}
		Instate s = new Instate(fname,lname,credit,funds);
		 if (list.contains(s)){
			 studentInListError();
			 return;
		 }
		 list.add(s);
		 System.out.println("Student successfully added");
	}
	
	
	/**
	 * This method adds an out of state student into the list.
	 * It takes 4 inputs from stdin, and then checks that inputs are valid.
	 * It creates an Oustate object using the inputs, and then searches the list for a Student with the same name.
	 * If found, it calls studentInListError(), otherwise, it adds the student into the list.
	 */
	private void outstateAdd() {
		String fname = stdin.next();
		String lname = stdin.next();
		
		String num = stdin.next();
		int credit = Integer.parseInt(num);
		if(credit < 1) {
			invalidCredit();
			return;
		}
		
		String bool = stdin.next();
		if(! ( (bool.equals("T") || bool.equals("F") ) ) ){
			invalidTriState();
			return;
		}
		boolean tristate = (bool.equals("T"))? true: false;
		
		Outstate s = new Outstate(fname, lname, credit, tristate);
		
		if(list.contains(s)) {
			studentInListError();
			return;
		}
		
		list.add(s);
		System.out.println("Student successfully added");

	}
	/**
	 * This method adds an international student to the list of students
	 * It reads the first name, last name, number of credits, and the exchange status of the student
	 * It checks whether the number of credits is below 9, if the exchange status is valid and if the student is already contained in the list
	 */
	private void internationalAdd() {
		String fname = stdin.next();
		String lname = stdin.next();
		int credit = Integer.parseInt(stdin.next());
		boolean exchangeStatus;
		String bool = stdin.next();
		if(bool.equals("T")) 
			exchangeStatus=true;
		else if (bool.equals("F"))
			exchangeStatus = false;
		else {
			invalidExchangeStatus();
			return;
		}
		
		if (credit <9) {
			invalidCredit();
			return;
		}
		International i = new International(fname,lname,credit,exchangeStatus);
		 if (list.contains(i)){
			 studentInListError();
			 return;
		 }
		 list.add(i);
		 System.out.println("Student successfully added");
	}
	/**
	 * This method checks if the list is empty and prints if it is not empty
	 */
	private void print() {
		if (list.isEmpty()){
			listEmptyError();
			return;
		}
		System.out.println("-- start of list --");
		list.print();
		System.out.println("-- end of list --");
	}
	
	
	/**
	 * This method removes a student from the list.
	 * It takes fname and lname, and then it creates a Student object using those inputs.
	 * It then searches the list to see if anyone has the same name.
	 * If not found, it calls notInListError().
	 * If found, it removes the student from the list.
	 */
	private void remove() {
		String fname = stdin.next();
		String lname = stdin.next();
		
		Student s = new Instate(fname, lname, 0, 0);
		
		if(list.isEmpty()) {
			removeFromEmptyListError();
			return;
		}
		if(!list.contains(s)) {
			notInListError(fname, lname);
			return;
		}
		
		list.remove(s);
	}
	
	/**
	 * This method alerts the user that the credit amount that they input is invalid.
	 */
	private void invalidCredit() {
		System.out.println("Invalid credit amount!");
	}
	
	/**
	 * This method alerts the user that the fund amount that they input is invalid.
	 */
	private void invalidFunds() {
		System.out.println("Invalid fund amount!");
	}
	
	/**
	 * This method alerts the user that they are trying to add a student that is already in the list 
	 */
	private void studentInListError() {
		System.out.println("Student is already in list");
	}
	
	/**
	 * This method alerts the user of an invalid tri-state input
	 */
	private void invalidTriState() {
		System.out.println("Invalid tri-state input");
	}
	
	/**
	 * This method alerts user that student is not in list
	 * @param fname is the first name of the student
	 * @param lname is the last name of the student
	 */
	private void notInListError(String fname, String lname) {
		System.out.println(fname + " " + lname + " was not found in the list");
	}
	
	/**
	 * This alerts the user of an invalid exchange student input
	 */
	private void invalidExchangeStatus(){
		System.out.println("Invalid exchange student input");	
	}
	
	/**
	 * This method alerts that the user is trying to print from an empty list.
	 */
	private void listEmptyError() {
		System.out.println("Printing an empty list");
	}
	
	/**
	 * This method alerts the user that they are trying to remove a student from an empty list
	 */
	private void removeFromEmptyListError() {
		System.out.println("Can't remove from an empty list");
	}
	
	/**
	 * This method alerts user of a successful quit
	 */
	private void quitMessage() {
		System.out.println("Program Terminated");
	}
}