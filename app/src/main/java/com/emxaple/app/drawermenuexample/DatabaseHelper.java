package com.emxaple.app.drawermenuexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_videos";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "tbl_video";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "Title";
    private static final String COL_YEAR = "Year";
    private static final String COL_RATED = "Rated";
    private static final String COL_RELEASED = "Released";
    private static final String COL_RUNTIME = "Runtime";
    private static final String COL_GENRE = "Genre";
    private static final String COL_DIRECTOR = "Director";
    private static final String COL_ACTORS = "Actors";
    private static final String COL_PLOT = "Plot";
    private static final String COL_WRITER = "Writer";
    private static final String COL_LANGUAGE = "Language";
    private static final String COL_AWARDS = "Awards";
    private static final String COL_POSTER = "Poster";
    private static final String COL_METASCORE = "Metascore";
    private static final String COL_IMDBRATING = "imdbRating";
    private static final String COL_IMDBVOTES = "imdbVotes";
    private static final String COL_IMDBID = "imdbID";
    private static final String COL_TYPE = "Video_Type";
    private static final String COL_DVD = "DVD";
    private static final String COL_BOX_OFFICE = "BoxOffice";
    private static final String COL_PRODUCTION = "Production";
    private static final String COL_WEBSITE = "Website";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT,"
                + COL_YEAR + " TEXT,"
                + COL_RATED + " TEXT,"
                + COL_RELEASED + " TEXT,"
                + COL_RUNTIME + " TEXT,"
                + COL_GENRE + " TEXT,"
                + COL_DIRECTOR + " TEXT,"
                + COL_ACTORS + " TEXT,"
                + COL_PLOT + " TEXT,"
                + COL_WRITER + " TEXT,"
                + COL_LANGUAGE + " TEXT,"
                + COL_AWARDS + " TEXT,"
                + COL_POSTER + " TEXT,"
                + COL_METASCORE + " TEXT,"
                + COL_IMDBRATING + " TEXT,"
                + COL_IMDBVOTES + " TEXT,"
                + COL_IMDBID + " TEXT,"
                + COL_TYPE + " TEXT,"
                + COL_DVD + " TEXT,"
                + COL_BOX_OFFICE + " TEXT,"
                + COL_PRODUCTION + " TEXT,"
                + COL_WEBSITE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addVideo(VideoModel videoModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITLE, videoModel.videoName);
        contentValues.put(COL_YEAR, videoModel.year);
        contentValues.put(COL_RATED, videoModel.rated);
        contentValues.put(COL_RELEASED, videoModel.released);
        contentValues.put(COL_RUNTIME, videoModel.runtime);
        contentValues.put(COL_GENRE, videoModel.genre);
        contentValues.put(COL_DIRECTOR, videoModel.director);
        contentValues.put(COL_ACTORS, videoModel.actors);
        contentValues.put(COL_PLOT, videoModel.plot);
        contentValues.put(COL_WRITER, videoModel.writer);
        contentValues.put(COL_LANGUAGE, videoModel.language);
        contentValues.put(COL_AWARDS, videoModel.wards);
        contentValues.put(COL_POSTER, videoModel.poster);
        contentValues.put(COL_METASCORE, videoModel.metascore);
        contentValues.put(COL_IMDBRATING, videoModel.imdbRating);
        contentValues.put(COL_IMDBVOTES, videoModel.imdbVotes);
        contentValues.put(COL_IMDBID, videoModel.imdbID);
        contentValues.put(COL_TYPE, videoModel.type);
        contentValues.put(COL_DVD, videoModel.dvd);
        contentValues.put(COL_BOX_OFFICE, videoModel.boxOffice);
        contentValues.put(COL_PRODUCTION, videoModel.production);
        contentValues.put(COL_WEBSITE, videoModel.website);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }

    public ArrayList<VideoModel> getVideos() {

        ArrayList<VideoModel> videos = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                VideoModel videoModel = new VideoModel();
                String id = cursor.getString(cursor.getColumnIndex(COL_ID));
                videoModel.videoName = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                videoModel.year = cursor.getString(cursor.getColumnIndex(COL_YEAR));
                videoModel.rated = cursor.getString(cursor.getColumnIndex(COL_RATED));
                videoModel.released = cursor.getString(cursor.getColumnIndex(COL_RELEASED));
                videoModel.runtime = cursor.getString(cursor.getColumnIndex(COL_RUNTIME));
                videoModel.genre = cursor.getString(cursor.getColumnIndex(COL_GENRE));
                videoModel.director = cursor.getString(cursor.getColumnIndex(COL_DIRECTOR));
                videoModel.actors = cursor.getString(cursor.getColumnIndex(COL_ACTORS));
                videoModel.plot = cursor.getString(cursor.getColumnIndex(COL_PLOT));
                videoModel.writer = cursor.getString(cursor.getColumnIndex(COL_WRITER));
                videoModel.language = cursor.getString(cursor.getColumnIndex(COL_LANGUAGE));
                videoModel.wards = cursor.getString(cursor.getColumnIndex(COL_AWARDS));
                videoModel.poster = cursor.getString(cursor.getColumnIndex(COL_POSTER));
                videoModel.metascore = cursor.getString(cursor.getColumnIndex(COL_METASCORE));
                videoModel.imdbRating = cursor.getString(cursor.getColumnIndex(COL_IMDBRATING));
                videoModel.imdbVotes = cursor.getString(cursor.getColumnIndex(COL_IMDBVOTES));
                videoModel.imdbID = cursor.getString(cursor.getColumnIndex(COL_IMDBID));
                videoModel.type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                videoModel.dvd = cursor.getString(cursor.getColumnIndex(COL_DVD));
                videoModel.boxOffice = cursor.getString(cursor.getColumnIndex(COL_BOX_OFFICE));
                videoModel.production = cursor.getString(cursor.getColumnIndex(COL_PRODUCTION));
                videoModel.website = cursor.getString(cursor.getColumnIndex(COL_WEBSITE));
                videos.add(videoModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return videos;

    }


}
