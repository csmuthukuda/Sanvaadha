package com.csmprojects.sanvaada;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    GridView mainGridView, displayGridView;
    ImageView mainImage;
    List<GifItem> displayItemsList;
    DisplayGridViewAdapter displayAdapter;
    //FFmpeg ffmpeg;
    int j, count;
    List<pl.droidsonroids.gif.GifDrawable> drawableList;
    // ProgressDialog pDialog;
    SweetAlertDialog pDialog;
    private TextView mainText;
    private ViewPager mainGridViewPager;
    private Button sendBtn, delete, play, food, no, verb, people, time, question, weight;
    static float RADIUS;
    ImageButton about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        RADIUS = pxFromDp(this, 150);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        TextView version = getSupportActionBar().getCustomView().findViewById(R.id.version);
        version.setVisibility(View.GONE);

        final Spinner language = getSupportActionBar().getCustomView().findViewById(R.id.language);
        String [] list= {"සිංහල","தமிழ்","English (US)", "日本語"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.lang_spinner_layout,R.id.langSpinnerTxtView, list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // this part is needed for hiding the original view
                View view = super.getView(position, convertView, parent);
                view.setVisibility(View.GONE);

                return view;
            }

//            @Override
//            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//               View view = super.getDropDownView(position, convertView, parent);
//               view.setBackgroundColor(getResources().getColor(R.color.white));
//
//               return  view;
//            }
        };
        language.setAdapter(dataAdapter);
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                language.setSelection(0);
                if(i>0)
                Toast.makeText(Main.this,"Sanvaadha is presently capable to serve people who communicate in Sinhala language and our team is working to release Tamil,English and Japanese languages soon.",Toast.LENGTH_SHORT).show();

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


//                    commandStrBldr.append(" -s 200x200 " + file.getAbsolutePath() + "/output.gif");
//                    Log.e("OUT PATH TAG ", commandStrBldr.toString());
//
//                    String[] command = commandStrBldr.toString().split(" ");

//                    ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
//
//                        @Override
//                        public void onStart() {
//                            pDialog.setTitle("Loading");
//                            pDialog.show();
//                        }
//
//                        @Override
//                        public void onProgress(String message) {
//                            //   Toast.makeText(Main.this,"Progress: "+message,Toast.LENGTH_SHORT).show();
//                            Log.e("Progress TAG: ", message);
//                        }
//
//                        @Override
//                        public void onFailure(String message) {
//                            Toast.makeText(Main.this, "Failed: " + message, Toast.LENGTH_SHORT).show();
//                            Log.e("Failure TAG: ", message);
//                        }
//
//                        @Override
//                        public void onSuccess(String message) {
//                            Toast.makeText(Main.this, "Success: " + message, Toast.LENGTH_SHORT).show();
//                            Log.e("Success TAG: ", message);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            pDialog.dismiss();
//                        }
//                    });


            }

        });


        //    String gif1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/gif1.gif";
        //    String gif2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/gif2.gif";

        //   String command1 = "-i " + gif1 + " -f mpegts " + file.getAbsolutePath() + "/gif1.ts";
        //   String command2 = "-i " + gif2 + " -f mpegts " + file.getAbsolutePath() + "/gif2.ts";
        //    String commandStr = "-i concat:" + gif1 + "|" + gif2 + " -s 200x200 " + file.getAbsolutePath() + "/output.gif";

        //  String commandStr="-i concat:"+file.getAbsolutePath()+"/gif1.ts|"+file.getAbsolutePath()+"/gif2.ts -pix_fmt rgb24 "+file.getAbsolutePath()+"/output.gif";
        // String commandStr="-f concat -safe 0 -i <(for f in ."+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/*.gif; do echo \"file '$PWD/$f'\"; done) -c copy output.gif";
        //      String fullCommandStr=command1  ;


        displayGridView = (GridView) findViewById(R.id.displayGridView);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
