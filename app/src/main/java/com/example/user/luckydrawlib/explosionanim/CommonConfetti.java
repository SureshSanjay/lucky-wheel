/**
 * Copyright (C) 2016 Robinhood Markets, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.user.luckydrawlib.explosionanim;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.ViewGroup;


import com.example.user.luckydrawlib.R;
import com.example.user.luckydrawlib.luckydraw.model.LuckyDrawModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonConfetti {
    private static int defaultConfettiSize;
    private static int defaultVelocitySlow;
    private static int defaultVelocityNormal;
    private static int defaultVelocityFast;
    private static int explosionRadius;

    private ConfettiManager confettiManager;

    private CommonConfetti(ViewGroup container) {
        ensureStaticResources(container);
    }


    /**
     * Configures a confetti manager that has confetti exploding out in all directions from the
     * provided x and y coordinates.
     *
     * @param snowflake the set of colors to colorize the confetti bitmaps.
     * @param container the container viewgroup to host the confetti animation.
     * @param x the x coordinate of the explosion source.
     * @param y the y coordinate of the explosion source.
     * @param snowflake
     * @return the created common confetti object.
     */
    public static CommonConfetti explosion(ViewGroup container, int x, int y, ArrayList<Integer> snowflake, Context context) {
        final CommonConfetti commonConfetti = new CommonConfetti(container);
        commonConfetti.configureExplosion(container, x, y, snowflake, context);
        return commonConfetti;
    }


    /**
     * Starts a one-shot animation that emits all of the confetti at once.
     *
     * @return the resulting {@link ConfettiManager} that's performing the animation.
     */
    public ConfettiManager oneShot() {
        return confettiManager.setNumInitialCount(30)
                .setEmissionDuration(100000)
                .animate();
    }

    private ConfettoGenerator getDefaultGenerator(Context context, ArrayList<Integer> snowflake) {
        final List<Bitmap> bitmaps = LuckyDrawModel.generateConfettiBitmaps(snowflake, context, defaultConfettiSize);
        final int numBitmaps = bitmaps.size();
        return new ConfettoGenerator() {
            @Override
            public Confetto generateConfetto(Random random) {
                return new BitmapConfetto(bitmaps.get(random.nextInt(numBitmaps)));
            }
        };
    }

    private void configureExplosion(ViewGroup container, int x, int y, ArrayList<Integer> snowflake, Context contexts) {
        final Context context = container.getContext();
        final ConfettoGenerator generator = getDefaultGenerator(contexts, snowflake);
        final ConfettiSource confettiSource = new ConfettiSource(x, y);

        confettiManager = new ConfettiManager(context, generator, confettiSource, container)
                .setTTL(8000)
                .setBound(new Rect(
                        x - 1000, y - 1000,
                        x + 1000, y + 1000
                ))
                .setVelocityX(0, defaultVelocityFast)
                .setVelocityY(0, defaultVelocityFast)
                .enableFadeOut(LuckyDrawModel.getDefaultAlphaInterpolator())
                .setInitialRotation(180, 180)
                .setRotationalAcceleration(180, 180)
                .setTargetRotationalVelocity(180);
    }

    private static void ensureStaticResources(ViewGroup container) {
        if (defaultConfettiSize == 0) {
            final Resources res = container.getResources();
            defaultConfettiSize = res.getDimensionPixelSize(R.dimen.default_confetti_size);
            defaultVelocitySlow = res.getDimensionPixelOffset(R.dimen.default_velocity_slow);
            defaultVelocityNormal = res.getDimensionPixelOffset(R.dimen.default_velocity_normal);
            defaultVelocityFast = res.getDimensionPixelOffset(R.dimen.default_velocity_fast);
            explosionRadius = res.getDimensionPixelOffset(R.dimen.default_explosion_radius);
        }
    }
}
