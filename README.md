# 🚗 Campus Smart-Parking System
### Object-Oriented Design, Implementation & Validation in Java

![Language](https://img.shields.io/badge/Language-Java-orange?logo=openjdk&logoColor=white)
![Assignment](https://img.shields.io/badge/Assignment-1-blue)
![Domain](https://img.shields.io/badge/Domain-Smart%20Parking-green)
![Status](https://img.shields.io/badge/Project-Academic%20Assignment-purple)

## 📌 Project Overview

The **Campus Smart-Parking System** is an object-oriented Java project designed to model parking operations within a university campus. It explores vehicle management, parking-slot allocation, ticket handling, parking-fee calculation and the extension of an existing design to accommodate new requirements.

The project follows a task-by-task approach, beginning with problem analysis and data representation and progressing towards implementation, testing, abstraction, encapsulation and design evaluation.

The goal is to develop a system design that is **modular, maintainable, reusable and extensible** as parking requirements evolve.

## 🎯 Objectives

- Analyse the requirements of a campus parking facility.
- Identify the appropriate classes, objects, data types and relationships.
- Apply fundamental Java and object-oriented programming concepts.
- Explore inheritance, overriding, polymorphism and abstraction.
- Protect data using encapsulation and access modifiers.
- Implement and evaluate parking operations through test cases.
- Extend the design to support new vehicle categories, pricing rules and multiple floors.

## 🧰 Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| JDK | Compilation and execution |
| Visual Studio Code | Development environment |
| Git | Version control |
| GitHub | Source-code hosting and commit history |

## 🏗️ Project Structure

```text
CSA0908--192424074--Assignment1/
│
├── README.md
│
├── Task01/
│   └── Problem analysis
├── Task02/
│   └── Data representation
├── Task03/
│   └── Control logic and pseudocode
├── Task04/
│   └── Classes, objects and constructors
├── Task05/
│   └── Static members and object life cycle
├── Task06/
│   └── Inheritance hierarchy
├── Task07/
│   └── Method overriding
├── Task08/
│   └── Polymorphism
├── Task09/
│   └── Abstraction
├── Task10/
│   └── Packages and encapsulation
├── Task11/
│   └── Implementation, testing and evaluation
└── Task12/
    └── Design challenge and adaptation
```

Each task folder is intended to contain its corresponding explanation, Java source files and execution evidence where applicable.

## 📚 Assignment Roadmap

| Task | Topic | Main Focus |
|---|---|---|
| 01 | Problem Analysis | Identify objects, responsibilities and business rules |
| 02 | Data Representation | Select data types and array structures |
| 03 | Control Logic | Develop algorithms and pseudocode |
| 04 | Classes & Constructors | Create objects and initialise their state |
| 05 | Static Members | Understand shared class-level data |
| 06 | Inheritance | Build a reusable vehicle hierarchy |
| 07 | Method Overriding | Implement specialised vehicle behaviour |
| 08 | Polymorphism | Use common references for different vehicle types |
| 09 | Abstraction | Separate common behaviour from specific capabilities |
| 10 | Encapsulation | Protect data and validate updates |
| 11 | Implementation & Testing | Evaluate operations, boundary cases and invalid inputs |
| 12 | Design Challenge | Adapt the design to changed requirements |

## ⚙️ System Features and Design Goals

The assignment explores the following capabilities:

- **Vehicle modelling:** represent different vehicle categories.
- **Parking management:** allocate and release suitable parking slots.
- **Ticket management:** associate parking records with vehicles.
- **Fee calculation:** calculate charges based on duration and vehicle category.
- **Electric-vehicle charging:** represent charging as an additional capability.
- **Validation:** reject invalid values and duplicate registrations where implemented.
- **Testing:** examine normal operations, boundary conditions and invalid cases.
- **Extensibility:** evaluate how the design can accommodate new requirements.

*These are the system's design goals; the actual implementation and test results should be verified against the Java programs in the repository.*

## 🧠 Object-Oriented Concepts Demonstrated

### Encapsulation
Private fields and controlled methods help protect object state and enforce validation rules.

### Inheritance
Specific vehicle classes can reuse common properties and behaviour from a parent class.

### Polymorphism
A common parent-class reference can represent different vehicle types and invoke their specialised behaviour.

### Abstraction
Abstract classes and interfaces separate shared responsibilities from optional or specialised capabilities.

### Constructors and Static Members
Constructors initialise objects, while static members represent data or behaviour shared at the class level.

## 🧪 Testing and Validation

Testing should cover the following scenarios:

- Vehicle entry and exit.
- Parking-slot allocation and release.
- Zero-minute parking and fee-slab boundaries.
- Full parking capacity.
- Duplicate registration numbers.
- Invalid slot numbers and unknown vehicle types.
- Electric-vehicle charging calculations.
- Daily collection verified against a manual calculation.
- New vehicle categories and time-dependent pricing rules.

Expected and actual results should be recorded in the relevant task documentation. Only tests that have actually been executed should be reported as passed.

## 🚀 How to Run the Programs

### Prerequisites

Install a compatible Java Development Kit (JDK) and configure Java in your terminal.

Verify the installation:

```bash
java -version
javac -version
```

Record the installed Java version in this README before submission.

### Compile and execute a task

Open the terminal in the repository root directory.

For example, to execute Task 4:

```bash
javac Task04/ConstructorDemo.java
java -cp Task04 ConstructorDemo
```

To execute Task 8:

```bash
javac Task08/FeeDemo.java
java -cp Task08 FeeDemo
```

To execute Task 9:

```bash
javac Task09/AbstractionDemo.java
java -cp Task09 AbstractionDemo
```

To execute Task 10:

```bash
javac Task10/EncapsulationDemo.java
java -cp Task10 EncapsulationDemo
```

To execute Task 11:

```bash
javac Task11/ParkingSystemDemo.java
java -cp Task11 ParkingSystemDemo
```

To execute Task 12:

```bash
javac Task12/DesignChallengeDemo.java
java -cp Task12 DesignChallengeDemo
```

Use the actual source filenames and public class names in your repository if they differ. Tasks containing multiple source files may require compiling those files together.

## 🔄 Design Challenge: Extending the System

The final task explores adapting the design to new requirements:

- **E-bikes:** introduce flat-fee pricing.
- **Delivery vans:** introduce time-dependent pricing.
- **Visitor passes:** represent a one-day parking-pass option.
- **Multiple floors:** support different slot capacities on different floors.
- **Pricing abstraction:** separate pricing policies from vehicle representation.

A maintainable design should minimise unnecessary changes to existing tested classes when new vehicle types or pricing rules are introduced.

## 📊 Evaluation and Documentation

The repository should include evidence appropriate to each task:

- Problem analysis and design justification.
- Data representation choices and complexity analysis.
- Algorithms and pseudocode.
- UML diagrams where required.
- Java source code.
- Execution output screenshots.
- Test tables with expected and actual results.
- Alternative-design comparisons and trade-offs.
- A final evaluation of the design challenge.

## 👩‍💻 Student Information

| Field | Details |
|---|---|
| Student Name | Jyotshna Singamsetty |
| Register Number | 192424074 |
| Course | B.Tech – Artificial Intelligence and Data Science |
| Assignment | Assignment 1 |
| Repository | `CSA0908--192424074--Assignment1` |

## 📈 Learning Outcomes

This assignment provides practical experience in translating a real-world problem into an object-oriented model, implementing Java classes, validating data, testing program behaviour and evaluating design trade-offs.

It also demonstrates the importance of version control, progressive commits, structured documentation and evidence-based software evaluation.

---

*Academic project developed as part of the Java Fundamentals and Object-Oriented Programming coursework.*
