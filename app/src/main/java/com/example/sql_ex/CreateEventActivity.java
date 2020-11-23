package com.example.sql_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.DBhelper;

import java.util.HashMap;
import java.util.Map;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_event );
        final Spinner eventType = findViewById( R.id.event_image_type );
        ArrayAdapter<CharSequence> types =
                ArrayAdapter.createFromResource( this, R.array.event_type, android.R.layout.simple_spinner_item );
        eventType.setAdapter( types );


        DBhelper dbHelper = new DBhelper( this, DBhelper.DATABASE_NAME, null, 1 );
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Button createBtn = findViewById( R.id.create_btn );
        EditText title = findViewById( R.id.event_title_edit );
        EditText date = findViewById( R.id.event_date );

        Map<String, Integer> images = new HashMap<>();
        images.put( "party", R.drawable.party );
        images.put( "concert", R.drawable.concert );
        images.put( "gathering", R.drawable.business_meeting );
        images.put( "food", R.drawable.food );


        createBtn.setOnClickListener((View)-> {
            String titleStr = title.getText().toString();
            String dateStr = date.getText().toString();
            String typeKey = eventType.getSelectedItem().toString().toLowerCase();
            if (TextUtils.isEmpty( titleStr ) || TextUtils.isEmpty( dateStr ) || TextUtils.isEmpty( typeKey )) {
                Log.i( "DEBUG", "EMPTY VAL" );
                return;
            }
            Log.i("INFO", String.format( "Saving %s %s %S", titleStr, dateStr, typeKey));

            ContentValues cv = new ContentValues( );
            cv.put( DBhelper.TITLE_COL, titleStr );
            cv.put( DBhelper.DATE_COL, dateStr );
            cv.put( DBhelper.IMAGE_ID_COL, images.get( typeKey ) );

            db.insert( DBhelper.TABLE_NAME, null, cv );
            title.setText( "" );
            date.setText( "" );
            Toast.makeText( this, "Event Created", Toast.LENGTH_LONG ).show();
            db.close();
            onBackPressed();

        });

    }
}