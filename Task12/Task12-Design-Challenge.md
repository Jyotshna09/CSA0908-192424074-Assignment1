# Task 12 – Re-evaluate and Adapt the Design

## A. Impact Analysis

The original Campus Smart-Parking System supports two-wheelers, cars and electric cars. The new requirements introduce e-bikes, delivery vans, one-day visitor passes and a five-floor facility with different slot counts.

The vehicle hierarchy, pricing logic, pass management and parking-slot representation must be reviewed.

A fixed-size rectangular array may not represent floors with different numbers of slots conveniently. A jagged array, such as `ParkingSlot[][]`, allows each floor to contain a different number of slots.

## B. Evaluation of the Original Design

The original object-oriented design can be retained as a foundation because inheritance, abstraction, encapsulation and polymorphism support extension.

However, vehicle-specific pricing rules should not be scattered across many classes. A separate pricing abstraction can centralise fee calculation and make future changes easier.

New vehicle types can be introduced as subclasses, while additional capabilities, such as charging or pass eligibility, can be represented using interfaces.

## C. Adaptation or Redesign

I recommend modifying the existing design rather than replacing it completely.

1. Add an `EBike` class with a flat parking fee.
2. Add a `DeliveryVan` class with a time-dependent fee that doubles the normal car rate between 9 AM and 5 PM.
3. Introduce a visitor one-day pass that records its validity and allows eligible visitors to receive the defined pass benefit.
4. Represent the five floors using a jagged array so that each floor can have a different number of slots.
5. Introduce a `PricingRule` abstraction to keep pricing logic separate from vehicle data.

The delivery-van rule requires entry and exit time information, so the system must record timestamps rather than only parking duration.

## D. Evidence-Based Defence

The modified design preserves existing tested classes where possible and adds new classes for the new requirements. It reduces the need to rewrite the parking service whenever a new vehicle type or pricing rule is introduced.

The new design should be tested for e-bike flat fees, delivery-van rates inside and outside the specified time window, valid and expired visitor passes, and slot allocation across floors with different capacities.

The trade-off is additional classes and configuration, but the improved separation of responsibilities makes the system easier to maintain and extend.

## Conclusion

The original design should be modified, not completely redesigned. A pricing abstraction, visitor-pass handling and jagged floor representation allow the system to accommodate the new requirements while preserving the existing object-oriented foundation.