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
    Main wordsActivity;
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

    private int[] verbGifArray = {R.drawable.abaranawa,R.drawable.adanawa,R.drawable.adhinawa,R.drawable.adinawa,R.drawable.ahanawa,R.drawable.arinawa,R.drawable.athu_gaanawa, R.drawable.awidinawa,
            R.drawable.balanawa, R.drawable.baninawa, R.drawable.bonawa, R.drawable.daduwam_karanawa, R.drawable.damanawa, R.drawable.denawa, R.drawable.duwanawa, R.drawable.ellanawa, R.drawable.enawa,
            R.drawable.ganan_karanawa, R.drawable.gannawa, R.drawable.genawa, R.drawable.haaranawa, R.drawable.hadanawa, R.drawable.hamuwenawa, R.drawable.hinawenawa, R.drawable.idagannawa, R.drawable.igena_gannawa,
            R.drawable.iranawa, R.drawable.kadanawa, R.drawable.kanawa, R.drawable.kapanawa, R.drawable.kasanawa, R.drawable.kata_karanawa, R.drawable.kiyanawa, R.drawable.kiyawanawa, R.drawable.kotanawa, R.drawable.liyanawa,
            R.drawable.makanawa, R.drawable.miladeegannawa, R.drawable.naanawa, R.drawable.natanawa, R.drawable.nawathinawa, R.drawable.nidigannawa, R.drawable.osawanawa, R.drawable.paadam_karanawa, R.drawable.palanawa, R.drawable.paninawa, R.drawable.peenanawa,
            R.drawable.peenawa, R.drawable.randu_karanwa, R.drawable.sellam_karanawa, R.drawable.sita_gannawa, R.drawable.sitawanawa, R.drawable.sodanawa, R.drawable.thaththu_karanawa, R.drawable.therum_gannawa, R.drawable.thoranawa, R.drawable.udaw_karanawa, R.drawable.uganwanawa, R.drawable.unukaranawa,
            R.drawable.uthuranawa, R.drawable.uyanawa, R.drawable.wada_karanawa, R.drawable.wapuranawa, R.drawable.wasanawa, R.drawable.wikunanawa, R.drawable.wiyadam_karanawa, R.drawable.yanawa};

    private String[] verbMeaningArray = {"අඹරනවා","අඬනවා", "අදිනවා", "අඳිනවා", "අහනවා", "අරිනවා", "අතුගානවා", "ඇවිදිනවා",
            "බලනවා", "බණිනවා", "බොනවා", "දඬුවම්-කරනවා", "දමනවා", "දෙනවා", "දුවනවා", "එල්ලනවා", "එනවා",
            "ගණන්-කරනවා", "ගන්නවා", "ගේනවා", "හාරනවා", "හදනවා", "හමුවෙනවා", "හිනාවෙනවා", "ඉදිගන්නවා", "ඉගෙන-ගන්නවා",
            "ඉරනවා", "කඩනවා", "කනවා", "කපනවා", "කසනවා", "කථා-කරනවා", "කියනවා", "කියවනවා", "කොටනවා", "ලියනවා",
            "මකනවා", "මිළදී-ගන්නවා", "නානවා", "නටනවා", "නවතිනවා", "නිදිගන්නවා", "ඔසවනවා", "පාඩම්-කරනවා", "පලනවා", "පනිනවා", "පීනනවා",
            "පේනවා", "රණ්ඩුකරනවා", "සෙල්ලම්කරනවා", "", "සිට-ගන්නවා", "සිටවනවා", "සෝදනවා", "තට්ටු-කරනවා", "තේරුම්-ගන්නවා", "තෝරනවා", "උදව්-කරනවා", "උගන්වනවා", "උණුකරනවා",
            "උතුරනවා", "උයනවා", "වැඩ-කරනවා", "වපුරනවා", "වසනවා", "විකුණනවා", "වියදම්-කරනවා", "යනවා" };

    private int[] timeGifArray = {R.drawable.january, R.drawable.february, R.drawable.march, R.drawable.april, R.drawable.may, R.drawable.june, R.drawable.july, R.drawable.august, R.drawable.september, R.drawable.october, R.drawable.november, R.drawable.december};

    private String[] timeMeaningArray = {"ජනවාරි", "පෙබරවාරි", "මාර්තු", "අප්\u200Dරේල්", "මැයි", "ජුනි", "ජුලි", "අගෝස්තු", "සැප්තැම්බර්", "ඔක්තෝම්බර්", "නොවැම්බර්", "දෙසැම්බර්"};

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.words_grid_view_layout, container, false);
        int itemListNo = getArguments().getInt("itemlistno");
        wordsActivity = (Main) getActivity();
        wordsGrid = view.findViewById(R.id.wordsLayoutWordsGrid);


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
                wordsActivity.meaningTxtView.append(meaningArray[i] + " ");
                wordsActivity.startMainAnimation();



            }
        });
    }






}


