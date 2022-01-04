package mycorda.app.types

import java.util.*
import kotlin.collections.ArrayList

/**
 * Some common type safe list. By avoid direct use of generics
 * these work better with serialisers as there is no longer the problem
 * of "erasures" losing information
 */

class StringList(data: List<String>) : ArrayList<String>(data)
class LongList(data: List<Long>) : ArrayList<Long>(data)
class DoubleList(data: List<Double>) : ArrayList<Double>(data)
class UUIDList(data: List<UUID>) : ArrayList<UUID>(data)
class UniqueIdList(data: List<UniqueId>) : ArrayList<UniqueId>(data)

/**
 * A simple (and slightly inefficient way) of creating a truely immutable
 * list
 */
abstract class SimpleImmutableList<T>(items: List<T>) : List<T> {
    private val items = ArrayList(items) // make a copy to ensure immutability

    override val size: Int
        get() = items.size

    override fun contains(element: T): Boolean = items.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = items.containsAll(elements)

    override fun get(index: Int): T = items.get(index)

    override fun indexOf(element: T): Int = items.indexOf(element)

    override fun isEmpty(): Boolean = items.isEmpty()

    override fun iterator(): Iterator<T> = items.iterator()

    override fun lastIndexOf(element: T): Int = items.lastIndexOf(element)

    override fun listIterator(): ListIterator<T> = items.listIterator()

    override fun listIterator(index: Int): ListIterator<T> = items.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<T> = items.subList(fromIndex, toIndex)
}

class ImmutableStringList(data: List<String>) : ArrayList<String>(data)
class ImmutableLongList(data: List<Long>) : ArrayList<Long>(data)
class ImmutableDoubleList(data: List<Double>) : ArrayList<Double>(data)
class ImmutableUUIDList(data: List<UUID>) : ArrayList<UUID>(data)
class ImmutableUniqueIdList(data: List<UniqueId>) : ArrayList<UniqueId>(data)


