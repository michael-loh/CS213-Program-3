package backend;

/**
 * 	This class serves as an abstract class for other types of Student classes to use
 * 	This class contains a constructor, toString(), and compareTo() that other subclasses will be using
 *	in their implementation. The tuitionDue() method will be overwritten in other classes. This class also
 * 	contains constants that will also be used in the subclasses.
 * 	@author Michael Loh
 */

public abstract class Student implements Comparable {
	
	protected final int PART_TIME_FEE = 846;
	protected final int FULL_TIME_FEE = 1441;
	
	private String fname;
	private String lname;
	protected int credit;
	
	
	/**
	 * The constructor takes arguments and assigns them to the classes global variables.
	 * @param fname is the first name of the student
	 * @param lname is the last name of the student
	 * @param credit is the amount of credits that the student is taking
	 */
	public Student(String fname, String lname, int credit) {
		this.fname = fname;
		this.lname = lname;
		this.credit = credit;
	} 
	
	
	/**
	 * This method compares the current student with another object to determine if they are equal.
	 * If the names are equal, then the objects are equal.
	 * Returns 0 when the first and last names are the same.
	 * It returns a non zero value if they are not the same.
	 * @param obj is the object that is being compared
	 */
	public int compareTo(Object obj) {
		
		if(obj.getClass().getSuperclass() != this.getClass().getSuperclass()) {
			return -1;
		}
		
		Student student = (Student)obj;
		if(student.fname.equals(this.fname) && student.lname.equals(this.lname)) {
			return 0;
		}
		
		return ( this.fname.compareTo(student.fname) == 0) ? this.lname.compareTo(student.lname) : this.fname.compareTo(student.fname);
	}
	
	
	/**
	 * This toString() method returns fname, lname, and credit in a string.
	 */
	public String toString() {
		return fname + " " + lname + ": [credit:" + credit + "]";
	}
	
	
	/**
	 * This abstract method will return the tuition due for a given student.
	 * It will be implemented in subclasses
	 */
	public abstract int tuitionDue();
	
}