package jt.projects.stepikapp.calendar;

class HolidayInfo {
    int id;
    String holidayName;
    String holidayDate;

    public HolidayInfo(String holidayName, String holidayDate) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public String getHolidayDate() {
        return holidayDate;
    }
}
