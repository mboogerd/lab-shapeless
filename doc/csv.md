# Simple CSV coding

Let's import some csv coding functionality

```scala
scala> import com.github.mboogerd.csv._
import com.github.mboogerd.csv._
```

And define a custom case class to try to encode to CSV

```scala
scala> case class Test(x: Int)
defined class Test

scala> case class CsvRow(description: String, flag: Boolean, count: Int, test: Test)
defined class CsvRow
```

And then some example data to encode

```scala
scala> val data = List(
     |     CsvRow("description-1", false, -1, Test(1)),
     |     CsvRow("description-2", true, 0, Test(1)),
     |     CsvRow("description-3", false, 1, Test(1))
     | )
data: List[CsvRow] = List(CsvRow(description-1,false,-1,Test(1)), CsvRow(description-2,true,0,Test(1)), CsvRow(description-3,false,1,Test(1)))
```

Then encoding the data to csv should work
```scala
scala> val csv = writeCsv(data)
csv: String =
description-1,no,-1,1
description-2,yes,0,1
description-3,no,1,1
```
