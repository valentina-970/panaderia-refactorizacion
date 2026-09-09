# Taller: Refactorización y Code Smells

## Grupos

El taller se realiza en los mismos grupos formados para la actividad de infografía.

## Objetivo

Identificar *code smells* en el proyecto existente y aplicar técnicas básicas de refactorización para mejorar la calidad del código.

## Actividad

Cada grupo debe:

1. Revisar un proyecto de software proporcionado o seleccionado.
2. Identificar **15 code smells**.
3. Aplicar refactorización para corregirlos.
4. Documentar el proceso.

## Entregable

Un documento corto en formato **.PDF** que contenga, para **cada** code smell identificado:

- **Nombre del problema.**
- **Fragmento antes** de la refactorización.
- **Técnica de refactorización aplicada.**
- **Fragmento después** de la refactorización.
- **Breve explicación.**

## Catálogo de técnicas de refactorización

Referencia: [refactoring.guru/es/refactoring/catalog](https://refactoring.guru/es/refactoring/catalog)

### 1. Composición de métodos
*(métodos largos o confusos)*
- Extract Method
- Inline Method
- Extract Variable
- Inline Temp
- Replace Temp with Query
- Split Temporary Variable
- Remove Assignments to Parameters
- Replace Method with Method Object
- Substitute Algorithm

### 2. Mover funcionalidad entre objetos
*(responsabilidades mal repartidas)*
- Move Method
- Move Field
- Extract Class
- Inline Class
- Hide Delegate
- Remove Middle Man
- Introduce Foreign Method
- Introduce Local Extension

### 3. Organización de datos
*(uso pobre de tipos primitivos, campos mal encapsulados)*
- Self Encapsulate Field
- Replace Data Value with Object
- Change Value to Reference
- Change Reference to Value
- Replace Array with Object
- Duplicate Observed Data
- Change Unidirectional Association to Bidirectional
- Change Bidirectional Association to Unidirectional
- Replace Magic Number with Symbolic Constant
- Encapsulate Field
- Encapsulate Collection
- Replace Type Code with Class
- Replace Type Code with Subclasses
- Replace Type Code with State/Strategy
- Replace Subclass with Fields

### 4. Simplificación de condicionales
*(lógica condicional enredada)*
- Decompose Conditional
- Consolidate Conditional Expression
- Consolidate Duplicate Conditional Fragments
- Remove Control Flag
- Replace Nested Conditional with Guard Clauses
- Replace Conditional with Polymorphism
- Introduce Null Object
- Introduce Assertion

### 5. Simplificación de llamadas a métodos
*(interfaces de clases confusas)*
- Rename Method
- Add Parameter
- Remove Parameter
- Separate Query from Modifier
- Parameterize Method
- Introduce Parameter Object
- Preserve Whole Object
- Remove Setting Method
- Hide Method
- Replace Constructor with Factory Method
- Replace Error Code with Exception
- Replace Exception with Test

### 6. Manejo de generalización
*(herencia mal diseñada)*
- Pull Up Field
- Pull Up Method
- Pull Up Constructor Body
- Push Down Field
- Push Down Method
- Extract Subclass
- Extract Superclass
- Extract Interface
- Collapse Hierarchy
- Form Template Method
- Replace Inheritance with Delegation
- Replace Delegation with Inheritance