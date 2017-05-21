package com.example.ian.top10downloader;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.ian.top10downloader.R.id.tvArtist;
import static com.example.ian.top10downloader.R.id.tvName;
import static com.example.ian.top10downloader.R.id.tvSummary;

/**
 * Created by ian on 18/05/2017.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        FeedEntry currentApp = applications.get(position);

        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        viewHolder.tvSummary.setText(currentApp.getSummary());

        return convertView;

    }

    private class ViewHolder{
        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;

        public ViewHolder(View view) {
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.tvArtist = (TextView) view.findViewById(R.id.tvArtist);
            this.tvSummary = (TextView) view.findViewById(R.id.tvSummary);
        }
    }
}
