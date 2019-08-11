package com.csmprojects.sanvaada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;

import static com.csmprojects.sanvaada.Const.adjectivesGifArray;
import static com.csmprojects.sanvaada.Const.adjectivesMeaningArray;
import static com.csmprojects.sanvaada.Const.alphabetGifArray;
import static com.csmprojects.sanvaada.Const.alphabetMeaningArray;
import static com.csmprojects.sanvaada.Const.animalsGifArray;
import static com.csmprojects.sanvaada.Const.animalsMeaningArray;
import static com.csmprojects.sanvaada.Const.birdsGifArray;
import static com.csmprojects.sanvaada.Const.birdsMeaningArray;
import static com.csmprojects.sanvaada.Const.colorGifArray;
import static com.csmprojects.sanvaada.Const.colorMeaningArray;
import static com.csmprojects.sanvaada.Const.drinksGifArray;
import static com.csmprojects.sanvaada.Const.drinksMeaningArray;
import static com.csmprojects.sanvaada.Const.familyGifArray;
import static com.csmprojects.sanvaada.Const.familyMeaningArray;
import static com.csmprojects.sanvaada.Const.garmentFactoryGifArray;
import static com.csmprojects.sanvaada.Const.garmentFactoryMeaningArray;
import static com.csmprojects.sanvaada.Const.hospitalGifArray;
import static com.csmprojects.sanvaada.Const.hospitalMeaningArray;
import static com.csmprojects.sanvaada.Const.policeGifArray;
import static com.csmprojects.sanvaada.Const.policeMeaningArray;
import static com.csmprojects.sanvaada.Const.postOfficeGifArray;
import static com.csmprojects.sanvaada.Const.postOfficeMeaningArray;
import static com.csmprojects.sanvaada.Const.prepositionsGifArray;
import static com.csmprojects.sanvaada.Const.prepositionsMeaningArray;
import static com.csmprojects.sanvaada.Const.pronounsGifArray;
import static com.csmprojects.sanvaada.Const.pronounsMeaningArray;
import static com.csmprojects.sanvaada.Const.questionsGifArray;
import static com.csmprojects.sanvaada.Const.questionsMeaningArray;
import static com.csmprojects.sanvaada.Const.sicknessGifArray;
import static com.csmprojects.sanvaada.Const.sicknessMeaningArray;
import static com.csmprojects.sanvaada.Const.timeGifArray;
import static com.csmprojects.sanvaada.Const.timeMeaningArray;
import static com.csmprojects.sanvaada.Const.verbGifArray;
import static com.csmprojects.sanvaada.Const.verbMeaningArray;

/**
 * Created by chathuranga on 7/21/17.
 */
public class MainGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<GifItem> items;
    private LayoutInflater inflater = null;
    private int[] gifArray;
    private String[] meaningArray;


    MainGridViewAdapter(Context context, int pageNo) {
        this.context = context;

        switch (pageNo) {
            case 0:
                gifArray = questionsGifArray;
                meaningArray = questionsMeaningArray;
                break;
            case 1:
                gifArray = verbGifArray;
                meaningArray = verbMeaningArray;
                break;

            case 2:
                gifArray = alphabetGifArray;
                meaningArray = alphabetMeaningArray;
                break;

            case 3:
                gifArray = timeGifArray;
                meaningArray = timeMeaningArray;
                break;

            case 4:
                gifArray = garmentFactoryGifArray;
                meaningArray = garmentFactoryMeaningArray;
                break;

            case 5:
                gifArray = birdsGifArray;
                meaningArray = birdsMeaningArray;
                break;

            case 6:
                gifArray = familyGifArray;
                meaningArray = familyMeaningArray;
                break;

            case 7:
                gifArray = policeGifArray;
                meaningArray = policeMeaningArray;
                break;

            case 8:
                gifArray = drinksGifArray;
                meaningArray = drinksMeaningArray;
                break;

            case 9:
                gifArray = sicknessGifArray;
                meaningArray = sicknessMeaningArray;
                break;

            case 10:
                gifArray = hospitalGifArray;
                meaningArray = hospitalMeaningArray;
                break;

            case 11:
                gifArray = colorGifArray;
                meaningArray = colorMeaningArray;
                break;

            case 12:
                gifArray = animalsGifArray;
                meaningArray = animalsMeaningArray;
                break;

            case 13:
                gifArray = postOfficeGifArray;
                meaningArray = postOfficeMeaningArray;
                break;

            case 14:
                gifArray = prepositionsGifArray;
                meaningArray = prepositionsMeaningArray;
                break;

            case 15:
                gifArray = pronounsGifArray;
                meaningArray = pronounsMeaningArray;
                break;

            case 16:
                gifArray = adjectivesGifArray;
                meaningArray = adjectivesMeaningArray;
                break;

        }
        items = new ArrayList<>();
        for (int i = 0; i < gifArray.length; i++) {
            items.add(new GifItem(gifArray[i], i, meaningArray[i], null));
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        ImageView imageView;
        if (convertView == null) {
            holder = new ViewHolder();
            // if it's not recycled, initialize some attributes
            convertView = inflater.inflate(R.layout.main_grid_view_item_layout, null);
            holder.gif = convertView.findViewById(R.id.mainGridViewItemImageVw);
            holder.meaning = convertView.findViewById(R.id.mainGridViewItemTextVw);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.meaning.setText(items.get(position).getMeaning());

        pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(context.getResources(),
                (items.get(position)).getGifId());
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(MainActivity.RADIUS);
        firstDrawable.setTransform(transform);
        holder.gif.setImageDrawable(firstDrawable);

        return convertView;

    }

    private class ViewHolder {
        TextView meaning;
        ImageView gif;
    }

}
