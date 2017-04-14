package me.vdkgid.historygid;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        int id = getIntent().getExtras().getInt("Id");

        TextView nameTextView = (TextView) findViewById(R.id.description_nameTextView);
        ImageView imageView = (ImageView) findViewById(R.id.description_ImageView);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_descriptionTextView);
        InfoParser infoParser = new InfoParser(this, id, 3, imageView, nameTextView, descriptionTextView);
        infoParser.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}
