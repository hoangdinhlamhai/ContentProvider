package Model;

public class CallLog {
    private String number;
    private String type;
    private String date;
    private String duration;

    public CallLog(String number, String type, String date, String duration) {
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;
    }

    // Getters
    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Number: " + number + ", Type: " + type + ", Date: " + date + ", Duration: " + duration + " seconds";
    }
}
