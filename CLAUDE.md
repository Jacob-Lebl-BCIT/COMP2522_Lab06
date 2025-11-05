# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a BCIT COMP 2522 lab project (Lab 6) focused on practicing lambda expressions in Java. The project implements a simple hockey team management system to demonstrate various functional interfaces including `Predicate<T>`, `Function<T,R>`, `Consumer<T>`, `Supplier<T>`, `UnaryOperator<T>`, `Comparator<T>`, and a custom `@FunctionalInterface`.

## Build and Run

This is an IntelliJ IDEA project without Maven or Gradle. To compile and run:

```bash
# Compile all Java files
javac -d out src/code/ca/bcit/cst/comp2522/lambdas/*.java

# Run the main class
java -cp out ca.bcit.cst.comp2522.lambdas.Main
```

## Project Structure

```
src/
  code/           # Source code (not src/main/java)
    ca/bcit/cst/comp2522/lambdas/
      HockeyPlayer.java        # Model class with validation
      HockeyTeam.java          # Team container class
      EligibilityRule.java     # Custom functional interface
      Main.java                # Driver with 8 lambda demonstrations
  res/            # Resources folder
  tests/          # Test source folder
out/              # Compiled output (gitignored)
```

## Code Architecture

### Domain Model
- **HockeyPlayer**: Immutable player model with four fields (name, position, yearOfBirth, goals). Position must be "F", "D", or "G". Contains static validation methods and position constants.
- **HockeyTeam**: Simple container with a name and a roster (`List<HockeyPlayer>`).
- **EligibilityRule**: Custom functional interface for testing player eligibility based on age and goal thresholds.

### Main Class Structure
The `Main` class is organized into eight separate task methods (`task1Supplier` through `task8EligibilityRule`), each demonstrating a different functional interface with lambda expressions. All tasks operate on a shared roster using traditional loops (not streams).

## BCIT COMP 2522 Coding Standards

This codebase follows strict BCIT coding conventions. When modifying code:

### Critical Rules
- **ALL parameters must be `final`** - This includes method parameters, for-each loop variables, and catch block variables
- **Declare, then initialize** - Group all variable declarations together, then initialize them separately
- **Always code to the interface** - Use `List<T>` not `ArrayList<T>`, `Map<K,V>` not `HashMap<K,V>`
- **No magic numbers** - Use constants for all numeric values except loop counters
- **Variable names must include units** - Use `priceUsd`, `weightKg`, `datePublished`, not `price`, `weight`, `date`
- **Boolean variables are NOT verbs** - Use `happy` not `isHappy` (but methods can be `isHappy()`)
- **JavaDoc is mandatory** - Every public class, constructor, and method must have JavaDoc. Comments are worth 51% of marks.
- **One method = one action** - Never combine multiple responsibilities
- **Always use braces** - Even for single-line if statements
- **No abbreviations** - Use full words: `quantity` not `qty`, `address` not `addr`
- **Constants use UPPER_SNAKE_CASE with units** - Example: `MAX_USERNAME_LENGTH_CHARS`
- **Package names must be lowercase reverse domain** - `ca.bcit.cst.comp2522.lambdas`

### Variable Declaration Pattern
```java
// declaring
final int x;
final int y;
final String message;

// initializing
x = 3;
y = 5;
message = "hello";

// using
System.out.println(message + ": " + (x + y));
```

### Position Constants
Use `HockeyPlayer.FORWARD`, `HockeyPlayer.DEFENCE`, `HockeyPlayer.GOALIE` when working with positions.

## Lab Requirements Context

This lab intentionally uses basic loops instead of streams to focus on lambda syntax. The eight tasks demonstrate:
1. Supplier for creating new players
2. Predicate chaining for filtering
3. Function for transforming player data
4. Consumer for side effects
5. UnaryOperator for string transformations
6. Comparator for sorting
7. Manual aggregation with loops
8. Custom functional interface with multi-parameter test method

Do not refactor to use Stream API as this defeats the pedagogical purpose of the lab.
