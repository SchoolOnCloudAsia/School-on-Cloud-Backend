# Crash Course Lecture Notes on Test-Driven Development (TDD) for Dummies

## Introduction to Test-Driven Development (TDD)

### What is Test-Driven Development?

•	Test-driven development (TDD) is a software development technique that emphasizes writing tests before writing the actual code.

### Why TDD?

• TDD helps ensure code quality.

• It encourages a more iterative and focused development process.

• It helps easier code maintenance and refactoring.

• TDD can lead to better design and reduced debugging time.

## The TDD Process

### The Three Phases of TDD

### 1.	**Red:** Write a failing test

•	Start by writing a test that checks a specific functionality.

•	Since you have not implemented the feature yet, the test should fail.

### 2.	**Green:** Write the minimum code to pass the test

•	Write the minimal code necessary to make the test pass.

•	Avoid writing extra functionality at this stage.

### 3.	**Refactor:** Improve the code

•	Refactor the code to improve its structure, readability, and performance.

•	Ensure all tests still pass.

### The TDD Cycle

•	Repeatedly go through the three phases: Red, Green, and Refactor.

•	The cycle helps you iteratively build and improve your code.

## Writing Good Tests

### Characteristics of Good Tests

•	**Isolated:** Tests should not depend on external factors or earlier tests.

•	**Repeatable:** Tests should produce the same results every time.

•	**Fast:** Tests should sprint to help frequent testing.

•	**Clear and Readable:** Tests should be easy to understand.

### Writing Test Cases

• Find specific test cases to cover different scenarios.

• Use descriptive test names to clarify the test’s purpose.

• Include assertions to check expected outcomes.

## TDD In-Action

### Example: Implementing a Simple Calculator in Java for Basic Arithmetic Operations

### 1. Red Phase: Write a failing addition test.
```
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(3, 4);
        assertEquals(7, result);
    }
}
```
### 2.	Green Phase: Write minimal code to pass the addition test.
```
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
}
```
### 3.	Refactor Phase: Improve the code.

•	At this stage, there is not much to refactor.  We can keep the code simple.

### Expanding Functionality

### 1. Red Phase: Write a failing subtraction test.
```
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(7, 4);
        assertEquals(3, result);
    }
}
```
### 2.	Green Phase: Write minimal code to pass the subtraction test.
```
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}
```
### 3.	Refactor Phase: Improve the code.

•	No significant refactoring is needed.

## TDD Benefits

•	Early detection of bugs.

•	Encourages smaller, focused functions and classes.

•	Facilitates easy code changes and refactoring.

•	Promotes test documentation.

•	Boosts developer confidence.

## Conclusion

•	Test-driven development (TDD) is a software development technique that involves writing tests before writing the actual code.

•	TDD follows a cycle of Red (write a failing test), Green (write minimal code to pass the test), and Refactor (improve the code).

•	Writing good tests and following TDD principles leads to higher code quality, easier maintenance, and more efficient development.
