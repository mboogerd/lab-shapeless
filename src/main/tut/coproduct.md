# Generic coproduct coding

In order to get Shapeless' constructors for Coproduct in scope, one imports:

```tut
import shapeless.{Coproduct, :+:, CNil, Inl, Inr}
```

Let's define an example coproduct
```tut:silent

case class Apple()
case class Orange()
case class Pear()

type Fruit = Apple :+: Orange :+: Pear :+: CNil
```

Creating instances of fruit, compatible with type `Fruit` requires navigating through `:+:` using `Inl` and `Inr`.

```tut:silent
// Apple is taking the left of the first coproduct
val apple: Fruit = Inl(Apple())

// Orange requires taking the right of the first coproduct, and the left of the result
val orange: Fruit = Inr(Inl(Orange()))

// Pear requires taking the right two times first, then the left of Pear :+: CNil
val pear: Fruit = Inr(Inr(Inl(Pear())))
```

An alternative mechanism to the type alias, that's also understood by `Generic` is to use sealed traits

```tut
import shapeless.Generic

sealed trait Appliance
case class Microwave(id: String, price: Double) extends Appliance
case class Dishwasher(id: String, price: Double) extends Appliance
case class Oven(id: String, price: Double) extends Appliance

val gen = Generic[Appliance]
```

which we can use to map back and from the generic and concrete representation:
```tut
val genMW = gen.to(Microwave("mw", 100.0))
val genDW = gen.to(Dishwasher("dw", 200.0))
val genOven = gen.to(Oven("oven", 300.0))

val oven = gen.from(genOven)
```