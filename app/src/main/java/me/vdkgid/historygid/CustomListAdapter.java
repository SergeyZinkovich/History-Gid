package me.vdkgid.historygid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> nameAr, smallAr;

    public CustomListAdapter( Activity context1, ArrayList<String> nameAr, ArrayList<String> smallAr) {
        super(context1, R.layout.list_item, nameAr);
        this.context = context1;
        this.nameAr=nameAr;
        this.smallAr=smallAr;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        //устанавливаем тексты и картинку в элемент списка
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        TextView name = (TextView) rowView.findViewById(R.id.text1);

        //загружаем картинку по ссылке, если картинка уже была загружена, берём её из кэша
        Picasso.with(context)
                .load(smallAr.get(position))
                .into(imageView);
        name.setText(nameAr.get(position));
        return rowView;
    }
}
