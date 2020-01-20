package com.levanb.movies_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.levanb.movies_app.R;
import com.levanb.movies_app.repository.datatype.Movie;

public class MovieDetailsFragment extends Fragment {
    public static MovieDetailsFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable("movie", movie);

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert savedInstanceState != null;
        Movie movie = (Movie) savedInstanceState.getSerializable("movie");
        assert movie != null;

        ImageView poster = view.findViewById(R.id.image_view_movie_poster);
        String transitionName = "poster" + movie.getId();
        ViewCompat.setTransitionName(poster, transitionName);
        TextView title = view.findViewById(R.id.text_view_title);
        TextView overview = view.findViewById(R.id.text_view_overview);

        // set poster image
        String posterUrl = movie.getPosterUrl();
        if (posterUrl != null) {
            Glide.with(view.getContext())
                    .asBitmap()
                    .load(movie.getPosterUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder))
                    .into(poster);
        }

        // set title and overview
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
    }
}