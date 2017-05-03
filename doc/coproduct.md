# Generic coproduct coding

In order to get Shapeless' constructors for Coproduct in scope, one imports:

```scala
scala> import shapeless.{Coproduct, :+:, CNil, Inl, Inr}
import shapeless.{Coproduct, $colon$plus$colon, CNil, Inl, Inr}
```

Let's define an example coproduct
```scala

case class Apple()
case class Orange()
case class Pear()

type Fruit = Apple :+: Orange :+: Pear :+: CNil
```

Creating instances of fruit, compatible with type `Fruit` requires navigating through `:+:` using `Inl` and `Inr`.

```scala
// Apple is taking the left of the first coproduct
val apple: Fruit = Inl(Apple())

// Orange requires taking the right of the first coproduct, and the left of the result
val orange: Fruit = Inr(Inl(Orange()))

// Pear requires taking the right two times first, then the left of Pear :+: CNil
val pear: Fruit = Inr(Inr(Inl(Pear())))
```

An alternative mechanism to the type alias, that's also understood by `Generic` is to use sealed traits

```scala
scala> import shapeless.Generic
import shapeless.Generic

scala> sealed trait Appliance
defined trait Appliance

scala> case class Microwave(id: String, price: Double) extends Appliance
defined class Microwave

scala> case class Dishwasher(id: String, price: Double) extends Appliance
defined class Dishwasher

scala> case class Oven(id: String, price: Double) extends Appliance
defined class Oven

scala> val gen = Generic[Appliance]
gen: shapeless.Generic[Appliance]{type Repr = shapeless.:+:[Microwave,shapeless.:+:[Dishwasher,shapeless.:+:[Oven,shapeless.CNil]]]} = anon$macro$1$1@3fafd62f
```

which we can use to map back and from the generic and concrete representation:
```scala
scala> val genMW = gen.to(Microwave("mw", 100.0))
genMW: gen.Repr = Inl(Microwave(mw,100.0))

scala> val genDW = gen.to(Dishwasher("dw", 200.0))
genDW: gen.Repr = Inr(Inl(Dishwasher(dw,200.0)))

scala> val genOven = gen.to(Oven("oven", 300.0))
genOven: gen.Repr = Inr(Inr(Inl(Oven(oven,300.0))))

scala> val oven = gen.from(genOven)
oven: Appliance = Oven(oven,300.0)
```
