package com.emxaple.app.drawermenuexample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchVideosActivity extends AppCompatActivity {

    private static final String TAG = SearchVideosActivity.class.getSimpleName();
    private static final String URL = "http://www.omdbapi.com/?apikey=98869092&t=";

    Button btn_search, btn_back, btn_save, btn_archive;
    EditText edt_search;
    TextView txt_result;
    ImageView img_poster;

    VideoModel videoModel;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_videos);

        setTitle("Search Videos");

        btn_back = findViewById(R.id.btn_back);
        btn_search = findViewById(R.id.btn_search);
        edt_search = findViewById(R.id.edt_search);
        txt_result = findViewById(R.id.txt_result);
        img_poster = findViewById(R.id.poster);

        btn_save = findViewById(R.id.btn_save);
        btn_archive = findViewById(R.id.btn_archive);

        btn_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchVideosActivity.this, VideoArchiveActivity.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoModel != null) {
                    DatabaseHelper db = new DatabaseHelper(SearchVideosActivity.this);
                    db.addVideo(videoModel);
                    Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchVideosActivity.this.finish();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = edt_search.getText().toString().trim();
                if (searchText.isEmpty()) {
                    edt_search.setError("Enter video name");
                    return;
                }
                img_poster.setVisibility(View.GONE);
                txt_result.setText("");
                Ion.with(SearchVideosActivity.this)
                        .load(URL + searchText)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (result != null) {
                                    Log.e(TAG, "Result: \n" + result);

                                    try {
                                        JSONObject jsonObject = new JSONObject(result);
                                        String Response = jsonObject.getString("Response");
                                        if (!Response.isEmpty() && Response.equals("True")) {
                                            String videoName = jsonObject.getString("Title");
                                            String year = jsonObject.getString("Year");
                                            String rated = jsonObject.getString("Rated");
                                            String released = jsonObject.getString("Released");
                                            String runtime = jsonObject.getString("Runtime");
                                            String genre = jsonObject.getString("Genre");
                                            String director = jsonObject.getString("Director");
                                            String writer = jsonObject.getString("Writer");
                                            String actors = jsonObject.getString("Actors");
                                            String plot = jsonObject.getString("Plot");
                                            String language = jsonObject.getString("Language");
                                            String country = jsonObject.getString("Country");
                                            String wards = jsonObject.getString("Awards");
                                            String poster = jsonObject.getString("Poster");
                                            String metascore = jsonObject.getString("Metascore");
                                            String imdbRating = jsonObject.getString("imdbRating");
                                            String imdbVotes = jsonObject.getString("imdbVotes");
                                            String imdbID = jsonObject.getString("imdbID");
                                            String type = jsonObject.getString("Type");
                                            String dvd = jsonObject.getString("DVD");
                                            String boxOffice = jsonObject.getString("BoxOffice");
                                            String production = jsonObject.getString("Production");
                                            String website = jsonObject.getString("Website");

                                            HashMap<String, String> params = new HashMap<>();
                                            params.put("Title", videoName);
                                            params.put("Year", year);
                                            params.put("Rated", rated);
                                            params.put("Released", released);
                                            params.put("Runtime", runtime);
                                            params.put("Genre", genre);
                                            params.put("Director", director);
                                            params.put("Writer", writer);
                                            params.put("Actors", actors);
                                            params.put("Plot", plot);
                                            params.put("Language", language);
                                            params.put("Country", country);
                                            params.put("Awards", wards);
                                            params.put("Poster", poster);
                                            params.put("Metascore", metascore);
                                            params.put("imdbRating", imdbRating);
                                            params.put("imdbVotes", imdbVotes);
                                            params.put("imdbID", imdbID);
                                            params.put("Type", type);
                                            params.put("DVD", dvd);
                                            params.put("BoxOffice", boxOffice);
                                            params.put("Production", production);
                                            params.put("Website", website);


                                            for (Map.Entry<String, String> entry : params.entrySet()) {
                                                String key = entry.getKey();
                                                String value = entry.getValue();
                                                String text = key + " : " + value + "\n\n";
                                                txt_result.append(text);
                                            }

                                            videoModel = new VideoModel();

                                            videoModel.videoName = videoName;
                                            videoModel.year = year;
                                            videoModel.rated = rated;
                                            videoModel.released = released;
                                            videoModel.runtime = runtime;
                                            videoModel.genre = genre;
                                            videoModel.director = director;
                                            videoModel.actors = actors;
                                            videoModel.plot = plot;
                                            videoModel.writer = writer;
                                            videoModel.language = language;
                                            videoModel.wards = wards;
                                            videoModel.poster = poster;
                                            videoModel.metascore = metascore;
                                            videoModel.imdbRating = imdbRating;
                                            videoModel.imdbVotes = imdbVotes;
                                            videoModel.imdbID = imdbID;
                                            videoModel.type = type;
                                            videoModel.dvd = dvd;
                                            videoModel.boxOffice = boxOffice;
                                            videoModel.production = production;
                                            videoModel.website = website;

                                            if (!poster.isEmpty()) {
                                                img_poster.setVisibility(View.VISIBLE);
                                                Picasso.get().load(poster).into(img_poster);
                                            }


                                        } else {
                                            Toast.makeText(getApplicationContext(), "Video Not Found!", Toast.LENGTH_SHORT).show();
                                            videoModel = null;
                                            return;
                                        }


                                    } catch (JSONException ex) {
                                        videoModel = null;
                                        ex.printStackTrace();
                                    }


                                }
                            }
                        });


            }
        });


    }
}
