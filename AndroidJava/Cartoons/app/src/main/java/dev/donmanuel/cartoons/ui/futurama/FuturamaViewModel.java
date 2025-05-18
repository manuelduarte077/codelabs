package dev.donmanuel.cartoons.ui.futurama;

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
import dev.donmanuel.cartoons.core.repository.FuturamaRepository;
import dev.donmanuel.cartoons.model.Favorite;
import dev.donmanuel.cartoons.model.Futurama;

public class FuturamaViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private final FuturamaRepository futuramaRepository;
    private final FavoriteRepository favoriteRepository;
    private final LiveData<List<Futurama>> futuramaData;
    private final LiveData<List<Integer>> favoriteFuturamaIds;

    public FuturamaViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Futurama");

        futuramaRepository = new FuturamaRepository();
        futuramaData = futuramaRepository.getFuturamaData();

        AppDatabase database = AppDatabase.getDatabase(application);
        favoriteRepository = new FavoriteRepository(database.favoriteDao());

        // Transform favorites list to a list of just the IDs for easier checking
        favoriteFuturamaIds = Transformations.map(
            favoriteRepository.getFuturamaFavorites(),
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

    public LiveData<List<Futurama>> getFuturamaData() {
        return futuramaData;
    }

    public LiveData<List<Integer>> getFavoriteFuturamaIds() {
        return favoriteFuturamaIds;
    }

    public void toggleFavorite(Futurama futurama, boolean isFavorite) {
        if (isFavorite) {
            favoriteRepository.addToFavorites(futurama);
        } else {
            favoriteRepository.removeFromFavorites(futurama);
        }
    }
}
