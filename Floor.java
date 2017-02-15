/**The code for Floor class manages the student database.
 * 
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 1 CSE 214
 * 	Section 10 Daniel Scanteianu
 *
 */
public class Floor {
	// Data fields
	private Student[] students;
	final int CAPACITY = 50;
	private int size = 0;

	// Constructor
	/**
	 * This constructor initializes a new Floor object and initializes the Student array
	 */
	public Floor() {
		students = new Student[CAPACITY];
	}

	// Methods
	/**
	 * This method adds students in the student array.
	 * 
	 * @param student this is the student object that is to be added to the student array	
	 * @param position this is the position of the student in the array
	 * 
	 * @throws FullFloorException 
	 * 		Indicates the floor is full.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates the position is invalid.
	 */
	public void addStudent(Student student, int position) throws FullFloorException, IllegalArgumentException {
		if (position - 1 == size && position <= CAPACITY && position >= 1) {
			students[position - 1] = student;
			size++;
		} else if (position > CAPACITY) {
			throw new FullFloorException("The floor is full! Please choose another floor.");
		} else if (students[position - 1] != null) {
			// Shift students over
			for (int i = CAPACITY - 1; i > position - 1; i--) {
				students[i] = students[i - 1];
			}
			students[position - 1] = student;
			size++;
		} else {
			throw new IllegalArgumentException("Invalid spot number");
		}
	}
	
	/**
	 * This method removes student from the array
	 * 
	 * @param position this is the position of the student in the array to be removed
	 * @return
	 * 		It returns the student object that was removed.
	 * @throws EmptyFloorException
	 * 		Thrown when the floor is empty
	 * @throws IllegalArgumentException
	 * 		Thrown when an invalid argument is passed.
	 */
	public Student removeStudent(int position) throws EmptyFloorException, IllegalArgumentException {
		Student student = null;
		if (size == 0) {
			throw new EmptyFloorException("The floor is empty!");
		} else if (students[position - 1] == null || position >= CAPACITY) {
			throw new IllegalArgumentException("Invalid number.");
		} else {
			student = students[position - 1]; // Create a copy of the student

			// Shift students over
			for (int i = position - 1; i + 1 < CAPACITY; i++) {
				students[i] = students[i + 1];
			}
			size--;
			return student;
		}
	}
	
	/**
	 * Gets the student through the position passed.
	 * 
	 * @param position this is the position of the student you want to retrieve
	 * @return
	 * 		It returns the student object whose position you requested.	  		
	 * @throws IllegalArgumentException
	 * 		Thrown when an invalid argument is passed.
	 */
	public Student getStudent(int position) throws IllegalArgumentException {
		if (students[position - 1] == null && position <= CAPACITY && position >= 1) {
			throw new IllegalArgumentException("Invalid position.");
		}
		return students[position - 1];
	}
	
	/**
	 * Places the student at a certain room
	 * 
	 * @param student the student object passed that is to be set at a certain room
	 * @param position the room which the student is to be placed
	 * @throws IllegalArgumentException
	 * 		Thrown when an illegal argument is passed.
	 */
	public void setStudent(Student student, int position) throws IllegalArgumentException {
		students[position - 1] = student;
	}
	
	/**
	 * Finds the current size of the floor.
	 * @return
	 * 		Returns the size of the floor. 
	 */
	public int count() {
		return size;
	}
	
	/**
	 * Clones the floor object
	 *@return
	 *	 Returns the floor that is cloned.
	 */
	public Object clone() throws CloneNotSupportedException {
		Floor floor = new Floor();
		floor.students = new Student[CAPACITY];
		for (int i = 0; i < size; i++) {
			floor.students[i] = (Student)this.students[i].clone();
		}
		floor.size=this.size;
		return floor;
	}
	
	/**
	 * Prints out the directory of the floor.
	 * @param floor_num Floor number
	 */
	public void getFloorPrint(int floor_num) {
		System.out.println("Floor " + floor_num + ":");
		System.out.println("Room    Name			ID 	 Writeups");
		for (int i = 0; i < size; i++) {
			System.out.println((i + 1) + " 	" + students[i].getName() + "		" + students[i].getID() + " 	  "
					+ students[i].getWrite_ups());
		}
	}
}
