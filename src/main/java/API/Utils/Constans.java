package API.Utils;

import java.io.File;
import java.util.regex.Matcher;

public class Constans {

   static String absolutePath = System.getProperty("user.dir");
   static String pathToBookingId = absolutePath + "/src/main/resources/";

    public static String getPathToResouces(String fileName){
        return (pathToBookingId +fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
    }

}
