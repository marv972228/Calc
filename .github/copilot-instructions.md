# Copilot Instructions — Calc

## Project Overview

**Calc** is a state machine-based GUI calculator, originally built as a college project for COP 4380 (Summer 2016) at Florida State University. The design is inspired by Miro Samek's *"Practical UML Statecharts in C/C++"*, specifically Figure 2.18 — "The final calculator statechart." It models a physical store-bought calculator as a hierarchical state machine and implements it in Java with a JavaFX UI.

This project has sentimental value as a pre-AI-era academic exercise. It may serve as the reference implementation for a future **Go rewrite** with a native desktop UI.

## Current Implementation (Java)

### Tech Stack
- **Language:** Java 8+
- **UI:** JavaFX (FXML + CSS)
- **Build:** Apache Ant (NetBeans project)
- **Math:** `java.math.BigDecimal` for arbitrary-precision arithmetic
- **Tests:** JUnit 4

### Architecture
The calculator uses a **flat-class hierarchical state machine** pattern. States are represented by enums and dispatched via switch statements in static methods. Each state class handles its own transitions:

```
calc/
  Calc.java              — JavaFX Application entry point
  CalcController.java    — FXML controller, bridges UI events → state machine
  FXMLCalcUI.fxml        — UI layout (absolute positioning, 427×595)
  style.css              — Orange-themed button styling

enums/
  EnumOperationState.java — 18 operational states (ON, OFF, BEGIN, RESULT, ZERO1, INT1, FRAC1, ...)
  EnumMathState.java      — 8 math operations (NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE, EXPONENT, PERCENT, SQUARE)

statemachine/
  CalcSM.java            — Central state machine: state storage, dispatch, output formatting
  Operand.java           — Digit-by-digit number builder (negation + zero + int + point + frac + calculated)
  ReadyState.java        — BEGIN and RESULT sub-states
  OperandOneState.java   — ZERO1, INT1, FRAC1 input states
  OperandTwoState.java   — ZERO2, INT2, FRAC2 input states (mirrors OperandOne)
  OpEnteredState.java    — Operator selection state
  MathOperationsState.java — BigDecimal arithmetic (add, subtract, multiply, divide, exponent, percent, negate)
  MemoryState.java       — M+, M-, MRC memory operations
  ErrorState.java        — Error handling (partially implemented)

test/
  AddTestFunctionality/     — 2 tests
  SubtractTestFunctionality/ — 1 test
  MultiplyTestFunctionality/ — 1 test
  DivideTestFunctionality/  — 1 test
  ExponentTestFunctionality/ — 2 tests
  PercentFunctionality/     — 1 test
```

### State Machine Flow
```
OFF → ON → BEGIN → [digit input] → OPERAND1 (ZERO1/INT1/FRAC1)
  → [operator] → OPENTERED → [digit input] → OPERAND2 (ZERO2/INT2/FRAC2)
  → [= or %] → RESULT → [operator] → chains back to OPENTERED
```

### Key Design Decisions
- **All static** — The entire state machine uses static fields and methods (no instances)
- **Operand as string builder** — Numbers are constructed digit-by-digit as concatenated string parts, converted to BigDecimal only at calculation time
- **Left-to-right evaluation** — No operator precedence; operations chain sequentially like a physical calculator
- **Two-operand model** — OP1 and OP2 are the only operands; chaining reuses OP1 with the running total

### Known Limitations
See `docs/analysis.md` for the full analysis, but key gaps include:
- Static global state (no instance isolation, not concurrent-safe)
- Duplicated OperandOne/OperandTwo logic (~180 lines)
- Integer-only exponents
- No parentheses, sqrt, trig, log, or scientific functions
- No keyboard input, undo/backspace, or responsive layout
- ErrorState is effectively dead code
- Test coverage is minimal (8 happy-path cases, intValue comparisons lose precision)

## Future Direction: Go Rewrite

The long-term goal is to rewrite this calculator in **Go** with a native desktop UI, using the Java version as the reference implementation. Planned improvements:

- **Instance-based state machine** — No global state; proper struct with methods
- **State pattern via interfaces** — Replace switch/enum dispatch with polymorphic state objects
- **Expression parser** — Support parentheses and operator precedence (Pratt parser or shunting-yard)
- **Scientific mode** — sqrt, trig, log, factorial, constants (pi, e)
- **Desktop UI** — Go-native GUI framework (Fyne, Gio, or Wails)
- **Keyboard input** — Full keyboard support alongside button clicks
- **Comprehensive tests** — Table-driven tests with edge cases, decimal precision, error recovery

### Candidate Go UI Frameworks
| Framework | Type | Notes |
|-----------|------|-------|
| [Fyne](https://fyne.io) | Native Go, cross-platform | Material Design-ish, good for forms/buttons |
| [Gio](https://gioui.org) | Immediate-mode, GPU-accelerated | More flexible, lower-level |
| [Wails](https://wails.io) | Go backend + web frontend | If we want HTML/CSS/JS for the UI |
| [Bubbletea](https://github.com/charmbracelet/bubbletea) | TUI | Terminal-only, good for a CLI mode |

## Build & Run (Java)

```bash
# Build with Ant (requires JDK 8+)
ant

# Run the jar
java -jar dist/Calc.jar

# Run tests (via Ant or IDE)
ant test
```
