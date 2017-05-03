# Simple CSV coding

Let's import some csv coding functionality

```tut
import com.github.mboogerd.csv._
```

And define a custom case class to try to encode to CSV

```tut
case class Test(x: Int)
case class CsvRow(description: String, flag: Boolean, count: Int, test: Test)
```

And then some example data to encode

```tut
val data = List(
    CsvRow("description-1", false, -1, Test(1)),
    CsvRow("description-2", true, 0, Test(1)),
    CsvRow("description-3", false, 1, Test(1))
)
```

Then encoding the data to csv should work
```tut
val csv = writeCsv(data)
```
