package com.accintern.ricardarmankuodis.scrollingapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener {

    private NestedScrollView mNestedScrollView;
    private boolean isTop = true;
    private FloatingActionButton fabUp;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fabUp = (FloatingActionButton) findViewById(R.id.fabUp);
        fabUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNestedScrollView.fullScroll(View.FOCUS_UP);
                view.setVisibility(View.INVISIBLE);
            }
        });

        mCoordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinator);
        mNestedScrollView= (NestedScrollView) findViewById(R.id.scrollView);
        mNestedScrollView.setOnScrollChangeListener(this
        );


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("Collapsing Toolbar");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(mCoordinatorLayout,"SNACKBAR!",Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // code
                    Snackbar.make(mCoordinatorLayout,"Still failed!",Snackbar.LENGTH_LONG).show();
                }
            }).show();
            return true;
        }else if(id== R.id.toTop){
            // not animated
//				mNestedScrollView.scrollTo(0,0);
            // animated
            // FIXME ne skrollit prjamo do konca
            // FIXME ne rabotaet, esli nazhali rjadom s toolbar'om (pri melkom scrolle)
            mNestedScrollView.fullScroll(View.FOCUS_UP);
            Log.d("MainActivity", "Scroll Y after: " + mNestedScrollView.getScrollY());
//				isTop=true;
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("MainActivity", "onPrepareOptionsMenu()");
        if (isTop) {
            menu.removeItem(R.id.toTop);
        } else {
        }
        return true;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.d("MainActivity","scrollY="+scrollY);
        if (scrollY <= 2) {
//			Log.d("MainActivity","scrollY=0");
            isTop = true;
            fabUp.setVisibility(View.INVISIBLE);
            invalidateOptionsMenu();
        } else if (isTop == true) {
            invalidateOptionsMenu();
            isTop = false;
        }
//			Log.d("MainActivity","scrollY>0");
    }

}
