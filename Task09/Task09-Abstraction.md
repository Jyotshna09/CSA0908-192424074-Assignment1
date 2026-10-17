# Task 9 – Introduce Abstraction

## 1. Objective

To use abstract classes and interfaces to separate what an object does from how it performs the operation in the Campus Smart-Parking System.

## 2. Abstract Class – Vehicle

The `Vehicle` class should be abstract because it represents a general vehicle. A general vehicle should not be created directly; instead, specific vehicles such as `TwoWheeler`, `Car`, and `ElectricCar` should be created.

Common fields such as registration number can be maintained in `Vehicle`.

The method `calculateFee()` can be abstract if each vehicle type must calculate its fee differently. Alternatively, a common implementation can be provided when the billing rules are shared.

## 3. Interface – Chargeable

The `Chargeable` interface represents the capability of charging an electric vehicle.

It can declare a method such as `chargingFee(int kWh)`, which calculates the charging fee based on electricity consumption.

Only vehicles that support charging need to implement this interface.

## 4. Abstract Class vs Interface

| Abstract Class | Interface |
|---|---|
| Can contain instance fields and constructors. | Cannot have ordinary per-object instance fields or constructors. |
| Can contain abstract and implemented methods. | Can contain abstract methods and default or static methods. |
| A class can extend only one class. | A class can implement multiple interfaces. |
| Used for common identity, state and behaviour. | Used to represent a capability or contract. |

## 5. Multiple Behaviours Through Interfaces

A class can extend `Vehicle` and implement multiple interfaces. For example, `ElectricCar` can extend `Car` and implement `Chargeable`. This allows it to inherit vehicle behaviour and provide charging behaviour without needing multiple class inheritance.

## 6. Default and Static Interface Methods

A default method is useful when all implementing classes can share a common behaviour without each class implementing it separately.

A static interface method is useful for a utility operation related to the interface. It is called using the interface name.

## 7. Alternative Concept and Complexity Analysis

If `Vehicle` were changed into an interface with default methods, its constructors and ordinary per-object instance fields could no longer be declared there. Each implementing class would need to manage its own vehicle state, potentially duplicating code.

If `Chargeable` were changed into an abstract class, a class could no longer extend both `Car` and that charging class because Java supports only one direct superclass. This would make combining vehicle inheritance and charging behaviour more difficult.

### Complexity Comparison

| Aspect | Abstract Class + Interface | Vehicle Interface + Chargeable Abstract Class |
|---|---|---|
| Method call | Normally O(1) for a method call, excluding the method's internal work. | Normally O(1) for a method call, excluding the method's internal work. |
| Object space | Stores the object's fields, including inherited instance fields. | Stores the fields declared in concrete classes; duplicated state may increase maintenance and potentially storage. |
| Constructors and shared state | Supported in the abstract `Vehicle` class. | Cannot be declared as ordinary constructors or instance fields in an interface. |
| Multiple behaviours | Supports one superclass and multiple interfaces. | Makes combining the vehicle superclass and charging superclass impossible through direct multiple class inheritance. |

The method-call time is generally not meaningfully different just because an interface or abstract class is used. Actual performance depends on the implementation and runtime. The main difference is in the design, state management and extensibility.

## 8. Conclusion

Using an abstract `Vehicle` class and a `Chargeable` interface provides a clear design. The abstract class manages shared vehicle state and behaviour, while the interface represents the optional charging capability. This supports code reuse, polymorphism and easier extension when new vehicle types are introduced.