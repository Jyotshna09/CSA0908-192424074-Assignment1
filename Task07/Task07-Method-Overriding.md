# TASK 7 – Override and Specialise Behaviour

## 1. Introduction

Method overriding allows a subclass to provide its own implementation of a method inherited from a superclass. In the Campus Smart-Parking System, different vehicle types may have different hourly parking rates.

For example, a two-wheeler may cost ₹10 per hour, while a car may cost ₹30 per hour. Overriding allows each vehicle subclass to define its own rate while using the common fee-calculation method.

## 2. Method Overriding

The superclass defines a method, and each subclass provides its own implementation.

```java
class Vehicle {
    protected double ratePerHour() {
        return 0;
    }
}

class TwoWheeler extends Vehicle {
    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {
    @Override
    protected double ratePerHour() {
        return 30;
    }
}
```

When `calculateFee()` calls `ratePerHour()`, Java selects the implementation belonging to the actual object at runtime.

## 3. Rules of Method Overriding

1. The subclass method must have the same name and parameter list as the overridden method.
2. The return type must be the same or a permitted covariant return type.
3. The overriding method cannot reduce the visibility of the inherited method.
4. A `private` method is not overridden in the usual polymorphic sense.
5. A `final` method cannot be overridden.
6. Static methods are hidden rather than overridden.
7. The `@Override` annotation helps the compiler detect mistakes in the method signature.

## 4. Use of the `final` Keyword

The `final` keyword can be used in different ways.

| Usage | Meaning | Example |
|---|---|---|
| Final variable | Cannot be reassigned after initialisation | `final String regNo` |
| Final method | Cannot be overridden by subclasses | `final void printTicket()` |
| Final class | Cannot be extended | `final class ParkingReceipt` |

A final registration number helps prevent accidental reassignment after a vehicle is created. A method should be declared `final` only when its behaviour must remain unchanged in subclasses.

## 5. Overriding `toString()`

The `toString()` method returns a readable string representation of an object.

```java
@Override
public String toString() {
    return "Vehicle{regNo='" + regNo + "'}";
}
```

This makes it easier to print vehicle details during testing and debugging.

## 6. Overriding `equals()` and `hashCode()`

The `equals()` method defines when two vehicle objects should be considered equal. In this design, two vehicles are equal if their registration numbers match.

```java
@Override
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Vehicle)) {
        return false;
    }
    Vehicle other = (Vehicle) o;
    return regNo.equals(other.regNo);
}

@Override
public int hashCode() {
    return regNo.hashCode();
}
```

The `hashCode()` method must be consistent with `equals()`: equal objects must have the same hash code. This is important when vehicles are stored in collections such as `HashSet` or used as keys in a `HashMap`.

The system should also normalise registration numbers consistently, for example by trimming spaces and converting letters to uppercase, before comparing them.

## 7. Alternative Concept: Using a `switch` Statement

Instead of overriding `ratePerHour()`, the program could store a vehicle type and use a `switch` statement to select the rate.

| Approach | Advantages | Limitations |
|---|---|---|
| Method overriding | Each subclass defines its own behaviour; easier to extend | Requires appropriate subclasses |
| `switch` statement | Simple for a small, fixed set of vehicle types | Adding a type requires modifying the central selection logic |

For `k` vehicle types, a switch may perform up to `O(k)` checks in a straightforward implementation, while a direct overridden-method call is typically `O(1)` dispatch overhead. The fee calculation itself is constant-time in either design.

## 8. Conclusion

Method overriding supports specialised behaviour without duplicating common logic. `@Override` helps prevent signature mistakes, `final` protects behaviour or state where appropriate, and `toString()`, `equals()` and `hashCode()` make vehicle objects easier to inspect and compare. A switch-based approach is possible but can become harder to maintain as vehicle categories grow.