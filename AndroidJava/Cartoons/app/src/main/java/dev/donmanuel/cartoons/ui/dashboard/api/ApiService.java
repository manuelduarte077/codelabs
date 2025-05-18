package dev.donmanuel.cartoons.ui.dashboard.api;

import java.util.List;

import dev.donmanuel.cartoons.model.Futurama;
import dev.donmanuel.cartoons.model.Simpsons;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("simpsons/episodes")
    Call<List<Simpsons>> getSimpsonsEpisodes();
}
