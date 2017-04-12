package me.vdkgid.historygid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Artic extends android.support.v4.app.Fragment {

    String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        name = args.getString("Name");
        super.setArguments(args);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("Name", name);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(name == null){
            name = savedInstanceState.getString("Name");
        }
        InfoView infoView =new InfoView(getContext(), name);
        return infoView;
    }
}
