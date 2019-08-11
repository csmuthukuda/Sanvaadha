package com.csmprojects.sanvaada;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.waynejo.androidndkgif.GifDecoder;
import com.waynejo.androidndkgif.GifEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;


public class Main extends AppCompatActivity {
    static float RADIUS;
    GridView mainGridView;
    ImageView mainImage;
    List<GifItem> displayItemsList;
    //  DisplayGridViewAdapter displayAdapter;
    //FFmpeg ffmpeg;
    int j, count;
    List<pl.droidsonroids.gif.GifDrawable> drawableList;
    // ProgressDialog pDialog;
    SweetAlertDialog pDialog;
    ImageButton about;
    TextView meaningTxtView;
    private TextView mainText;
    private ViewPager mainGridViewPager, wordsGridViewPager;
    private Button sendBtn, delete, play,
            question, verbs, alphabet, time, garment, birds, family, police, drinks, sickness, hospital, colors, animals,
            postOffice, prepositions, pronouns, adjectives ;
    private boolean isGifKbShowing = true;
    private int previousBtnClicked = -1;
    private List<Button> btnArr;
    private HorizontalScrollView scrollView;

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        scrollView = findViewById(R.id.scrollView);
        RADIUS = pxFromDp(this, 150);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        TextView version = getSupportActionBar().getCustomView().findViewById(R.id.version);
        version.setVisibility(View.GONE);

        meaningTxtView = (TextView) findViewById(R.id.mainLayoutMeaningTxtView);
        meaningTxtView.setMovementMethod(new ScrollingMovementMethod());

        final Spinner language = getSupportActionBar().getCustomView().findViewById(R.id.language);
        String[] list = {"සිංහල", "தமிழ்", "English (US)", "日本語"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.lang_spinner_layout, R.id.langSpinnerTxtView, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // this part is needed for hiding the original view
                View view = super.getView(position, convertView, parent);
                view.setVisibility(View.GONE);

                return view;
            }

        };
        language.setAdapter(dataAdapter);
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                language.setSelection(0);
                if (i > 0)
                    Toast.makeText(Main.this, "Sanvaadha is presently capable to serve people who communicate in Sinhala language and our team is working to release Tamil,English and Japanese languages soon.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        about = getSupportActionBar().getCustomView().findViewById(R.id.about);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(Main.this, AboutActivity.class);
                startActivity(aboutIntent);

            }
        });

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.setTitleText("Please Wait");
        if (checkPermission()) {
            Log.e("WritePermission: ", "Already granted");
            // Code for above or equal 23 API Oriented Device
            // Your Permission granted already .Do next code
        } else {
            requestPermission(); // Code for permission
        }


        sendBtn = (Button) findViewById(R.id.mainLayoutSendBtn);


        //Intiate FFmpeg
//        ffmpeg = FFmpeg.getInstance(this);
//        try {
//            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
//                @Override
//                public void onStart() {
//                }
//
//                @Override
//                public void onFailure() {
//                }
//
//                @Override
//                public void onSuccess() {
//                }
//
//                @Override
//                public void onFinish() {
//                }
//            });
//        } catch (FFmpegNotSupportedException e) {
//            e.printStackTrace();
//        }
//        final StringBuilder commandStrBldr = new StringBuilder("-i concat:");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermission()) {
                    if (!displayItemsList.isEmpty())
                        new GifCombiner().execute();
                    else
                        Toast.makeText(Main.this, "Write Something  :)", Toast.LENGTH_SHORT).show();
                } else {
                    requestPermission(); // Code for permission
                }

            }

        });

        // displayGridView = (GridView) findViewById(R.id.displayGridView);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
//        mainText = (TextView) findViewById(R.id.mainTxtView);
        delete = (Button) findViewById(R.id.mainLayoutDeleteBtn);
        play = (Button) findViewById(R.id.mainLayoutPlayBtn);

        final pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(getResources(),
                R.drawable.awidinawa);
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(RADIUS);
        firstDrawable.setTransform(transform);
        firstDrawable.stop();
        mainImage.setImageDrawable(firstDrawable);


        displayItemsList = new ArrayList<>();
