package mycorda.app.types

import java.util.ArrayList
import kotlin.reflect.KClass

/**
 * An easy to store and serialize container for core exception information.
 *
 * Passing the full Java Exception around is problematic as its heavy weight(due to the
 * stack trace), potentially hard to serialise (as it may change between JVM versions)
 * and possibly a security vulnerability (as there is no easy way to guarantee there
 * is no sensitive or private information in the stack trace).
 */
data class ExceptionInfo(val clazzName: String, val message: String) {
    constructor(clazz: KClass<out Throwable>, message: String) : this(clazz.qualifiedName!!, message)
    constructor(ex: Throwable) : this(ex::class, ex.message!!)
}

/**
 * A type safe list of ExceptionInfo.
 */
class ExceptionInfoList(data: List<ExceptionInfo>) : ArrayList<ExceptionInfo>(data) {
    constructor() : this(emptyList())
}