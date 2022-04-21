package curriculum;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Curriculum {
    public LocalTime WorkingStartTime;
    public LocalTime WorkingEndTime;
    public String Name;
    private LocalDateTime dateStarted;
    public ArrayList<Course> Courses = new ArrayList<>();
    LocalDateTime nowDateTime = LocalDateTime.of(2022, 04, 20, 17, 0);
    Duration finishInfo = new Duration(0, 0, true);

    public Curriculum(LocalTime workingStartTime, LocalTime workingEndTime, String name) {
        WorkingStartTime = workingStartTime;
        WorkingEndTime = workingEndTime;
        Name = name;
    }

    public void StartProgram() {
        this.dateStarted = LocalDateTime.of(2022, Month.APRIL, 13, 15, 0);
    }

    //    46 hours Curriculum
//    Startday  4/16/22 13:00
//    EndDay 46hours 5 days 6 hours
//    NotFinished 6/18/22 15:00 63days
//    Finished 4/12/22 11:00
    public LocalDateTime lastDayStudy() {
        int allHours = Courses.stream().map(x -> x.Duration).reduce(0, (x, y) -> x + y);
        int days = allHours / 8;
        int hours = allHours % 8;
        LocalDateTime lastDayStudy = dateStarted.plusDays(days);
        long newDayOrNot = ChronoUnit.HOURS.between(WorkingEndTime, lastDayStudy.plusHours(hours));
        if (newDayOrNot > 0) {
            return lastDayStudy.plusDays(1).withHour(0).plusHours(WorkingStartTime.plusHours(newDayOrNot).getHour());

        } else
            return lastDayStudy.plusHours(hours);
    }

    public Duration getFinishInfo() {
        LocalDateTime llastDayStudy = lastDayStudy();

        if (llastDayStudy.compareTo(nowDateTime) > 0) {
            currNotOverHowTime();
        }
        if (howDaysTime() > 0) {
            currOverHowTime();
        }
        return finishInfo;
    }


    public long howDaysTime() {

        long daysToEnd = ChronoUnit.DAYS.between(lastDayStudy(), nowDateTime);

        return daysToEnd;
    }

    public long howHoursTime() {
        long hoursToEnd = lastDayStudy().getHour() - nowDateTime.getHour();
        return hoursToEnd;
    }

    public void currNotOverHowTime() {
        LocalDateTime llastDayStudy = lastDayStudy();
        long hours = 0;
        finishInfo.Finished = false;
        finishInfo.Days = (int) Math.abs(howDaysTime());

        if (howHoursTime() < 0) {
            hours = WorkingEndTime.getHour() - nowDateTime.getHour() + llastDayStudy.getHour() - WorkingStartTime.getHour();
            finishInfo.Hours = (int) hours;
        } else
            finishInfo.Hours = (int) howHoursTime();
    }

    public void currOverHowTime() {
        finishInfo = new Duration((int) howDaysTime(), (int) Math.abs(howHoursTime()), true);
    }


}
