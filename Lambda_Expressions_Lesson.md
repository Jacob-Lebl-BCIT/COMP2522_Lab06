# COMP2522 Lesson 6: Functional Interfaces and Lambda Expressions

## Learning Outcomes

- Understand and use **Functional Interfaces**
- Write and apply **Lambda Expressions**

---

## What is a Functional Interface?

A **functional interface** is an interface that contains **exactly one abstract method** (though it may also contain default and static methods).

### Key Points

- You can optionally mark it with the `@FunctionalInterface` annotation
- The annotation will cause a compiler error if the interface doesn't have exactly ONE abstract method
- Because there's only one abstract method, Java knows:
  - The method signature (parameter types, number, and order)
  - The return type
  - This allows us to use a shortcut: **lambda expressions**

### Definition

A **lambda expression** is the implementation of a functional interface's single abstract method in a way that makes it:
- **Readable**
- **Flexible**
- **Short**

---

## Lambda Expression Introduction

Functional interfaces can be implemented by lambda expressions by creating an **anonymous function** that exists without actually belonging to any class.

### Why Use Lambda Expressions?

1. **Pass functions as parameters** - Lambda expressions can be passed as parameters (like objects) to methods and executed on demand
2. **Functional programming** - We can write functional programming instead of / in addition to OOP
3. **Concise code** - Code will be short yet very readable

### Lambda Syntax

The basic syntax is: `(arg list) -> {code}`

**Examples:**

```java
() -> single-line-of-code

() -> {multiple "lines of code"}

(a1) -> {code using a1, whose type is known by the Functional Interface's method signature}

(Type a2) -> {code using a2, with the type explicitly declared}

(a3, a4, a5, a6) -> {code using a3, a4, a5, a6}
```

---

## Lambda Syntax Rules

### Parentheses Rules

- `() -> {}` - Parentheses **required** if no parameters
- `x -> {}` - Parentheses **not needed** for single parameter, unless:
- `(int x) -> {}` - Parentheses **needed** if you define the datatype for a single parameter
- `(x, y) -> {}` - Parentheses **needed** for multiple parameters

### Curly Braces Rules

- `x -> code` - Curly braces **not needed** if just one single instruction and the evaluation matches the method's return type
- `x -> {multiple lines}` - Curly braces **needed** for multiple statements

---

## Example: Custom Functional Interface

```java
@FunctionalInterface
interface Nameable {
    String getOneString(String s1, String s2, int n);
}

class Main {
    public static void main(final String[] args) {
        // Repeat strings n times
        Nameable repeatNames = (first, last, repeat) -> {
            String s = "";
            for(int i = 0; i < repeat; i++) {
                s += first;
                s += last;
            }
            return s;
        };
        System.out.println(repeatNames.getOneString("tiger", "woods", 3));

        // String gets first n chars
        Nameable getSubstrings = (string1, string2, n) -> {
            String s = "";
            s += string1.substring(0, n);
            s += string2.substring(0, n);
            return s;
        };
        System.out.println(getSubstrings.getOneString("tiger", "woods", 3));

        // Get letters at position n
        Nameable nthChars = (str1, str2, n) -> {
            return "" + str1.charAt(n) + str2.charAt(n);
        };
        System.out.println(nthChars.getOneString("tiger", "woods", 3));
    }
}
```

---

## Example: Math Operations

```java
@FunctionalInterface
interface Mathable {
    double doMath(int op1, int op2);
}

class MathStuff {
    public static void main(final String[] args) {
        Mathable add = (a, b) -> a + b;
        System.out.println(add.doMath(2, 6)); // 8.0

        Mathable power = (x, y) -> Math.pow(x, y);
        // or power = (x, y) -> {return Math.pow(x, y);};
        System.out.println(power.doMath(2, 6)); // 64.0
    }
}
```

---

## Java's Built-In Functional Interfaces

Java provides many functional interfaces in the `java.util.function` package.

