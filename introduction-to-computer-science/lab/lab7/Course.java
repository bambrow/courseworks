package lab7;

/**
 * The Course class.
 * 
 * The variables name, numSeats and constant credits are required to initialize
 * a course instance. The students can be added to the course unrepeatedly when
 * there are seats avaliable. The roster of all students and average GPA of all
 * students can be generated and calculated.
 * 
 * @author Weiqiang Li
 *
 */
public class Course {

	private String name;
	private final int credits;
	private int remainingSeats;
	private Student[] students;

	/**
	 * Initialize a course.
	 * 
	 * @param name
	 * @param credits
	 * @param numSeats
	 */
	public Course(String name, int credits, int numSeats) {
		this.name = name;
		this.credits = credits;
		this.remainingSeats = numSeats;
		this.students = new Student[numSeats];
	}

	/**
	 * Add a student unrepeatedly to the course when there are seats avaliable.
	 * 
	 * @param student
	 *            a student defined by Student class
	 * @return whether the student is added sucessfully
	 */
	public boolean addStudent(Student student) {
		if (this.remainingSeats <= 0) {
			return false;
		} else {
			int numStudents = students.length - remainingSeats;
			for (int i = 0; i < numStudents; i++) {
				if (students[i].equals(student)) {
					return false;
				}
			}
			students[numStudents] = student;
			remainingSeats--;
			return true;
		}
	}
	
	/**
	 * Generate the roster of all students registered to this course.
	 * 
	 * @return the string of all students' names
	 */
	public String generateRoster() {
		int numStudents = students.length - remainingSeats;
		if (numStudents == 0) {
			return null;
		} else {
			StringBuilder roster = new StringBuilder();
			for (int i = 0; i < numStudents; i++) {
				roster.append(students[i].getName() + "; ");
			}
			return roster.toString();
		}
	}
	
	/**
	 * Calculate the average GPA of all students registered to this course.
	 * 
	 * @return the average GPA
	 */
	public double averageGPA() {
		int numStudents = students.length - remainingSeats;
		if (numStudents == 0) {
			return .0;
		} else {
			double sumGPA = .0;
			for (int i = 0; i < numStudents; i++) {
				sumGPA += students[i].getGPA();
			}
			return sumGPA / numStudents;
		}
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + " Credits: " + this.credits;
	}

	/**
	 * Get the course's name.
	 * 
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the course's name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the course's credits.
	 * 
	 * @return the credits of the course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Get the number of remaining seats.
	 * 
	 * @return the number of remaining seats
	 */
	public int getRemainingSeats() {
		return remainingSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
