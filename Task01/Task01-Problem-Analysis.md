
# Task 1 – Understand the Problem and Identify the Objects

## 1. Problem Understanding

The Campus Smart-Parking System is designed to manage a multi-level parking facility used by students, staff and visitors. The facility contains multiple floors, each having a fixed number of parking slots. It supports two-wheelers, cars and electric vehicles (EVs). Electric vehicles may also use charging slots, for which separate charging fees are applicable.

When a vehicle enters the facility, the system must allocate a suitable free parking slot, issue a unique parking ticket and record the entry time. When the vehicle leaves, the system must record the exit time, calculate the parking fee and release the occupied slot.

Students and staff holding valid monthly passes can park free of charge, while visitors must pay the applicable fee. The system should maintain correct parking records, prevent invalid operations and allow new vehicle categories and pricing rules to be introduced without rewriting the existing classes.

The objective is to analyse the parking problem, identify the objects required and design an object-oriented solution using Java.

## 2. Inputs and Outputs

### 2.1 Inputs

The system receives the following inputs:

1. Vehicle registration number.
2. Vehicle category, such as two-wheeler, car or electric vehicle.
3. User category, such as student, staff or visitor.
4. Entry time and exit time.
5. Number of floors and number of slots on each floor.
6. Parking rates for different vehicle categories.
7. Monthly pass details and validity.
8. Charging information, when applicable.
9. Floor and slot details when a specific slot operation is performed.

### 2.2 Outputs

The system produces the following outputs:

1. Allocated floor number and parking slot number.
2. Unique parking ticket number.
3. Recorded entry and exit times.
4. Total parking duration.
5. Calculated parking fee.
6. Charging fee, when applicable.
7. Updated slot availability and occupancy status.
8. Confirmation when a slot is successfully allocated or released.
9. Error messages when no suitable slot is available or an invalid operation is attempted.

## 3. Candidate Classes and Their Responsibilities

The nouns in the problem statement help identify candidate classes. Each class represents an entity or responsibility in the parking system.

| Candidate class | Responsibilities | Justification |
|---|---|---|
| Vehicle | Stores common vehicle information, including registration number. | A vehicle has its own identity and common properties. |
| TwoWheeler | Represents a two-wheeler and provides its applicable parking rate. | A two-wheeler may have a different parking rate from a car. |
| Car | Represents a car and provides its applicable parking rate. | A car has category-specific parking behaviour. |
| ElectricCar | Represents an electric car and supports charging-fee calculation. | An electric car may use charging facilities and incur additional charges. |
| ParkingSlot | Stores the floor number, slot number and occupancy information. | Each slot must have a defined location and availability status. |
| ParkingTicket | Stores the ticket number, vehicle details, entry time and exit details. | A ticket records an individual parking session. |
| ParkingFacility | Manages floors, slots, vehicle allocation and slot release. | The facility coordinates parking operations across multiple floors. |
| ParkingUser | Represents a student, staff member or visitor. | User categories determine eligibility for parking benefits. |
| ParkingPass | Stores pass details and validity information. | The system must verify eligibility for free parking. |
| PricingRule | Defines how parking fees are calculated. | Separating pricing rules makes future changes easier to manage. |

These candidate classes separate the responsibilities of vehicles, parking slots, tickets, users, passes and pricing. Their exact fields and methods will be refined during the design and implementation tasks.

### 3.1 Identification of Nouns and Verbs

Nouns in the problem statement help identify candidate classes and data fields. Verbs identify operations that can become methods.

**Candidate nouns:**
- Vehicle
- Parking slot
- Parking facility
- Parking ticket
- Parking user
- Parking pass
- Floor
- Parking fee

**Candidate verbs:**
- Allocate
- Release
- Issue
- Record
- Calculate
- Search
- Validate
- Park

For example, the noun `Vehicle` becomes a class, while the verb `allocate` can become a method such as `allocateSlot()`.

## 4. Candidate Methods

The following methods represent the main operations of the system.

| Method | Responsibility |
|---|---|
| `allocateSlot()` | Searches for and assigns a suitable free slot. |
| `releaseSlot()` | Releases an occupied slot when a vehicle leaves. |
| `issueTicket()` | Generates and issues a unique parking ticket. |
| `calculateFee()` | Calculates the parking fee according to the applicable rules. |
| `calculateChargingFee()` | Calculates the charging fee for an eligible electric vehicle. |
| `isPassValid()` | Checks whether a parking pass is valid. |
| `findVehicle()` | Searches for a vehicle using its registration number. |
| `recordExit()` | Records the exit time and completes the parking session. |

These methods divide the main operations into manageable units and make the design easier to understand and maintain.

## 5. Business Rules

