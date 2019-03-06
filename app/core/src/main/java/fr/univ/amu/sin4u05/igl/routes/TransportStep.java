package fr.univ.amu.sin4u05.igl.routes;

import java.time.LocalDateTime;

/**
 * A transport route step is a route step that must be travelled on a transport line.
 */
public class TransportStep extends RouteStep {

    private final TransportLine line;
    private final String headSign;

    /**
     * Constructs a new {@code TransportStep} with the specified characteristics.
     *
     * @param line          the transport line to travel on.
     * @param headSign      the line direction to travel to.
     * @param departureStop the departure stop.
     * @param arrivalStop   the arrival stop.
     * @param departureTime the departure time.
     * @param arrivalTime   the arrival time.
     */
    public TransportStep(TransportLine line, String headSign,
                         StationStop departureStop, StationStop arrivalStop,
                         LocalDateTime departureTime, LocalDateTime arrivalTime) {

        super(RouteStepType.Transport, departureStop, arrivalStop, departureTime, arrivalTime);
        this.line = line;
        this.headSign = headSign;
    }

    /**
     * Returns the transport line to travel on.
     *
     * @return the transport line to travel on.
     */
    public TransportLine getLine() {
        return line;
    }

    /**
     * Returns the line direction to travel to.
     *
     * @return the line direction to travel to.
     */
    public String getHeadSign() {
        return headSign;
    }

    @Override
    protected boolean canMerge(RouteStep other) {
        if (other.getType() != RouteStepType.Transport) return false;
        TransportStep that = (TransportStep) other;
        return line.equals(that.line) &&
                headSign.equals(that.headSign) &&
                getArrivalStop().equals(that.getDepartureStop());
    }

    @Override
    protected RouteStep merge(RouteStep other) {
        TransportStep that = (TransportStep) other;
        return new TransportStep(line, headSign,
                getDepartureStop(), that.getArrivalStop(),
                getDepartureTime(), that.getArrivalTime());
    }

    /**
     * Returns a string representation of this {@code TransportStep} object.
     *
     * @return a {@code String} representation of this object.
     */
    @Override
    public String toString() {
        return "" + getDepartureTime().toLocalTime().toString() +
                " -> " + getArrivalTime().toLocalTime().toString() +
                ", take the " + getLine() + " (towards " + getHeadSign() +
                ") from " + getDepartureStop() + " to " + getArrivalStop();
    }
}
