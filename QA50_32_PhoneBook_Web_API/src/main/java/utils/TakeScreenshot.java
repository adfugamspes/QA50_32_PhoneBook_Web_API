package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshot {

    public static void main(String[] args) {
        System.out.println(createFileName());
    }

    public static void takeScreenshot(TakesScreenshot screenshot){
        String filename = createFileName();
        File screenFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenFile.toPath(), new File(filename).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String createFileName(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        String currentDate = formatter.format(date);
        System.out.println(currentDate);
        return "src/test/resources/tests_logs/screenshots/scrrenshot-" + currentDate + ".png";
    }
}
