package main.java.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

import javax.swing.JComponent;

import main.java.items.Passenger;

/**
 * Container for passengers
 * @author Yury
 *
 */
public abstract class AbstractContainer extends JComponent {
    private static final long serialVersionUID = 1L;
    private int x = UIDimensions.FIRST_PASSENGER_MARGIN;

    private Set<Passenger> passengers;

    public AbstractContainer(Set<Passenger> passengers) {
        super();
        this.passengers = passengers;
        setMaximumSize(new Dimension(Integer.MAX_VALUE, UIDimensions.PASSENGERS_CONTAINER_HEIGHT - 1));
        setMinimumSize(new Dimension(UIDimensions.PASSENGERS_CONTAINER_HEIGHT - UIDimensions.PASSENGERS_MARGIN, UIDimensions.PASSENGERS_CONTAINER_HEIGHT - UIDimensions.PASSENGERS_MARGIN));
        setPreferredSize(getMinimumSize());
    }

    public void setPassengers(Set<Passenger> passengers){
        this.passengers = passengers;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setColor(Color.blue);
        x = UIDimensions.FIRST_PASSENGER_MARGIN;
        drawContainer(graphics2d);
        if(passengers != null){
            for(Passenger passenger : passengers){
                drawPassenger(graphics2d, passenger);
                if(getSize().getWidth() < x){
                    break;
                }
                x += UIDimensions.PASSENGERS_MARGIN;
            }
        }

    }

    /**
     * draw passengers container
     * @param graphics2d
     */
    protected abstract void drawContainer (Graphics2D graphics2d);

    private void drawPassenger(Graphics2D graphics2d, Passenger passenger){
        String id = String.valueOf(passenger.getId());

        Rectangle2D stringBounds = graphics2d.getFontMetrics().getStringBounds(id, graphics2d);
        int width = (int) Math.round(stringBounds.getWidth());
        int height = (int) Math.round(stringBounds.getHeight());

        graphics2d.setColor(Color.black);
        graphics2d.drawString(id, x + UIDimensions.PASSENGERS_MARGIN, UIDimensions.ID_Y);
        graphics2d.draw(new Rectangle2D.Double(x, UIDimensions.ID_Y - height,
                width + 2 * UIDimensions.PASSENGERS_MARGIN, height + UIDimensions.PASSENGERS_MARGIN));
        x += width + 2 * UIDimensions.PASSENGERS_MARGIN;
    }
}
