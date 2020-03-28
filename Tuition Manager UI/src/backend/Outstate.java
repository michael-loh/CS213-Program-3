/**
 * This class serves as an out of state student representation of the Student class.
 * This class implements the tuitionDue() method following the guidelines of an out of state student
 * This class overrides the toString() method by appending on fields from this subclass.
 * @author micha
 *
 */
package backend;

public class Outstate extends Student{

	private final int TUITION = 756;
	
	private boolean tristate;
	
	/**
	 * This class calls the super class constructor with its associated fields, and it assigns a value to tristate.
	 * @param fname is sent to the superclass constructor.
	 * @param lname is sent to the superclass constructor.
	 * @param credits is sent to the superclass constructor
	 * @param tristate describes whether or not the student lives within the tristate area.
	 */
	public Outstate(String fname, String lname, int credits, boolean tristate) {
		super(fname, lname, credits);
		this.tristate = tristate;
	}

	/**
	 * This class calculates the tuition for an out of state student.
	 * If the student has at least 12 credits, the student is a full time student and pays a full time fee.
	 * If the student has less than 12 credits, the student is a part time student and pays a part time fee.
	 * Each out of state student pays $756 per credit.
	 * If the student is a full time student and lives in the tristate area, that student pays $200 less per credit.
	 */
	@Override
	public int tuitionDue() {
		int bill = 0;
		
		int creditAmount = Math.min(15, credit);
		
		int tuition = (creditAmount >= 12 && tristate) ? TUITION-200 : TUITION;
		
		bill += tuition * creditAmount;
		
		if(creditAmount >= 12) {
			bill += FULL_TIME_FEE;
		}
		else {
			bill += PART_TIME_FEE;
		}
		
		return bill;
	}
	
	/**
	 * This returns all elements of this object in a String.
	 * It calls toString() from the superclass, and then appends on the tristate value onto the end of the string.
	 */
	@Override
	public String toString() {
		return super.toString() + ",[tristate:" + tristate + "]";
	}
	
	
	/**
	 * Test bed main. 
	 * Test cases for International are here.
	 * @param args: no command line arguments.
	 */
	public static void main(String[]args) {
		Outstate o1 = new Outstate("Jon", "Snow", 8, false);
		System.out.println(o1.toString());
		System.out.println(o1.tuitionDue());
		
		Outstate o2 = new Outstate("Sam", "Tarly", 8, true);
		System.out.println(o2.tuitionDue());
		
		Outstate o3 = new Outstate("Ned", "Stark", 15, false);
		Outstate o4 = new Outstate("Catelyn", "Stark", 16, false);
		
		System.out.println(o3.tuitionDue());
		System.out.println(o4.tuitionDue());
		
		Outstate o5 = new Outstate("Jorah", "Mormont", 13, false);
		Outstate o6 = new Outstate("Tyrion", "Lannister", 13, true);
		
		System.out.println(o5.tuitionDue());
		System.out.println(o6.tuitionDue());
		
		System.out.println(o5.compareTo(o5));
		System.out.println(o5.compareTo(o6));
		
		Instate i1 = new Instate("Jorah", "Mormont", 12, 0);
		
		System.out.println(i1.compareTo(o5));
	}
}
