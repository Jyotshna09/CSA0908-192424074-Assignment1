# TASK 6 – Design the Inheritance Hierarchy

## 1. Introduction

Inheritance allows a subclass to reuse the fields and methods of a superclass. In the Campus Smart-Parking System, different vehicle types share common information such as registration number, but they may have different parking fee rules.

## 2. Proposed Class Hierarchy

The superclass is `Vehicle`. The subclasses are `TwoWheeler` and `Car`. `ElectricCar` extends `Car` because an electric car is also a car, while adding charging capability.

```text
              Vehicle
              /     \
     TwoWheeler      Car
                       |
                  ElectricCar
```

This hierarchy demonstrates **hierarchical inheritance** because `TwoWheeler` and `Car` share the same parent, and **multilevel inheritance** because `ElectricCar` extends `Car`, which extends `Vehicle`.

## 3. Responsibilities of the Classes

| Class | Inherited behaviour | Specialised behaviour |
|---|---|---|
| `Vehicle` | Stores registration number and common fee-calculation behaviour | Declares the hourly rate contract |
| `TwoWheeler` | Inherits common vehicle information | Defines a rate of ₹10 per hour |
| `Car` | Inherits common vehicle information | Defines a rate of ₹30 per hour |
| `ElectricCar` | Inherits car and vehicle behaviour | Implements the `Chargeable` interface to calculate charging fees |

## 4. Use of the `super` Keyword

The `super` keyword refers to the superclass. A subclass constructor can use `super(regNo)` to call its parent's constructor and initialise inherited information.

Example:

```java
class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }
}
```

`ElectricCar` can call `super(regNo)` to initialise the inherited car and vehicle information. Constructor execution begins with the superclass constructor and proceeds down the inheritance chain.

## 5. Constructor Execution Trace

When this statement executes:

```java
Vehicle v = new ElectricCar("TN03C3");
```

The constructors execute in this order:

1. `Vehicle` constructor initialises the registration number.
2. `Car` constructor executes after the `Vehicle` constructor.
3. `ElectricCar` constructor executes after the `Car` constructor.
4. The created object is referenced by the variable `v`, whose declared type is `Vehicle`.

The actual object is an `ElectricCar`, even though the reference type is `Vehicle`.

## 6. UML Class Diagram

```text
+--------------------------+
|        Vehicle           |
+--------------------------+
| - regNo: String          |
+--------------------------+
| + getRegNo(): String     |
| + calculateFee(minutes)  |
+--------------------------+
             ^
             |
       +-----+------+
       |            |
+--------------+ +--------------+
| TwoWheeler   | | Car          |
+--------------+ +--------------+
| ratePerHour()| | ratePerHour()|
+--------------+ +--------------+
                       ^
                       |
                +--------------+
                | ElectricCar  |
                +--------------+
                | chargingFee()|
                +--------------+
```

The arrow indicates inheritance from the parent class to the child class.

## 7. Alternative: Composition

Instead of using inheritance, a single `Vehicle` class could contain a `VehicleType` object that stores the vehicle's rate and related behaviour. This is composition: a vehicle has a type object rather than being a specialised subclass.

| Aspect | Inheritance | Composition |
|---|---|---|
| Add a vehicle type | Usually requires a new subclass | Usually requires a new type or strategy object |
| Fee calculation | Uses overridden methods | Delegates to the type or pricing object |
| Runtime flexibility | Type is represented by the object's class | Type or pricing behaviour can be replaced when designed to support it |
| Object structure | May create a deeper class hierarchy | Adds a reference to a separate object |

Both approaches can calculate fees in `O(1)` time when rate lookup is constant-time. Inheritance stores the shared state in the vehicle object, while composition adds a reference to a type or strategy object and may require another object allocation.

## 8. Conclusion

Inheritance models the relationship between vehicles and allows subclasses to specialise fee behaviour. The `super` keyword ensures inherited fields are initialised correctly. Composition is an alternative when vehicle types or pricing behaviour need to change independently of the vehicle class hierarchy.