package students;

import curriculum.Curriculum;

public class Student {
    public String FirstName;
    public String LastName;
    public Curriculum Curriculum;

    public Student(String firstName, String lastName, Curriculum curriculum) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Curriculum = curriculum;
    }
}
