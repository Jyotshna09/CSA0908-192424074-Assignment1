# TASK 4 – Design Classes, Objects and Constructors

## 1. Introduction

Classes define the properties and behaviours of objects in the Campus Smart-Parking System. Objects represent individual vehicles, parking tickets, parking slots and the parking facility.

The system uses constructors to initialise objects with valid data. The `this` keyword refers to the current object, while constructor chaining avoids repeating initialisation logic.

## 2. Class Design

| Class | Fields | Responsibilities |
|---|---|---|
| `Vehicle` | Registration number, entry time | Stores common vehicle information. |
| `TwoWheeler` | Inherits vehicle fields | Represents a two-wheeler and its fee rules. |
| `Car` | Inherits vehicle fields | Represents a car and its fee rules. |
| `ElectricCar` | Charging-related information | Represents an electric car that can use charging facilities. |
| `ParkingSlot` | Floor number, slot number, occupancy | Tracks whether a slot is available. |
| `ParkingTicket` | Ticket number, vehicle, entry time | Records a vehicle's parking session. |
| `ParkingFacility` | Floors and slots | Allocates slots and releases them when vehicles leave. |

## 3. Types of Constructors

### A. Default constructor

A default constructor is supplied by Java only when no constructor is declared in the class. It initialises fields to their default values.

Example:

```java
class ParkingSlot {
    int slotNumber;
    boolean occupied;
}
```

The compiler provides a no-argument constructor for this class.

### B. Parameterised constructor

A parameterised constructor receives values when an object is created.

```java
class ParkingSlot {
    private final int slotNumber;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
```

Usage:

```java
ParkingSlot slot = new ParkingSlot(10);
```

The value `10` is stored as the slot number.

### C. Overloaded constructors and constructor chaining

A class can contain multiple constructors with different parameter lists.

```java
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
}
```

The first constructor calls the second using `this(...)`. This is called constructor chaining and avoids repeating initialisation code. The call to `this(...)` must be the first statement in the constructor.

## 4. Use of the `this` Keyword

The `this` keyword refers to the current object. It distinguishes an instance field from a parameter with the same name.

```java
this.slotNumber = slotNumber;
```

The left-hand `slotNumber` refers to the field of the current object, while the right-hand `slotNumber` refers to the constructor parameter.

## 5. Copy Constructor

A copy constructor creates a new object using the information in an existing object.

```java
class ParkingTicket {
    private final int ticketNumber;
    private final String vehicleNumber;

    public ParkingTicket(int ticketNumber, String vehicleNumber) {
        this.ticketNumber = ticketNumber;
        this.vehicleNumber = vehicleNumber;
    }

    public ParkingTicket(ParkingTicket other) {
        this(other.ticketNumber, other.vehicleNumber);
    }
}
```

Usage:

```java
ParkingTicket first = new ParkingTicket(101, "TN01AB1234");
ParkingTicket copy = new ParkingTicket(first);
```

The copied ticket contains the same values, but it is a separate object. In a real parking system, a duplicate copy should not be treated as a new valid ticket with the same unique ticket number. A copy constructor is useful for copying data, but ticket issuance must still guarantee unique ticket numbers.

## 6. Object Creation and Memory

When an object is created using `new`, Java allocates memory for the object on the heap. A local reference variable typically resides in a stack frame and refers to that object.

Example:

```java
ParkingSlot slot = new ParkingSlot(10);
```

Here, `slot` is a reference variable, and the `ParkingSlot` object is created on the heap.

When an object is no longer reachable through any live reference, it becomes eligible for garbage collection. Garbage collection timing is controlled by the Java Virtual Machine (JVM).

## 7. Alternative Concept and Complexity Analysis

An alternative is to create an object using a no-argument constructor and then call setter methods.

| Approach | Advantages | Limitations |
|---|---|---|
| Parameterised constructor | Initialises required fields immediately | Requires the correct arguments when creating an object |
| Default constructor followed by setters | Allows fields to be set separately | The object may temporarily contain incomplete or invalid data |
| Static factory method | Can use meaningful names and perform validation before returning an object | Requires additional factory methods |

For creating `n` tickets, ordinary field initialisation is generally `O(1)` per ticket and `O(n)` total for `n` tickets, assuming a fixed number of fields. The resulting objects require `O(n)` space. Validation adds a cost depending on the checks performed.

Constructor-based validation is preferable for required fields because it prevents the creation of an incompletely initialised ticket.

## 8. Conclusion

Constructors initialise objects and help maintain valid system data. Parameterised and overloaded constructors support different creation requirements, `this` enables constructor chaining, and copy constructors can duplicate object data when appropriate. Understanding references, heap allocation and garbage collection explains how Java manages the lifecycle of parking objects.