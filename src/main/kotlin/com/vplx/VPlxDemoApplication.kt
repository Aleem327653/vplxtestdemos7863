package com.vplx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VPlxDemoApplication

fun main(args: Array<String>) {
	runApplication<VPlxDemoApplication>(*args)
	println("VPLX Project Running")
}
