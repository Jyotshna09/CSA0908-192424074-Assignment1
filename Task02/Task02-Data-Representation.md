# TASK 2 – Choose the Data Representation

## 1. Introduction

The Campus Smart-Parking System stores information about parking floors, slots, vehicles, entry time, parking duration and parking fees. Choosing appropriate Java data types and data structures helps the system maintain accuracy, use memory efficiently and handle different numbers of slots on different floors.

## 2. Data Types Selected for Each Field

| Field | Java Data Type | Reason |
|---|---|---|
| Floor number | `int` | Floor numbers are whole numbers. |
| Slot number | `int` | Slot numbers are whole numbers. |
| Vehicle registration number | `String` | Registration numbers contain letters and digits. |
| Entry time | `LocalDateTime` | Stores the date and time when a vehicle enters. |
| Parking duration | `long` | Can represent the total parking duration in minutes. |
| Parking fee | `double` | Supports fees containing decimal values. |
| Slot occupancy | `String` | Stores the vehicle registration number; `null` represents a free slot. |
| Charging units | `double` | Represents electricity consumed in kWh when fractional values are needed. |

For accurate financial calculations in a real application, `BigDecimal` would be preferable to `double` because binary floating-point values can introduce rounding errors.

## 3. Representation of Floors and Slots

I selected a **jagged two-dimensional array**, `String[][] slots`, to represent the parking facility.

Each row represents one floor, and each element in that row represents a parking slot.

Example:

```java
String[][] slots = new String[2][];
slots[0] = new String[3];
slots[1] = new String[2];
```

This creates two floors: the first floor has three slots, and the second floor has two slots.

Initially, the array elements contain `null`, which indicates that the slots are free. When a vehicle enters, its registration number is stored in the allocated slot.

Example:

```java
slots[0][1] = "TN01AB1234";
```

This stores the vehicle registration number in the second slot of the first floor. Java array indexes begin at zero.

**Why a jagged array?**

- Different floors can have different numbers of slots.
- It avoids allocating unused slot positions on every floor.
- It makes allocation and release straightforward using floor and slot indexes.
- It supports future expansion of the parking facility.

## 4. Type Conversion and Precision

Parking duration is recorded in minutes, but billing may be calculated in hours. Therefore, the conversion must avoid losing fractional hours.

For example, suppose a vehicle is parked for 90 minutes and the fee is ₹40 per hour, charged proportionally.

**Incorrect calculation using integer division:**

```java
int minutes = 90;
int ratePerHour = 40;

double fee = (minutes / 60) * ratePerHour;
System.out.println(fee);
```

Output:

```text
40.0
```

This is incorrect because `minutes / 60` performs integer division. It produces `1`, discarding the remaining 30 minutes.

**Correct calculation using floating-point division:**

```java
int minutes = 90;
double ratePerHour = 40.0;

double hours = minutes / 60.0;
double fee = hours * ratePerHour;

System.out.println(fee);
```

Output:

```text
60.0
```

The correct result is ₹60 because 90 minutes equals 1.5 hours, and `1.5 × 40 = 60`.

Using `60.0` ensures floating-point division. The design should also define its rounding policy and whether any part of an hour is rounded up, billed proportionally or covered by a fee slab.

## 5. Input Method

I selected Java's `Scanner` class because it allows the user to enter values interactively.

Example:

```java
import java.util.Scanner;

public class ParkingInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter vehicle number: ");
        String vehicleNo = sc.nextLine();

        System.out.print("Enter parking duration in minutes: ");
        long minutes = sc.nextLong();

        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Duration: " + minutes + " minutes");

        sc.close();
    }
}
```

Command-line arguments are also possible, but `Scanner` is more convenient for interactive input in this assignment. Input validation should reject negative durations and invalid slot numbers.

## 6. Alternative Representation and Complexity Analysis

### A. One-dimensional array

If every floor has the same number of slots, a one-dimensional array can represent all slots.

The index can be calculated as:

`index = floor × slotsPerFloor + slot`

For example, with three slots per floor, floor index `1` and slot index `2` map to index `5`.

However, this formula assumes the same number of slots on every floor. If floors have different slot counts, the formula must be modified using prefix offsets or a mapping of each floor to its starting index.

### B. ArrayList of arrays

An alternative is:

```java
ArrayList<String[]> floors = new ArrayList<>();
floors.add(new String[3]);
floors.add(new String[2]);
```

This supports floors with different slot counts. Each floor can be accessed by its index, and each slot can be accessed within that floor's array.

### C. Complexity comparison

Let:

- `F` = number of floors
- `S` = maximum number of slots on a floor
- `N` = total number of parking slots

| Representation | Locate a slot by known floor and slot | Search all slots for a free slot | Space |
|---|---|---|---|
| Jagged two-dimensional array | `O(1)` | `O(N)` | `O(N)` |
| One-dimensional array with equal floor sizes | `O(1)` using the formula | `O(N)` | `O(N)` |
| `ArrayList<String[]>` | `O(1)` by floor and slot index | `O(N)` | `O(N)` |

For the jagged array, `N` is the sum of the slot counts across all floors. Its total slot storage is proportional to the actual number of slots. The `ArrayList` representation has additional small collection-management overhead.

## 7. Conclusion

The jagged two-dimensional array `String[][]` is suitable for the Campus Smart-Parking System because different floors can contain different numbers of slots. `String` represents registration numbers, integer types represent indexes and durations, `LocalDateTime` records entry time, and a numeric type represents the fee. Floating-point division prevents the loss of fractional hours during proportional billing. The chosen representation is simple, efficient and extendable for a multi-floor parking facility.