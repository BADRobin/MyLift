package main.java.ui;

import main.java.items.Passenger;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;

public class StoreyContainer extends AbstractContainer{
    private static final long serialVersionUID = -3569533584264153000L;

    public StoreyContainer(Set<Passenger> passengers) {
        super(passengers);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, UIDimensions.PASSENGERS_CONTAINER_HEIGHT - 1));
        setMinimumSize(new Dimension(UIDimensions.PASSENGERS_CONTAINER_HEIGHT - UIDimensions.PASSENGERS_MARGIN, UIDimensions.PASSENGERS_CONTAINER_HEIGHT - UIDimensions.PASSENGERS_MARGIN));
    }

    protected void drawContainer (Graphics2D graphics2d){
        graphics2d.setColor(Color.gray);
        graphics2d.draw(new Rectangle2D.Double(0, 0, getSize().getWidth() - 1, getSize().getHeight() - 1));
    }

}
