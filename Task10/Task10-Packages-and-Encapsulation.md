# Task 10 – Enforce Encapsulation through Packages and Access Modifiers

## 1. Objective

To organise the Campus Smart-Parking System into packages and protect data using access modifiers and encapsulation.

## 2. Package Structure

The project can be organised into the following packages:

- `parking.model` – contains classes such as `Vehicle`, `Car`, `TwoWheeler`, and `ParkingSlot`.
- `parking.service` – contains `ParkingService`, which manages parking operations and slot allocation.
- `parking.app` – contains `ParkingApplication`, the main class used to run the system.

Packages improve organisation, readability and maintainability.

## 3. Access Modifiers

| Modifier | Purpose |
|---|---|
| `private` | Protects fields from direct access outside the class. |
| Default | Allows access within the same package. |
| `protected` | Allows access within the same package and through inheritance, subject to Java's access rules. |
| `public` | Allows access wherever the class or member is accessible. |

Fields such as registration number and parking fee should generally be `private`. Public getters and validated methods can provide controlled access.

## 4. Validation Using Getters and Setters

Setters should validate data before changing an object's state. For example, a parking duration must not be negative, and a slot number must be within the valid range.

Example:

```java
public void setDuration(int duration) {
    if (duration < 0) {
        throw new IllegalArgumentException("Duration cannot be negative");
    }
    this.duration = duration;
}
```

Getters return the stored values without allowing callers to modify private fields directly.

## 5. Wrapper Classes and Autoboxing

Wrapper classes represent primitive values as objects. For example, `Integer` wraps an `int`.

Autoboxing automatically converts a primitive `int` into an `Integer` object. Unboxing converts an `Integer` back into an `int`.

Using `==` to compare two `Integer` objects can compare object references rather than numeric values. Use `.equals()` to compare their values.

## 6. Alternative Concept and Complexity Analysis

If fields were public and validation were performed by calling code, any part of the program could assign invalid values. Every caller would need to repeat the same validation checks, increasing code duplication and the chance of errors.

With encapsulation, validation is centralised in the class. Each update still takes O(1) time when the validation consists of a fixed number of checks. Public fields might allow direct assignment in O(1), but they sacrifice validation and control.

## 7. Conclusion

Encapsulation and packages protect object state, reduce duplicated validation and improve maintainability. Private fields with validated methods help ensure that invalid parking data does not enter the system.