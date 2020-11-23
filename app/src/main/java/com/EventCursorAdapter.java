package com;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sql_ex.R;

public class EventCursorAdapter extends CursorAdapter {
    public EventCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super( context, c, autoRequery );
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from( context ).inflate( R.layout.event_item, parent,false);
    }



    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titletext = view.findViewById( R.id.event_title_text );
        TextView datetext = view.findViewById( R.id.event_date_text );
        ImageView img = view.findViewById( R.id.event_img_holder );

        titletext.setText(cursor.getString( cursor.getColumnIndex( DBhelper.TITLE_COL ) ));
        datetext.setText(cursor.getString(cursor.getColumnIndex(DBhelper.DATE_COL) ));
        img.setImageResource(cursor.getInt(cursor.getColumnIndex(DBhelper.IMAGE_ID_COL) ));

    }
}


