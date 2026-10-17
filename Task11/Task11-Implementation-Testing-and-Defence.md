# Task 11 – Implement, Test and Defend the Design

## 1. Objective

To implement and test the Campus Smart-Parking System, verify normal and boundary cases, identify invalid operations, and evaluate the object-oriented design.

## 2. Test Cases

| Test Case | Expected Result |
|---|---|
| Two-wheeler enters and leaves | Slot allocated, fee calculated, slot released |
| Car enters and leaves | Correct parking fee calculated |
| Electric vehicle uses charging | Parking and charging fees calculated separately |
| Last free slot is used | Vehicle is accepted |
| No free slots remain | New vehicle is rejected |
| Zero-minute parking | Parking fee is zero |
| Stay exactly at a fee-slab boundary | Fee follows the defined boundary rule |
| Unknown vehicle type | Input rejected with an appropriate message |
| Duplicate registration number | Duplicate entry rejected |
| Invalid slot number | Invalid slot rejected |

The actual results must be recorded after running the implementation.

## 3. Daily Collection Calculation

Assume the following illustrative fee rules: two-wheelers cost Rs 10 per started hour, cars cost Rs 30 per started hour, and electric cars have the same parking rate as cars. Charging costs Rs 12 per kWh.

| Vehicle | Duration | Charging | Fee |
|---|---:|---:|---:|
| Two-wheeler | 90 minutes | None | Rs 20 |
| Car | 120 minutes | None | Rs 60 |
| Electric car | 60 minutes | 10 kWh | Rs 150 |
| **Total** | | | **Rs 230** |

Hand calculation: Rs 20 + Rs 60 + (Rs 30 + Rs 120) = **Rs 230**.

The program's total collection must be compared with this hand-calculated value using the same fee rules.

## 4. Object-Oriented Design Evaluation

- **Encapsulation:** Private fields and controlled methods protect vehicle and ticket data.
- **Inheritance:** Specific vehicle classes reuse common vehicle properties and behaviour.
- **Polymorphism:** A common `Vehicle` reference can invoke the correct fee-calculation method for different vehicle types.
- **Abstraction:** Abstract classes and interfaces separate common behaviour from specialised implementations.

One limitation is that a simple array-based ticket store requires a linear search to find a ticket during exit processing.

## 5. Alternative Data Structures and Complexity

Let `n` be the number of active parking tickets.

| Data Structure | Ticket Search | Space |
|---|---|---|
| Array | O(n) | O(n) |
| `ArrayList<ParkingTicket>` | O(n) | O(n) |
| `HashMap<String, ParkingTicket>` | Average O(1) lookup by registration number | O(n) |

A `HashMap` can improve average ticket lookup time when exiting vehicles. However, it requires additional map storage and careful handling of duplicate registration numbers.

## 6. Conclusion

The system should be evaluated using actual execution results, including successful operations, boundary cases, invalid inputs and the daily fee total. The results help determine whether the design is correct, maintainable and suitable for future extensions.