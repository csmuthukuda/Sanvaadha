package com.csmprojects.sanvaada;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;

public class Words extends AppCompatActivity  {
    GridView displayGrid, wordsGrid;
    TextView meaningTxtView;
    List<GifItem> displayItemsList;
    DisplayGridViewAdapter displayGridAdapter;
    ViewPager wordsGridViewPager;
    Button backSPace, food, no, verb, people, time, question, weight;
    ImageView wordsImage;
    static  float RADIUS;

    int j;
    List<pl.droidsonroids.gif.GifDrawable> drawableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_layout);
        RADIUS = pxFromDp(this,150);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        displayItemsList = new ArrayList<>();

        wordsImage = (ImageView) findViewById(R.id.wordsImageView);

        final pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(getResources(),
                R.drawable.appa);
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(RADIUS);
        firstDrawable.setTransform(transform);
        firstDrawable.stop();
        wordsImage.setImageDrawable(firstDrawable);

        meaningTxtView = (TextView) findViewById(R.id.wordsLayoutMeaningTxtView);
        wordsGrid = (GridView) findViewById(R.id.wordsLayoutWordsGrid);
        displayGrid = (GridView) findViewById(R.id.wordsLayoutDisplayGrid);

        wordsGridViewPager = (ViewPager) findViewById(R.id.wordsLayoutViewPager);
        WordsGridViewPagerAdapter pagerAdapter = new WordsGridViewPagerAdapter(getSupportFragmentManager());
        wordsGridViewPager.setAdapter(pagerAdapter);


        displayGridAdapter = new DisplayGridViewAdapter(this, displayItemsList);
        displayGrid.setAdapter(displayGridAdapter);


        backSPace = (Button) findViewById(R.id.wordsLayoutBackspaceButton);
        food = (Button) findViewById(R.id.wordsLayoutFoodBtn);
        no = (Button) findViewById(R.id.wordsLayoutNoBtn);
        verb = (Button) findViewById(R.id.wordsLayoutVerbBtn);
        people = (Button) findViewById(R.id.wordsLayoutPeopleBtn);
        time = (Button) findViewById(R.id.wordsLayoutTimeBtn);
        question = (Button) findViewById(R.id.wordsLayoutQuestionBtn);
        weight = (Button) findViewById(R.id.wordsLayoutWeightBtn);


        backSPace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!displayItemsList.isEmpty()) {
                    displayItemsList.remove(displayItemsList.size() - 1);
                    displayGridAdapter.notifyDataSetChanged();
                    StringBuilder builder = new StringBuilder();
                    for (GifItem item : displayItemsList) {
                        builder.append(item.getMeaning()).append(" ");
                    }
                    meaningTxtView.setText(builder.toString());

                    if (displayItemsList.size() == 0) {
                        wordsImage.setImageDrawable(firstDrawable);
                    } else {
                        startMainAnimation();
                    }

                } else
                    wordsImage.setImageDrawable(firstDrawable);

            }
        });


        backSPace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (displayItemsList.size() > 0) {
                    for (pl.droidsonroids.gif.GifDrawable drawable : drawableList) {
                        drawable.recycle();
                    }
                    displayItemsList.clear();
                    displayGridAdapter.notifyDataSetChanged();
                    meaningTxtView.setText("");
                    wordsImage.setImageDrawable(firstDrawable);
                    return true;
                }
                return false;

            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(0, true);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(1, true);
            }
        });

        verb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(2, true);
            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(3, true);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(4, true);
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(5, true);
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsGridViewPager.setCurrentItem(6, true);
            }
        });
    }


    void startMainAnimation() {
        drawableList = new ArrayList<>();
        for (j = 0; j < displayItemsList.size(); j++) {
            Log.e("J value: ", String.valueOf(j));
            pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(getResources(),
                    displayItemsList.get(j).getGifId());
            firstDrawable.setLoopCount(0);
            Transform transform = new CornerRadiusTransform(RADIUS);
            firstDrawable.setTransform(transform);
            drawableList.add(firstDrawable);

        }
        GifDrawable[] arr= drawableList.toArray(new GifDrawable[0]);

        ChainedGifDrawable drawable = new ChainedGifDrawable(arr);
        wordsImage.setImageDrawable(drawable);
        drawable.setAnimationEnabled();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return false;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
