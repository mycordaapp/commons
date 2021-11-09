# xunitpatterns
[home](../README.md)


Supporting classes for patterns identified by [xunitpatterns](http://xunitpatterns.com/).

## Spy

A basic [Spy](See http://xunitpatterns.com/Test%20Spy.html). See below

```kotlin
class SystemUnderTest(private val spy: Spy = Spy()) {
    private fun foo() {
        spy.spy("called foo")
    }

    fun somethingComplicated() {
        // if some decision then call foo
        foo()
    }
}

val spy = Spy()
val sut = SystemUnderTest(spy)
sut.somethingComplicated()

spy.secrets()
```

