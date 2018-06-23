package com.dvsmart.timetable.konstr;

/**
 * Created by stanislavtopanov on 11/10/16.
 */

public class KonstTable {


  public String number_;
    public String direction_;
    public String timeTo_;
    public String timeOff_;
    public String timeDirection_;

    public KonstTable(){}


    public KonstTable(String number, String direction, String timeTo, String timeOff, String timeDirection)
    {
        number_ = number;
        direction_ = direction;
        timeTo_ = timeTo;
        timeOff_ = timeOff;
        timeDirection_ = timeDirection;

    }

    public String getNumber_() {
        return number_;
    }

    public void setNumber_(String number_) {
        this.number_ = number_;
    }

    public String getDirection_() {
        return direction_;
    }

    public void setDirection_(String direction_) {
        this.direction_ = direction_;
    }

    public String getTimeTo_() {
        return timeTo_;
    }

    public void setTimeTo_(String timeTo_) {
        this.timeTo_ = timeTo_;
    }

    public String getTimeOff_() {
        return timeOff_;
    }

    public void setTimeOff_(String timeOff_) {
        this.timeOff_ = timeOff_;
    }

    public String getTimeDirection_() {
        return timeDirection_;
    }

    public void setTimeDirection_(String timeDirection_) {
        this.timeDirection_ = timeDirection_;
    }
}
