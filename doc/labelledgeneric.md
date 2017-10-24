# LabelledGeneric / Records

A Record is a Map-like datastructure that maintains knowledge about what type will be returned, depending
on the (type of the) value you provide as key. Whereas Generic provides a mapping between arbitrary case classes 
and HLists, LabelledGeneric provides a similar mapping for arbitrary case classes and Record.

LabelledGeneric summons fields with name `name` and type `A` of the map as follows:

```scala
A with KeyTag[Symbol with Tagged["name"], A]
```

Let's look at an example
```scala
scala> import shapeless.LabelledGeneric
import shapeless.LabelledGeneric

scala> case class Agent(name: String, age: Int, human: Boolean)
defined class Agent

scala> val agent = Agent("Merlijn", 32, true)
agent: Agent = Agent(Merlijn,32,true)

scala> val gen = LabelledGeneric[Agent].to(agent)
gen: shapeless.::[String with shapeless.labelled.KeyTag[Symbol with shapeless.tag.Tagged[String("name")],String],shapeless.::[Int with shapeless.labelled.KeyTag[Symbol with shapeless.tag.Tagged[String("age")],Int],shapeless.::[Boolean with shapeless.labelled.KeyTag[Symbol with shapeless.tag.Tagged[String("human")],Boolean],shapeless.HNil]]] = Merlijn :: 32 :: true :: HNil
```

Let's try this out with some JSON-like stuff

```scala
scala> import com.github.mboogerd.json._
import com.github.mboogerd.json._
```
