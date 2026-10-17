
class ParkingSlot {
    private int slotNumber;
    private boolean occupied;

    public ParkingSlot(int slotNumber) {
        setSlotNumber(slotNumber);
        this.occupied = false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        if (slotNumber <= 0) {
            throw new IllegalArgumentException("Slot number must be positive");
        }
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void parkVehicle() {
        if (occupied) {
            throw new IllegalStateException("Slot is already occupied");
        }
        occupied = true;
    }

    public void releaseSlot() {
        occupied = false;
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(5);

        System.out.println("Slot number: " + slot.getSlotNumber());
        System.out.println("Initially occupied: " + slot.isOccupied());

        slot.parkVehicle();
        System.out.println("After parking: " + slot.isOccupied());

        slot.releaseSlot();
        System.out.println("After releasing: " + slot.isOccupied());

        try {
            slot.setSlotNumber(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }
    }
}
