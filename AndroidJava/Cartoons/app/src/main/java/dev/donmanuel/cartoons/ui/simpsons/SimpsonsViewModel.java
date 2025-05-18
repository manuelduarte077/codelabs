package dev.donmanuel.cartoons.ui.simpsons;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import dev.donmanuel.cartoons.core.database.AppDatabase;
import dev.donmanuel.cartoons.core.repository.FavoriteRepository;
import dev.donmanuel.cartoons.core.repository.SimpsonsRepository;
import dev.donmanuel.cartoons.model.Favorite;
import dev.donmanuel.cartoons.model.Simpsons;

public class SimpsonsViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private final SimpsonsRepository simpsonsRepository;
    private final FavoriteRepository favoriteRepository;
    private final LiveData<List<Simpsons>> simpsonsData;
    private final LiveData<List<Integer>> favoriteSimpsonIds;

    public SimpsonsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Simpsons");

        simpsonsRepository = new SimpsonsRepository();
        simpsonsData = simpsonsRepository.getSimpsonsData();

        AppDatabase database = AppDatabase.getDatabase(application);
        favoriteRepository = new FavoriteRepository(database.favoriteDao());

        // Transform favorites list to a list of just the IDs for easier checking
        favoriteSimpsonIds = Transformations.map(
            favoriteRepository.getSimpsonsFavorites(),
            favorites -> {
                List<Integer> ids = new ArrayList<>();
                for (Favorite favorite : favorites) {
                    ids.add(favorite.getItemId());
                }
                return ids;
            }
        );
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Simpsons>> getSimpsonsData() {
        return simpsonsData;
    }

    public LiveData<List<Integer>> getFavoriteSimpsonIds() {
        return favoriteSimpsonIds;
    }

    public void toggleFavorite(Simpsons simpsons, boolean isFavorite) {
        if (isFavorite) {
            favoriteRepository.addToFavorites(simpsons);
        } else {
            favoriteRepository.removeFromFavorites(simpsons);
        }
    }
}
