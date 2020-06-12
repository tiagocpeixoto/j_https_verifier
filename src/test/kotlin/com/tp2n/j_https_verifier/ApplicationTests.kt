package com.tp2n.j_https_verifier

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {
    @Test
    fun contextLoads() {
    }

    @Test
    fun testUrls() {
        main(arrayOf(TestFixture.googleUrl, TestFixture.facebookUrl))
    }
}
