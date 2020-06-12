package com.tp2n.j_https_verifier

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.security.cert.Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLPeerUnverifiedException

class HttpsVerifier {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun verify(https_url: String, printContent: Boolean = false): Boolean {
        log.info("------------- Verifying URL : $https_url")
        val url: URL
        return try {
            url = URL(https_url)
            val con = url.openConnection() as HttpsURLConnection

            //dumpl all cert info
            val result = printHttpsCert(con)

            //dump all the content
            if (result && printContent) printContent(con)
            result
        } catch (e: MalformedURLException) {
            log.error("Error for $https_url", e)
            false
        } catch (e: IOException) {
            log.error("Error for $https_url", e)
            false
        }
    }

    private fun printHttpsCert(con: HttpsURLConnection?): Boolean {
        if (con != null) {
            return try {
                log.info("Response Code : " + con.responseCode)
                log.info("Cipher Suite : " + con.cipherSuite)
                val certs: Array<Certificate> = con.serverCertificates
                for (cert in certs) {
                    log.info("Cert Type : " + cert.type)
                    log.info("Cert Hash Code : " + cert.hashCode())
                    log.info("Cert Public Key Algorithm : "
                            + cert.publicKey.algorithm)
                    log.info("Cert Public Key Format : "
                            + cert.publicKey.format)
                }
                true
            } catch (e: SSLPeerUnverifiedException) {
                log.error("Error", e)
                e.printStackTrace()
                false
            } catch (e: IOException) {
                log.error("Error", e)
                e.printStackTrace()
                false
            }
        }
        return false
    }

    private fun printContent(con: HttpsURLConnection?) {
        if (con != null) {
            try {
                log.info("******** Content of the URL ********")
                val br = BufferedReader(
                    InputStreamReader(con.inputStream)
                )
                var input: String?
                while (br.readLine().also { input = it } != null) {
                    println(input)
                }
                br.close()
            } catch (e: IOException) {
                log.error("Error", e)
            }
        }
    }
}
