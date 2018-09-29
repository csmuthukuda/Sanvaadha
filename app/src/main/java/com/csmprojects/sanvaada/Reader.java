package com.csmprojects.sanvaada;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Reader extends AppCompatActivity {
    TextView meaningTxtView;
    Button back, keyboard;
    ImageButton about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reader_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        Spinner language = getSupportActionBar().getCustomView().findViewById(R.id.language);
        language.setVisibility(View.GONE);

        about = getSupportActionBar().getCustomView().findViewById(R.id.about);
        about.setVisibility(View.GONE);

        TextView version = getSupportActionBar().getCustomView().findViewById(R.id.version);
        version.setVisibility(View.GONE);

        meaningTxtView = (TextView) findViewById(R.id.readerLayoutMeaningTxtView);
        String meaning = "";
        if (this.getIntent().hasExtra("meaning")) {
            meaning = this.getIntent().getStringExtra("meaning");
            meaningTxtView.setText(meaning);
        }

        back = (Button) findViewById(R.id.readerLayoutBackBtn);
        keyboard = (Button) findViewById(R.id.readerLayoutKeyBoardBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Reader.this, Main.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(mainIntent);
                finish();
            }
        });

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reader.this, Words.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //NavUtils.navigateUpFromSameTask(this);
            Intent mainIntent = new Intent(Reader.this, Main.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(mainIntent);
            finish();
           // overridePendingTransition(android.R.anim.slide_out_right, 0);
            return true;
        }
        return false;
    }

}
