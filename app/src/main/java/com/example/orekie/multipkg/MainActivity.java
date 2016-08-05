package com.example.orekie.multipkg;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orekie.searchlite.extended_search.ISearchLayoutProvider;
import com.orekie.searchlite.extended_search.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout container;
    final String[] dexPath = new String[]{"/data/app/com.example.content-1/base.apk", "/data/app/com.example.content-2/base.apk"};
    final String implName = "com.orekie.searchlite.extended_search.SearchLayoutProvider";

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class c = null;
                try {
                    c = loadClass();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.d("main", "first load failed");
                    i++;
                    try {
                        c = loadClass();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                        Log.d("main", "second load failed");
                    }
                }
                if (c != null) {
                    try {
                        ISearchLayoutProvider o = (ISearchLayoutProvider) c.newInstance();
                        View v = o.getView(getResources(dexPath[i]), new Model[]{new Model(new String[]{"Hello World !"})}, getLayoutInflater())[0];
                        Log.d("main", "v is null->" + (v == null));
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        container.removeAllViews();
                        container.addView(v, params);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有安装插件", Toast.LENGTH_SHORT).show();
                    i = 0;
                }

            }
        });

    }

    private Class loadClass() throws ClassNotFoundException {
        PathClassLoader classLoader = new PathClassLoader(dexPath[i], getClassLoader());
        return classLoader.loadClass(implName);
    }

    private Resources getResources(String dexPath) {
        Resources mResource;
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        mResource = new Resources(assetManager, getResources().getDisplayMetrics(), getResources().getConfiguration());
        return mResource;
    }
}
