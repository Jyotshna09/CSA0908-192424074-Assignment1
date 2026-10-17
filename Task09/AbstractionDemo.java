interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double calculateFee(int minutes);
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee(int minutes) {
        if (minutes <= 0) {
            return 0;
        }
        return ((minutes + 59) / 60) * 10.0;
    }
}

class ElectricCar extends Vehicle implements Chargeable {
    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee(int minutes) {
        if (minutes <= 0) {
            return 0;
        }
        return ((minutes + 59) / 60) * 30.0;
    }

    @Override
    public double chargingFee(int kWh) {
        if (kWh < 0) {
            return 0;
        }
        return kWh * 12.0;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle bike = new TwoWheeler("TN01A1");
        Vehicle ev = new ElectricCar("TN03C3");

        System.out.println("Vehicle registration: " + bike.getRegNo());
        System.out.println("Two-wheeler parking fee: Rs " + bike.calculateFee(90));

        System.out.println("Vehicle registration: " + ev.getRegNo());
        System.out.println("Electric car parking fee: Rs " + ev.calculateFee(90));

        Chargeable chargeable = (Chargeable) ev;
        System.out.println("Electric car charging fee: Rs " + chargeable.chargingFee(10));
    }
}