//        displayAdapter = new DisplayGridViewAdapter(Main.this, displayItemsList);
//        displayGridView.setAdapter(displayAdapter);



        mainGridViewPager = (ViewPager) findViewById(R.id.mainLayoutViewPager);

        MainGridViewPagerAdapter pagerAdapter = new MainGridViewPagerAdapter(getSupportFragmentManager(), isGifKbShowing);
        mainGridViewPager.setAdapter(pagerAdapter);
        mainGridViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            highLightButton(btnArr.get(i));
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        isGifKbShowing = false;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play.setBackgroundResource(isGifKbShowing ? R.drawable.key_board : R.drawable.sign);

                MainGridViewPagerAdapter pagerAdapter = new MainGridViewPagerAdapter(getSupportFragmentManager(), isGifKbShowing);
                mainGridViewPager.setAdapter(pagerAdapter);
                mainGridViewPager.getAdapter().notifyDataSetChanged();
                isGifKbShowing = !isGifKbShowing;

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!displayItemsList.isEmpty()) {
                    displayItemsList.remove(displayItemsList.size() - 1);
                    //  displayGridAdapter.notifyDataSetChanged();
                    StringBuilder builder = new StringBuilder();
                    for (GifItem item : displayItemsList) {
                        builder.append(item.getMeaning()).append(" ");
                    }
                    meaningTxtView.setText(builder.toString());

                    if (displayItemsList.size() == 0) {
                        mainImage.setImageDrawable(firstDrawable);
                    } else {
                        startMainAnimation();
                    }

                } else
                    mainImage.setImageDrawable(firstDrawable);

            }
        });


        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (displayItemsList.size() > 0) {
                    for (pl.droidsonroids.gif.GifDrawable drawable : drawableList) {
                        drawable.recycle();
                    }
                    displayItemsList.clear();
                    // displayGridAdapter.notifyDataSetChanged();
                    meaningTxtView.setText("");
                    mainImage.setImageDrawable(firstDrawable);
                    return true;
                }
                return false;

            }
        });

        question = (Button) findViewById(R.id.mainLayoutQuestionsBtn);
        verbs = (Button) findViewById(R.id.mainLayoutVerbsBtn);
        alphabet = (Button) findViewById(R.id.mainLayoutAlphabetBtn);
        time = (Button) findViewById(R.id.mainLayoutTimeBtn);
        garment = (Button) findViewById(R.id.mainLayoutGarmentBtn);
        birds = (Button) findViewById(R.id.mainLayoutBirdsBtn);
        family = (Button) findViewById(R.id.mainLayoutFamilyBtn);
        police = (Button) findViewById(R.id.mainLayoutPoliceBtn);
        drinks = (Button) findViewById(R.id.mainLayoutDrinksBtn);
        sickness = (Button) findViewById(R.id.mainLayoutSicknessBtn);
        hospital = (Button) findViewById(R.id.mainLayoutHospitalBtn);
        colors = (Button) findViewById(R.id.mainLayoutColorsBtn);
        animals = (Button) findViewById(R.id.mainLayoutAnimalsBtn);
        postOffice = (Button) findViewById(R.id.mainLayoutPostOfficeBtn);
        prepositions = (Button) findViewById(R.id.mainLayoutPrepositionsBtn);
        pronouns = (Button) findViewById(R.id.mainLayoutPronounsBtn);
        adjectives = (Button) findViewById(R.id.mainLayoutAdjectivesBtn);


        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(question);
                mainGridViewPager.setCurrentItem(0, true);
            }
        });

        verbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(verbs);
                mainGridViewPager.setCurrentItem(1, true);
            }
        });

        alphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(alphabet);
                mainGridViewPager.setCurrentItem(2, true);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(time);
                mainGridViewPager.setCurrentItem(3, true);
            }
        });

        garment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(garment);
                mainGridViewPager.setCurrentItem(4, true);
            }
        });

        birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(birds);
                mainGridViewPager.setCurrentItem(5, true);
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(family);
                mainGridViewPager.setCurrentItem(6, true);
            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(police);
                mainGridViewPager.setCurrentItem(7, true);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(drinks);
                mainGridViewPager.setCurrentItem(8, true);
            }
        });

        sickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(sickness);
                mainGridViewPager.setCurrentItem(9, true);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(hospital);
                mainGridViewPager.setCurrentItem(10, true);
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(colors);
                mainGridViewPager.setCurrentItem(11, true);
            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(animals);
                mainGridViewPager.setCurrentItem(12, true);
            }
        });

        postOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(postOffice);
                mainGridViewPager.setCurrentItem(13, true);
            }
        });

        prepositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(prepositions);
                mainGridViewPager.setCurrentItem(14, true);
            }
        });

        pronouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(pronouns);
                mainGridViewPager.setCurrentItem(15, true);
            }
        });

        adjectives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highLightButton(adjectives);
                mainGridViewPager.setCurrentItem(16, true);
            }
        });

        btnArr = new ArrayList<>();
        btnArr.add(question);
        btnArr.add(verbs);
        btnArr.add(alphabet);
        btnArr.add(time);
        btnArr.add(garment);
        btnArr.add(birds);
        btnArr.add(family);
        btnArr.add(police);
        btnArr.add(drinks);
        btnArr.add(sickness);
        btnArr.add(hospital);
        btnArr.add(colors);
        btnArr.add(animals);
        btnArr.add(postOffice);
        btnArr.add(prepositions);
        btnArr.add(pronouns);
        btnArr.add(adjectives);

    }

    void highLightButton(Button button){
        if(previousBtnClicked > 0){
          Button  prevBtn = findViewById(previousBtnClicked);
            Drawable buttonDrawable = prevBtn.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            //the color is a direct color int and not a color resource
            DrawableCompat.setTint(buttonDrawable, Color.parseColor("#FF000000"));
            prevBtn.setBackground(buttonDrawable);
        }
        previousBtnClicked = button.getId();
        Drawable buttonDrawable = button.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(buttonDrawable, Color.parseColor("#63A0E3"));
        button.setBackground(buttonDrawable);

        scrollView.requestChildFocus(button,button);
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
        Log.e("drawableList size ", String.valueOf(drawableList.size()));
        GifDrawable[] arr = drawableList.toArray(new GifDrawable[0]);
        ChainedGifDrawable drawable = new ChainedGifDrawable(arr);
        mainImage.setImageDrawable(drawable);
        drawable.setAnimationEnabled();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    Toast.makeText(Main.this, "Permission granted,Continue your work", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    private class GifCombiner extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                StringBuilder text = new StringBuilder() ;

                Log.e("WritePermission: ", "Already granted");
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), "sanwaada");
                if (!file.exists()) {
                    file.mkdir();
                    // If you require it to make the entire directory path including parents,
                    // use directory.mkdirs(); here instead.
                }
                for (int i = 0; i < displayItemsList.size(); i++) {
                    text.append(displayItemsList.get(i).getMeaning()).append(" ");
                    int id = displayItemsList.get(i).getGifId();
                    File f = new File(file.getAbsolutePath() + "/" + i + ".gif");
                    InputStream inputStream = getResources().openRawResource(id); // id drawable
                    OutputStream out = new FileOutputStream(f);
                    byte buf[] = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0)
                        out.write(buf, 0, len);
                    out.close();
                    inputStream.close();
                    displayItemsList.get(i).setPath(f.getAbsolutePath());
                    Log.e("File TAG ", f.getName() + " created");

                    //  commandStrBldr.append(f.getAbsolutePath());
