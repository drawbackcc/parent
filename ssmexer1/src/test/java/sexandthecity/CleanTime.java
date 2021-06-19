package sexandthecity;

import org.junit.Test;

import java.io.*;
import java.util.regex.Pattern;

public class CleanTime {
    @Test
    public void count() throws IOException {
        //可以
        String fileName = "C:\\Users\\Chenzhimei\\Videos\\欲望都市\\Sex and the City 中英字幕\\s01\\nmv-satcs1ep1-x264.Eng.srt";
        //也可以
//        String fileName2 = "C:/Users/Chenzhimei/Videos/欲望都市/Sex and the City 中英字幕/s01/nmv-satcs1ep1-x264.Eng.srt";
//        System.out.println(new File(fileName).exists());
//        System.out.println(new File(fileName2).exists());

//        Pattern pattern = Pattern.compile("[0-9]+\\n");
//        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2},\\d{3}");//00:22:01,853 --> 00:22:05,220
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = null;
        String pattern = "\\d{2}:\\d{2}:\\d{2},\\d{3}.*";
        while((string = bufferedReader.readLine()) != null){
            if(!string.matches(pattern))
                System.out.println(string);
        }

    }
}
