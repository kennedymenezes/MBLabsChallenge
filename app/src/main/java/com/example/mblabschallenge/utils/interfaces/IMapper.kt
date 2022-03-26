package com.example.mblabschallenge.utils.interfaces

interface IMapper <E , O> {
    fun transform(entry: E) : O
}