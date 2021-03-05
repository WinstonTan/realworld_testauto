package util;

import org.openqa.selenium.WebDriver;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UniqueKeyGenerator {

    public String getUniqueKey()
    {
        return new SimpleDateFormat("yyMMdd_HHmmssSSS").
                format(new Timestamp(System.currentTimeMillis()));
    }
}
