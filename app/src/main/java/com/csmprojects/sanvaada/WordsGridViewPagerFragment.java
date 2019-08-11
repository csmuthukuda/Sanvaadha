package com.csmprojects.sanvaada;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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
 * Created by chathuranga on 10/1/17.
 */
public class WordsGridViewPagerFragment extends Fragment   {

    List<GifItem> items;
    GridView wordsGrid;
    MainActivity wordsActivity;
    ArrayAdapter<String> wordListAdapter;
    ByteArrayOutputStream out;
    boolean isAnimCompleted;
    int j, count;
    List<pl.droidsonroids.gif.GifDrawable> drawableList;

    private int[] gifArray;
    private String[] meaningArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.words_grid_view_layout, container, false);
        int itemListNo = getArguments().getInt("itemlistno");
        wordsActivity = (MainActivity) getActivity();
        wordsGrid = view.findViewById(R.id.wordsLayoutWordsGrid);


        switch (itemListNo) {
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


