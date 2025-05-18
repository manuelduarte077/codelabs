package dev.donmanuel.cartoons.ui.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dev.donmanuel.cartoons.R;
import dev.donmanuel.cartoons.model.Simpsons;

public class SimpsonsAdapter extends RecyclerView.Adapter<SimpsonsAdapter.SimpsonsViewHolder> {

    private List<Simpsons> episodesList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Simpsons episode);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setEpisodes(List<Simpsons> episodes) {
        this.episodesList = episodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SimpsonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_simpson_episode, parent, false);
        return new SimpsonsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpsonsViewHolder holder, int position) {
        Simpsons episode = episodesList.get(position);
        holder.bind(episode);
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    class SimpsonsViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnailImageView;
        private TextView titleTextView;
        private TextView seasonEpisodeTextView;
        private TextView ratingTextView;
        private TextView descriptionTextView;
        private TextView airDateTextView;

        public SimpsonsViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.iv_thumbnail);
            titleTextView = itemView.findViewById(R.id.tv_episode_title);
            seasonEpisodeTextView = itemView.findViewById(R.id.tv_season_episode);
            ratingTextView = itemView.findViewById(R.id.tv_rating);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
            airDateTextView = itemView.findViewById(R.id.tv_air_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(episodesList.get(position));
                }
            });
        }

        public void bind(Simpsons episode) {
            titleTextView.setText(episode.getName());
            seasonEpisodeTextView.setText(String.format(Locale.getDefault(),
                    "Season %d, Episode %d", episode.getSeason(), episode.getEpisode()));
            ratingTextView.setText(String.format(Locale.getDefault(),
                    "Rating: %.1f", episode.getRating()));
            descriptionTextView.setText(episode.getDescription());

            // Format the air date
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date = inputFormat.parse(episode.getAirDate());
                String formattedDate = outputFormat.format(date);
                airDateTextView.setText("Air Date: " + formattedDate);
            } catch (ParseException e) {
                airDateTextView.setText("Air Date: " + episode.getAirDate());
            }

            // Load image with Glide
            Glide.with(itemView.getContext())
                    .load(episode.getThumbnailUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(thumbnailImageView);
        }
    }
}
