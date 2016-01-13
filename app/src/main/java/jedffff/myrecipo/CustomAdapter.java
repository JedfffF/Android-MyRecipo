package jedffff.myrecipo;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jed on 1/8/2016.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] foods;
    private final Integer[] imgs;
    private final String[] time;

    public CustomAdapter(Context context, String[] foods, Integer[] imgs, String[] time) {
        super(context,R.layout.custom_row, foods);
        this.context = context;
        this.foods = foods;
        this.imgs = imgs;
        this.time = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.MyText);
        ImageView myImage = (ImageView) customView.findViewById(R.id.myImages);

        TextView desText = (TextView) customView.findViewById(R.id.description);
        TextView cooktimeText = (TextView) customView.findViewById(R.id.cooktime);

        myText.setText(singleFoodItem);
        desText.setText("Dishes - " + singleFoodItem);
        cooktimeText.setText("Estimated cook time:" + time[position] + " minutes");
        myImage.setImageResource(imgs[position]);
        return customView;
    }
}
