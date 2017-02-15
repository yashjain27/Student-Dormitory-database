/**The code for Student class manages the student's information.
 * 
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 1 CSE 214
 * 	Section 10 Daniel Scanteianu
 *
 */
public class Student {
	//Data fields
	private String name;
	private int studentID;
	private int write_ups;
	
	//Constructors
	/**
	 * Empty constructor, creates a student object
	 */
	public Student(){
		//Empty constructor
	}
	
	/**
	 * 
	 * @param name - the name of the student
	 * @param studentID - Student ID number
	 * @param write_ups - Number of writeups
	 */
	public Student(String name, int studentID, int write_ups){
		this.name=name;
		this.studentID=studentID;
		this.write_ups=write_ups;
	}
	
	//Mutator
	/**
	 * 
	 * @param name - sets the name of the student
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * 
	 * @param studentID - sets the Student's ID number
	 */
	public void setID(int studentID){
		this.studentID=studentID;
	}
	
	/**
	 * 
	 * @param write_ups - sets the amount of write-ups the students has
	 */
	public void setWrite_ups(int write_ups){
		this.write_ups=write_ups;
	}
	
	//Accessors
	/**
	 * 
	 * @return returns the student's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return returns the Student's ID number
	 */
	public int getID(){
		return studentID;
	}
	
	/**
	 * 
	 * @return returns the number of write-ups the student has
	 */
	public int getWrite_ups(){
		return write_ups;
	}
	
	//Clone Method
	/**
	 * This is the clone method that clones student's name, ID, and writeups/
	 * @return returns the student object
	 */
	public Object clone(){
		Student student = new Student();
		student.setID(this.studentID);
		student.setName(this.name);
		student.setWrite_ups(this.write_ups);
		return student;
	}
}
