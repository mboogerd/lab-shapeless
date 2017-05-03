# Special types

## Literal type

Literals typically have more than one type. All the following show the various legal types of a string literal

```scala
scala> "Hello" : String
res0: String = Hello

scala> "Hello" : AnyRef
res1: AnyRef = Hello

scala> "Hello" : Any
res2: Any = Hello
```

The default behavior of the compiler is to "widen" literals to their nearest non-literal type. We can retrieve
 the literal type using the `narrow` macro that shapeless provides:
 
```scala
scala> import shapeless.syntax.singleton._
import shapeless.syntax.singleton._

scala> // unforunately, this macro fails in tut, otherwise we would see it has type 42 or Int(42), which is a subtype of Int
     | // var x = 42.narrow
```

## Phantom types

### Introduction
Phantom types are types with no run-time semantics, such as:

```scala
     | trait Marker
defined trait Marker
```

These allow you to change the compile-time type of a type without affecting its run-time behavior.

```scala
scala> val withMarker = "someString".asInstanceOf[String with Marker]
withMarker: String with Marker = someString

scala> // The additional type is lost after any operation, so re-wrapping is required if you want to preserve it
     | val derivedString = withMarker + "right"
derivedString: String = someStringright

scala> val derivedString2 = "left" + withMarker
derivedString2: String = leftsomeString
```

### KeyTag

Rather than using `asInstanceOf`, shapeless provides the `->>` operator to tag an expression on the right with the singleton-type of the literal expression on the left

```scala
scala> val tagged = "tag" ->> "tagged"
tagged: String with shapeless.labelled.KeyTag[String("tag"),String] = tagged
```

Where KeyTag is defined as follows:
```scala
trait KeyTag[K, +V]
```

### FieldType
A third syntax is `field`, which returns the `FieldType` alias 

```scala
scala> import shapeless.labelled.field
import shapeless.labelled.field

scala> field[Marker](123)
res6: shapeless.labelled.FieldType[Marker,Int] = 123
```

Where FieldType is defined as follows

```scala
type FieldType[K, +V] = V with KeyTag[K, V]
```

This syntax can be combined with a so called `Witness` to extract the fieldname from a tagged field.

```scala
scala> import shapeless.Witness
import shapeless.Witness

scala> import shapeless.labelled.FieldType
import shapeless.labelled.FieldType

scala> def getFieldName[K, V](value: FieldType[K, V])(implicit witness: Witness.Aux[K]): K = witness.value
getFieldName: [K, V](value: shapeless.labelled.FieldType[K,V])(implicit witness: shapeless.Witness.Aux[K])K

scala> def getFieldValue[K, V](value: FieldType[K, V]): V = value
getFieldValue: [K, V](value: shapeless.labelled.FieldType[K,V])V

scala> getFieldName(tagged)
res7: String = tag

scala> getFieldValue(tagged)
res8: String = tagged
```
