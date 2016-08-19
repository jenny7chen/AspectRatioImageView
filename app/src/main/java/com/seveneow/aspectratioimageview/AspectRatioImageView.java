package com.seveneow.aspectratioimageview; /**
 * Created by jennychen on 16/8/18.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.seveneow.aspectratioimageview.R;

public class AspectRatioImageView extends ImageView {
  private float imageRatio = -1;
  private float height = 0;

  public AspectRatioImageView(Context context) {
    super(context);
    readAttr(context, null);
  }

  public AspectRatioImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    readAttr(context, attrs);
  }

  private void readAttr(Context context, AttributeSet attrs) {
    TypedArray a = context.obtainStyledAttributes(attrs, new int[]{R.attr.image_aspect_ratio});
    imageRatio = a.getFloat(0, -1);
    a.recycle();
    invalidate();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec,
                           int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    Drawable image = getDrawable();
    if (imageRatio == -1) {
      imageRatio = image.getIntrinsicHeight() / image.getIntrinsicWidth();
    }

    height = width * imageRatio;
    setMeasuredDimension(width, (int) height);
  }
}