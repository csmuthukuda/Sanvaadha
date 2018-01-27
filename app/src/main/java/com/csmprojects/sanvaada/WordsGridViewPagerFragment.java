package com.csmprojects.sanvaada;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.AnimationListener;

/**
 * Created by chathuranga on 10/1/17.
 */
public class WordsGridViewPagerFragment extends Fragment   {

    List<GifItem> items;
    GridView wordsGrid;
    Words wordsActivity;
    ArrayAdapter<String> wordListAdapter;
    ByteArrayOutputStream out;
    boolean isAnimCompleted;
    int j, count;
    List<pl.droidsonroids.gif.GifDrawable> drawableList;

    private int[] gifArray;
    private String[] meaningArray;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.words_grid_view_layout, container, false);
        int itemListNo = getArguments().getInt("itemlistno");
        wordsActivity = (Words) getActivity();
        wordsGrid = (GridView) view.findViewById(R.id.wordsLayoutWordsGrid);


        switch (itemListNo) {
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
            items.add(new GifItem(gifArray[i], i, meaningArray[i],null));
        }

        wordListAdapter = new ArrayAdapter<>(getContext(), R.layout.words_layout_words_grid_text_view, meaningArray);
        wordsGrid.setAdapter(wordListAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //gifout = new ByteArrayOutputStream();
        wordsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                GifItem item = items.get(i);
                wordsActivity.displayItemsList.add(item);
                wordsActivity.displayGridAdapter.notifyDataSetChanged();
                wordsActivity.meaningTxtView.append(meaningArray[i] + " ");
                wordsActivity.startMainAnimation();
//                Glide.with(getActivity()).load(item.getGifId()).asGif().
//                        transform(new GifDrawableTransformation(new CropCircleTransformation(getActivity()), Glide.get(getActivity()).getBitmapPool()))
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .into(wordsActivity.wordsImage);


            }
        });
    }






}


