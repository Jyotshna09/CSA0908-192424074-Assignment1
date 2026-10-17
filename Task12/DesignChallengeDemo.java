import java.time.LocalTime;

interface PricingRule {
    double calculateFee(Vehicle vehicle, int minutes, LocalTime entry, LocalTime exit);
}

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

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    public double hourlyRate() {
        return 30.0;
    }
}

class EBike extends Vehicle {
    EBike(String regNo) {
        super(regNo);
    }

    public double hourlyRate() {
        return 0.0;
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) {
        super(regNo);
    }

    public double hourlyRate() {
        return 30.0;
    }
}

class StandardPricingRule implements PricingRule {
    public double calculateFee(Vehicle vehicle, int minutes, LocalTime entry, LocalTime exit) {
        if (minutes <= 0) {
            return 0.0;
        }
        int hours = (minutes + 59) / 60;
        return hours * vehicle.hourlyRate();
    }
}

class EBikePricingRule implements PricingRule {
    private final double flatFee;

    EBikePricingRule(double flatFee) {
        this.flatFee = flatFee;
    }

    public double calculateFee(Vehicle vehicle, int minutes, LocalTime entry, LocalTime exit) {
        return flatFee;
    }
}

class DeliveryVanPricingRule implements PricingRule {
    public double calculateFee(Vehicle vehicle, int minutes, LocalTime entry, LocalTime exit) {
        if (minutes <= 0) {
            return 0.0;
        }
        int hours = (minutes + 59) / 60;
        boolean daytime = !entry.isBefore(LocalTime.of(9, 0))
                && exit.compareTo(LocalTime.of(17, 0)) <= 0;
        double rate = daytime ? vehicle.hourlyRate() * 2 : vehicle.hourlyRate();
        return hours * rate;
    }
}

class VisitorPass {
    private final String visitorId;
    private final LocalTime validUntil;

    VisitorPass(String visitorId, LocalTime validUntil) {
        this.visitorId = visitorId;
        this.validUntil = validUntil;
    }

    public boolean isValidAt(LocalTime time) {
        return !time.isAfter(validUntil);
    }

    public String getVisitorId() {
        return visitorId;
    }
}

class ParkingFacility {
    private final int[][] slots;

    ParkingFacility(int[] slotsPerFloor) {
        slots = new int[slotsPerFloor.length][];
        for (int i = 0; i < slotsPerFloor.length; i++) {
            if (slotsPerFloor[i] <= 0) {
                throw new IllegalArgumentException("Each floor needs at least one slot");
            }
            slots[i] = new int[slotsPerFloor[i]];
        }
    }

    public int getFloorCount() {
        return slots.length;
    }

    public int getSlotCount(int floor) {
        return slots[floor].length;
    }
}

public class DesignChallengeDemo {
    public static void main(String[] args) {
        LocalTime entry = LocalTime.of(10, 0);
        LocalTime exit = LocalTime.of(12, 0);

        Vehicle car = new Car("TN01A1");
        Vehicle bike = new EBike("TN02B2");
        Vehicle van = new DeliveryVan("TN03C3");

        PricingRule standard = new StandardPricingRule();
        PricingRule ebikeRule = new EBikePricingRule(25.0);
        PricingRule vanRule = new DeliveryVanPricingRule();

        System.out.println("Car fee: Rs "
                + standard.calculateFee(car, 120, entry, exit));
        System.out.println("E-bike flat fee: Rs "
                + ebikeRule.calculateFee(bike, 120, entry, exit));
        System.out.println("Delivery van daytime fee: Rs "
                + vanRule.calculateFee(van, 120, entry, exit));

        VisitorPass pass = new VisitorPass("VISITOR01", LocalTime.of(23, 59));
        System.out.println("Visitor pass valid: " + pass.isValidAt(exit));

        ParkingFacility facility = new ParkingFacility(new int[]{10, 12, 8, 15, 9});
        System.out.println("Number of floors: " + facility.getFloorCount());
        System.out.println("Slots on floor 2: " + facility.getSlotCount(1));
    }
}
