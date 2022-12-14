package airport;

import java.util.*;

public abstract class Flight {

    private String id;

//    List<Passenger> passengers = new ArrayList<Passenger>();
    Set<Passenger> passengers = new HashSet<>();
    public Flight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
//    public List<Passenger> getPassengers() {
//        return Collections.unmodifiableList(passengers);
//    }
    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }

    public abstract boolean addPassenger(Passenger passenger);

    public abstract boolean removePassenger(Passenger passenger);

}
