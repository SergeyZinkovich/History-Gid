package me.vdkgid.historygid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.view.View;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class InfoView extends View {

    String name;

    public InfoView(Context context, String name) {
        super(context);
        this.name = name;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(80);
        canvas.drawText(name, 300,300, paint);
        canvas.drawRect(0,0,200,200,paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        Bitmap sbitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
            canvas.drawBitmap(sbitmap,0,0,paint);
        super.onDraw(canvas);
    }
}