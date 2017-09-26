package com.example.thiqah.testretrofit.ItemView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.thiqah.testretrofit.R;
import com.example.thiqah.testretrofit.data.User;

public class ItemActivity extends AppCompatActivity implements ItemContract.View {

    public static final String USER_TITLE_KEY = "userKey";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        String s = getIntent().getExtras().getString(USER_TITLE_KEY);
        ItemPresenter itemPresenter = new ItemPresenter(this, s, this);
        itemPresenter.validateUser();
    }

    @Override
    public void ShowItem(User user) {
        TextView textView = (TextView) findViewById(R.id.txt_item);
        textView.setText(user.getTitle());
    }
}
