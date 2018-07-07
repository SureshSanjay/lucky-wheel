package com.example.user.luckydrawlib.luckydraw.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.luckydrawlib.R;
import com.example.user.luckydrawlib.explosionanim.CommonConfetti;
import com.example.user.luckydrawlib.luckydraw.model.CountDownAnimation;
import com.example.user.luckydrawlib.luckydraw.model.LuckyDrawModel;
import com.example.user.luckydrawlib.luckydraw.model.ObjectlDownView;
import com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint.LuckyWheelView;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.presenter.LuckdrawPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;


public class LuckyDrawActivity extends AppCompatActivity implements ILuckyDrawListener, CountDownAnimation.CountDownListener
         {

             String weelResponse = "{\n" +
                     "    \"StatusCode\": 302,\n" +
                     "    \"Status\": true,\n" +
                     "    \"Message\": \"Prize check completed.\",\n" +
                     "    \"Result\": {\n" +
                     "        \"WheelSettingId\": \"a978e162-7f41-48c8-a31d-daf4f4d4bf15\",\n" +
                     "        \"Color1\": \"#fe0000\",\n" +
                     "        \"Color2\": \"#ff7403\",\n" +
                     "        \"Color3\": \"#304fff\",\n" +
                     "        \"Color4\": \"#11df10\",\n" +
                     "        \"WinningColor\": \"#ffffff\",\n" +
                     "        \"SpinSpeed\": \"5\",\n" +
                     "        \"TextOrientation\": \"Horizontal\",\n" +
                     "        \"TextSize\": 13,\n" +
                     "        \"Duration\": 1,\n" +
                     "        \"TextColor\": \"#0f0101\",\n" +
                     "        \"PromotionPackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "        \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "        \"WheelDetails\": [\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"baa6ff27-c1d2-4370-9a44-9a0045e42c0b\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Text\",\n" +
                     "                \"DisplayName\": \"100\",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 1,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"e44fed79-e006-4653-b0b6-09bf43ee4614\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"baa6ff27-c1d2-4370-9a44-9a0045e42c0b\",\n" +
                     "                        \"CreatedDate\": \"2018-01-30T12:30:10.287\",\n" +
                     "                        \"DisplayOrder\": 1,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"9a81b262-9396-4186-b22e-fd284906212a\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"baa6ff27-c1d2-4370-9a44-9a0045e42c0b\",\n" +
                     "                        \"CreatedDate\": \"2018-01-30T12:32:49.147\",\n" +
                     "                        \"DisplayOrder\": 2,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"6cafb690-d555-4d74-a4f6-94ac7079a330\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Image\",\n" +
                     "                \"DisplayName\": \"Disko Stick \",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 2,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"65f1db14-005f-42f8-9c6d-854ca3e70bb0\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"6cafb690-d555-4d74-a4f6-94ac7079a330\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 2,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"83e246b1-fd6e-40bd-a74a-c5256267fe31\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"6cafb690-d555-4d74-a4f6-94ac7079a330\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 2,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"ee602fcd-842f-4769-aaf8-2b34d1d245d9\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Text\",\n" +
                     "                \"DisplayName\": \"15000\",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 3,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"3e5ea1cd-4ab3-4370-94d3-16a1ba2709ec\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"ee602fcd-842f-4769-aaf8-2b34d1d245d9\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 3,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"db311513-1c8f-4735-971f-83d2051c057e\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"ee602fcd-842f-4769-aaf8-2b34d1d245d9\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 3,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"ba1300a9-e1bc-4c63-8479-c93dd6692958\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Image\",\n" +
                     "                \"DisplayName\": \"Disko Stick \",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 4,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"b641f5b9-54fd-411d-a079-0b68abf25458\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"ba1300a9-e1bc-4c63-8479-c93dd6692958\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 4,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"ab63c895-1434-465c-a34b-98a096d90e12\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"ba1300a9-e1bc-4c63-8479-c93dd6692958\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 4,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"24d90edf-6974-4a30-97d6-e5d467346e5f\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Text\",\n" +
                     "                \"DisplayName\": \"2000\",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 5,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"866b1bdd-7a2f-4133-a34a-3a6c603fdea1\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"24d90edf-6974-4a30-97d6-e5d467346e5f\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 5,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"716368c8-7ff4-422c-8f2b-b6deb2fe7382\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"24d90edf-6974-4a30-97d6-e5d467346e5f\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 5,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"2c6e9acf-4989-4bee-a7e7-de549c06d8d9\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Text\",\n" +
                     "                \"DisplayName\": \"30 Kyats\",\n" +
                     "                \"Amount\": 30,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 6,\n" +
                     "                \"IsForWinning\": true,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"326e0720-8147-400c-970c-2822b6be5788\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"2c6e9acf-4989-4bee-a7e7-de549c06d8d9\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 6,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"0546feb0-337b-498f-9764-c3ba4a8a31d1\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"2c6e9acf-4989-4bee-a7e7-de549c06d8d9\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 6,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"204fb9e7-8cc2-4c7e-9481-5aed59145d06\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Text\",\n" +
                     "                \"DisplayName\": \"100\",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 7,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"aae3670f-c883-4586-9e46-9b0f3ca8ec55\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"204fb9e7-8cc2-4c7e-9481-5aed59145d06\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 7,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"5f9f7901-9483-4fa4-9bd2-c013efc42f33\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"204fb9e7-8cc2-4c7e-9481-5aed59145d06\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 7,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            },\n" +
                     "            {\n" +
                     "                \"WheelDetailId\": \"7de1878a-014c-478b-bb20-b134c7057958\",\n" +
                     "                \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                \"WheelDetailType\": \"Image\",\n" +
                     "                \"DisplayName\": \"Disko Stick \",\n" +
                     "                \"Amount\": 0,\n" +
                     "                \"ParentWheelDetailId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                     "                \"CreatedDate\": \"2018-01-10T17:14:09\",\n" +
                     "                \"DisplayOrder\": 8,\n" +
                     "                \"IsForWinning\": false,\n" +
                     "                \"WheelDetails\": [\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"58b7a821-9763-47d6-a0bb-29142a8506d7\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"20 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"7de1878a-014c-478b-bb20-b134c7057958\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 8,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    },\n" +
                     "                    {\n" +
                     "                        \"WheelDetailId\": \"3e737e54-9ac4-4797-b392-e76d288ae3b2\",\n" +
                     "                        \"PackageId\": \"fba6d9b0-4b47-4139-850d-6a140e988efa\",\n" +
                     "                        \"WheelDetailType\": \"Text\",\n" +
                     "                        \"DisplayName\": \"50 Lkhs\",\n" +
                     "                        \"Amount\": 0,\n" +
                     "                        \"ParentWheelDetailId\": \"7de1878a-014c-478b-bb20-b134c7057958\",\n" +
                     "                        \"CreatedDate\": \"2018-02-21T12:23:00\",\n" +
                     "                        \"DisplayOrder\": 8,\n" +
                     "                        \"IsForWinning\": false,\n" +
                     "                        \"WheelDetails\": []\n" +
                     "                    }\n" +
                     "                ]\n" +
                     "            }\n" +
                     "        ]\n" +
                     "    },\n" +
                     "    \"Results\": null\n" +
                     "}";


    CardView frameLayout;

    Activity activity;

    LuckyWheelView luckyWheelView;

    private ObjectlDownView mSimulationView;

    private FrameLayout circleView;

    private FrameLayout countDownView;

    /**
     * Toolbar object initialize
     */
    private Toolbar mToolbar;

    private Window window;

    private ILuckyDrawListener luckyDrawListener;

    private String luckyNumber;

    LuckyDrawResponse luckyDrawResponse;

    private CountDownAnimation countDownAnimation;

    TextView countdown;

    TextView spinTxt;

    ConstraintLayout wheelView;

    FrameLayout frameLayoutContainer;

    public ImageView handCursorView;

    private String scanTrackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_draw);

        frameLayout = findViewById(R.id.frame_layout);
        circleView =  findViewById(R.id.circle_views);
        countdown =  findViewById(R.id.textView);
        countDownView = (CardView) findViewById(R.id.frame_layout2);
        spinTxt =  findViewById(R.id.spinText);
        frameLayoutContainer =  findViewById(R.id.container);
        wheelView =  findViewById(R.id.circle_view);
        handCursorView =  findViewById(R.id.handCursor);
        activity = this;
        luckyDrawListener = this;
        final TextView spinText =  findViewById(R.id.spinText);
        spinText.setShadowLayer(2.0f, 0.0f, 0.0f, Color.GRAY);
        luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);

        mSimulationView = new ObjectlDownView(this, this);
        circleView.addView(mSimulationView);
        mSimulationView.startSimulation();
        Log.i("Response : ",""+weelResponse) ;
        //ObjectMapper mapper = new ObjectMapper();
        try {
            //luckyDrawResponse = mapper.readValue(weelResponse, LuckyDrawResponse.class);
        }catch (Exception e){

        }


            luckyDrawResponse = new Gson().fromJson(weelResponse, LuckyDrawResponse.class);
            generateTokenForAppSatistics();
           /*  luckyNumber = bundle.getString("data");
            scanTrackId = bundle.getString("scanTrackId");*/


        final float[] hsv;
        final int[] runColor = new int[1];
        hsv = new float[3]; // Transition color
        hsv[1] = 1;
        hsv[2] = 1;
        ValueAnimator anim = ValueAnimator.ofFloat(0.0f, 1.0f);
        anim.setDuration(325);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                hsv[0] = 360 * animation.getAnimatedFraction();
                runColor[0] = Color.HSVToColor(hsv);
                spinText.setTextColor(runColor[0]);
            }
        });
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
        toolbar();
        initCountDownAnimation();
        startCountDownAnimation();
    }

    /**
     * Set common tool bar for this activity
     */
    private void toolbar() {

    }



    public void generateTokenForAppSatistics() {
        LuckdrawPresenter luckdrawPresenter = new LuckdrawPresenter(this, luckyWheelView, luckyDrawListener);
        luckdrawPresenter.onRequestFromView(luckyDrawResponse);
    }

    public void verifyLuckyPrize() {
        LuckdrawPresenter luckdrawPresenter = new LuckdrawPresenter(this, luckyWheelView, luckyDrawListener);
        luckdrawPresenter.onRequestFromViewForVerify(luckyDrawResponse);
    }

    @Override
    public void onBackPressed() {
        //handler.removeCallbacks(runnable);
        //hideProgressDialog();
        LuckyDrawModel luckyDrawModel = new LuckyDrawModel();
        luckyDrawModel.finishHandler();
        finish();
        // put your code here...
    }


    @Override
    public void onDrawViewListener(LuckyDrawResponse luckyDrawResponse) {
    }

    @Override
    public void onDrawViewListener() {
        Glide.with(this).load(R.drawable.hand_icon_red).into(handCursorView);

        spinTxt.setText(getResources().getString(R.string.winning_prize));
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("LuckyDrawViewDetails");
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                frameLayout.setVisibility(View.GONE);

                if (!(activity).isFinishing()) {
/*                    LuckyConfirmFragment fragment2 = new LuckyConfirmFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.attach(fragment2);
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fragmentTransaction.add(R.id.container, fragment2, "LuckyDrawViewDetails");
                    fragmentTransaction.addToBackStack("luckyDraw");
                    fragmentTransaction.commit();*/
                }
            }
        };
        handler.postDelayed(runnable, 6000);

        getCommonConfetti();
        getCommonConfetti().oneShot();
    }

    private void initCountDownAnimation() {
        countDownAnimation = new CountDownAnimation(countdown, getStartCount());
        countDownAnimation.setCountDownListener(this);
    }

    private void startCountDownAnimation() {
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        countDownAnimation.setAnimation(animationSet);
        countDownAnimation.setStartCount(getStartCount());
        countDownAnimation.start();
    }

    private int getStartCount() {
        return 3;
    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {
        frameLayout.setVisibility(View.VISIBLE);
        countDownView.setVisibility(View.GONE);
    }

    private CommonConfetti getCommonConfetti() {
        Point centerPoint = getCenterPointOfView(frameLayout);
        final int centerX = centerPoint.x - 120;
        final int centerY = centerPoint.y;
        ArrayList<Integer> myImageList = new ArrayList<>();
        myImageList.add(R.drawable.blu);

        return CommonConfetti.explosion(frameLayout, centerX, centerY, myImageList, this);
    }

    private Point getCenterPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        int x = location[0] + view.getWidth() / 2;
        int y = location[1] + view.getHeight() / 7 * 2;
        return new Point(x, y);
    }


}
