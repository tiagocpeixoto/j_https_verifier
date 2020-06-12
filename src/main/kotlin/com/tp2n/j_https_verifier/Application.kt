package com.tp2n.j_https_verifier

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JHttpsVerifierApplication : CommandLineRunner {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun run(vararg args: String?) {
        log.info("Running...")
        val verifier = HttpsVerifier();
        args.forEach { if (it != null) verifier.verify(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<JHttpsVerifierApplication>(*args)
}

//fun main(args: List<String>) {
//    val verifier = HttpsVerifier();
//    args.forEach {
//        verifier.verify(it)
//    }
//}

