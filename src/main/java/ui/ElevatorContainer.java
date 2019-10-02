package main.java.ui;

import main.java.items.Passenger;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;


    public class ElevatorContainer extends AbstractContainer{
        private static final long serialVersionUID = 8388064106781936255L;

        public ElevatorContainer(Set<Passenger> passengers, int passengersViewNumber) {
            super(passengers);

            setMaximumSize(new Dimension(UIDimensions.PASSENGERS_CONTAINER_WIDTH * passengersViewNumber, UIDimensions.PASSENGERS_CONTAINER_HEIGHT));
            setMinimumSize(new Dimension(UIDimensions.PASSENGERS_CONTAINER_WIDTH, UIDimensions.PASSENGERS_CONTAINER_HEIGHT));
            setPreferredSize(getMaximumSize());
        }

        @Override
        protected void drawContainer(Graphics2D graphics2d) {
            if(getPassengers() != null){
                graphics2d.setColor(Color.blue);
                graphics2d.draw(new Rectangle2D.Double(0, 0, getSize().getWidth() - 1,
                        getSize().getHeight() - 1));
            }
        }
    }

