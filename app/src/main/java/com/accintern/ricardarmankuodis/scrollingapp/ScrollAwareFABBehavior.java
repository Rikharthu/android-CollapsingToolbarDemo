package com.accintern.ricardarmankuodis.scrollingapp;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    // Because we are defining this behavior statically within the XML,
    // we must also implement a constructor to enable layout inflation to work correctly.
    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {

        //  Currently, the default behavior is used for the Floating Action Button
        // to make room for the Snackbar as shown in this video. We want to extend
        // this behavior to signal that we wish to handle scroll events in the vertical direction:
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                // we do not want to override default behavior to snackbar
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        // call super!
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);

        // check Y position and determine whether to animate this view
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            // going down and button is still visible
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // going up, but button is invisible atm
            child.show();
        }
    }

}