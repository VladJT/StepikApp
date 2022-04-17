package jt.projects.stepikapp.calendar;

 class ListOfHolidaysInfo {
    public HolidayInfo[] holidayInfo;

    public ListOfHolidaysInfo(int size) {
        holidayInfo = new HolidayInfo[size];
    }

    public void addHoliday(String holidayName, String holidayDate, int id) {
        holidayInfo[id] = new HolidayInfo(holidayName, holidayDate);
    }
}
