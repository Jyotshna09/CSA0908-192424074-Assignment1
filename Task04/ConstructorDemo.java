class ParkingTicket {
    private final int ticketNumber;
    private final String vehicleNumber;

    public ParkingTicket(int ticketNumber) {
        this(ticketNumber, "UNKNOWN");
    }

    public ParkingTicket(int ticketNumber, String vehicleNumber) {
        this.ticketNumber = ticketNumber;
        this.vehicleNumber = vehicleNumber;
    }

    public ParkingTicket(ParkingTicket other) {
        this(other.ticketNumber, other.vehicleNumber);
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String toString() {
        return "Ticket Number: " + ticketNumber
                + ", Vehicle Number: " + vehicleNumber;
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        ParkingTicket ticket1 = new ParkingTicket(101);
        ParkingTicket ticket2 = new ParkingTicket(102, "TN01AB1234");
        ParkingTicket ticket3 = new ParkingTicket(ticket2);

        System.out.println("Default-style constructor: " + ticket1);
        System.out.println("Parameterized constructor: " + ticket2);
        System.out.println("Copy constructor: " + ticket3);
        System.out.println("Are ticket2 and ticket3 the same object? "
                + (ticket2 == ticket3));
    }
}
