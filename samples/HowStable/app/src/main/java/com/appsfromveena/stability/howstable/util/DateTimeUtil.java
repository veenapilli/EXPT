package com.appsfromveena.stability.howstable.util;

import com.streamoid.bot.chatsdk.chat.models.adapterData.ConversationData;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by veena on 23/3/17.
 */

public class DateTimeUtil {
    /*
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
return fmt.format(date1).equals(fmt.format(date2));
     */

    public static String getDate(long time){
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    public static String getCurrentUtcTime() {
        DateTime dt = new DateTime().withZone(DateTimeZone.forID("UTC"));
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        return fmt.print(dt);
    }

    public static String convertTimeUtcToZone(String utcTime) {
        if(!TextUtil.isStringNotNullOrEmpty(utcTime)) return "";
        DateTimeFormatter formatter = DateTimeFormat.forPattern( "HH:mm" ).withZone(DateTimeZone.getDefault());
        DateTime dateTime2 = new DateTime( utcTime, DateTimeZone.getDefault());
        return formatter.print(dateTime2);

    }
    public static String convertUtcToZoneDate(String utcTime) {
        if(!TextUtil.isStringNotNullOrEmpty(utcTime)) return "";
        DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd, MMM YY, hh:mm" ).withZone(DateTimeZone.getDefault());
        DateTime dateTime2 = new DateTime( utcTime, DateTimeZone.getDefault());
        return formatter.print(dateTime2);

    }
    public static boolean needToAddTime(String time1, String time2) {
        if(!TextUtil.isStringNotNullOrEmpty(time1) || !TextUtil.isStringNotNullOrEmpty(time2)){
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/YY/hh/mm" );
        DateTime dateTime1 = new DateTime( time1);
        DateTime dateTime2 = new DateTime( time2);
        if(formatter.print(dateTime1).equals(formatter.print(dateTime2))){
            return false;
        }else{
            return true;
        }

    }

    public static String getTimeToAdd(ConversationData data) {
        if(null != data && null != data.getQueryResponseItem() &&
                null != data.getQueryResponseItem().getCreated_at()) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/YY/hh/mm" );
            DateTime dateTime1 = new DateTime( data.getQueryResponseItem().getCreated_at());
            return formatter.print(dateTime1);
        }else{
            return "";
        }
    }
}
