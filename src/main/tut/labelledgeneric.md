# LabelledGeneric / Records

A Record is a Map-like datastructure that maintains knowledge about what type will be returned, depending
on the (type of the) value you provide as key. Whereas Generic provides a mapping between arbitrary case classes 
and HLists, LabelledGeneric provides a similar mapping for arbitrary case classes and Record.

LabelledGeneric summons fields with name `name` and type `A` of the map as follows:

```scala
A with KeyTag[Symbol with Tagged["name"], A]
```

Let's look at an example
```tut
import shapeless.LabelledGeneric

case class Agent(name: String, age: Int, human: Boolean)

val agent = Agent("Merlijn", 32, true)

val gen = LabelledGeneric[Agent].to(agent)
```

Let's try this out with some JSON-like stuff

```tut
import com.github.mboogerd.json._


```