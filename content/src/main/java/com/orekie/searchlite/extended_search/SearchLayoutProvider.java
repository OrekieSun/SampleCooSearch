package com.orekie.searchlite.extended_search;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.content.R;

/**
 * Created by orekie on 16-8-5.
 */
public class SearchLayoutProvider implements ISearchLayoutProvider {
    @Override
    public View[] getView(Resources resources, Model[] model, LayoutInflater inflater) {
        View v = inflater.inflate(resources.getLayout(R.layout.layout_sample), null);
        ((TextView) v.findViewById(R.id.tv)).setText(model[0].getData()[0]);
        ((ImageView) v.findViewById(R.id.iv)).setImageDrawable(resources.getDrawable(R.drawable.icon));
        return new View[]{v};
    }
}
