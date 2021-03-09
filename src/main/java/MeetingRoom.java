import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

class Person {
    private String  name;
    public Person(String name) {
        this.name = name;
    }
}
class Meeting {
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private List<Person> persons;
    //TODO: add checker for start time to be before endtime
    public Meeting(LocalTime startTime, LocalTime endTime, String description) {
        this.startTime   = startTime;
        this.endTime     = endTime;
        this.description = description;
        this.persons     = new LinkedList<>();
    }
    public void add (Person p){
        this.persons.add(p);
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return this.description + ": " + this.startTime.toString() + " - " + this.endTime.toString();
    }
}
class MeetingRoom {
    private List<Meeting> meetings;
    //TODO: limit end date to not be later than 8:00 PM
    public MeetingRoom(Meeting m){
        checkIfMeetingsStartsBefore8AM(m);
        this.meetings  = new LinkedList<>();
        this.meetings.add(m);
    }
    public void addMeeting(Meeting m) {
        checkIfMeetingsStartsBefore8AM(m);
        if(this.overlaps(m)){
            throw new RuntimeException("There's already a meeting in this interval: " + m.getStartTime().toString() + " - " + m.getEndTime().toString());
        }
        this.meetings.add(m);
    }
    public boolean overlaps(Meeting m){
        for(int i = 0; i < this.meetings.size(); i++){
            LocalTime scheduledStartTime = this.meetings.get(i).getStartTime();
            LocalTime scheduledEndTime   = this.meetings.get(i).getEndTime();
            if(    (m.getStartTime().isBefore(scheduledStartTime)  && m.getEndTime().isAfter(scheduledEndTime))
                    || (m.getStartTime().isBefore(scheduledStartTime)  && m.getEndTime().isAfter(scheduledStartTime))
                    || (m.getStartTime().isAfter(scheduledStartTime)   && m.getEndTime().isBefore(scheduledEndTime))
                    || (m.getStartTime().isBefore(scheduledEndTime)    && m.getEndTime().isAfter(scheduledEndTime))
            ) {
                return true;
            }
        }
        return false;
    }
    //TODO: find a better solution
    private void checkIfMeetingsStartsBefore8AM(Meeting m){
        if(m.getStartTime().isBefore(LocalTime.of( 8, 0, 0))){
            throw new RuntimeException("Meeting cannot be reserved before 8:00 AM");
        }
    }
    public String toString() {
        String r = "MeetingRoom meetings: \n\n";
        for(Meeting m : this.meetings ){
            r = r + m.toString() + "\n";
        }
        return  r;
    }
    //TODO: add method to return available time slots for MeetingRoom.
}
// st  10:00   12:00 en

