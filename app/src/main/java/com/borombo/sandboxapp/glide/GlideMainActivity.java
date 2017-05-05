package com.borombo.sandboxapp.glide;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.borombo.sandboxapp.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GlideMainActivity extends AppCompatActivity {

    // ButterKnife Used
    @BindView(R.id.basicImage)
    ImageView basicImg;
    @BindView(R.id.resizedImage)
    ImageView resizedImg;
    @BindView(R.id.placeholderImage)
    ImageView placeholderImg;
    @BindView(R.id.croppedImage)
    ImageView croppedImg;
    @BindView(R.id.transformedImage)
    ImageView transformedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_main);
        ButterKnife.bind(this);

        // Basic img
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/0/02/Homer_Simpson_2006.png")
                .into(basicImg);

        // Resized img
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png")
                .override(400,400)
                .into(resizedImg);

        // Placeholder img
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/0/0b/Marge_Simpson.png")
                .placeholder(R.drawable.glide_icon)
                .error(R.drawable.glide_icon)
                .into(placeholderImg);

        // Cropped img
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/9/9d/Maggie_Simpson.png")
                .centerCrop()
                .into(croppedImg);

        // Transformed img
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/en/e/ec/Lisa_Simpson.png")
                .bitmapTransform(new CropCircleTransformation(this))
                .into(transformedImg);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
