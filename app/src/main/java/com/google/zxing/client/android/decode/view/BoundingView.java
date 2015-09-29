package com.google.zxing.client.android.decode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.client.android.decode.CameraManager;

/**
 * View for displaying bounds for active camera region
 */
public class BoundingView extends View {
    /**
     * Camera manager
     */
    private CameraManager cameraManager;

    public BoundingView(Context context) {
        super(context);
    }

    public BoundingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets camera manger
     * @param cameraManager
     */
    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (cameraManager != null) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.parseColor("#00000000"));

//             Rect boundingRect = cameraManager.getBoundingRectUi(canvas.getWidth(), canvas.getHeight());
            Rect boundingRect = cameraManager.getBoundingRect();
            canvas.drawRect(boundingRect, paint);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.parseColor("#44FF0000"));
            paint.setStrokeWidth(5);
            int incY = (canvas.getHeight() - boundingRect.height())/2;
            int incX = (canvas.getWidth() - boundingRect.width())/2;

            int y = (boundingRect.height()/2) + incY;
            int x = boundingRect.width() + incX;
            canvas.drawLine(incX, y, x, y, paint);
            canvas.drawRect(new Rect(incX, boundingRect.top, x, boundingRect.bottom), paint);
        }
    }
}