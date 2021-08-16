package com.jesusrojo.usersmvvm.data.api


import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.model.UserRaw
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersAPIServiceTest {

    private lateinit var service: UsersAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName:String){
      val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)
    }

    private suspend fun prepareAPIResponse(): List<UserRaw> {
        enqueueMockResponse("users_data_response.json")
        val responseBody = service.fetchUsers().body()
        return responseBody!!
    }

    @Test
    fun fetchDatas_sentRequest_receivedExpected(){
        runBlocking {
            val responseBody = prepareAPIResponse()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path)
                .isEqualTo("/users")
        }
    }

    @Test
    fun fetchDatas_receivedResponse_correctPageSize(){
      runBlocking {
          val responseBody = prepareAPIResponse()

          assertThat(responseBody.size).isEqualTo(10)
      }
    }

    @Test
    fun fetchDatas_receivedResponse_correctContent(){
        runBlocking {
            val responseBody = prepareAPIResponse()
          
            val data = responseBody[0]
         
            assertThat(data.name).isEqualTo("Leanne Graham")
            assertThat(data.userName).isEqualTo("Bret")
            assertThat(data.email).isEqualTo("Sincere@april.biz")
        }
    }
}