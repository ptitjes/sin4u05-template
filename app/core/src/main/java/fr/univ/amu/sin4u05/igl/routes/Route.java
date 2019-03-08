package fr.univ.amu.sin4u05.igl.routes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A route from one station to another station, as computed by {@link RouteSearch}.
 */
@SuppressWarnings("WeakerAccess")
public class Route {

    private final List<RouteStep> steps;

    /**
     * Constructs a new {@code Route} with the specified list of route steps.
     *
     * @param steps the list of route steps.
     */
    public Route(List<RouteStep> steps) {
        assert !steps.isEmpty();
        this.steps = Collections.unmodifiableList(steps);
    }

    /**
     * Returns the departure stop of the route.
     *
     * @return the departure stop of the route.
     */
    public StationStop getDepartureStop() {
        return steps.get(0).getDepartureStop();
    }

    /**
     * Returns the arrival stop of the route.
     *
     * @return the arrival stop of the route.
     */
    public StationStop getArrivalStop() {
        return steps.get(steps.size() - 1).getArrivalStop();
    }

    /**
     * Returns the departure time of the route.
     *
     * @return the departure time of the route.
     */
    public LocalDateTime getDepartureTime() {
        return steps.get(0).getDepartureTime();
    }

    /**
     * Returns the arrival time of this route.
     *
     * @return the arrival time of the route.
     */
    public LocalDateTime getArrivalTime() {
        return steps.get(steps.size() - 1).getArrivalTime();
    }

    /**
     * Returns the list of steps of the route.
     *
     * @return the list of steps of the route.
     */
    public List<RouteStep> getSteps() {
        return steps;
    }

    /**
     * Returns the duration of this route.
     *
     * @return the duration of the route.
     */
    public Duration getDuration() {
        return Duration.between(getDepartureTime(), getArrivalTime());
    }

    /**
     * Computes a compressed view of the route. It is computed by collapsing steps that of the same types and that use
     * the same transport lines.
     *
     * @return the compressed view of the route.
     */
    public Route compressRoute() {
        List<RouteStep> compressedSteps = new ArrayList<>();

        RouteStep lastStep = null;
        for (RouteStep step : steps) {
            if (lastStep != null && lastStep.canMerge(step)) {
                lastStep = lastStep.merge(step);
            } else {
                if (lastStep != null) compressedSteps.add(lastStep);
                lastStep = step;
            }
        }
        if (lastStep != null) compressedSteps.add(lastStep);

        return new Route(compressedSteps);
    }

    /**
     * Returns a string representation of this {@code Route} object.
     *
     * @return a {@code String} representation of this object.
     */
    @Override
    public String toString() {
        return steps.stream().reduce("", (acc, step) -> acc + step.toString() + "\n", (s1, s2) -> s1 + s2);
    }
}
