# Generic product encoding

In order to construct HLists, we need the following imports

```scala
scala> import shapeless.{HList, ::, HNil}
import shapeless.{HList, $colon$colon, HNil}
```

We can then build products as follows:

```scala
scala> val myProduct: Boolean :: Int :: String :: HNil =
     |     true :: 1337 :: "1337" :: HNil
myProduct: shapeless.::[Boolean,shapeless.::[Int,shapeless.::[String,shapeless.HNil]]] = true :: 1337 :: 1337 :: HNil
```

And perform typesafe head tail on them:

```scala
scala> myProduct.head
res0: Boolean = true

scala> myProduct.tail
res1: shapeless.::[Int,shapeless.::[String,shapeless.HNil]] = 1337 :: 1337 :: HNil
```

And get exceptions when surpassing the list boundary
```scala
scala> myProduct.tail.tail.tail.head
<console>:15: error: could not find implicit value for parameter c: shapeless.ops.hlist.IsHCons[shapeless.HNil]
       myProduct.tail.tail.tail.head
                                ^
```

`Generic` allows us to create a generic representation of instances of product-like types, such as case classes and tuples.
```scala
scala> import shapeless.Generic
import shapeless.Generic

scala> case class Agent(firstName: String, lastName: String, human: Boolean)
defined class Agent

scala> val agentGen = Generic[Agent]
agentGen: shapeless.Generic[Agent]{type Repr = shapeless.::[String,shapeless.::[String,shapeless.::[Boolean,shapeless.HNil]]]} = anon$macro$4$1@54c1707d
```

And Generic supports type-safe conversion from the given representation to a generic one
```scala
scala> val agentInstance = Agent("Merlijn", "Boogerd", true)
agentInstance: Agent = Agent(Merlijn,Boogerd,true)

scala> val genericAgent = agentGen.to(agentInstance)
genericAgent: agentGen.Repr = Merlijn :: Boogerd :: true :: HNil
```

as well as conversion in the opposite direction

```scala
scala> val agentInstance2 = agentGen.from(genericAgent)
agentInstance2: Agent = Agent(Merlijn,Boogerd,true)
```

Given that tuples really are case classes, `Generic` works for them as well
```scala
scala> val tupledAgentGen = Generic[(String, String, Boolean)]
tupledAgentGen: shapeless.Generic[(String, String, Boolean)]{type Repr = shapeless.::[String,shapeless.::[String,shapeless.::[Boolean,shapeless.HNil]]]} = anon$macro$8$1@e898ad8

scala> tupledAgentGen.from(genericAgent)
res3: (String, String, Boolean) = (Merlijn,Boogerd,true)
```

Generic can also be derived for nested case classes
```scala
scala> case class Collective(agents: List[Agent])
defined class Collective

scala> val collectiveGen = Generic[Collective]
collectiveGen: shapeless.Generic[Collective]{type Repr = shapeless.::[List[Agent],shapeless.HNil]} = anon$macro$10$1@65746da6

scala> collectiveGen.to(Collective(List(agentInstance, agentInstance.copy(human = false))))
res4: collectiveGen.Repr = List(Agent(Merlijn,Boogerd,true), Agent(Merlijn,Boogerd,false)) :: HNil
```
