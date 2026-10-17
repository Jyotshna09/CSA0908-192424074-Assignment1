
class ParkingFacility {
    private static int nextTicketNumber = 1000;
    private final String facilityName;

    public ParkingFacility(String facilityName) {
        this.facilityName = facilityName;
    }

    public int issueTicket() {
        return nextTicketNumber++;
    }

    public static int getNextTicketNumber() {
        return nextTicketNumber;
    }

    public String getFacilityName() {
        return facilityName;
    }
}

public class StaticDemo {
    public static void main(String[] args) {
        ParkingFacility north = new ParkingFacility("North Gate");
        ParkingFacility south = new ParkingFacility("South Gate");

        System.out.println("Facility 1: " + north.getFacilityName());
        System.out.println("Ticket issued: " + north.issueTicket());

        System.out.println("Facility 2: " + south.getFacilityName());
        System.out.println("Ticket issued: " + south.issueTicket());

        System.out.println("Next ticket number: "
                + ParkingFacility.getNextTicketNumber());
    }
}
