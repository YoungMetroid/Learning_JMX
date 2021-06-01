package com.Chapter1;

import java.util.Calendar;
import java.text.DateFormat;

public class DateTimeService implements  DateTimeServiceMBean
{
    private String time = "0:00";
    private String date = "01-01-0001";

    public String printTime()
    {
        System.out.println("The set time is: " + time);
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
        System.out.println(time);
    }
    public String getTime()
    {
        return time;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    public String getDate()
    {
        return date;
    }


    public void stop(){}
    public void start() {}
}
