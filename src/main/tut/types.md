# Special types

## Literal type

Literals typically have more than one type. All the following show the various legal types of a string literal

```tut
"Hello" : String
"Hello" : AnyRef
"Hello" : Any
```

The default behavior of the compiler is to "widen" literals to their nearest non-literal type. We can retrieve
 the literal type using the `narrow` macro that shapeless provides:
 
```tut
import shapeless.syntax.singleton._

// unforunately, this macro fails in tut, otherwise we would see it has type 42 or Int(42), which is a subtype of Int
// var x = 42.narrow
```

## Phantom types

### Introduction
Phantom types are types with no run-time semantics, such as:

```tut
trait Marker
```

These allow you to change the compile-time type of a type without affecting its run-time behavior.

```tut
val withMarker = "someString".asInstanceOf[String with Marker]

// The additional type is lost after any operation, so re-wrapping is required if you want to preserve it
val derivedString = withMarker + "right"
val derivedString2 = "left" + withMarker
```

### KeyTag

Rather than using `asInstanceOf`, shapeless provides the `->>` operator to tag an expression on the right with the singleton-type of the literal expression on the left

```tut
val tagged = "tag" ->> "tagged"
```

Where KeyTag is defined as follows:
```scala
trait KeyTag[K, +V]
```

### FieldType
A third syntax is `field`, which returns the `FieldType` alias 

```tut
import shapeless.labelled.field
field[Marker](123)
```

Where FieldType is defined as follows

```scala
type FieldType[K, +V] = V with KeyTag[K, V]
```

This syntax can be combined with a so called `Witness` to extract the fieldname from a tagged field.

```tut
import shapeless.Witness
import shapeless.labelled.FieldType

def getFieldName[K, V](value: FieldType[K, V])(implicit witness: Witness.Aux[K]): K = witness.value
def getFieldValue[K, V](value: FieldType[K, V]): V = value

getFieldName(tagged)
getFieldValue(tagged)
```