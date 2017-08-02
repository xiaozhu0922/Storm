package csmz.storm.zhu.wallpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonFragment;

public class WallpaperFragment extends CommonFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallpaper_fragment, container, false);
        return view;
    }
}
