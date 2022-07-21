package com.rohan.foodie

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
            flowOf(1, 2, 3, 4)
                .onEach {
                    delay(2)
                    println("Collecting: $it")
                }
                .launchIn(this)

            flowOf("a", "b", "c", "d")
                .onEach {
                    delay(1)
                }
                .collect {
                    println("Collecting: $it")
                }

    }

}

suspend fun dump(){
    val mFlow :Flow<Int> = flow {
    for (i in 0..5) {
        delay(1000)
        emit(i)
    }
}
    mFlow.collect {
        println("Collector 1: $it")
    }}

fun testAsyncCall() = runBlocking {
    println("Program starts on thread: ${Thread.currentThread().name}")

    val time = measureTimeMillis {

        val result1 = async {  doTaskOne() }
        val result2 = async {  doTaskTwo() }

        result2.await()
        result1.await()
    }
    println("result completes in : $time")
    println("Program ends")
}
suspend fun doTaskOne() : String{
    delay(1000)
    println("returning form task 1")
    return "Task 1"
}

suspend fun doTaskTwo() :String{
    delay(1000)
    println("returning form task 2")
    return "Task 2"
}
suspend fun testWithTimeout(){
    try {
        withTimeout(50) {
            for (i in 0..500) {
                print("$i.")
                delay(5)
            }
        }
    } catch (ex: TimeoutCancellationException) {
        println()
        print("Timeout before task completes")
    } finally {
        println()
        println("Program Ends")
    }
}

suspend fun testWithTimeoutOrNull(){
    val result: String? =withTimeoutOrNull(50000) {
        for (i in 0..500) {
            print("$i.")
            delay(5)
        }
        "Task Completed before Timeout"
    }
    if(!result.isNullOrEmpty()){
        println()
        print(result)
    }else{
        println()
        print("Timeout before task completes")
    }
    println()
    println("Program Ends")
}