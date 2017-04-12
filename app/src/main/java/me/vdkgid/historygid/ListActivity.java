package me.vdkgid.historygid;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ListView listView = (ListView)findViewById(R.id.ListView);

        ArrayList<HashMap<String, Object>> mCatList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;

        for(int i = 0; i < 10; i++) {
            hm = new HashMap<>();
            hm.put("Name", "test"+i); // Название
            hm.put("Icon", R.mipmap.ic_launcher); // Картинка
            mCatList.add(hm);
        }
        hm = new HashMap<>();
        hm.put("Name", "1");
        hm.put("Icon", R.drawable.colosian);
        mCatList.add(hm);

        hm = new HashMap<>();
        hm.put("Name", "2");
        hm.put("Icon", R.drawable.colosian);
        mCatList.add(hm);

        hm = new HashMap<>();
        hm.put("Name", "3");
        hm.put("Icon", R.drawable.colosian);
        mCatList.add(hm);

        SimpleAdapter adapter = new SimpleAdapter(this, mCatList,
                R.layout.list_item, new String[]{"Name", "Icon"},
                new int[]{R.id.text1, R.id.img});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ActivityDescription.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    public void onMapButtonClick(View view) {
        finish();
    }
}
