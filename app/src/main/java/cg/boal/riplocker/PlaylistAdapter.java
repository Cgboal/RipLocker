package cg.boal.riplocker;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cgboal on 28/03/2017.
 */

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    private Context context;
    private List<Playlist> data = null;

    public PlaylistAdapter(Context context, List<Playlist> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
        }
        TextView txtView = (TextView) row.findViewById(R.id.tvName);
        Playlist p = data.get(position);
        txtView.setText(p.getName());
        return row;

    }

}
