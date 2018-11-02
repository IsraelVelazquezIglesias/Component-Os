package igl.vel.isr.appdef1_0.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import igl.vel.isr.appdef1_0.R;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private int[] ImageId = new int[]{R.drawable.agelectronica, R.drawable.ledtronik};

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ImageId.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(ImageId[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
