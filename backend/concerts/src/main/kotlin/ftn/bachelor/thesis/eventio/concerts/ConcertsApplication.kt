package ftn.bachelor.thesis.eventio.concerts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConcertsApplication

fun main(args: Array<String>) {
	runApplication<ConcertsApplication>(*args)
}
