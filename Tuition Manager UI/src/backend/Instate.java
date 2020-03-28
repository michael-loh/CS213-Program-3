/**
 * This class serves as an instate student representation of the Student class.
 * This class implememnts the tuitionDue() method for an instate student, and it overrides the toString() method.
 * @author "Michael Loh"
 */
package backend;

public class Instate extends Student{
	
	private final int TUITION = 433;
	
	private int funds;
	
	
	/**
	 * This constructor calls the super constructor with the given parameters, and it assigns funds a given value
	 * @param fname is sent to the superclass constructor.
	 * @param lname is sent to the superclass constructor.
	 * @param credit is sent to the superclass constructor.
	 * @param funds is the amount of funding an instate student is receiving.
	 */
	public Instate(String fname, String lname, int credit, int funds) {
		super(fname, lname, credit);
		this.funds = funds;
	}

	/**
	 * This method calculates the amount of tuition that is due for an instate student
	 * The student pays Tuition per credit taken, but if the student exceeds 15 credits, they only pay 15 credits worth.
	 * If the student takes less than 12 credits, they pay a part time fee, otherwise, they pay a full time fee.
	 * If the student receives funds and is a full time student, that amount is subtracted from the tuition total.
	 */
	@Override
	public int tuitionDue() {
		
		int bill = 0;
		
		int creditAmount = Math.min(15, credit);
		
		bill += TUITION * creditAmount;
		
		if(creditAmount >= 12) {
			bill += FULL_TIME_FEE;
		}
		else {
			bill += PART_TIME_FEE;
		}
		
		bill -= (creditAmount >= 12) ? funds : 0;
		return bill;
	}
	
	/**
	 * This toString() method calls the super classes toString(), and then it appends the funds onto the end of it.
	 */
	@Override
	public String toString() {
		return super.toString() + ",[funds:" + funds + "]";  
	}
	
	/**
	 * Test bed main.
	 * Test cases here for Instate are here.
	 * @param args: no command line arguments.
	 */
	public static void main(String[]args) {
		Instate i1 = new Instate("Michael", "Loh", 11, 0);
		System.out.println(i1.toString());
		System.out.println(i1.tuitionDue());
		
		Student i2 = new Instate("Katie", "Perry", 11, 1000);
		System.out.println(i2.tuitionDue());
		
		Student i3 = new Instate("George", "Lucas", 12, 0);
		Student i4 = new Instate("Ben", "Kenobi", 15, 0);
		Student i5 = new Instate("Anakin", "Skywalker", 16, 0);
		
		System.out.println(i3.tuitionDue());
		System.out.println(i4.tuitionDue());
		System.out.println(i5.tuitionDue());
		
		Student i6 = new Instate("Kate", "Upton", 13, 0);
		Student i7 = new Instate("Tom", "Cruise", 13, 4000);
		
		System.out.println(i6.tuitionDue());
		System.out.println(i7.tuitionDue());
	}
	
}