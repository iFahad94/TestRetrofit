package com.example.thiqah.testretrofit.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thiqah.testretrofit.ItemView.ItemActivity;
import com.example.thiqah.testretrofit.R;
import com.example.thiqah.testretrofit.data.User;
import com.example.thiqah.testretrofit.notification.ClickActionHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ListView listView;
    private MainPresenter mainPresenter;
    private ArrayList<User> users;
    private MyAdapter myAdapter;
    private ProgressBar progressBar;

    private UserClickListener userClickListener = new UserClickListener() {
        @Override
        public void onUserClicked(User clickedUser) {
            mainPresenter.openUserDetails(clickedUser);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.loadUsers();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount && absListView.getAdapter() != null) {
                    ArrayList<User> usersList = users;
                    users.addAll(usersList);
                    myAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        checkIntent(getIntent());
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        this.users = users;
        myAdapter = new MyAdapter(users);
        listView.setAdapter(myAdapter);
    }

    @Override
    public void openUserView(String title) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(ItemActivity.USER_TITLE_KEY, title);
        startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {

        private ArrayList<User> User;

        MyAdapter(ArrayList<User> User) {
            this.User = User;
        }

        @Override
        public int getCount() {
            return User.size();
        }

        @Override
        public Object getItem(int i) {
            return User.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;

            final User user = (User) getItem(i);

            if (rowView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
                rowView = layoutInflater.inflate(R.layout.item_list, viewGroup, false);
            }

            TextView textView = rowView.findViewById(R.id.txtName);
            TextView txtIndex = rowView.findViewById(R.id.txtIndex);

            textView.setText(user.getTitle());
            txtIndex.setText(String.valueOf(i));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userClickListener.onUserClicked(user);
                }
            });
            return rowView;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkIntent(intent);
    }

    interface UserClickListener {
        void onUserClicked(User clickedUser);
    }

    public void checkIntent(Intent intent) {
        if (intent.hasExtra("click_action")) {
            ClickActionHelper.startActivity(intent.getStringExtra("click_action"), intent.getExtras(), this);
        }
    }
}
