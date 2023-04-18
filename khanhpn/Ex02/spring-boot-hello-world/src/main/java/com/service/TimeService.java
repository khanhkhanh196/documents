package com.service;

import com.common.Printer;
import org.apache.commons.lang3.time.CalendarUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.time.TimeZones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TimeService {
    private Printer printer;

    @Autowired
    private TimeService(Printer printer) {
        this.printer = printer;
    }
    public void calenderUltils(){
        Calendar rightNow = Calendar.getInstance();
        CalendarUtils calendarUtils = new CalendarUtils(rightNow);
        System.out.println("Date: "+ calendarUtils.getDayOfMonth() +"/"+calendarUtils.getMonth()+"/"+calendarUtils.getYear());
    }
}
