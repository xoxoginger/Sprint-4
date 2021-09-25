package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <K,V>compare(p1: Pair<K,V>, p2: Pair<K,V>): Boolean {
    return ((p1.first == p2.first) && (p1.second == p2.second))
}

// 2.
fun <T: Comparable<T>>countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter { it > elem }.count()
}

// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val stackListOwn: MutableList<T> = mutableListOf()

    fun push(elem: T) {
        stackListOwn.add(elem)
    }

    fun pop(): T {
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
