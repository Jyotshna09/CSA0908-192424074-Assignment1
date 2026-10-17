# TASK 8 – Apply Polymorphism

## 1. Introduction

Polymorphism means one interface or reference can represent different object types. In Java, polymorphism includes compile-time polymorphism through method overloading and runtime polymorphism through method overriding.

In the Campus Smart-Parking System, a `Vehicle` reference can refer to a `TwoWheeler`, `Car` or `ElectricCar`. Java selects the appropriate overridden method at runtime.

## 2. Compile-Time Polymorphism: Method Overloading

Method overloading means using the same method name with different parameter lists.

Example:

```java
double calculateFee(int minutes)
double calculateFee(LocalTime entry, LocalTime exit)
```

The compiler selects the method based on the argument types and number of arguments. The methods must differ in their parameter lists; changing only the return type is not sufficient.

## 3. Runtime Polymorphism: Method Overriding

A superclass declares a method, and subclasses override it with specialised behaviour. When the method is called through a superclass reference, Java selects the implementation belonging to the actual object.

Example:

```java
Vehicle vehicle = new Car("TN02B2");
double fee = vehicle.calculateFee(130);
```

Although the declared reference type is `Vehicle`, the actual object is a `Car`. Java therefore uses the car's overridden rate method when calculating the fee.

## 4. Upcasting and Dynamic Method Dispatch

Upcasting assigns a subclass object to a superclass reference.

```java
Vehicle[] parked = {
    new TwoWheeler("TN01A1"),
    new Car("TN02B2"),
    new ElectricCar("TN03C3")
};
```

The array holds different vehicle types through the common `Vehicle` reference. A loop can call `calculateFee()` on every element without explicitly checking each type.

Dynamic method dispatch selects the correct overridden method at runtime. This reduces the need for long `if-else` chains or repeated type checks.

## 5. Charging Interface

The `Chargeable` interface represents the capability to calculate charging fees. An electric vehicle implements this interface, while an ordinary vehicle does not need to implement it.

```java
interface Chargeable {
    double chargingFee(int kWh);
}
```

The program can test whether a vehicle supports charging and then invoke `chargingFee()` through the interface.

## 6. Alternative Concept and Complexity Analysis

An alternative is to check every vehicle using `instanceof` and cast it to its concrete type before calculating the fee.

| Approach | Time per dispatch | Maintenance |
|---|---|---|
| Dynamic method dispatch | Typically `O(1)` dispatch overhead | New subclasses can provide their own implementation |
| Repeated type checks | Up to `O(k)` checks for `k` vehicle types in a simple chain | Central conditions must be updated when new types are introduced |

The fee calculation itself is constant-time in this example. The main benefit of polymorphism is extensibility and simpler code, rather than a major performance improvement for a small number of vehicle types.

## 7. Conclusion

Polymorphism enables a common `Vehicle` reference to work with different vehicle classes. Overloading demonstrates compile-time polymorphism, while overriding and dynamic method dispatch demonstrate runtime polymorphism. The `Chargeable` interface represents a capability that only applicable vehicles need to implement.