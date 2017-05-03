# Dependent types

Dependent typed functions are functions where the resulting type depends on values being supplied. We do so by not
supplying all types as type parameters and using an auxiliary type of one of the supplied parameters to our pleasing.

For example, `Second` is one of the types that uses an auxiliary type, besides its type parameter. Depending on the
resolution of implicits for `Second`, Out will take a different value.
```scala
import shapeless.{HList, ::, HNil}

trait Second[L <: HList] {
  type Out
  def apply(value: L): Out
}
object Second {
  type Aux[L <: HList, O] = Second[L] { type Out = O }
  def apply[L <: HList](implicit inst: Second[L]): Aux[L, inst.Out] = inst
  
  implicit def hlistSecond[A, B, Rest <: HList]: Aux[A :: B :: Rest, B] =
    new Second[A :: B :: Rest] {
      type Out = B
      def apply(value: A :: B :: Rest): B =
        value.tail.head
    }
}
```

It makes a difference whether we use `implicitly` or a summoner method. `implicitly` will erase the type of the auxiliary
type whereas the summoner method will preserve it

```scala
scala> import Second._
import Second._

scala> // Out is erased
     | implicitly[Second[String :: Int :: HNil]]
res2: Second[shapeless.::[String,shapeless.::[Int,shapeless.HNil]]] = Second$$anon$1@269aac33

scala> // Out is preserved
     | Second[String :: Int :: HNil]
res4: Second[shapeless.::[String,shapeless.::[Int,shapeless.HNil]]]{type Out = Int} = Second$$anon$1@7fc1ddff

scala> // And is dependent on the implicit parameter supplied to Second.apply
     | Second[Int :: String :: HNil]
res6: Second[shapeless.::[Int,shapeless.::[String,shapeless.HNil]]]{type Out = String} = Second$$anon$1@1a77da3d
```

Finally, `the` is a method provided by Shapeless to perform this implicit resolution in a erasure-safe and concise way:
```scala
scala> import shapeless._
import shapeless._

scala> the[Second[String :: Int :: HNil]]
res7: Second.Aux[shapeless.::[String,shapeless.::[Int,shapeless.HNil]],Int] = Second$$anon$1@55af814e
```

