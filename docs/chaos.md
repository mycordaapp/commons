# Chaos

[home](../README.md)

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
        // fail statistically 1 in 10 times
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

## Using chaosType

The `chaos()` method also allows for a logical `chaosType` to be setup. This allows for finer grained control of the
types of chaotic behaviour. If a type is missing then the `chaos` method simply prints a warning message to the console

```kotlin
class TestDoubleWithChaosTypes(private val chaos: Chaos) {
    fun foo(): String {
        chaos.chaos("errors")
        chaos.chaos("delays")
        chaos.chaos("unknown")
        return "bar"
    }
}

val chaos = Chaos(
    mapOf(
        "errors" to listOf(FailNPercent(10)),
        "delays" to listOf(DelayUptoNTicks(5)),
    )
)

val testDouble = TestDoubleWithChaosTypes(chaos)
testDouble.foo()


```

## Using DI

In many cases test code will be wired up using a DI framework, in which case it probably makes sense to inject `Chaos`
using the framework. For example, if using the [Registry](https://github.com/mycordaapp/registry#readme)

```kotlin
class TestDoubleWithRegistry(registry: Registry = Registry()) {
    private val chaos = registry.getOrElse(Chaoss::class.java, Chaos())
    fun foo(): String {
        chaos.chaos()
        return "bar"
    }
}

// setup the Registry
val registry = Registry()
val chaos = Chaos(listOf(DelayUptoNTicks(2), FailNPercent(10)))
registry.store(chaos)

// create a test double 
val testDouble = TestDoubleWithRegistry(registry)
// foo() will have the injected chaotic behaviour stored in the registry
testDouble.foo()

// note, in this pattern we can also just create a TestDouble alone and everything 
// reverts to the default behaviour coded in (typically just Noop())
TestDoubleWithRegistry().foo()

```