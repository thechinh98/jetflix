package com.example.jetflix.util

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}