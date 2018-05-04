package lab7;

/**
 * The Student class.
 * 
 * The variables firstName, lastName and constant studentID are required to
 * initialize a student instance. The credits and GPA will only be changed when
 * submitting a new grade. The GPA is a weighted average between 0.00 and 4.00,
 * and will always be rounded to two decimal places.
 * 
 * @author Weiqiang Li
 *
 */
public class Student {

	private String firstName;
	private String lastName;
	private final int studentID;
	private int credits;
	private double GPA;

	/**
	 * Initialize a student.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param id
	 */
	public Student(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = id;
		this.credits = 0;
		this.GPA = .0;
	}

	/**
	 * Get the students class standing based on how many credits they have.
	 * 
	 * @return the class standing
	 */
	public String getClassStanding() {
		if (this.credits < 30) {
			return "Freshman";
		} else if (this.credits < 60) {
			return "Sophomore";
		} else if (this.credits < 90) {
			return "Junior";
		} else {
			return "Senior";
		}
	}

	/**
	 * Submit a new grade, and calculate the new GPA and total credits.
	 * 
	 * @param grade
	 *            the grade of the course
	 * @param credits
	 *            the credits of the course
	 */
	public void submitGrade(double grade, int credits) {
		if (grade < 0 || grade > 4 || credits < 0) {
			throw new IllegalArgumentException("Invalid credits or grade!");
		} else {
			this.GPA = (this.GPA * this.credits + grade * credits) / (this.credits + credits);
			roundGPA();
			this.credits += credits;
		}
	}

	/**
	 * Create the child of two students. The name of child is the combination of
	 * both parents' names, and the ID of child is the sum of parents' ID. The
	 * GPA of child is the average of parents' GPA, and the credits of child is
	 * the maximum credits of parents.
	 * 
	 * @param that
	 *            the other student
	 * @return the child of two students
	 */
	public Student createLegacy(Student that) {
		Student child = new Student(this.getName(), that.getName(), this.studentID + that.studentID);
		child.GPA = (this.GPA + that.GPA) / 2;
		roundGPA();
		child.credits = Math.max(this.credits, that.credits);
		return child;
	}

	@Override
	public String toString() {
		return "Name: " + this.getName() + " ID: " + this.studentID;
	}

	/**
	 * Round the GPA to two decimal places.
	 */
	private void roundGPA() {
		this.GPA = (double) Math.round(this.GPA * 1000) / 1000;
	}

	/**
	 * Get the student's full name.
	 * 
	 * @return the full name of the student
	 */
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	/**
	 * Get the student's first name.
	 * 
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Get the student's last name.
	 * 
	 * @return the last name of the student
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Change the student's first name.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Change the student's last name.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the student's credits.
	 * 
	 * @return the credits of the student
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Get the student's GPA.
	 * 
	 * @return the GPA of the student
	 */
	public double getGPA() {
		return this.GPA;
	}

	/**
	 * Get the student's ID.
	 * 
	 * @return the ID of the student
	 */
	public int getStudentID() {
		return this.studentID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + studentID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (studentID != other.studentID)
			return false;
		return true;
	}

}
