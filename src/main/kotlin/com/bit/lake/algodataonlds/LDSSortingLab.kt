/*
* Copyright (c) 2024, Patrick Wilmes <p.wilmes89@gmail.com>
* All rights reserved.
*
* SPDX-License-Identifier: BSD-2-Clause
*/
package com.bit.lake.algodataonlds

import com.bit.lake.algodataonlds.datagen.Person
import com.bit.lake.algodataonlds.datagen.PersonDataGenerator
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class LDSSortingLab(
    private val personDataGenerator: PersonDataGenerator,
) {

    private val generatedPersons = mutableListOf<Person>()

    @ShellMethod
    fun genLds(
        @ShellOption("number") number: Long,
    ) {
        generatedPersons.addAll(personDataGenerator.generatePersons(number))
    }

    @ShellMethod
    fun cleanLds() {
        generatedPersons.clear()
    }

    @ShellMethod
    fun statusLds() {
        println("LDS ${generatedPersons.size} persons")
    }
}