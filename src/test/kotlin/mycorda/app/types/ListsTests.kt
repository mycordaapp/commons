package mycorda.app.types

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class ListsTests {

    @Test
    fun `SimpleImmutableList should be equal`() {
        val list1 = MyList(listOf("foo", "bar"))
        val list2 = MyList(listOf("foo", "bar"))
        val list3 = MyOtherList(listOf("foo", "bar"))
        val list4 = MyList(listOf("hello", "world"))

        // equality
        assertThat(list1, equalTo(list2))
        assertThat(list2, equalTo(list1))
        assertThat(list1 as Any, !equalTo(list3 as Any))    // same content, but different classes
        assertThat(list1, !equalTo(list4))  // same classes, but different content
        assertThat(list1 as Any?, !equalTo(null as Any?) )  // null cannot be equal

        // hashcode
        assertThat(list1.hashCode(), equalTo(list2.hashCode()))
    }

}

class MyList(strings: List<String>) : SimpleImmutableList<String>(strings)
class MyOtherList(strings: List<String>) : SimpleImmutableList<String>(strings)