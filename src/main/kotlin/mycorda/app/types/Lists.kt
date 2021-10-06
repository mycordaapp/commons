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


