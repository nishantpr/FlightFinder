package com.np.flightfinder.model;

public class Time{

    int hour;
    int minute;

    public Time(String s){
        s = "000"+s;
        int len = s.length();
        hour = Integer.parseInt(s.substring(len-4, len-2));
        minute = Integer.parseInt(s.substring(len-2));
    }

    public int getDuration(Time end){
        int minuteOfDay = this.hour*60 + this.minute;
        int endMinuteOfDay = end.hour*60 + end.minute;

        if(endMinuteOfDay < minuteOfDay){
            return endMinuteOfDay + (1440-minuteOfDay);
        }

        return endMinuteOfDay-minuteOfDay;
    }

}
