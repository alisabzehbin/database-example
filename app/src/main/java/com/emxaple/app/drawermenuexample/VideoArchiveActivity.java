package com.emxaple.app.drawermenuexample;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VideoArchiveActivity extends AppCompatActivity {

    RecyclerView list_archive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        setTitle("Archives Videos");
        list_archive = findViewById(R.id.list_archive);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list_archive.getContext(),((LinearLayoutManager) layoutManager).getOrientation());
        list_archive.addItemDecoration(dividerItemDecoration);
        list_archive.setLayoutManager(layoutManager);
        list_archive.setHasFixedSize(true);
        list_archive.setItemAnimator(new DefaultItemAnimator());
        ArrayList<VideoModel> list = new DatabaseHelper(this).getVideos();
        ListAdapter adapter = new ListAdapter(this, list);
        list_archive.setAdapter(adapter);



    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        Context context;
        ArrayList<VideoModel> videos;

        public ListAdapter(Context context, ArrayList<VideoModel> list) {
            this.context = context;
            this.videos = new ArrayList<>();
            this.videos.addAll(list);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = parent.inflate(context, R.layout.archive_item, null);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            String poster = videos.get(position).poster;
            if (poster != null && !poster.isEmpty()) {
                Picasso.get().load(poster).into(holder.img_video);
            }

            holder.txtVideo.setText(String.format("Video Name : %s (click to show detail)", videos.get(position).videoName));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle(videos.get(position).videoName);
                    String content = "";
                    HashMap<String, String> params = new HashMap<>();
                    params.put("Title", videos.get(position).videoName);
                    params.put("Year", videos.get(position).year);
                    params.put("Rated", videos.get(position).rated);
                    params.put("Released", videos.get(position).released);
                    params.put("Runtime", videos.get(position).runtime);
                    params.put("Genre", videos.get(position).genre);
                    params.put("Director", videos.get(position).director);
                    params.put("Writer", videos.get(position).writer);
                    params.put("Actors", videos.get(position).actors);
                    params.put("Plot", videos.get(position).plot);
                    params.put("Language", videos.get(position).language);
                    params.put("Country",videos.get(position).country);
                    params.put("Awards",videos.get(position).wards);
                    params.put("Poster", videos.get(position).poster);
                    params.put("Metascore", videos.get(position).metascore);
                    params.put("imdbRating", videos.get(position).imdbRating);
                    params.put("imdbVotes",videos.get(position).imdbVotes);
                    params.put("imdbID", videos.get(position).imdbID);
                    params.put("Type", videos.get(position).type);
                    params.put("DVD", videos.get(position).dvd);
                    params.put("BoxOffice",videos.get(position).boxOffice);
                    params.put("Production",videos.get(position).production);
                    params.put("Website", videos.get(position).website);

                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String text = key + " : " + value + "\n\n";
                        content = content.concat(text);
                    }
                    alertDialog.setMessage(content);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return videos.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_video;
            TextView txtVideo;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtVideo = itemView.findViewById(R.id.txt_video);
                img_video = itemView.findViewById(R.id.img_video);
            }
        }

    }
}