**Reference:** https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/function/package-summary.html

### Example: Using forEach with Consumer

The `java.lang.Iterable` interface has a default `forEach` method that takes a `Consumer` functional interface.

- `Consumer` has one abstract method: `void accept(T t)`
- The `forEach` method can take a lambda expression that processes any single type

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BookStore {
    public static void main(final String[] args) {
        List<String> titles;
        titles = new ArrayList<>();

        String[] data = {
            "Anna Karenina", "Madame Bovary", "War and Peace",
            "The Great Gatsby", "Lolita", "Middlemarch",
            "The Adventures of Huckleberry Finn",
            "The Stories of Anton Chekhov", "In Search of Lost Time", "Hamlet"
        };

        titles = Arrays.stream(data).toList();

        // Print titles with length < 10
        titles.forEach(title -> {
            if(title.length() < 10) {
                System.out.println(title);
            }
        });

        // Print titles containing "of"
        titles.forEach(t -> {
            if(t.contains("of")) {
                System.out.println(t);
            }
        });
    }
}
```

---

## Example: Custom Converter Interface

```java
import java.util.ArrayList;
import java.util.List;

public class Notebook {
    public static void main(final String[] args) {
        final List<String> notes;
        notes = new ArrayList<>();
        notes.add("Work Hard");
        notes.add("Have Fun");

        // Using lambda expression
        display(notes, s -> s.toUpperCase());

        // Or using method reference
        // display(notes, Notebook::upperIt);
    }

    private static String lowerIt(String input) {
        return input.toLowerCase();
    }

    private static String upperIt(String input) {
        return input.toUpperCase();
    }

    private static void display(List<String> list, Converter converter) {
        for(String s: list) {
            System.out.println(converter.convert(s));
        }
    }
}

interface Converter {
    String convert(String input);
}
```

---

## Example: Passing Lambdas to Methods

```java
@FunctionalInterface
interface Importantable {
    String getImportantValue();
}

interface Mathyable {
    double doOperation(double x, double y);
}

class Arithmetic {
    double add(Mathyable m, double a, double b) {
        return m.doOperation(a, b);
    }

    double subtract(Mathyable m, double a, double b) {
        return m.doOperation(a, b);
    }

    double getRectangleArea(Mathyable m, double a, double b) {
        return m.doOperation(a, b);
    }
}

class Whatever {
    public static void main(final String[] args) {
        Importantable pi;
        pi = () -> "3.14159";
        System.out.println("Value of Pi = " + pi.getImportantValue());

        Importantable bcSchools;
        bcSchools = () -> "bcit";
        System.out.println("important BC school = " + bcSchools.getImportantValue());

        String first = "tiger";
        String last = "woods";
        Importantable fullName;
        fullName = () -> first + " " + last;
        System.out.println("important name = " + fullName.getImportantValue());

        Arithmetic a = new Arithmetic();
        System.out.println(a.add((c, d) -> c + d, 7, 8));           // 15.0
        System.out.println(a.subtract((c, d) -> c - d, 7, 8));      // -1.0
        System.out.println(a.getRectangleArea((c, d) -> c * d, 7, 8)); // 56.0
    }
}
```

---

## Example: Redefining at Runtime

The same functional interface variable can be reassigned different lambda implementations at runtime:

```java
@FunctionalInterface
interface Nameable {
    String getOneString(String s1, String s2, int n);
}

