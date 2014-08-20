package com.example.nino.androiddraganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    ImageView img;
    private static final String IMAGEVIEW_TAG = "Android Logo";
    String msg;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.iv_logo);
        img.setTag(IMAGEVIEW_TAG);

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeType = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(), mimeType, item);

                // Instantiate the drag shadow builder
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);

                // start drag
                v.startDrag(dragData, shadowBuilder, null, 0);
                return true;
            }
        });

        img.setOnDragListener(new View.OnDragListener(){

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int x_cord = (int) v.getX();
                int y_cord = (int) v.getY();
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                        layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        // we are not doing anything for this event
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();

                        Log.d(msg,"X :" + Integer.toString(x_cord) + "AND Y :"+ Integer.toString(y_cord));
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "Action is DragEvent.ACTION_DROP");
                        // do nothing
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
