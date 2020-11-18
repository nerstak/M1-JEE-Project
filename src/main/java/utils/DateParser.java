package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static Date stringDashToDate(String strDate){
        strDate = strDate.replace("-","/");
        try {
           return new SimpleDateFormat("yyyy/MM/dd").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
