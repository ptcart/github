package admin.window;


// 날짜 포맷터 클래스
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.DateFormatter;

class DateLabelFormatter extends DateFormatter {
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            return dateFormatter.format((Date) value);
        }
        return "";
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text != null && !text.isEmpty()) {
            return dateFormatter.parse(text);
        }
        return null;
    }
}
