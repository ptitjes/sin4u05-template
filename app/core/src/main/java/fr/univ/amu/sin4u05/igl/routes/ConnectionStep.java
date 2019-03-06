package fr.univ.amu.sin4u05.igl.routes;

import java.time.LocalDateTime;

/**
 * A connection route step is a route step that must be travelled by walking.
 */
public class ConnectionStep extends RouteStep {

    private final double distance;

    /**
     * Constructs a new {@code ConnectionStep} with the specified characteristics.
     *
     * @param distance      the distance to travel.
     * @param departureStop the departure stop.
     * @param arrivalStop   the arrival stop.
     * @param departureTime the departure time.
     * @param arrivalTime   the arrival time.
     */
    public ConnectionStep(double distance,
                          StationStop departureStop, StationStop arrivalStop,
                          LocalDateTime departureTime, LocalDateTime arrivalTime) {

        super(RouteStepType.Connection, departureStop, arrivalStop, departureTime, arrivalTime);
        this.distance = distance;
    }

    /**
     * Returns the distance to travel on foot.
     *
     * @return the distance to travel (in meters).
     */
    public double getDistance() {
        return distance;
    }

    @Override
    protected boolean canMerge(RouteStep other) {
        return other.getType() == RouteStepType.Connection;
    }

    @Override
    protected RouteStep merge(RouteStep other) {
        ConnectionStep that = (ConnectionStep) other;
        return new ConnectionStep(this.distance + that.distance,
                getDepartureStop(), that.getArrivalStop(),
                getDepartureTime(), that.getArrivalTime());
    }

    /**
     * Returns a string representation of this {@code ConnectionStep} object.
     *
     * @return a {@code String} representation of this object.
     */
    @Override
    public String toString() {
        return "" + getDepartureTime().toLocalTime().toString() +
                " -> " + getArrivalTime().toLocalTime().toString() +
                ", walk from " + getDepartureStop() + " to " + getArrivalStop() +
                " (" + Math.round(getDistance()) + " m, " + getDuration().toMinutes() + " min)";
    }
}