class Main {
    public static void main(final String[] args) {
        // Repeat strings n times
        Nameable repeatNames = (first, last, repeat) -> {
            String s = "";
            for(int i = 0; i < repeat; i++) {
                s += first;
                s += last;
            }
            return s;
        };
        System.out.println(repeatNames.getOneString("tiger", "woods", 3));

        // String gets first n chars
        Nameable getSubstrings = (string1, string2, numLetters) -> {
            String s = "";
            s += string1.substring(0, numLetters);
            s += string2.substring(0, numLetters);
            return s;
        };
        System.out.println(getSubstrings.getOneString("tiger", "woods", 3));

        // Get letters at position n
        Nameable nthChars = (str1, str2, n) -> {
            return "" + str1.charAt(n) + str2.charAt(n);
        };
        System.out.println(nthChars.getOneString("tiger", "woods", 3));
    }
}
```

---

## Example: Passing Behavior as Parameters

```java
interface Caseable {
    String changeCase(String s);
}

class StringStuff {
    public static void main(String[] args) {
        Caseable upper = s -> s.toUpperCase();
        Caseable lower = s -> s.toLowerCase();
        Caseable title = s -> {
            return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
        };

        Person p = new Person("tigEr");
        System.out.println(p.getName(upper));  // TIGER
        System.out.println(p.getName(lower));  // tiger
        System.out.println(p.getName(title));  // Tiger
    }
}

class Person {
    private String name;

    Person(String n) {
        name = n;
    }

    String getName(Caseable c) {
        return c.changeCase(name);
    }
}
```

---

## Example: Using Comparator with Lambda

Lambda expressions are particularly useful with `Comparator`:

```java
class Person {
    private final String name;
    private final int yearBorn;

    public Person(final String name, final int yearBorn) {
        this.name = name;
        this.yearBorn = yearBorn;
    }

    @Override
    public String toString() {
        return name + " (" + yearBorn + ")";
    }

    public static void main(final String[] args) {
        final List<Person> people;
        people = new ArrayList<>();
        people.add(new Person("Alice", 2000));
        people.add(new Person("Bob", 1999));
        people.add(new Person("Charlie", 1988));

        // Sort by year of birth
        Collections.sort(people, (p1, p2) -> p1.yearBorn - p2.yearBorn);
        System.out.println("Sorted by age: " + people);

        // Sort by name using another Comparator
        Collections.sort(people, (p1, p2) -> p1.name.compareTo(p2.name));
        System.out.println("Sorted by name: " + people);
    }
}
```

---

## Example: Sorting Numbers

```java
public class Main {
    public static void main(final String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(20);
        numbers.add(15);

        // Sort the list in descending order using a lambda expression
        Collections.sort(numbers, (a, b) -> b - a);

        // Print the sorted list
        System.out.println(numbers); // Output: [20, 15, 10, 5]
    }
}
```

---

## Lambda vs Anonymous Inner Classes

### When to Use Lambda Expressions

In modern Java (since Java 8), it's often better and more concise to **use lambda expressions instead of anonymous inner classes**, especially for:

- **Functional interfaces** (interfaces with a single abstract method) like `Comparator`, `Runnable`, `Consumer`, etc.
- Lambdas provide a more **readable and concise** way to implement functional interfaces

### When Anonymous Inner Classes Are Still Necessary

- Implementing interfaces with **multiple methods**
- Using **non-functional interfaces**

---

## Key Takeaways

1. **Functional interfaces** have exactly one abstract method
2. **Lambda expressions** implement functional interfaces concisely
3. Lambda syntax: `(parameters) -> {code}` or `(parameters) -> expression`
4. Java provides many built-in functional interfaces in `java.util.function`
5. Lambda expressions can be:
   - Passed as method parameters
   - Assigned to variables
   - Redefined at runtime
6. When you see the `->` arrow operator, you're seeing a lambda expression
7. Lambdas make code more readable, flexible, and shorter

---

## Common Built-In Functional Interfaces

From `java.util.function` package:

- `Predicate<T>` - tests a condition, returns boolean
- `Function<T, R>` - transforms input to output
- `Consumer<T>` - performs an action on input, returns nothing
- `Supplier<T>` - supplies a value, takes no input
- `UnaryOperator<T>` - special case of Function where input and output are same type
- `Comparator<T>` - compares two objects

**Full documentation:** https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/function/package-summary.html
