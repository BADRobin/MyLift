package main.java.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Building {
    private final Elevator elevator;
    private final List<Storey> storeys;
    private final Controller controller;
    private final Properties properties;
    private final Set<Passenger> passengers;

    public Building (Properties properties){
    super();
    this.properties = properties;
        Random random = new Random();
        int storiesNumber = properties.getStoriesNumber();
        elevator = newElevator(properties.getElevator)
    }
}
