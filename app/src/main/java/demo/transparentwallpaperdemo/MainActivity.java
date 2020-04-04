package demo.transparentwallpaperdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    String[] imgUrl = {
            "https://fsb.zobj.net/crop.php?r=7TaarmTBWNoz3Ni3q_4RHmMokv4vvtevmhHZQfJetm3T4sbY0w7Sw8_2BFe8Hl70MPK8aCooRUZSRJwQF5ifpGhX2HFO5WQbwSneGKNo_KA4rS1e-fqgMnlfVon_X6SBeS045OZy1glXrXED",
            "https://fsa.zobj.net/crop.php?r=O5XDFCO5g2mTYJjL4Dsb8Hnl6n_SUHe-Mhr04MtBbW8Odnl_rSddiLU2yMB2z-X8zqnzpuWN-KCDrORRn_v4VM30w4SDBbvTx2VvSIIhLG5wfhfmNFngcCNoyKZvNsTNgszBo8IZR3-OIknA",
            "https://fsa.zobj.net/crop.php?r=mSwaFN6BST0BW5uhEQNJBBMyETyuDz_Gwd_61XsATro59t21YOomy_eDmqb8BXvdWSP6-g4rfSR3sb3m_ONtNEPLYFguxuBXJU3o9fJwe9vnDm6O8M95LYaWfJhndWaE22l8jhtVy3YVY_Xt",
            "https://mobilehd.blob.core.windows.net/main/2017/02/girl-sexy-black-swimsuit-look-1080x1920.jpg",
            "https://mobilehd.blob.core.windows.net/main/2018/11/ratajkowski-emily-model-girl-lingerie-violet-sexy-pretty-wall.jpg",
            "https://mobilehd.blob.core.windows.net/main/2018/11/sexy-woman-nude-fashion-lingerie-girl-erotic-model-glamour-portrait-monochrome-skin.jpg",
            "https://c4.wallpaperflare.com/wallpaper/74/899/631/eva-green-sexy-wallpaper-preview.jpg",
            "https://c4.wallpaperflare.com/wallpaper/1012/636/935/sexy-girl-image-2880x1800-wallpaper-preview.jpg",
            "https://c4.wallpaperflare.com/wallpaper/252/207/781/sensuality-sensual-sexy-woman-girl-shorts-evelyn-sharma-legs-belly-tummy-top-wallpaper-preview.jpg",
            "https://c4.wallpaperflare.com/wallpaper/335/207/603/girl-blonde-model-room-wallpaper-preview.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vpager);
        viewPager.setAdapter(new ViewPagerAdapter(this,imgUrl));


    }

    public void SetWall(View view){
        int pos = viewPager.getCurrentItem();

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(imgUrl[pos])
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try{
                            WallpaperManager.getInstance(MainActivity.this).setBitmap(resource);

                        }catch (Exception e){}
                    }
                });
    }

    public class ViewPagerAdapter extends PagerAdapter{

        Context context;
        String[] imgUrl;
        LayoutInflater inflater;

        public ViewPagerAdapter(Context mcontext, String[] imgurl){
            this.context = mcontext;
            this.imgUrl = imgurl;
            inflater = LayoutInflater.from(context);

        }
        @Override
        public int getCount() {
            return imgUrl.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = inflater.inflate(R.layout.img_layout,container,false);
            ImageView imageView = view.findViewById(R.id.imgv);

            Picasso.get()
                    .load(imgUrl[position])
                    .into(imageView);

            container.addView(imageView);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }
}
