package com.example.search.extensions

fun String.removeFirst(): String {
    var array = this.split(" ")
    array = array.drop(0)
    return array.joinToString(" ")
}