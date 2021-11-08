# Chaos

A very simple framework for introducing chaotic behavior into code. As the code must be modified the approach is only
intended for use within non production code such as test doubles.

Once added, the necessary type of chaos can be added at runtime via DI

## A simple example

First, create a test double (or decorate a "real" class)

```kotlin
// a test double that support chaos 
class TestDouble(private val chaos: Chaos) {
    fun foo(): String {
        chaos.chaos()
        return "bar"
    }
}
```

Setup some chaotic behaviours

```kotlin
 val chaos = Chaos(
    listOf(
        // average delay of 1 "platform" tick (tuned to the basic tick rate on the OS/Hardware)
        DelayUptoNTicks(2),
        // fail statistically 1 in 10 timed
        FailNPercent(10)

    )
)

```

Now calls to the method will experience the required level of "chaos"

```kotlin
val testDouble = TestDouble(chaos)

// calls to foo method now have the injected chaotic behaviors 
testDouble.foo()
```


