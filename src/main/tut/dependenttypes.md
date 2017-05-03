# Dependent types

Dependent typed functions are functions where the resulting type depends on values being supplied. We do so by not
supplying all types as type parameters and using an auxiliary type of one of the supplied parameters to our pleasing.

For example, `Second` is one of the types that uses an auxiliary type, besides its type parameter. Depending on the
resolution of implicits for `Second`, Out will take a different value.
```tut:silent:book
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

```tut
import Second._

// Out is erased
implicitly[Second[String :: Int :: HNil]]

// Out is preserved
Second[String :: Int :: HNil]

// And is dependent on the implicit parameter supplied to Second.apply
Second[Int :: String :: HNil]
```

Finally, `the` is a method provided by Shapeless to perform this implicit resolution in a erasure-safe and concise way:
```tut
import shapeless._
the[Second[String :: Int :: HNil]]
```

