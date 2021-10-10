package ch.bettelini.regions;

import ch.bettelini.regions.utils.Point;

public interface Region {
    
    boolean contains(double x, double y);
    
    default boolean contains(Point point) {
        return this.contains(point.getX(), point.getY());
    }
}