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
                gifArray = qAndaGifArray;
                meaningArray = qAndaMeaningArray;
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

//    private int[] nounGifArray ={R.drawable.ithuru,R.drawable.salli,R.drawable.badu,R.drawable.kadaya,R.drawable.mobitel,R.drawable.laisthuwa};
//
//    private String[] nounMeaningArray ={"ඉතුරු", "සල්ලි", "බඩු", "කඩය", "මොබිටෙල්", "ලැයිස්තුව" };

    private int[] verbGifArray = {R.drawable.menna, R.drawable.thiyenawada, R.drawable.monawada, R.drawable.oona, R.drawable.kiyada,
            R.drawable.denna, R.drawable.ganna, R.drawable.puluwan, R.drawable.dannawa};

    private String[] verbMeaningArray = {"මෙන්න", "තියෙනවද", "මොනවද", "ඕන", "කීයද", "දෙන්න", "ගන්න", "පුළුවන්", "දන්නවා"};

    private int[] timeGifArray = {R.drawable.laga, R.drawable.ara, R.drawable.dan, R.drawable.iwarai, R.drawable.tikak, R.drawable.thawa, R.drawable.menna};

    private String[] timeMeaningArray = {"ළඟ", "අර", "දැන්", "ඉවරයි", "ටිකක්", "තව", "මෙන්න"};

    private int[] peopleGifArray = {R.drawable.mata, R.drawable.oyata};

    private String[] peopleMeaningArray = {"මට", "ඔයාට"};

    private int[] qAndaGifArray = {R.drawable.ow, R.drawable.natha, R.drawable.oo, R.drawable.kiyada, R.drawable.harida, R.drawable.kawda};

    private String[] qAndaMeaningArray = {"ඔව්", "නැත", "ඕ", "කීයද", "හරිද", "කව්ද"};

    private int[] weightGifArray = {R.drawable.onekg, R.drawable.twokg, R.drawable.threekg, R.drawable.fiftyg, R.drawable.onehundredfiftyg,
            R.drawable.threehundredg, R.drawable.threehundredfiftyg, R.drawable.fourhundredg, R.drawable.fivehundredg};

    private String[] weightMeaningArray = {"කිලෝග්‍රෑම් එක", "කිලෝග්‍රෑම් දෙක", "කිලෝග්‍රෑම් තුන", "ග්‍රෑම් පනහ",
            "ග්‍රෑම් එකසියපනහ", "ග්‍රෑම් තුන්සීය", "ග්‍රෑම් තුන්සියපනහ", "ග්‍රෑම් හාරසීය", "ග්‍රෑම් පන්සීය"};

    private int[] foodGifArray = {R.drawable.appa, R.drawable.banis, R.drawable.biththara, R.drawable.elawalu,
            R.drawable.haal, R.drawable.idiappa, R.drawable.kadala, R.drawable.karawala, R.drawable.keek, R.drawable.kibulabanis,
            R.drawable.kos, R.drawable.malupaan, R.drawable.munata, R.drawable.noodles, R.drawable.paan,
            R.drawable.parippu, R.drawable.rospan, R.drawable.anchor, R.drawable.babysaban, R.drawable.biscuit, R.drawable.elawaluroti,
            R.drawable.lifebuoy, R.drawable.lux, R.drawable.milo, R.drawable.nestomalt, R.drawable.paspanguwa, R.drawable.piti,
            R.drawable.pittu, R.drawable.rathulunu, R.drawable.rathumiris, R.drawable.rolls, R.drawable.roti, R.drawable.seeni,
            R.drawable.sunlight, R.drawable.toffee, R.drawable.viyalimiris, R.drawable.ithuru, R.drawable.salli, R.drawable.badu, R.drawable.kadaya, R.drawable.mobitel, R.drawable.laisthuwa};

    private String[] foodMeaningArray = {"ආප්ප", "බනිස්", "බිත්තර", "එලවළු", "හාල්", "ඉදි ආප්ප", "කඩල", "කරවල", "කේක්",
            "කිඹුලා බනිස්", "කොස්", "මාළු පාන්", "මුන් ඇට", "නූඩ්ලස්", "පාන්", "පරිප්පු", "රෝස් පාන්", "ඇන්කර්", "බේබි සබන්", "බිස්කට්", "එලවළු රොටි", "ලයිෆ් බෝයි", "ලක්ස්",
            "මයිලෝ", "නෙස්ටමෝල්ට්", "පස් පංගුව", "පිටි", "පිට්ටු", "රතු ළූනු", "රතු මිරිස්", "රෝල්ස්", "රොටි", "සීනි", "සන් ලයිට්", "ටොෆී", "වියලි මිරිස්", "ඉතුරු", "සල්ලි", "බඩු", "කඩය", "මොබිටෙල්", "ලැයිස්තුව"};

    private int[] numbersGifArray = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five,
            R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.fifteen, R.drawable.twenty, R.drawable.twentyfive,
            R.drawable.thirty, R.drawable.thirtyfive, R.drawable.fourty, R.drawable.fourtyfive, R.drawable.fifty, R.drawable.fiftyfive, R.drawable.sixty, R.drawable.sixtyfive,
            R.drawable.seventy, R.drawable.seventyfive, R.drawable.eighty, R.drawable.eightyfive, R.drawable.ninety, R.drawable.ninetyfive, R.drawable.hundred, R.drawable.onehundredten, R.drawable.twohundredfifty};

    private String[] numbersMeaningArray = {"එක", "දෙක", "තුන", "හතර", "පහ", "හය", "හත", "අට", "නමය", "දහය", "පහළොව", "විස්ස", "විසිපහ", "තිහ", "තිස්පහ", "හතලිහ", "හතලිස්පහ", "පනහ", "පනස්පහ", "හැට",
            "හැටපහ", "හැත්තෑව", "හැත්තෑපහ", "අසූව", "අසූපහ", "අනූව", "අනූපහ", "සීය", "එකසියදහය", "දෙසියපනහ"};

    private class ViewHolder {
        TextView meaning;
        ImageView gif;
    }

}
