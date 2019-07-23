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
                gifArray = foodGifArray;
                meaningArray = foodMeaningArray;
                break;
            case 1:
                gifArray = numbersGifArray;
                meaningArray = numbersMeaningArray;
                break;

            case 2:
                gifArray = verbGifArray;
                meaningArray = verbMeaningArray;
                break;

            case 3:
                gifArray = peopleGifArray;
                meaningArray = peopleMeaningArray;
                break;

            case 4:
                gifArray = timeGifArray;
                meaningArray = timeMeaningArray;
                break;

            case 5:
                gifArray = questionsGifArray;
                meaningArray = questionsMeaningArray;
                break;

            case 6:
                gifArray = weightGifArray;
                meaningArray = weightMeaningArray;
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

//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(130, 130));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        Glide.with(context).load((items.get(position)).getGifId()).asGif().override(50,50).transform(new GifDrawableTransformation(new CropCircleTransformation(context),
//                Glide.get(context).getBitmapPool())).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(holder.gif);
        holder.meaning.setText(items.get(position).getMeaning());

        pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(context.getResources(),
                (items.get(position)).getGifId());
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(Main.RADIUS);
        firstDrawable.setTransform(transform);
        holder.gif.setImageDrawable(firstDrawable);

        return convertView;

    }

    private int[] verbGifArray = {R.drawable.abaranawa, R.drawable.adanawa, R.drawable.adhinawa, R.drawable.adinawa, R.drawable.ahanawa, R.drawable.arinawa, R.drawable.athu_gaanawa, R.drawable.awidinawa,
            R.drawable.balanawa, R.drawable.baninawa, R.drawable.bonawa, R.drawable.daduwam_karanawa, R.drawable.damanawa, R.drawable.denawa, R.drawable.duwanawa, R.drawable.ellanawa, R.drawable.enawa,
            R.drawable.ganan_karanawa, R.drawable.gannawa, R.drawable.genawa, R.drawable.haaranawa, R.drawable.hadanawa, R.drawable.hamuwenawa, R.drawable.hinawenawa, R.drawable.idagannawa, R.drawable.igena_gannawa,
            R.drawable.iranawa, R.drawable.kadanawa, R.drawable.kanawa, R.drawable.kapanawa, R.drawable.kasanawa, R.drawable.kata_karanawa, R.drawable.kiyanawa, R.drawable.kiyawanawa, R.drawable.kotanawa, R.drawable.liyanawa,
            R.drawable.makanawa, R.drawable.miladeegannawa, R.drawable.naanawa, R.drawable.natanawa, R.drawable.nawathinawa, R.drawable.nidigannawa, R.drawable.osawanawa, R.drawable.paadam_karanawa, R.drawable.palanawa, R.drawable.paninawa, R.drawable.peenanawa,
            R.drawable.peenawa, R.drawable.randu_karanwa, R.drawable.sellam_karanawa, R.drawable.sita_gannawa, R.drawable.sitawanawa, R.drawable.sodanawa, R.drawable.thaththu_karanawa, R.drawable.therum_gannawa, R.drawable.thoranawa, R.drawable.udaw_karanawa, R.drawable.uganwanawa, R.drawable.unukaranawa,
            R.drawable.uthuranawa, R.drawable.uyanawa, R.drawable.wada_karanawa, R.drawable.wapuranawa, R.drawable.wasanawa, R.drawable.wikunanawa, R.drawable.wiyadam_karanawa, R.drawable.yanawa};

    private String[] verbMeaningArray = {"අඹරනවා", "අඬනවා", "අදිනවා", "අඳිනවා", "අහනවා", "අරිනවා", "අතුගානවා", "ඇවිදිනවා",
            "බලනවා", "බණිනවා", "බොනවා", "දඬුවම්-කරනවා", "දමනවා", "දෙනවා", "දුවනවා", "එල්ලනවා", "එනවා",
            "ගණන්-කරනවා", "ගන්නවා", "ගේනවා", "හාරනවා", "හදනවා", "හමුවෙනවා", "හිනාවෙනවා", "ඉදිගන්නවා", "ඉගෙන-ගන්නවා",
            "ඉරනවා", "කඩනවා", "කනවා", "කපනවා", "කසනවා", "කථා-කරනවා", "කියනවා", "කියවනවා", "කොටනවා", "ලියනවා",
            "මකනවා", "මිළදී-ගන්නවා", "නානවා", "නටනවා", "නවතිනවා", "නිදිගන්නවා", "ඔසවනවා", "පාඩම්-කරනවා", "පලනවා", "පනිනවා", "පීනනවා",
            "පේනවා", "රණ්ඩුකරනවා", "සෙල්ලම්කරනවා", "", "සිට-ගන්නවා", "සිටවනවා", "සෝදනවා", "තට්ටු-කරනවා", "තේරුම්-ගන්නවා", "තෝරනවා", "උදව්-කරනවා", "උගන්වනවා", "උණුකරනවා",
            "උතුරනවා", "උයනවා", "වැඩ-කරනවා", "වපුරනවා", "වසනවා", "විකුණනවා", "වියදම්-කරනවා", "යනවා"};

    private int[] timeGifArray = {R.drawable.january, R.drawable.february, R.drawable.march, R.drawable.april, R.drawable.may, R.drawable.june, R.drawable.july, R.drawable.august, R.drawable.september, R.drawable.october, R.drawable.november, R.drawable.december
            , R.drawable.sunday, R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday, R.drawable.thursday, R.drawable.friday, R.drawable.saturday};

    private String[] timeMeaningArray = {"ජනවාරි", "පෙබරවාරි", "මාර්තු", "අප්\u200Dරේල්", "මැයි", "ජුනි", "ජුලි", "අගෝස්තු", "සැප්තැම්බර්", "ඔක්තෝම්බර්", "නොවැම්බර්", "දෙසැම්බර්"
            , "ඉරිදා", " සඳුදා", "අඟහරුවාදා", "බදාදා", "බ්\u200Dරහස්පතින්දා", "සිකුරාදා", "සෙනසුරාදා"};

    private int[] peopleGifArray = {};

    private String[] peopleMeaningArray = {};

    private int[] questionsGifArray = {R.drawable.ai, R.drawable.awashyada, R.drawable.bara_keeyada, R.drawable.denawada, R.drawable.dennada, R.drawable.ganna_puluwanda, R.drawable.gewanna_onada, R.drawable.harida, R.drawable.kaatada, R.drawable.kauda
            , R.drawable.kawadada, R.drawable.keeyada, R.drawable.keeyak_onada, R.drawable.keeyatada, R.drawable.kochcharada, R.drawable.kohida, R.drawable.kohomada, R.drawable.kopamanada, R.drawable.liwwada, R.drawable.liyanna_puluwanda, R.drawable.liyannada
            , R.drawable.mokada, R.drawable.monawada, R.drawable.onada, R.drawable.prashnarthaya, R.drawable.puluwanda, R.drawable.thiyenawada, R.drawable.udaw_karanawada, R.drawable.welawa_keeyatada};

    private String[] questionsMeaningArray = {"ඇයි", "අවශ්යද", "බර-කීයද", "දෙනවද", "දෙන්නද", "ගන්න-පුලුවන්ද", "ගෙවන්න-ඕනද", "හරිද", "කාටද", "කවුද",
            "කවද්ද", "කීයද", "කීයක්-ඕනෙද", "කීයටද", "කොච්චරද", "කොහිද", "කොහොමද", "කොපමණද", "ලිව්වද", "ලියන්න-පුලුවන්ද", "ලියන්නද",
            "මොකද", "මොනවද", "ඕනද", "ප්රශ්නාර්ථ", "පුලුවන්ද", "තියනවද", "උදව්-කරනවද", "වෙලාව-කීයටද"};

    private int[] weightGifArray = {};

    private String[] weightMeaningArray = {};

    private int[] foodGifArray = {};

    private String[] foodMeaningArray = {};

    private int[] numbersGifArray = {};

    private String[] numbersMeaningArray = {};

    private class ViewHolder {
        TextView meaning;
        ImageView gif;
    }

}
