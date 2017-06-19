package cg.boal.riplocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cgboal on 29/03/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    private Context context;
    private List<Song> data = null;

    public SongAdapter(Context context, List<Song> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
        }
        TextView txtTitle = (TextView) row.findViewById(R.id.txtTitle);
        TextView txtArtist = (TextView) row.findViewById(R.id.txtArtist);
        Song s = data.get(position);
        txtTitle.setText(s.getTitle());
        txtArtist.setText(s.getArtist());
        return row;
    }
}
