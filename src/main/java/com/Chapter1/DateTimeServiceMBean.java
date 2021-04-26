package com.Chapter1;

public interface DateTimeServiceMBean
{
    public String printTime();
    public void setTime(String time);
    public String getTime();
    public void setDate(String date);
    public String getDate();

    public void stop();
    public void start();


}