The system must satisfy the following business rules:

1. A parking slot must not contain more than one vehicle at a time.
2. A vehicle must be allocated only to a suitable and available slot.
3. Every parking ticket must have a unique ticket number that is never reused.
4. An occupied slot must be released when the vehicle leaves.
5. Invalid floor numbers and slot numbers must be rejected.
6. A vehicle must not have multiple active parking sessions for the same registration number.
7. Students and staff with valid monthly passes must receive the specified free-parking benefit.
8. Visitors without an applicable pass must pay the required parking fee.
9. Electric-vehicle charging fees must be calculated separately when applicable.
10. Parking duration and parking fees must follow the configured billing rules.
11. If no suitable slot is available, the system must report that allocation has failed.
12. Invalid input values must be rejected to maintain correct parking records.

These rules ensure that parking operations remain consistent and reliable.

## 6. Assumptions

The following assumptions are made for the initial design:

1. Every vehicle has a valid registration number.
2. Each vehicle is identified by its registration number.
3. Each floor may contain a different number of parking slots.
4. Entry and exit times are recorded using a consistent date-time representation.
5. Parking rates and billing rules are configured by the facility administrator.
6. A parking slot is free when it is not assigned to an active parking session.
7. Ticket numbers are generated using a mechanism that prevents reuse.
8. Monthly passes contain validity information that can be checked by the system.
9. Charging fees apply when an electric vehicle uses the applicable charging service.
10. The initial design represents a single parking facility; support for multiple facilities can be added if required.

These assumptions define the initial scope of the system and can be revised if the requirements change.

## 7. Alternative Concept: Parallel Arrays Instead of Objects

In the object-oriented design, each vehicle is represented by a `Vehicle` object containing its related data and behaviour.

An alternative design stores vehicle information in parallel arrays such as:

- `regNo[]` – stores vehicle registration numbers.
- `type[]` – stores vehicle categories.
- `entryTime[]` – stores vehicle entry times.

In parallel arrays, the information at the same index belongs to the same vehicle. For example, `regNo[0]`, `type[0]` and `entryTime[0]` describe the first vehicle.

### 7.1 Comparison of the Two Designs

| Aspect | Vehicle objects | Parallel arrays |
|---|---|---|
| Data organization | Related data is grouped inside each vehicle object. | Related data is stored in separate arrays. |
| Finding a vehicle | Linear search takes O(n) time without an additional index. | Searching `regNo[]` takes O(n) time. |
| Adding a new attribute | Add a field and the required behaviour to the class. | Create another array and maintain matching indices. |
| Data consistency | Related information is grouped within an object. | All arrays must remain aligned for each vehicle. |
| Maintainability | Easier to extend with vehicle-specific methods and behaviour. | Becomes harder to maintain as attributes increase. |
| Storage | O(n) total space for n vehicles, excluding implementation overhead. | O(n) total space for a fixed number of parallel arrays. |

### 7.2 Time Complexity of Searching

Let `n` represent the number of vehicles stored in the system.

**Vehicle objects:**

To find a vehicle by registration number, the system checks each vehicle object's registration number until a match is found.

- Best-case time complexity: O(1), when the first vehicle matches.
- Worst-case time complexity: O(n), when the vehicle is at the end or is not present.

**Parallel arrays:**

The system searches the `regNo[]` array sequentially.

- Best-case time complexity: O(1), when the first entry matches.
- Worst-case time complexity: O(n), when the vehicle is at the end or is not present.

Therefore, both approaches have O(n) worst-case search time when no additional search index is maintained.

### 7.3 Space Complexity and Adding Attributes

In the object-oriented design, each vehicle requires an object containing its attributes. The object and its stored reference also have memory overhead, depending on the Java implementation.

In the parallel-array design, each vehicle requires one entry in every array. With three attributes, three corresponding array entries must be maintained for each vehicle.

For a fixed number of attributes and n vehicles, both approaches require O(n) total space. Exact memory usage depends on the Java implementation and the types of the stored values.

When a new attribute is introduced, the object-oriented design generally requires modifying the vehicle class and its relevant methods. The parallel-array design requires creating another array and ensuring that its entries remain aligned with the existing arrays.

The object-oriented approach is therefore easier to extend and maintain as the system grows.

## Conclusion

The Campus Smart-Parking System can be modelled using classes representing vehicles, parking slots, tickets, users, passes and pricing rules. Identifying their responsibilities helps separate the system's operations and supports future extensions.

Both vehicle objects and parallel arrays have O(n) worst-case search time and O(n) total space for a fixed number of vehicle attributes. However, the object-oriented approach groups related data and behaviour, improves maintainability and makes it easier to introduce new vehicle categories and pricing rules.
