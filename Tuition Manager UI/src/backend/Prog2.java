package backend;

/**
 * This class is the driver class for the project.
 * This main method is called when running the project.
 * @author Michael Loh
 *
 */
public class Prog2 {

	/**
	 * This is the main method. It calls the run emthod in Tuition Manager and starts the program.
	 * @param args: no command line arguments.
	 */
	public static void main(String[] args) {
		new TuitionManager().run();
	}

}
