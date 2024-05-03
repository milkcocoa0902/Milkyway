package com.milkcocoa.info.milkyway.models.aturi

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RecordKeyTest{
    @Test
    fun test_001(){
        assertDoesNotThrow {
            RecordKey("3jui7kd54zh2y")
        }
    }

    @Test
    fun test_002(){
        assertDoesNotThrow {
            RecordKey("example.com")
        }
    }

    @Test
    fun test_003(){
        assertDoesNotThrow {
            RecordKey("~1.2-3_")
        }
    }

    @Test
    fun test_004(){
        assertDoesNotThrow {
            RecordKey("pre:fix")
        }
    }

    @Test
    fun test_005(){
        assertThrows<IllegalStateException> {
            RecordKey("alpha/beta")
        }
    }

    @Test
    fun test_006(){
        assertThrows<IllegalStateException> {
            RecordKey("@handle")
        }
    }

    @Test
    fun test_007(){
        assertThrows<IllegalStateException> {
            RecordKey("\"quote\"")
        }
    }

    @Test
    fun test_008(){
        assertThrows<IllegalStateException> {
            RecordKey("any+space")
        }
    }

    @Test
    fun test_009(){
        assertThrows<IllegalStateException> {
            RecordKey("any space")
        }
    }
}