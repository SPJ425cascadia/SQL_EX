package com.example.sql_ex;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.DBhelper;
import com.EventCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final Intent intent = new Intent(this, CreateEventActivity.class );
        FloatingActionButton fab = findViewById( R.id.add_event_btn );
        fab.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity( intent );
                eventList = findViewById( R.id.event_list );}

            protected void onResume() {

                DBhelper dbHelper = new DBhelper(MainActivity.this, DBhelper.DATABASE_NAME, null, 1 );
                SQLiteDatabase reader = dbHelper.getReadableDatabase();
                String[] columns = {"_id", DBhelper.TITLE_COL, DBhelper.DATE_COL, DBhelper.IMAGE_ID_COL};
                Cursor cursor = reader.query( DBhelper.TABLE_NAME,columns, null,null,null,null, null );
                EventCursorAdapter cursorAdapter = new EventCursorAdapter(MainActivity.this, cursor, true );
                eventList.setAdapter( cursorAdapter );
            }
        } );
    }
}