//        mainText = (TextView) findViewById(R.id.mainTxtView);
        delete = (Button) findViewById(R.id.mainLayoutDeleteBtn);
        play = (Button) findViewById(R.id.mainLayoutPlayBtn);

        final pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(getResources(),
                R.drawable.appa);
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(RADIUS);
        firstDrawable.setTransform(transform);
        firstDrawable.stop();
        mainImage.setImageDrawable(firstDrawable);


        displayItemsList = new ArrayList<>();
        displayAdapter = new DisplayGridViewAdapter(Main.this, displayItemsList);
        displayGridView.setAdapter(displayAdapter);

        mainGridViewPager = (ViewPager) findViewById(R.id.mainLayoutViewPager);
        MainGridViewPagerAdapter pagerAdapter = new MainGridViewPagerAdapter(getSupportFragmentManager());
        mainGridViewPager.setAdapter(pagerAdapter);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(Main.this, Reader.class);
                String meaning = "";
                for (int i = 0; i < displayItemsList.size(); i++) {
                    meaning = meaning + " " + displayItemsList.get(i).getMeaning();
                }
                playIntent.putExtra("meaning", meaning);
                startActivityForResult(playIntent, 0);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!displayItemsList.isEmpty()) {
                    displayItemsList.remove(displayItemsList.size() - 1);
                    displayAdapter.notifyDataSetChanged();

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
                if (!displayItemsList.isEmpty()) {
                    for (pl.droidsonroids.gif.GifDrawable drawable : drawableList) {
                        drawable.recycle();
                    }
                    displayItemsList.clear();
                    displayAdapter.notifyDataSetChanged();
                    mainImage.setImageDrawable(firstDrawable);
                    return true;
                }
                return false;

            }
        });
        food = (Button) findViewById(R.id.mainLayoutFoodBtn);
        no = (Button) findViewById(R.id.mainLayoutNoBtn);
        people = (Button) findViewById(R.id.mainLayoutPeopleBtn);
        time = (Button) findViewById(R.id.mainLayoutTimeBtn);
        question = (Button) findViewById(R.id.mainLayoutQuestionBtn);
        verb = (Button) findViewById(R.id.mainLayoutVerbBtn);
        weight = (Button) findViewById(R.id.mainLayoutWeightBtn);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(0, true);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(1, true);
            }
        });

        verb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(2, true);
            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(3, true);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(4, true);
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(5, true);
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainGridViewPager.setCurrentItem(6, true);
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
//    public static class com.csmprojects.sanvaada.ChainedGifDrawable extends Drawable implements AnimationListener {
//        private final pl.droidsonroids.gif.GifDrawable[] mDrawables;
//        private int mCurrentIndex;
//
//        public com.csmprojects.sanvaada.ChainedGifDrawable(GifDrawable[] drawableArray) {
//            mDrawables = drawableArray;
//
//            for (pl.droidsonroids.gif.GifDrawable drawable : drawableArray) {
//                drawable.addAnimationListener(this);
//
//            }
//        }
//
//        @Override
//        public void onAnimationCompleted(int loopNumber) {
//            Log.e("mcurrntindex 1 ", String.valueOf(mCurrentIndex));
//            mCurrentIndex++;
//            mCurrentIndex %= mDrawables.length;
//            Log.e("mcurrntindex 2", String.valueOf(mCurrentIndex));
//            mDrawables[mCurrentIndex].setCallback(getCallback());
//            mDrawables[mCurrentIndex].invalidateSelf();
//        }
//
//        @Override
//        public void invalidateSelf() {
//            mDrawables[mCurrentIndex].invalidateSelf();
//            Log.e("Called invalidateSelf " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public void scheduleSelf(@NonNull Runnable what, long when) {
//            mDrawables[mCurrentIndex].scheduleSelf(what, when);
//            Log.e("Called scheduleSelf " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public void unscheduleSelf(@NonNull Runnable what) {
//            mDrawables[mCurrentIndex].unscheduleSelf(what);
//            Log.e("Called unscheduleSelf " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public int getIntrinsicWidth() {
//            Log.e("Called getIntrinsicWidth " + mCurrentIndex, "Checked");
//            return mDrawables[mCurrentIndex].getIntrinsicWidth();
//        }
//
//        @Override
//        public int getIntrinsicHeight() {
//            Log.e("Called getIntrinsicHeight " + mCurrentIndex, "Checked");
//            return mDrawables[mCurrentIndex].getIntrinsicHeight();
//        }
//
//        @Override
//        protected void onBoundsChange(Rect bounds) {
//            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
//                drawable.setBounds(bounds);
//            }
//            Log.e("Called onboundsChange " + mCurrentIndex, "Checked");
//        }
//
//
//        @Override
//        public void draw(@NonNull Canvas canvas) {
//            mDrawables[mCurrentIndex].draw(canvas);
//            Log.e("Called draw " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
//            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
//                drawable.setAlpha(alpha);
//            }
//            Log.e("Called setAlpha " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public void setColorFilter(@Nullable ColorFilter colorFilter) {
//            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
//                drawable.setColorFilter(colorFilter);
//            }
//            Log.e("Called setcolorfilter " + mCurrentIndex, "Checked");
//        }
//
//        @Override
//        public int getOpacity() {
//            Log.e("Called getOpacity  " + mCurrentIndex, "Checked");
//            return PixelFormat.TRANSLUCENT;
//        }
//
//        public void setAnimationEnabled() {
//
//            mDrawables[0].setCallback(getCallback());
//        }
//    }

    private class GifCombiner extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {


                Log.e("WritePermission: ", "Already granted");
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), "sanwaada");
                if (!file.exists()) {
                    file.mkdir();
                    // If you require it to make the entire directory path including parents,
                    // use directory.mkdirs(); here instead.
                }
                for (int i = 0; i < displayItemsList.size(); i++) {

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
                int width = 300;
                int height = 300;
                int delayMs = 200;

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

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
