
import java.util.Arrays;

public class ParkingFacility {
    private final String[][] slots;

    public ParkingFacility(int[] slotsPerFloor) {
        slots = new String[slotsPerFloor.length][];
        for (int f = 0; f < slotsPerFloor.length; f++) {
            slots[f] = new String[slotsPerFloor[f]];
        }
    }

    public int[] allocate(String vehicleNo) {
        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicleNo;
                    return new int[]{f, s};
                }
            }
        }
        return null;
    }

    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) {
            return false;
        }

        if (slot < 0 || slot >= slots[floor].length) {
            return false;
        }

        if (slots[floor][slot] == null) {
            return false;
        }

        slots[floor][slot] = null;
        return true;
    }

    public static void main(String[] args) {
        ParkingFacility p = new ParkingFacility(new int[]{2, 1});

        System.out.println("Vehicle 1 allocated at [floor, slot]: "
                + Arrays.toString(p.allocate("TN01AB1111")));

        System.out.println("Vehicle 2 allocated at [floor, slot]: "
                + Arrays.toString(p.allocate("TN02CD2222")));

        System.out.println("Vehicle 3 allocated at [floor, slot]: "
                + Arrays.toString(p.allocate("TN03EF3333")));

        System.out.println("Vehicle 4 allocation when parking is full: "
                + p.allocate("TN04GH4444"));

        System.out.println("Release slot (0, 1): " + p.release(0, 1));
        System.out.println("Release same slot again: " + p.release(0, 1));
        System.out.println("Release invalid floor (5, 0): " + p.release(5, 0));

        System.out.println("Vehicle 4 allocated after a slot is freed: "
                + Arrays.toString(p.allocate("TN04GH4444")));
    }
}
