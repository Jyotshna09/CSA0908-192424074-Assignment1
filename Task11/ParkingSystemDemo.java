
import java.util.*;

abstract class Vehicle {
    String regNo;
    Vehicle(String r) {
        if (r == null || r.trim().isEmpty())
            throw new IllegalArgumentException("Invalid registration");
        regNo = r;
    }
    abstract double rate();
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String r) { super(r); }
    double rate() { return 10; }
}

class Car extends Vehicle {
    Car(String r) { super(r); }
    double rate() { return 30; }
}

class ElectricCar extends Car {
    ElectricCar(String r) { super(r); }
    double chargingFee(int units) {
        if (units < 0) throw new IllegalArgumentException("Invalid units");
        return units * 12;
    }
}

class ParkingTicket {
    Vehicle v;
    int slot, minutes, units;

    ParkingTicket(Vehicle v, int s, int m, int u) {
        if (v == null || s <= 0 || m < 0 || u < 0)
            throw new IllegalArgumentException("Invalid ticket details");
        if (!(v instanceof ElectricCar) && u != 0)
            throw new IllegalArgumentException("Invalid charging units");
        this.v = v;
        slot = s;
        minutes = m;
        units = u;
    }

    double fee() {
        int hours = (minutes + 59) / 60;
        double total = hours * v.rate();
        if (v instanceof ElectricCar)
            total += ((ElectricCar) v).chargingFee(units);
        return total;
    }
}

class ParkingService {
    Map<String, ParkingTicket> tickets = new HashMap<>();
    int capacity;
    double collection;

    ParkingService(int c) {
        if (c <= 0) throw new IllegalArgumentException("Invalid capacity");
        capacity = c;
    }

    void enter(Vehicle v, int minutes) {
        enter(v, minutes, 0);
    }

    void enter(Vehicle v, int minutes, int units) {
        if (v == null || minutes < 0 || units < 0)
            throw new IllegalArgumentException("Invalid input");
        if (tickets.containsKey(v.regNo))
            throw new IllegalArgumentException("Duplicate registration");
        if (tickets.size() >= capacity)
            throw new IllegalStateException("Parking is full");

        boolean[] used = new boolean[capacity + 1];
        for (ParkingTicket t : tickets.values()) used[t.slot] = true;

        int s = 1;
        while (s <= capacity && used[s]) s++;

        tickets.put(v.regNo, new ParkingTicket(v, s, minutes, units));
        System.out.println("Entry: " + v.getClass().getSimpleName()
                + " " + v.regNo + " slot " + s);
    }

    void exit(String regNo) {
        ParkingTicket t = tickets.remove(regNo);
        if (t == null) throw new IllegalArgumentException("No active ticket");
        double amount = t.fee();
        collection += amount;
        System.out.println("Exit: " + regNo + ", fee Rs " + amount);
    }
}

public class ParkingSystemDemo {
    public static void main(String[] args) {
        ParkingService p = new ParkingService(2);

        p.enter(new TwoWheeler("TN01A1"), 90);
        p.enter(new Car("TN02B2"), 120);

        try {
            p.enter(new Car("TN02B2"), 60);
        } catch (Exception e) {
            System.out.println("Duplicate test: " + e.getMessage());
        }

        try {
            p.enter(new Car("TN03C3"), 60);
        } catch (Exception e) {
            System.out.println("Full parking test: " + e.getMessage());
        }

        p.exit("TN01A1");
        p.exit("TN02B2");

        System.out.println("Zero-minute fee: Rs "
                + new ParkingTicket(new TwoWheeler("TN04D4"), 1, 0, 0).fee());

        try {
            new ParkingTicket(new Car("TN05E5"), 0, 60, 0);
        } catch (Exception e) {
            System.out.println("Invalid slot test: " + e.getMessage());
        }

        p.enter(new ElectricCar("TN03C3"), 60, 10);
        p.exit("TN03C3");

        System.out.println("Total collection: Rs " + p.collection);
    }
}
