class Vehicle {
    protected final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
        System.out.println("Vehicle constructor: " + regNo);
    }

    public String getRegNo() {
        return regNo;
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
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
        System.out.println("TwoWheeler constructor");
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
        System.out.println("Car constructor");
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

class ElectricCar extends Car {
    ElectricCar(String regNo) {
        super(regNo);
        System.out.println("ElectricCar constructor");
    }

    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("Creating a TwoWheeler:");
        Vehicle v1 = new TwoWheeler("TN01A1");
        System.out.println("Registration: " + v1.getRegNo());
        System.out.println("Parking fee for 90 minutes: Rs "
                + v1.calculateFee(90));

        System.out.println();

        System.out.println("Creating an ElectricCar:");
        ElectricCar ev = new ElectricCar("TN03C3");
        System.out.println("Registration: " + ev.getRegNo());
        System.out.println("Parking fee for 90 minutes: Rs "
                + ev.calculateFee(90));
        System.out.println("Charging fee for 10 kWh: Rs "
                + ev.chargingFee(10));
    }
}
