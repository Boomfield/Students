import curriculum.Course;
import curriculum.Curriculum;
import students.Student;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShcoolIT {
    public static void main(String[] args) {
        Course strutsFramework = new Course("Automation", 16);
        Course javaScript = new Course("JavaScript", 9);
        Course java = new Course("Java", 21);
        Curriculum javaDev = new Curriculum(LocalTime.of(10, 0), LocalTime.of(18, 0), "Java Developer");
        javaDev.Courses.add(strutsFramework);
        javaDev.Courses.add(javaScript);
        javaDev.Courses.add(java);
        Student yevhnii = new Student("Yevhenii", "Hurov", javaDev);
        yevhnii.Curriculum.StartProgram();
        System.out.println( LocalDateTime.of(2022,04,20,20,0));
        System.out.println(yevhnii.Curriculum.lastDayStudy());
        System.out.println(yevhnii.Curriculum.getFinishInfo());
        if (yevhnii.Curriculum.getFinishInfo().Finished) {
            System.out.println(yevhnii.FirstName + " " + yevhnii.LastName + " (" + javaDev.Name + ") - Обучение закончено. После окончания прошло " +
                    yevhnii.Curriculum.getFinishInfo().Days + " д " + yevhnii.Curriculum.getFinishInfo().Hours + " ч");
        } else
            System.out.println(yevhnii.FirstName + " " + yevhnii.LastName + " (" + javaDev.Name + ") - Обучение не закончено. До окончания осталось " +
                    yevhnii.Curriculum.getFinishInfo().Days + " д " + yevhnii.Curriculum.getFinishInfo().Hours + " ч");
    }
}
