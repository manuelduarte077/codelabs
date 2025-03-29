package dev.donmanuel.restexample.data.repository

import dev.donmanuel.restexample.data.network.models.MealItem
import dev.donmanuel.restexample.data.network.models.Meals
import dev.donmanuel.restexample.utils.Api
import dev.donmanuel.restexample.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MealRepository(
    private val client: HttpClient
) {
    fun fetchMeals(location: String = "British"): Flow<Response<Meals>> = flow {
        emit(Response.Loading())

        val meatDto = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = Api.Host
                path(Api.Path)
                parameters.append("a", location)
            }
        }.body<Meals>()
        emit(Response.Success(meatDto))
    }.catch { error ->
        emit(Response.Error(error))
    }

    fun fetchMealById(id: String): Flow<Response<MealItem>> = flow {
        emit(Response.Loading())

        val meatDto = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = Api.Host
                path(Api.LookUpPath)
                parameters.append("i", id)
            }
        }.body<MealItem>()
        emit(Response.Success(meatDto))
    }.catch { error ->
        emit(Response.Error(error))
    }

}