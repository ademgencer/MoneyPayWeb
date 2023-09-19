package readers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    // Todo: Açıklama satırları eklenecek!

    Properties properties = new Properties();

    String file;


    public static PropertyReader read() {
        return read("config");
    }


    public static PropertyReader read(String fileName) {
        return new PropertyReader(fileName);
    }


    PropertyReader(String fileName) {// fileName -> config, config2 gibi istenen dosyayı gireriz.
        try {
            this.file = "src/test/resources/datafiles/" + fileName + ".properties"; // .json, .xml ...
            FileReader fileReader = new FileReader(file);
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public String get(String key) {
        return properties.getProperty(key);
    }


    public void set(String key, String value) {
        properties.setProperty(key, value);
    }


    public void del(String key) {
        properties.remove(key);
    }


    public void store(String comment) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            properties.store(fileWriter, comment);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
