package com.tp2n.j_https_verifier

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HttpsVerifierTests {

    private val verifier = HttpsVerifier()

    @Test
    fun testGoogle() {
        assertThat(verifier.verify(TestFixture.googleUrl)).isTrue()
    }

    @Test
    fun testFacebook() {
        assertThat(verifier.verify(TestFixture.facebookUrl)).isTrue()
    }
}
