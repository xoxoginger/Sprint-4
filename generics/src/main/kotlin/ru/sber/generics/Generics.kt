package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <K,V>compare(p1: Pair<K,V>, p2: Pair<K,V>): Boolean {
    return ((p1.first == p2.first) && (p1.second == p2.second))
}

// 2.
fun <Any: Comparable<Any>>countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    return anArray.filter { it > elem }.count()
}

// 3.
class Sorter<Any: Comparable<Any>> {
    val list: MutableList<Any> = mutableListOf()

    fun add(value: Any) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<Any> {
    private val stackListOwn: MutableList<Any> = mutableListOf()

    fun push(elem: Any) {
        stackListOwn.add(elem)
    }

    fun pop(): Any {
        if (isEmpty())
            return throw IndexOutOfBoundsException("Мы уже всё удалили:(")
        else {
            return stackListOwn.removeLast()
        }
    }

    fun isEmpty(): Boolean {
        return stackListOwn.isEmpty()
    }

    fun size(): Int {
        return stackListOwn.size
    }
}
