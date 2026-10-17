
class Vehicle {
    protected final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo.trim().toUpperCase();
    }

    protected double ratePerHour() {
        return 0;
    }

    public double calculateFee(int minutes) {
        if (minutes <= 0) {
            return 0;
        }

        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }

    @Override
    public String toString() {
        return "Vehicle{regNo='" + regNo + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) o;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

public class OverridingDemo {
    public static void main(String[] args) {
        Vehicle bike = new TwoWheeler("tn01a1");
        Vehicle car = new Car("TN02B2");
        Vehicle anotherCar = new Car("TN02B2");

        System.out.println("Bike details: " + bike);
        System.out.println("Car details: " + car);
        System.out.println("Bike fee for 90 minutes: Rs "
                + bike.calculateFee(90));
        System.out.println("Car fee for 90 minutes: Rs "
                + car.calculateFee(90));
        System.out.println("Are the two car objects equal? "
                + car.equals(anotherCar));
        System.out.println("Do the two car objects have the same hash code? "
                + (car.hashCode() == anotherCar.hashCode()));
    }
}
