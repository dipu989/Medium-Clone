package io.realworld.api

import io.realworld.api.models.entities.UserCreds
import io.realworld.api.models.requests.SignupRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*
import kotlin.random.Random

class ConduitClientTest {

    private val conduitClient = ConduitClient()

    @Test
    fun `GET articles`() {
        runBlocking {
            val articles = conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(
                author = "444"
            )
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tags`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(
                tags = listOf("dragons")
            )
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST users - create user` () {
        val userCred = UserCreds (
            email = "testemail${Random.nextInt(999,99999)}@test.com",
            password = "pass${Random.nextInt(9999, 999999)}",
            username = "rand_user_${Random.nextInt(99,999)}"
        )

        runBlocking {
            val resp = conduitClient.api.signupUser(SignupRequest(userCred))
            assertEquals(userCred.username, resp.body()?.user?.username)
        }
    }

}
