import java.time.LocalTime;

public class DemoApp {
    public static void main(String[] args) {

        Meeting m1 = new Meeting(LocalTime.of(10, 0, 0), LocalTime.of(12, 0, 0), "meeting 1");
        Meeting m2 = new Meeting(LocalTime.of(8, 0, 0), LocalTime.of(9, 0, 0),"meeting 2");
        Meeting m3 = new Meeting(LocalTime.of(11, 0, 0), LocalTime.of(13, 0, 0),"meeting 3");
        Meeting m4 = new Meeting(LocalTime.of(12, 0, 0), LocalTime.of(14, 0, 0),"meeting 4");
        MeetingRoom mr = new MeetingRoom(m1);
        mr.addMeeting(m2);
//        mr.addMeeting(m3);
        mr.addMeeting(m4);
        System.out.println(mr);
    }
}
