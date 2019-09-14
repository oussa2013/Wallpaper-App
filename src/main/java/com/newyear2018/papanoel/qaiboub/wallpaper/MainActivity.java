package com.newyear2018.papanoel.qaiboub.wallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;











public class MainActivity extends AppCompatActivity {




   Integer[] myPicture = {R.drawable.qaiboub_1 ,R.drawable.qaiboub_2,
           R.drawable.qaiboub_3 ,R.drawable.qaiboub_4,
           R.drawable.qaiboub_5 ,R.drawable.qaiboub_6,
           R.drawable.qaiboub_7 ,R.drawable.qaiboub_8,
           R.drawable.qaiboub_9 ,R.drawable.qaiboub_10,
           R.drawable.qaiboub_11 ,R.drawable.qaiboub_12,
           R.drawable.qaiboub_13 ,R.drawable.qaiboub_14,
           R.drawable.qaiboub_15 ,R.drawable.qaiboub_16,
           R.drawable.qaiboub_17 ,R.drawable.qaiboub_18,
           R.drawable.qaiboub_19 ,R.drawable.qaiboub_20,
           R.drawable.qaiboub_21 ,R.drawable.qaiboub_22,
           R.drawable.qaiboub_23 ,R.drawable.qaiboub_24,
           R.drawable.qaiboub_25 ,R.drawable.qaiboub_26,
           R.drawable.qaiboub_27 ,R.drawable.qaiboub_28,
           R.drawable.qaiboub_29 ,R.drawable.qaiboub_30,
           R.drawable.qaiboub_31 ,R.drawable.qaiboub_32,
           R.drawable.qaiboub_33 ,R.drawable.qaiboub_34,
           R.drawable.qaiboub_35 ,R.drawable.qaiboub_36,
           R.drawable.qaiboub_37 ,R.drawable.qaiboub_38,
           R.drawable.qaiboub_39 ,R.drawable.qaiboub_40,
           R.drawable.qaiboub_41 ,R.drawable.qaiboub_42,
           R.drawable.qaiboub_43 ,R.drawable.qaiboub_44,
           R.drawable.qaiboub_45 ,R.drawable.qaiboub_46,
           R.drawable.qaiboub_47 ,R.drawable.qaiboub_48,
           R.drawable.qaiboub_49 ,R.drawable.qaiboub_50,
           R.drawable.qaiboub_51
   };
    GridView myGridV;
    ImageView myImageV;
    Drawable myDrawble;
    WallpaperManager myWallpaperM ;


    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//banner ads

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//ads to 10 seconds
        prepareAd();

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            public void run() {
                Log.i("hello", "world");
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG"," Interstitial not loaded");
                        }

                        prepareAd();


                    }
                });

            }
        }, 10, 10, TimeUnit.SECONDS);



        myGridV = findViewById(R.id.gridV);
        myImageV = findViewById(R.id.imageV);

      myGridV.setAdapter(new PicturesAdapter(getApplicationContext()));

        SetWallpaper();

    }


//function for ads
public void  prepareAd(){

    mInterstitialAd = new InterstitialAd(this);
    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
    mInterstitialAd.loadAd(new AdRequest.Builder().build());
}


    private void SetWallpaper(){

        myWallpaperM = WallpaperManager.getInstance(getApplicationContext());
        myDrawble = myWallpaperM.getDrawable();
        myImageV.setImageDrawable(myDrawble);

    }

    public class PicturesAdapter extends BaseAdapter {

        Context myCntx;


        public PicturesAdapter(Context applicationContext) {

            myCntx = applicationContext;
        }

        @Override
        public int getCount() {
            return myPicture.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            final ImageView gridImageV;

            if (view == null){


            gridImageV = new ImageView(myCntx);
            gridImageV.setLayoutParams(new GridView.LayoutParams(512 , 640));
            gridImageV.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            else {


                gridImageV = (ImageView) view;
            }

            gridImageV.setImageResource(myPicture [i]);



            gridImageV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        myWallpaperM.setResource(myPicture [i]);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    SetWallpaper();

                }
            });

            return gridImageV;
        }
    }

}
