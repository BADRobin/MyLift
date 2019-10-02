package main.java;

import main.java.items.Building;
import main.java.items.Properties;
import main.java.readers.FilePropertiesReader;
import main.java.ui.UIRunner;
import main.java.working.Controller;

public class Start {
    public static void main (String [] args){
        Properties properties = FilePropertiesReader.getProperties();


        if (properties.getAnimationBoost() == 0) {
            Building building = new Building(properties);
            Controller controller = building.getController();
            controller.run();
        }else {
            UIRunner.run(properties);
        }
    }
}
