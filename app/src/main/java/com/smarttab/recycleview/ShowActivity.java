package com.smarttab.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowActivity extends AppCompatActivity {

    TextView email,name;
    String getName,getEmail;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        email= (TextView) findViewById(R.id.getEmail);
        name= (TextView) findViewById(R.id.getName);
        imageView= (ImageView) findViewById(R.id.getImage);
        email.setText(getIntent().getStringExtra("email"));
        name.setText(getIntent().getStringExtra("name"));
        Picasso.with(this).load(Constant.ImagePath+getIntent().getStringExtra("image")).into(imageView);

    }
}
