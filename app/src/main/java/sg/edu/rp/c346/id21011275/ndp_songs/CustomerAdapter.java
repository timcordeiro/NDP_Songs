package sg.edu.rp.c346.id21011275.ndp_songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<sg.edu.rp.c346.id21023135.l10myndpsongs.Song> rowList;

    public CustomerAdapter(Context context, int resource, ArrayList<sg.edu.rp.c346.id21023135.l10myndpsongs.Song> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        rowList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tvTitle = rowView.findViewById(R.id.tvTitle2);
        TextView tvYear = rowView.findViewById(R.id.tvYear2);
        TextView tvStars = rowView.findViewById(R.id.tvStars2);
        TextView tvSinger = rowView.findViewById(R.id.tvSinger2);
        tvTitle.setText(rowList.get(position).getTitle());
        tvYear.setText(rowList.get(position).getYear());
        tvStars.setText(rowList.get(position).getTvStars());
        tvSinger.setText(rowList.get(position).getSingers());

        return rowView;
    }
}
