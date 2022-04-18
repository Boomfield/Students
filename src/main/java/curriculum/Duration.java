package curriculum;

public class Duration {
    public int Days;
    public int Hours;
    public boolean Finished;

    public Duration(int days, int hours,boolean finished) {
        Days = days;
        Hours = hours;
        Finished = finished;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "Days=" + Days +
                ", Hours=" + Hours +
                ", Finished=" + Finished +
                '}';
    }
}
