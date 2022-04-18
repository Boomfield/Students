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
    public Duration getFinishInfo() {
        LocalDateTime nowDateTime = LocalDateTime.of(2022, 04, 20, 20, 0);
        Duration finishInfo = new Duration(0, 0, true);
        LocalDateTime llastDayStudy = lastDayStudy();
        long daysToEnd = ChronoUnit.DAYS.between(lastDayStudy(), nowDateTime);
        long hoursToEnd = llastDayStudy.getHour() - nowDateTime.getHour();
        long hours = 0;
        if (llastDayStudy.compareTo(nowDateTime) < 0) {
            finishInfo.Finished = false;
            finishInfo.Days = (int) Math.abs(daysToEnd);
            finishInfo.Hours = (int) hoursToEnd;
        }
        if (hoursToEnd < 0) {
            hours = WorkingEndTime.getHour() - nowDateTime.getHour() + llastDayStudy.getHour() - WorkingStartTime.getHour();
            finishInfo.Hours = (int) hours;
            finishInfo.Days = (int) Math.abs(daysToEnd);
            finishInfo.Finished = false;

        }
        if (daysToEnd > 0) {
            finishInfo.Finished = true;
            finishInfo.Days = (int) daysToEnd;
            finishInfo.Hours = (int) Math.abs(hoursToEnd);
        }
        return finishInfo;
    }

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

}
