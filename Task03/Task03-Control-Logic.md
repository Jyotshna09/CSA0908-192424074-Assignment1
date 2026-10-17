# TASK 3 – Design the Control Logic

## 1. Introduction

The control logic of the Campus Smart-Parking System manages two main operations:

1. Allocating the first available parking slot to a vehicle.
2. Releasing an occupied slot when a vehicle leaves.

The system uses nested loops to search floors and slots, conditional statements to validate positions, and `return` to stop processing when the required result is found.

## 2. Pseudocode for Slot Allocation

**Algorithm: ALLOCATE-SLOT**

**Input:** Vehicle registration number

**Output:** Floor and slot indexes of the allocated slot, or `null` if the facility is full

1. BEGIN
2. FOR each floor `f` from `0` to the number of floors minus one
3. &nbsp;&nbsp;&nbsp;&nbsp;FOR each slot `s` on floor `f`
4. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IF `slots[f][s]` is `null`
5. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Store the vehicle number in `slots[f][s]`
6. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RETURN `{f, s}`
7. &nbsp;&nbsp;&nbsp;&nbsp;END FOR
8. END FOR
9. RETURN `null`
10. END

The algorithm searches floor by floor and slot by slot. Once a free slot is found, it stores the registration number and immediately returns its location. If every slot is occupied, it returns `null`.

## 3. Pseudocode for Slot Release

**Algorithm: RELEASE-SLOT**

**Input:** Floor index `f` and slot index `s`

**Output:** `true` if the slot is released; otherwise `false`

1. BEGIN
2. IF `f` is less than `0` OR `f` is greater than or equal to the number of floors
3. &nbsp;&nbsp;&nbsp;&nbsp;RETURN `false`
4. END IF
5. IF `s` is less than `0` OR `s` is greater than or equal to the number of slots on floor `f`
6. &nbsp;&nbsp;&nbsp;&nbsp;RETURN `false`
7. END IF
8. IF `slots[f][s]` is `null`
9. &nbsp;&nbsp;&nbsp;&nbsp;RETURN `false`
10. END IF
11. Set `slots[f][s]` to `null`
12. RETURN `true`
13. END

This algorithm validates the floor and slot indexes before accessing the array. It also prevents releasing a slot that is already free.

## 4. Pseudocode for Parking Fee Calculation

**Algorithm: CALCULATE-FEE**

**Input:** Parking duration in minutes and applicable vehicle rate

**Output:** Parking fee

1. BEGIN
2. IF duration is less than or equal to `0`
3. &nbsp;&nbsp;&nbsp;&nbsp;RETURN `0`
4. END IF
5. Convert duration into hours according to the selected billing rule.
6. Calculate the fee using the applicable vehicle rate and fee slabs.
7. IF the calculated fee exceeds the daily maximum
8. &nbsp;&nbsp;&nbsp;&nbsp;Set fee to the daily maximum.
9. END IF
10. RETURN fee
11. END

**Assumption:** For this pseudocode, the billing policy must define the first-hour charge, additional-hour charge, daily maximum and rounding rule. The exact rates are not specified in the assignment, so they must be configured before implementation.

## 5. Java Implementation

Create a separate Java program in Task 3 that completes the given `ParkingFacility` class. The required operations are:

- `allocate(String vehicleNo)`: finds and occupies the first free slot.
- `release(int floor, int slot)`: validates the indexes and frees the slot.

The implementation must preserve the `main()` method supplied in the assignment.

## 6. Use of Control Statements

- **`for` loops:** Search each floor and each slot.
- **`if` statements:** Check whether a slot is free and whether an index is valid.
- **`return`:** Stop searching after allocation or return a failure result.
- **`break`:** Can stop a loop, although returning directly is simpler when the result is already known.
- **`continue`:** Can skip occupied slots, although an `if` condition is sufficient for this implementation.

## 7. Alternative Concept and Complexity Analysis

An alternative is to maintain a collection of free slots for each floor. For example, a stack can store the available slot indexes.

| Operation | Nested-loop approach | Free-slot stack approach |
|---|---|---|
| Allocation | `O(N)` in the worst case | `O(1)` amortized for a stack with a known non-empty floor |
| Release | `O(1)` after validation | `O(1)` to add the slot back to a stack |
| Extra management space | `O(1)` beyond the slot array | Up to `O(N)` for the free-slot collections |
| Complexity | Simple to implement | Faster allocation, but requires careful stack maintenance |

Here, `N` represents the total number of parking slots. If free slots are stored in a separate stack for each floor, the system must also track which floors have available slots.

## 8. Conclusion

Nested loops and conditional statements provide a simple and reliable way to allocate and release parking slots. Index validation prevents invalid array access, while `return` avoids unnecessary searching. A free-slot stack can improve allocation performance but requires additional storage and maintenance.