package com.example.user.luckydrawlib.luckydraw.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;


import com.example.user.luckydrawlib.R;

import static android.content.Context.POWER_SERVICE;
import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by User on 11/18/2017.
 */

public class ObjectlDownView extends View implements SensorEventListener {

    private SensorManager mSensorManager;

    private PowerManager mPowerManager;

    private WindowManager mWindowManager;

    private Display mDisplay;

    private int mCenter;

    private Paint mBackgroundPaint;

    private int mPadding;

    private int mRadius;

    private Circle circle;

    private PowerManager.WakeLock mWakeLock;

    private float mStartAngle = 0;

    Activity activity;

    public void init() {
        mSensorManager = (SensorManager) activity.getSystemService(SENSOR_SERVICE);
        // Get an instance of the PowerManager
        mPowerManager = (PowerManager) activity.getSystemService(POWER_SERVICE);

        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) activity.getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        // Create a bright wake lock
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass()
                .getName());
    }


    // diameter of the balls in meters
    private static final float sBallDiameter = 0.004f;
    private static final float sBallDiameter2 = sBallDiameter * sBallDiameter;

    // friction of the virtual table and air
    private static final float sFriction = 0.1f;

    private Sensor mAccelerometer;
    private long mLastT;
    private float mLastDeltaT;

    private float mXDpi;
    private float mYDpi;
    private float mMetersToPixelsX;
    private float mMetersToPixelsY;
    private Bitmap mBitmap;
    private Bitmap mWood;
    private float mXOrigin;
    private float mYOrigin;
    private float mSensorX;
    private float mSensorY;
    private long mSensorTimeStamp;
    private long mCpuTimeStamp;
    private float mHorizontalBound;
    private float mVerticalBound;
    private final ObjectlDownView.ParticleSystem mParticleSystem = new ObjectlDownView.ParticleSystem();

    /*
     * Each of our particle holds its previous and current position, its
     * acceleration. for added realism each particle has its own friction
     * coefficient.
     */
    class Particle {
        private float mPosX;
        private float mPosY;
        private float mAccelX;
        private float mAccelY;
        private float mLastPosX;
        private float mLastPosY;
        private float mOneMinusFriction;

        Particle() {
            // make each particle a bit different by randomizing its
            // coefficient of friction
            final float r = ((float) Math.random() - 0.5f) * 0.2f;
            mOneMinusFriction = 1.0f - sFriction + r;
        }

        public void computePhysics(float sx, float sy, float dT, float dTC) {
            // Force of gravity applied to our virtual object
            final float m = 1000.0f; // mass of our virtual object
            final float gx = -sx * m;
            final float gy = -sy * m;

                /*
                 * ·F = mA <=> A = ·F / m We could simplify the code by
                 * completely eliminating "m" (the mass) from all the equations,
                 * but it would hide the concepts from this sample code.
                 */
            final float invm = 1.0f / m;
            final float ax = gx * invm;
            final float ay = gy * invm;

            /*
             * Time-corrected Verlet integration The position Verlet
             * integrator is defined as x(t+Æt) = x(t) + x(t) - x(t-Æt) +
             * a(t)Ætö2 However, the above equation doesn't handle variable
             * Æt very well, a time-corrected version is needed: x(t+Æt) =
             * x(t) + (x(t) - x(t-Æt)) * (Æt/Æt_prev) + a(t)Ætö2 We also add
             * a simple friction term (f) to the equation: x(t+Æt) = x(t) +
             * (1-f) * (x(t) - x(t-Æt)) * (Æt/Æt_prev) + a(t)Ætö2
             */
            final float dTdT = dT * dT;
            final float x = mPosX + mOneMinusFriction * dTC * (mPosX - mLastPosX) + mAccelX
                    * dTdT;
            final float y = mPosY + mOneMinusFriction * dTC * (mPosY - mLastPosY) + mAccelY
                    * dTdT;
            mLastPosX = mPosX;
            mLastPosY = mPosY;
            mPosX = x;
            mPosY = y;
            mAccelX = ax;
            mAccelY = ay;
        }

        /*
         * Resolving constraints and collisions with the Verlet integrator
         * can be very simple, we simply need to move a colliding or
         * constrained particle in such way that the constraint is
         * satisfied.
         */
        public void resolveCollisionWithBounds() {
            final float xmax = mHorizontalBound;
            final float ymax = mVerticalBound;
            final float x = mPosX;
            final float y = mPosY;
            if (x > xmax) {
                mPosX = xmax;
            } else if (x < -xmax) {
                mPosX = -xmax;
            }
            if (y > ymax) {
                mPosY = ymax;
            } else if (y < -ymax) {
                mPosY = -ymax;
            }
        }
    }

    /*
     * A particle system is just a collection of particles
     */
    class ParticleSystem {
        static final int NUM_PARTICLES = 15;
        private ObjectlDownView.Particle mBalls[] = new ObjectlDownView.Particle[NUM_PARTICLES];

        ParticleSystem() {
                /*
                 * Initially our particles have no speed or acceleration
                 */
            for (int i = 0; i < mBalls.length; i++) {
                mBalls[i] = new ObjectlDownView.Particle();
            }
        }

        /*
         * Update the position of each particle in the system using the
         * Verlet integrator.
         */
        private void updatePositions(float sx, float sy, long timestamp) {
            final long t = timestamp;
            if (mLastT != 0) {
                final float dT = (float) (t - mLastT) * (1.0f / 1000000000.0f);
                if (mLastDeltaT != 0) {
                    final float dTC = dT / mLastDeltaT;
                    final int count = mBalls.length;
                    for (int i = 0; i < count; i++) {
                        ObjectlDownView.Particle ball = mBalls[i];
                        ball.computePhysics(sx, sy, dT, dTC);
                    }
                }
                mLastDeltaT = dT;
            }
            mLastT = t;
        }

        /*
         * Performs one iteration of the simulation. First updating the
         * position of all the particles and resolving the constraints and
         * collisions.
         */
        public void update(float sx, float sy, long now) {
            // update the system's positions
            updatePositions(sx, sy, now);

            // We do no more than a limited number of iterations
            final int NUM_MAX_ITERATIONS = 10;

                /*
                 * Resolve collisions, each particle is tested against every
                 * other particle for collision. If a collision is detected the
                 * particle is moved away using a virtual spring of infinite
                 * stiffness.
                 */
            boolean more = true;
            final int count = mBalls.length;
            for (int k = 0; k < NUM_MAX_ITERATIONS && more; k++) {
                more = false;
                for (int i = 0; i < count; i++) {
                    ObjectlDownView.Particle curr = mBalls[i];
                    for (int j = i + 1; j < count; j++) {
                        ObjectlDownView.Particle ball = mBalls[j];
                        float dx = ball.mPosX - curr.mPosX;
                        float dy = ball.mPosY - curr.mPosY;
                        float dd = dx * dx + dy * dy;
                        // Check for collisions
                        if (dd <= sBallDiameter2) {
                                /*
                                 * add a little bit of entropy, after nothing is
                                 * perfect in the universe.
                                 */
                            dx += ((float) Math.random() - 0.5f) * 0.0001f;
                            dy += ((float) Math.random() - 0.5f) * 0.0001f;
                            dd = dx * dx + dy * dy;
                            // simulate the spring
                            final float d = (float) Math.sqrt(dd);
                            final float c = (0.5f * (sBallDiameter - d)) / d;
                            curr.mPosX -= dx * c;
                            curr.mPosY -= dy * c;
                            ball.mPosX += dx * c;
                            ball.mPosY += dy * c;
                            more = true;
                        }
                    }
                        /*
                         * Finally make sure the particle doesn't intersects
                         * with the walls.
                         */
                    curr.resolveCollisionWithBounds();
                }
            }
        }

        public int getParticleCount() {
            return mBalls.length;
        }

        public float getPosX(int i) {
            return mBalls[i].mPosX;
        }

        public float getPosY(int i) {
            return mBalls[i].mPosY;
        }
    }

    public void startSimulation() {
            /*
             * It is not necessary to get accelerometer events at a very high
             * rate, by using a slower rate (SENSOR_DELAY_UI), we get an
             * automatic low-pass filter, which "extracts" the gravity component
             * of the acceleration. As an added benefit, we use less power and
             * CPU resources.
             */
        // mSensorManager.registerListener( this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        invalidate();
    }

    public void stopSimulation() {
        mSensorManager.unregisterListener(this);
    }

    public ObjectlDownView(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        init();
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mXDpi = metrics.xdpi;
        mYDpi = metrics.ydpi;
        mMetersToPixelsX = mXDpi / 0.0254f;
        mMetersToPixelsY = mYDpi / 0.0254f;

        // rescale the ball so it's about 0.5 cm on screen
        Bitmap ball = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ball);
        final int dstWidth = (int) (sBallDiameter * mMetersToPixelsX + 0.5f);
        final int dstHeight = (int) (sBallDiameter * mMetersToPixelsY + 0.5f);
        mBitmap = Bitmap.createScaledBitmap(ball, dstWidth, dstHeight, true);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        //mWood = BitmapFactory.decodeResource(getResources(), R.drawable.wood, opts);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // compute the origin of the screen relative to the origin of
        // the bitmap
        mXOrigin = (w - mBitmap.getWidth()) * 0.5f;
        mYOrigin = (h - mBitmap.getHeight()) * 0.5f;
        mHorizontalBound = ((w / mMetersToPixelsX - sBallDiameter) * 0.5f);
        mVerticalBound = ((h / mMetersToPixelsY - sBallDiameter) * 0.5f);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
            /*
             * record the accelerometer data, the event's timestamp as well as
             * the current time. The latter is needed so we can calculate the
             * "present" time during rendering. In this application, we need to
             * take into account how the screen is rotated with respect to the
             * sensors (which always return data in a coordinate space aligned
             * to with the screen in its native orientation).
             */

        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }

        mSensorTimeStamp = event.timestamp;
        mCpuTimeStamp = System.nanoTime();
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mPadding = getPaddingLeft() == 0 ? 10 : getPaddingLeft();
        mRadius = width - mPadding * 2;
        mCenter = width / 2;
        initCircle();
        setMeasuredDimension(width, width);
    }

    private void initCircle() {
        int width = getMeasuredWidth() == 0 ? getWidth() : getMeasuredWidth();
        int height = getMeasuredHeight() == 0 ? getHeight() : getMeasuredHeight();
        circle = new Circle(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float tmpAngle = mStartAngle;
        float sweepAngle = 360 / 8;
        drawBackgroundColor(canvas, activity.getResources().getColor(R.color.light_blue));

        if (circle == null) {
            initCircle();
        }

        for (int i = 0; i < 8; i++) {

/*             Bitmap bitmap = Bitmap.createBitmap(
                    2, // Width
                    2, // Height
                    Bitmap.Config.ARGB_8888 // Config
            );

           Canvas canvass = new Canvas(bitmap);
            canvass.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            int radius = Math.min(canvas.getWidth(),canvas.getHeight()/2);
            int padding = 5;

            canvass.drawCircle(
                    canvas.getWidth() / 2, // cx
                    canvas.getHeight() / 2, // cy
                    radius - padding, // Radius
                    paint // Paint
            );*/

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
            //Bitmap bitmap = LuckyWheelUtils.drawableToBitmap(R.drawable.ok);
            drawImage(canvas, tmpAngle, bitmap);
            tmpAngle += sweepAngle;
        }

            /*
             * draw the background
             */

        //canvas.drawBitmap(mWood, 0, 0, null);

            /*
             * compute the new position of our object, based on accelerometer
             * data and present time.
             */

        /*final ObjectlDownView.ParticleSystem particleSystem = mParticleSystem;
        final long now = mSensorTimeStamp + (System.nanoTime() - mCpuTimeStamp);
        final float sx = mSensorX;
        final float sy = mSensorY;

        particleSystem.update(sx, sy, now);

        final float xc = mXOrigin;
        final float yc = mYOrigin;
        final float xs = mMetersToPixelsX;
        final float ys = mMetersToPixelsY;
        final Bitmap bitmap = mBitmap;
        final int count = particleSystem.getParticleCount();
        for (int i = 0; i < count; i++) {
                *//*
                 * We transform the canvas so that the coordinate system matches
                 * the sensors coordinate system with the origin in the center
                 * of the screen and the unit is the meter.
                 *//*

            final float x = xc + particleSystem.getPosX(i) * xs;
            final float y = yc - particleSystem.getPosY(i) * ys;
            canvas.drawBitmap(bitmap, x, y, null);
        }

        Paint textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setStrokeWidth(1);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                getHeight() / 3, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR));

        textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,45,
                getResources().getDisplayMetrics()));

        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.

        canvas.drawText("Sanjay", xPos, yPos, textPaint);
        // and make sure to redraw asap
        invalidate();*/
    }

    private void drawImages(Canvas canvas, float tmpAngle, Bitmap bitmap) {
        int imgWidth = mRadius / 8;
        float angle = (float) ((tmpAngle + 360 / 8 / 2) * Math.PI / 180);
        int x = (int) (mCenter + mRadius / 1 / 2 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 1 / 2 * Math.sin(angle));
        int imageSize = imgWidth / 6;
        Rect rect = new Rect(x - imageSize, y - imageSize, x + imageSize, y + imageSize);
        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(ContextCompat.getColor(activity, R.color.white), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        //canvas.drawBitmap(bitmap, null, rect, paint);
        canvas.drawBitmap(bitmap, x, y, null);
    }

    /**
     * @param canvas
     * @param tmpAngle
     * @param bitmap
     */
    private void drawImage(Canvas canvas, float tmpAngle, Bitmap bitmap) {
        int imgWidth = mRadius / 8;
        float angle = (float) ((tmpAngle + 360 / 8 / 2) * Math.PI / 180);
        int x = (int) (mCenter + (mRadius - 20) / 2 * Math.cos(angle));
        int y = (int) (mCenter + (mRadius - 20) / 2 * Math.sin(angle));
        int imageSize = imgWidth / 8;
        Rect rect = new Rect(x - imageSize, y - imageSize, x + imageSize, y + imageSize);
        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(ContextCompat.getColor(activity, R.color.white), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, null, rect, paint);
        //canvas.drawBitmap(bitmap, null, rect, null);
    }


    private void drawBackgroundColor(Canvas canvas, int color) {
        if (color == -1)
            return;
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(color);
/*        mBackgroundPaint.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                getHeight() / 3, Color.YELLOW, Color.RED, Shader.TileMode.REPEAT));*/

        mBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(mCenter, mCenter, mCenter, mBackgroundPaint);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
