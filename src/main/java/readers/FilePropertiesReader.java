package main.java.readers;

import main.java.items.Properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FilePropertiesReader {
    private static final String FILENAME = "src/main/java/source/config.property";
    private static final String REGEX = ";";
    private static final String KEY_STORIES_NUMBER = "storiesNumber";
    private static final String KEY_ELEVATOR_CAPACITY = "elevatorCapacity";
    private static final String KEY_PASSENGERS_NUMBER = "passengersNumber";
    private static final String KEY_ANIMATION_BOOST = "animationBoost";

    public static Properties getProperties(){
        Scanner sc = null;
        Map<String, String> properties = new HashMap<String, String>();
        try{
            sc = new Scanner(new File(FILENAME));
            while(sc.hasNext()){
                String line[] = sc.nextLine().split(REGEX);
                properties.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(sc != null){
                sc.close();
            }
        }
        int storiesNumber = Integer.parseInt(properties.get(KEY_STORIES_NUMBER));
        int elevatorCapacity = Integer.parseInt(properties.get(KEY_ELEVATOR_CAPACITY));
        int passengersNumber = Integer.parseInt(properties.get(KEY_PASSENGERS_NUMBER));
        int animationBoost = Integer.parseInt(properties.get(KEY_ANIMATION_BOOST));
        return new Properties(storiesNumber, elevatorCapacity, passengersNumber, animationBoost);
    }
}
