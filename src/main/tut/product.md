# Generic product encoding

In order to construct HLists, we need the following imports

```tut
import shapeless.{HList, ::, HNil}
```

We can then build products as follows:

```tut
val myProduct: Boolean :: Int :: String :: HNil =
    true :: 1337 :: "1337" :: HNil
```

And perform typesafe head tail on them:

```tut
myProduct.head
myProduct.tail
```

And get exceptions when surpassing the list boundary
```tut:fail
myProduct.tail.tail.tail.head
```

`Generic` allows us to create a generic representation of instances of product-like types, such as case classes and tuples.
```tut
import shapeless.Generic

case class Agent(firstName: String, lastName: String, human: Boolean)

val agentGen = Generic[Agent]
```

And Generic supports type-safe conversion from the given representation to a generic one
```tut
val agentInstance = Agent("Merlijn", "Boogerd", true)
val genericAgent = agentGen.to(agentInstance)
```

as well as conversion in the opposite direction

```tut
val agentInstance2 = agentGen.from(genericAgent)
```

Given that tuples really are case classes, `Generic` works for them as well
```tut
val tupledAgentGen = Generic[(String, String, Boolean)]
tupledAgentGen.from(genericAgent)
```

Generic can also be derived for nested case classes
```tut
case class Collective(agents: List[Agent])
val collectiveGen = Generic[Collective]

collectiveGen.to(Collective(List(agentInstance, agentInstance.copy(human = false))))
```