/**
 * This class is used to store all student types in a list.
 * It has operations to add and remove students from this list.
 * This class is implemented as a dynamic sized array, where the array will grow in size when
 * the array is full.
 * @author micha
 *
 */
package backend;

public class StudentList {
	
	private final int GROW_SIZE = 10;
	private Student[] list;
	private int totalStudents;
	
	/**
	 * The constructor initializes the array that will store all of the students.
	 * It is initialized at array size 10.
	 * It also intializes the totalStudents to 0.
	 */
	public StudentList() {
		list = new Student[10];
		totalStudents = 0;
	}
	
	/**
	 * This method adds a student into the list.
	 * If list is full, it calls grow() add more space to the list.
	 * @param s is the student that will be added into the list.
	 */
	public void add(Student s) {
		if( totalStudents == list.length ) {
			grow();
		}
		list[totalStudents++] = s;
	}
	
	/**
	 * This method removes a student from the list.
	 * It calls the findStudent() method to find the index of the student that should be removed.
	 * If the student is not found, the method returns without doing anything.
	 * When the student is found, it removes that student and shifts every student to the right of it over one space.
	 * @param s is the student that will be removed from the list.
	 */
	public void remove(Student s) {
	
		int index = findStudent(s);
		
		if(index == -1) {
			return;
		}
		
		for(int i = index; i < totalStudents - 1; i++) {
			list[i] = list[i+1];
		}
		totalStudents--;
		list[totalStudents] = null;
	}
	
	/**
	 * This method prints each student in the list with their tuition due.
	 */
	public void print() {
		for(int i = 0; i < totalStudents; i++) {
			System.out.println(list[i].toString());
			System.out.println("Tuition due: " + list[i].tuitionDue());
			System.out.println();
		}
	}
	
	/**
	 * This method grows list by GROW_SIZE.
	 * It creates a new list with size list.length + GROW_SIZE.
	 * It then copies over everything over from the old list into the new list. 
	 * Then it assigns the new list to list.
	 */
	private void grow() {
		Student[] newList = new Student[list.length + GROW_SIZE];
		for(int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		
		list = newList;
	}
	
	/**
	 * This method finds the index of a student in the list.
	 * It searches the list using sequential search.
	 * @param s is the student that is being searched for.
	 * @return the index is found or -1 if the student isn't found.
	 */
	private int findStudent(Student s) {
		
		for(int i = 0; i < list.length; i++) {
			if(s.compareTo(list[i]) == 0) 
				return i;
		}
		
		return -1;
	}
	
	/**
	 * This method checks if a student exists in the list.
	 * @param s is the student being searched for.
	 * @return true if the student is within the list and false if the student is not in the list.
	 */
	public boolean contains(Student s) {
		
		for(int i = 0; i < totalStudents; i++) {
			Student t = list[i];
			if(s.compareTo(t) == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the list is empty.
	 * @return true if empty and false if not.
	 */
	public boolean isEmpty() {
		return (totalStudents == 0)? true:false;
	}
	
	
	public String toString() {
		String str = "";
		for(int i = 0; i < totalStudents; i++) {
			str += list[i].toString() +'\n';
			str += "Tuition due: " + list[i].tuitionDue();
			str += '\n';
		}
		return str;
	}
	
}
