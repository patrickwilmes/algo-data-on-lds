/*
* Copyright (c) 2024, Patrick Wilmes <p.wilmes89@gmail.com>
* All rights reserved.
*
* SPDX-License-Identifier: BSD-2-Clause
*/
package com.bit.lake.algodataonlds.datagen

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.stream.Collectors
import kotlin.random.Random
import kotlin.random.nextLong

data class Person(
    val birthdate: OffsetDateTime,
    val firstname: String,
    val surname: String,
    val hometown: String,
)

@Service
class PersonDataGenerator {
    private val firstNames = loadResourceLines("data/first-names.txt")
        .shuffled() + loadResourceLines("data/middle-names.txt").shuffled()
    private val surnames = loadResourceLines("data/names.txt").shuffled()
    private val places = loadResourceLines("data/places.txt").shuffled()

    private fun loadResourceLines(resourcePath: String): List<String> {
        val resource = ClassPathResource(resourcePath)
        resource.inputStream.bufferedReader().use { reader ->
            return reader.lines().toList()
        }
    }

    fun generatePersons(amount: Long): List<Person> = (0L until amount).toList().parallelStream().map {
        Person(
            birthdate = OffsetDateTime.now().minusYears(Random.nextLong(LongRange(20, 50))),
            firstname = firstNames[Random.nextInt(0, firstNames.size - 1)],
            surname = surnames[Random.nextInt(0, surnames.size - 1)],
            hometown = places[Random.nextInt(0, places.size - 1)],
        )
    }.collect(Collectors.toList())
}
