package dev.donmanuel.cartoons.ui.home.api;

import java.util.List;

import dev.donmanuel.cartoons.model.Futurama;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("futurama/inventory")
    Call<List<Futurama>> getFuturamaCharacters();
}
