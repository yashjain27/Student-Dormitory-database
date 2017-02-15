import java.util.InputMismatchException;
import java.util.Scanner;


/**The code is the driver class.
 * 
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 1 
 *  CSE 214 Section 10
 * 	Daniel Scanteianu
 *
 */
public class ResidenceHallManager {

	public static void main(String[] args) {
				
		// Declaration of variables
		Scanner input = new Scanner(System.in);
		String choice, name;

		// Floor objects
		Floor floor1 = new Floor();
		Floor floor2 = new Floor();
		Floor floor3 = new Floor();
		Floor[] floor = new Floor[] { floor1, floor2, floor3 };
		int floor_num = 1, spot =0, writeups, floor_num2=1, spot2=0; // Default floor number

		// Do-While loop
		do {
			System.out.println("\nPlease select an option");
			System.out.println("A) Add a student");
			System.out.println("R) Remove a student");
			System.out.println("S) Swap Students");
			System.out.println("M) Move Student");
			System.out.println("F) Select Floor");
			System.out.println("C) Copy Floor");
			System.out.println("P) Print Current Floor");
			System.out.println("W) Write Up Student");
			System.out.println("Q) Quit");
			
			choice = input.nextLine(); // User chooses their option
		
			System.out.println();
			
			switch (choice.toUpperCase()) {
			case "A":
				try {
					// Student object
					Student student = new Student();
					
					System.out.println("\nAdd A Student:");
					System.out.print("Please enter a name: ");
					student.setName(input.nextLine());
					student.setID(getInputInt("\nPlease enter an id number: "));
					spot=getInputInt("\nPlease enter a spot number: ", 1, 50);
					floor[floor_num - 1].addStudent(student, spot);
				} catch (FullFloorException ex) {
					System.out.println(ex);
				} catch (IllegalArgumentException ex) {
					System.out.println(ex);
				}
				break;
			case "R":
				try{
					Student student = floor[floor_num-1].removeStudent(getInputInt("Please select a student number: ", 1, 50));
					System.out.println(student.getName() + " has been removed.");
				}catch(EmptyFloorException ex){
					System.out.println(ex);
				}catch(IllegalArgumentException ex){
					System.out.println(ex);
				}
				break;
			case "S":
				//Student 1 and 2 objects
				Student student1 = new Student();
				Student student2 = new Student();
				try{
					//Student 1
					floor_num=getInputInt("Please enter Student 1 floor: ", 1, 3);
					spot=getInputInt("Please enter Student 1 room: ", 1, 50);
					student1 = floor[floor_num-1].getStudent(spot);
					
					//Student 2
					floor_num2=getInputInt("Please enter Student 2 floor: ", 1, 3);
					spot2=getInputInt("Please enter Student 2 room: ", 1, 50);
					student2 = floor[floor_num2-1].getStudent(spot2);
					
					//Swap
					floor[floor_num2-1].setStudent(student1, spot2);
					floor[floor_num-1].setStudent(student2, spot);
				}catch(IllegalArgumentException ex){
					System.out.println("Invalid room number");
				}
				break;
			case "M":
				System.out.print("Please enter source floor:");
				floor_num=input.nextInt();
				System.out.print("Please enter source room: ");
				spot=input.nextInt();
				System.out.print("Please enter destination floor: ");
				floor_num2=input.nextInt();
				System.out.print("Please enter destination room: ");
				spot2=input.nextInt();
				input.nextLine(); //Flushing
				Student student3 = new Student(); //Local student object
				student3=floor[floor_num-1].getStudent(spot); //Retrieve the student at specified floor and room
				
				try {
					floor[floor_num2-1].addStudent(student3, spot2);
					floor[floor_num-1].removeStudent(spot);
				} catch (IllegalArgumentException e1) {
					System.out.println("Invalid room number");
				} catch (FullFloorException e1) {
					System.out.println("The floor is full");
				} catch (EmptyFloorException ex){
					System.out.println("Floor is empty!");
				}
							
				break;
			case "F":
				floor_num=getInputInt("Please select a floor: ", 1, 3);
				break;
			case "C":
				floor_num=getInputInt("Please enter the source floor: ", 1, 3);
				floor_num2=getInputInt("Please select the destination floor: ", 1, 3);
				try {
					floor[floor_num2-1]=(Floor)floor[floor_num-1].clone();
				} catch (CloneNotSupportedException e) {
					System.out.println("Cloning failed!");
				}
				break;
			case "P":
				floor[floor_num-1].getFloorPrint(floor_num);
				break;
			case "W":
				System.out.print("Please enter a student name: ");
				name=input.nextLine();
				
				//Student object
				Student student = new Student();
				
				for(int i=0; i<floor[floor_num-1].count();i++){
					student = floor[floor_num-1].getStudent(i+1);
					if(name.equals(student.getName())){		//Search for student name
						writeups=student.getWrite_ups();	//get the current number of writeups
						writeups++;							//Increment the writeups
						student.setWrite_ups(writeups);		//Set the new amount
						spot=i+1;
						System.out.println(student.getName() + " has " + student.getWrite_ups() + " writeup.");
						break;
					}
				}
				try{
				if(student.getWrite_ups()==3){
					floor[floor_num-1].removeStudent(spot);
					System.out.println(name + " has 3 writeups and has been removed from the building.");
				}
				}catch(EmptyFloorException ex){
					System.out.println("Empty floor");
				}catch(IllegalArgumentException ex){
					System.out.println("Student not found");
				}
				break;
			case "Q":
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
			
		} while (!choice.equals("Q"));
		
		System.out.println("Thanks for using the program!");
		
	}
	
	public static int getInputInt(String message) {
		return getInputInt(message, -1, -1);
	}
	
	public static int getInputInt(String message, int min, int max) {
		Scanner in = new Scanner(System.in);
		int out = -1;
		
		while(true) {
			System.out.print(message);

			try {
				out = in.nextInt();

				if(!(min == -1 && min == max) && (out < min || out > max)) {
					System.out.println("Error: outside range [" + min + ", " + max + "]");
				} else {
					break;
				}
			} catch(InputMismatchException e) {
				System.out.println("Invalid input please try again.");
			}
		}
		in.nextLine(); //Flushing
		return out;
	}

}

