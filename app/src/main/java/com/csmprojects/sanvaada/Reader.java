package com.csmprojects.sanvaada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Reader extends AppCompatActivity {
TextView meaningTxtView;
    Button back,keyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        meaningTxtView=(TextView)findViewById(R.id.readerLayoutMeaningTxtView);
        String meaning="";
        if(this.getIntent().hasExtra("meaning")){
            meaning=this.getIntent().getStringExtra("meaning");
            meaningTxtView.setText(meaning);
        }

        back=(Button)findViewById(R.id.readerLayoutBackBtn);
        keyboard=(Button)findViewById(R.id.readerLayoutKeyBoardBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Reader.this,Main.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(mainIntent);
                finish();
            }
        });

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent = new Intent(Reader.this,Words.class);
                startActivity(intent);
            }
        });
    }

}
