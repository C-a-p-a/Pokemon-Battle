package no.uib.inf101.sem2.game;

import no.uib.inf101.grid.Location;

public class SingleLocationMove implements IMoveType<Location> {
    
    public final Location location;

    public SingleLocationMove(Location location) {
        this.location = location;
    }

    @Override
    public Location getMove() {
        return location;
    }

    
}
