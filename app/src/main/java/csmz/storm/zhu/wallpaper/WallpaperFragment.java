package csmz.storm.zhu.wallpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonFragment;
import csmz.storm.zhu.wallpaper.view.IWallpaperView;

public class WallpaperFragment extends CommonFragment implements IWallpaperView {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallpaper_fragment, container, false);
        return view;
    }

    @Override
    public void initUI(View view) {
        recyclerView = findView(R.id.recycler_wallpaper, view);
    }

    @Override
    public void initData() {

    }
}