//                        if (!(i == (displayItemsList.size() - 1))) {
//                            commandStrBldr.append("|");
//                        }

                }

                GifDecoder gifDecoder = new GifDecoder();
                List<Bitmap> bitmapList = new ArrayList<>();
                for (int j = 0; j < displayItemsList.size(); j++) {
                    boolean isSucceeded = gifDecoder.load(displayItemsList.get(j).getPath());

                    if (isSucceeded) {
                        for (int i = 0; i < gifDecoder.frameNum(); ++i) {
                            Bitmap bitmap = gifDecoder.frame(i);
                            bitmapList.add(bitmap);
                        }
                        Log.e("Decode TAG: ", String.valueOf(isSucceeded));
                        File toDel = new File(displayItemsList.get(j).getPath());
                        if (toDel.exists())
                            toDel.delete();
                    }
                }
                GifEncoder gifEncoder = new GifEncoder();
                String outPath = file.getAbsolutePath() + "/out.gif";
                int width = 100;
                int height = 100;
                int delayMs = 10;

                gifEncoder.init(width, height, outPath, GifEncoder.EncodingType.ENCODING_TYPE_SIMPLE_FAST);
                gifEncoder.setDither(true);
                for (Bitmap bitmap : bitmapList) {
                    boolean status = gifEncoder.encodeFrame(bitmap, delayMs);
                    Log.e("Encode TAG: ", String.valueOf(status));
                }
                gifEncoder.close();
                Log.e("Encode TAG: ", "Success");



                Intent share = new Intent();
                share.setType("image/gif");
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                share.setAction(Intent.ACTION_SEND);
                File shareFile = new File(outPath);
                Uri newFile = FileProvider.getUriForFile(Main.this, "com.csmprojects.sanvaada", shareFile);
                share.setDataAndType(newFile, Main.this.getContentResolver().getType(newFile));
                share.putExtra(Intent.EXTRA_STREAM, newFile);
                share.putExtra(Intent.EXTRA_TEXT, text.length() > 0 ? text.toString() : " no text");
                // startActivity(share);
                startActivity(Intent.createChooser(share, "Share"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pDialog.dismiss();

        }

    }
}
