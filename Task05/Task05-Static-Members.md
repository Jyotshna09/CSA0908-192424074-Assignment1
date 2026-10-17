# TASK 5 – Apply Static Members and the Object Life Cycle

## 1. Introduction

In Java, `static` members belong to the class rather than to an individual object. They are useful for storing information shared across objects, such as a ticket counter. The object life cycle describes how objects are created, used and eventually become eligible for garbage collection.

## 2. Static Variables

A static variable is shared by all instances of a class.

In the parking system, a ticket counter can generate unique ticket numbers.

```java
private static int nextTicketNumber = 1000;
```

Whenever a new ticket is issued, the counter can be incremented to generate the next ticket number. An instance variable would give every ticket object its own counter, which would not reliably provide a shared sequence.

## 3. Static Methods and Static Blocks

A static method belongs to the class and can be called without creating an object.

For example:

```java
public static int getNextTicketNumber() {
    return nextTicketNumber;
}
```

A static block runs once when the class is initialised. It is appropriate for complex initialisation of static data, but it is unnecessary when a simple assignment is sufficient.

## 4. Object Life Cycle

1. **Creation:** An object is created using `new`.
2. **Use:** The object is accessed through references and passed to methods as needed.
3. **Loss of reachability:** When no live reference can reach an object, it becomes eligible for garbage collection.
4. **Garbage collection:** The Java Virtual Machine (JVM) may reclaim the object's memory. The exact time is not guaranteed.

After a vehicle leaves, its parking ticket may be removed from the collection of active tickets. If no other live reference points to the ticket, it becomes eligible for garbage collection.

## 5. Passing and Returning Objects

Java passes argument values to methods. When an object reference is passed, the value of that reference is copied, so the method can access the same object.

A method can also return an object reference. For example, a parking facility may return a `ParkingTicket` when a vehicle enters.

## 6. Alternative Concept and Complexity Analysis

Suppose the ticket counter were an instance variable of `ParkingFacility`.

If the university had two independent facilities, such as north and south gates, each facility would maintain a separate counter. That is suitable when ticket numbering is local to each facility. If ticket numbers must be unique across all facilities, a shared counter or a central ticket-number service is needed.

| Design | Space for counters | Behaviour |
|---|---|---|
| Static counter | `O(1)` shared counter | One sequence shared by the class |
| Instance counter | `O(k)` for `k` facility objects | Each facility maintains its own sequence |

For `n` ticket objects, ticket storage is `O(n)` regardless of the counter design. Incrementing a simple counter takes `O(1)` time. In a concurrent system, ticket generation must also be made thread-safe to avoid duplicate numbers.

## 7. Conclusion

Static members are useful for class-wide information such as a shared ticket counter. Instance members store information belonging to individual objects. Understanding references, object reachability and garbage collection helps manage the life cycle of parking tickets and other system objects.