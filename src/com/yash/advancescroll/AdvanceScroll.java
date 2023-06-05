package com.yash.advancescroll;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.*;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollStateListener;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class AdvanceScroll extends AndroidNonvisibleComponent {

  public AdvanceScroll(ComponentContainer container) {
    super(container.$form());
  }

  @SimpleFunction(description = "Add over scroll effect ")
  public void AddOverScroll(AndroidViewComponent hvArrangement) {
    View v1 = hvArrangement.getView();
    IOverScrollDecor decor = null;
    try {
      if (v1 instanceof ScrollView) {
        decor = OverScrollDecoratorHelper.setUpOverScroll((ScrollView) v1);
      } else if (v1 instanceof HorizontalScrollView) {
        decor = OverScrollDecoratorHelper.setUpOverScroll((HorizontalScrollView) v1);
      } else {
        Toast.makeText(form, "Only Valid Views Allowed", Toast.LENGTH_SHORT).show();
      }
    } catch (Exception e) {
      ErrorOccured(e.toString());
    }
    decor.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
      @Override
      public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
        onOverScrollingChange(offset);
      }
    });


   v1.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
     @Override
     public void onScrollChanged() {
       int scrollY = v1.getScrollY();
       onScrollchanging(scrollY);
     }
   });

    decor.setOverScrollStateListener(new IOverScrollStateListener() {
      @Override
      public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {

      }
    });
  }

@SimpleFunction(description = "")
public void scrollTo(int scrollto,AndroidViewComponent hvArrangement){
  View v1 = hvArrangement.getView();
  v1.scrollTo(0,scrollto);
}

  @SimpleFunction(description = "")
  public float getscrollY(AndroidViewComponent hvArrangement){
    View v1 = hvArrangement.getView();
    return v1.getScrollY();
  }
  @SimpleEvent(description = "")
  public void onScrollchanging(int scrollY) {
    EventDispatcher.dispatchEvent(this, "onScrollchanging", scrollY);
  }

  @SimpleProperty(description = "")
  public int ORIENTATION_HORIZONTAL() {
    return 1;
  }

  @SimpleProperty(description = "")
  public int ORIENTATION_VERTICAL() {
    return 0;
  }

  @SimpleFunction(description = "")
  public void AddEffectToComponent(AndroidViewComponent view, int orientation) {
    View v1 = view.getView();
    IOverScrollDecor decor1 = null;
    decor1 = OverScrollDecoratorHelper.setUpStaticOverScroll(v1, orientation);
    decor1.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
      @Override
      public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
        onOverScrollingChange(offset);
      }
    });
  }

  @SimpleEvent(description = "")
  public void ErrorOccured(String error) {
    EventDispatcher.dispatchEvent(this, "ErrorOccured", error);
  }

  @SimpleEvent(description = "")
  public void onOverScrollingChange(float offset) {
    EventDispatcher.dispatchEvent(this, "onOverScrollingChange", offset);
  }


//  @SimpleEvent(description = "")
//  public void onOverScrollingChange(float offset) {
//    EventDispatcher.dispatchEvent(this, "onOverScrollingChange", offset);
//  }

  @SimpleFunction(description = "")
  public void SetThumb(HVArrangement arrangement, Object startColor, Object endColor,int radius) {
    View v1 = arrangement.getView();
    GradientDrawable thumb;
    if (startColor.toString().contains("#") && endColor.toString().contains("#")) {
      thumb = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]
              {Color.parseColor((String) startColor), Color.parseColor((String) endColor)});

    } else {
      thumb = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]
              {Integer.parseInt(startColor.toString()), Integer.parseInt(endColor.toString())});
    }
    thumb.setCornerRadius(radius);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      if (v1 instanceof ScrollView) {
        ScrollView sv = (ScrollView) v1;
        sv.setVerticalScrollbarThumbDrawable(thumb);
      } else if (v1 instanceof HorizontalScrollView) {
        HorizontalScrollView hsv = (HorizontalScrollView) v1;
        hsv.setHorizontalScrollBarEnabled(true);
        hsv.setHorizontalScrollbarThumbDrawable(thumb);
      }
    }

  }

  @SimpleFunction(description = "")
  public void SetTrack(HVArrangement arrangement, Object startColor, Object endColor,int radius) {
    View v1 = arrangement.getView();
    GradientDrawable thumb;
    if (startColor.toString().contains("#") && endColor.toString().contains("#")) {
      thumb = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]
              {Color.parseColor((String) startColor), Color.parseColor((String) endColor)});
    } else {
      thumb = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]
              {Integer.parseInt(startColor.toString()), Integer.parseInt(endColor.toString())});
    }
    thumb.setCornerRadius(radius);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      if (v1 instanceof ScrollView) {
        ScrollView sv = (ScrollView) v1;
        sv.setVerticalScrollbarTrackDrawable(thumb);
      } else if (v1 instanceof HorizontalScrollView) {
        HorizontalScrollView hsv = (HorizontalScrollView) v1;
        hsv.setHorizontalScrollBarEnabled(true);
        hsv.setHorizontalScrollbarTrackDrawable(thumb);
      }}}

  @SimpleFunction(description = "")
  public void SetThumbSize(HVArrangement arrangement,int size) {
    View v1 =  arrangement.getView();
    if (v1 instanceof ScrollView) {
      ScrollView sv = (ScrollView) v1;
      sv.setScrollBarSize(size);
    }
    else if (v1 instanceof HorizontalScrollView) {
      HorizontalScrollView hsv = (HorizontalScrollView)v1;
      hsv.setScrollBarSize(size);
    }

  }

  @SimpleFunction(description = "")
  public void SetEdgeColor(HVArrangement arrangement,Object color) {
    View v1 =  arrangement.getView();
    if (v1 instanceof ScrollView) {
      ScrollView sv = (ScrollView) v1;
      if (color.toString().contains("#")) {
        sv.setEdgeEffectColor(Color.parseColor((String) color));
      } else sv.setEdgeEffectColor(Integer.parseInt(color.toString()));

    }
    else if (v1 instanceof HorizontalScrollView) {
      HorizontalScrollView hsv = (HorizontalScrollView)v1;
      if (color.toString().contains("#")) {
        hsv.setEdgeEffectColor(Color.parseColor((String) color));
      } else hsv.setEdgeEffectColor(Integer.parseInt(color.toString()));
    }

  }


  @SimpleFunction(description = "")
  public void SetFadeDuration(HVArrangement arrangement,int duration) {
    View v1 =  arrangement.getView();
    if (v1 instanceof ScrollView) {
      ScrollView sv = (ScrollView) v1;
      sv.setScrollBarFadeDuration(duration);
    }
    else if (v1 instanceof HorizontalScrollView) {
      HorizontalScrollView hsv = (HorizontalScrollView)v1;
      hsv.setScrollBarFadeDuration(duration);
    }

  }



}
