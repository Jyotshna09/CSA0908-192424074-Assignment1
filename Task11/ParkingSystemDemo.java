import java.util.HashMap;
import java.util.Map;

abstract class Vehicle {
    private final String regNo;

    Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid registration number");
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double hourlyRate();
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    public double hourlyRate() {
        return 10.0;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    public double hourlyRate() {
        return 30.0;
    }
}

class ElectricCar extends Car {
    ElectricCar(String regNo) {
        super(regNo);
    }

    public double chargingFee(int kWh) {
        if (kWh < 0) {
            throw new IllegalArgumentException("Invalid charging amount");
        }
        return kWh * 12.0;
    }
}

class ParkingTicket {
    private final Vehicle vehicle;
    private final int slotNumber;
    private final int durationMinutes;

    ParkingTicket(Vehicle vehicle, int slotNumber, int durationMinutes) {
        if (slotNumber <= 0) {
            throw new IllegalArgumentException("Invalid slot number");
        }
        if (durationMinutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        this.vehicle = vehicle;
        this.slotNumber = slotNumber;
        this.durationMinutes = durationMinutes;
    }

    public double calculateFee() {
        if (durationMinutes == 0) {
            return 0;
        }
        int hours = (durationMinutes + 59) / 60;
        return hours * vehicle.hourlyRate();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}

class ParkingService {
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    private final int totalSlots;
    private double totalCollection;

    ParkingService(int totalSlots) {
        if (totalSlots <= 0) {
            throw new IllegalArgumentException("Total slots must be positive");
        }
        this.totalSlots = totalSlots;
    }

    public ParkingTicket enter(Vehicle vehicle, int durationMinutes) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
        if (activeTickets.containsKey(vehicle.getRegNo())) {
            throw new IllegalArgumentException("Duplicate registration number");
        }
        if (durationMinutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (activeTickets.size() >= totalSlots) {
            throw new IllegalStateException("Parking is full");
        }

        boolean[] used = new boolean[totalSlots + 1];
        for (ParkingTicket ticket : activeTickets.values()) {
            used[ticket.getSlotNumber()] = true;
        }

        int slot = 1;
        while (slot <= totalSlots && used[slot]) {
            slot++;
        }

        ParkingTicket ticket = new ParkingTicket(vehicle, slot, durationMinutes);
        activeTickets.put(vehicle.getRegNo(), ticket);
        System.out.println("Entry: " + vehicle.getClass().getSimpleName()
                + " " + vehicle.getRegNo() + " allocated slot " + slot);
        return ticket;
    }

    public double exit(String regNo) {
        ParkingTicket ticket = activeTickets.remove(regNo);
        if (ticket == null) {
            throw new IllegalArgumentException("No active ticket found");
        }
        double fee = ticket.calculateFee();
        totalCollection += fee;
        System.out.println("Exit: " + regNo + ", parking fee Rs " + fee);
        return fee;
    }

    public double getTotalCollection() {
        return totalCollection;
    }
}

public class ParkingSystemDemo {
    public static void main(String[] args) {
        ParkingService service = new ParkingService(2);

        service.enter(new TwoWheeler("TN01A1"), 90);
        service.enter(new Car("TN02B2"), 120);

        try {
            service.enter(new Car("TN02B2"), 60);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate test: " + e.getMessage());
        }

        try {
            service.enter(new Car("TN03C3"), 60);
        } catch (IllegalStateException e) {
            System.out.println("Full parking test: " + e.getMessage());
        }

        service.exit("TN01A1");
        service.exit("TN02B2");

        System.out.println("Zero-minute fee: Rs "
                + new ParkingTicket(new TwoWheeler("TN04D4"), 1, 0).calculateFee());

        try {
            new ParkingTicket(new Car("TN05E5"), 0, 60);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid slot test: " + e.getMessage());
        }

        service.enter(new ElectricCar("TN03C3"), 60);
        service.exit("TN03C3");

        System.out.println("Total collection: Rs " + service.getTotalCollection());
        System.out.println("Expected total: Rs 110.0");
    }
}
