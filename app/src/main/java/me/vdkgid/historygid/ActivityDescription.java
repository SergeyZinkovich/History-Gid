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

        ImageView imageView = (ImageView) findViewById(R.id.description_ImageView);
        imageView.setImageResource(R.drawable.colosian);

        TextView textView = (TextView) findViewById(R.id.description_TextView);
        textView.setText(R.string.large_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}
