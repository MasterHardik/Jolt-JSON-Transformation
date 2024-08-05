# JSON Language for Transform(JoLT): JSON-JSON Transformation

Jolt (JsOn Language for Transform) is a transformation library written in Java that allows a developer to convert one JSON structure to another. Jolt provides a set of transformation types, each with their own DSL (called specifications), that define the new structure for outgoing JSON data.

## Jolt Transformations

Jolt allows the following transformations to generate the desired result:

- ```shift```: Copies data from input to the output tree.
- ```default```: Applies default values to the tree.
- ```remove```: Removes data from the tree.
- ```sort```: Sorts the Map key values alphabetically.
- ```cardinality```: Adjusts the cardinality of input data.
- ```modify-default-beta```: Applies default values without overwriting existing values.
- ```modify-overwrite-beta```: Overwrites existing values with specified defaults.

## Why Jolt Exists

Aside from writing your own custom code to do a transform, there are two general approaches to performing a JSON to JSON transform in Java:

1. **JSON -> XML -> XSLT or STX -> XML -> JSON**: This is a complex and convoluted approach. XSLT is more complicated than Jolt because it tries to do the whole transform with a single DSL.

2. **Write a Template (Velocity, FreeMarker, etc)**: This approach involves creating a template that takes hydrated JSON input and writes textual JSON output. Working from the output format backward to the input is complex for non-trivial transforms. Jolt, on the other hand, works forward from the input data to the output format, simplifying the process.


These methods were not only inefficient but also hard to maintain and scale. Jolt was created to provide a simpler, more intuitive solution that allows for declarative JSON transformations, making the process more straightforward and improving developer productivity.

## The Company Behind Jolt

Jolt was developed by Bazaarvoice, a company known for providing software that helps brands and retailers collect and display user-generated content, such as reviews and ratings, on their websites. The need for efficient and scalable data transformation solutions within their services led to the creation of Jolt, which has since been made available as an open-source library to benefit the broader developer community.

## Alternatives

Here are some other interesting JSON manipulation tools to consider:

- **jq**: An awesome command line tool to extract data from JSON files (available via brew).
- **JsonPath**: A Java library to extract data from JSON using an XPath-like syntax.
- **JsonSurfer**: A Java streaming JsonPath processor dedicated to processing big and complicated JSON data.

## Performance

The primary goal of Jolt is to improve developer speed by providing declarative rather than imperative transforms. Jolt should also have better runtime performance compared to some alternatives.

Optimizations include:

- Transforms can be initialized once with their spec and re-used many times in a multi-threaded environment.
- Initialized Jolt transforms can service multiple web requests from a DropWizard service.
- Optimized wildcard logic to reduce the use of Regex, resulting in significant speed improvements.
- Parallel tree walk optimization in Shiftr.

### Considerations

- Jolt is not stream-based, so you need enough memory to hold large JSON documents during transformation.
- The transform process creates and discards many objects, increasing garbage collector activity.


## Online Tool

You can also try Jolt transformations online at this website: [Jolt Demo](https://jolt-demo.appspot.com/).

## Files

- `test_jolt.java`: The Java test file that executes the Jolt transformation.
- `input.json`: The input JSON file containing the data to be transformed.
- `spec.json`: The specification JSON file defining the transformation rules.

## Usage

### Setup the Environment

1. Ensure you have Java installed on your system.
2. Download the necessary Jolt libraries.

### Running the Test

1. Compile and run `test_jolt.java` to apply the transformation specified in `spec.json` to `input.json`.

2. You can copy the code from the `input.json` and `spec.json` files and try it online at [Jolt Demo](https://jolt-demo.appspot.com/).


## Symbols

Jolt uses several special symbols in its specifications:

- **`*` (Asterisk)**: Wildcard to match any key.
- **`@`**: References the data itself.
- **`$`**: Refers to the root of the input JSON tree.
- **`#`**: Refers to the context value in the current scope.
- **`|` (Pipe)**: Logical OR to match multiple possible values.

### Explanation of Operations and Symbols

## Shift Operation

**Purpose**: Copies data from the input to the output tree.

**Example**: ```"name": "fullName"``` shifts the name value to fullName in the output.

## Default Operation

**Purpose**: Applies default values to the output if they do not exist.

**Example**: ```"country": "USA"``` adds ```"country": "USA"``` to the output if country is not present.

## Remove Operation

**Purpose**: Removes data from the output.

**Example**: ```"address": ""``` removes the address field from the output.

## Sort Operation

**Purpose**: Sorts the map key values alphabetically.

**Example**: ```"itemsList": "items"``` sorts the items array and maps it to itemsList.

## Cardinality Operation

Purpose: Adjusts the cardinality (number of items) of input data.
Example: ```"itemsList": "items"``` converts the items array into itemsList.