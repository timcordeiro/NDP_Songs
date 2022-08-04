package sg.edu.rp.c346.id21011275.ndp_songs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewSongs extends AppCompatActivity {
    ToggleButton tbStar;
    Spinner spnYear;
    ListView lv;
    ArrayList<Song> al, alFilter, alStarFilter;
    //ArrayAdapter<Song> aa;
    ArrayList<String> alYear;
    ArrayAdapter<String> aaYear;
    CustomerAdapter caSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_songs);
        tbStar = findViewById(R.id.tbStars);
        spnYear = findViewById(R.id.spinYear);
        lv = findViewById(R.id.lvSong);

        al = new ArrayList<>();
        alFilter = new ArrayList<>();
        alStarFilter = new ArrayList<>();

        caSong = new CustomerAdapter(this, R.layout.row,al);
        lv.setAdapter(caSong);

        DBHelper dbh = new DBHelper(ListViewSongs.this);
        al.addAll(dbh.getAllSongs());

        alYear = dbh.getAllYears();
        alYear.add(0,"Filter by year");

        aaYear = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, alYear);

        spnYear.setAdapter(aaYear);

        tbStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tbStar.isChecked()) {
                    for (int i = 0; i < al.size(); i++) {
                        if (al.get(i).getStars() != 5) {
                            alStarFilter.add(al.get(i));
                        }
                    }
                    al.removeAll(alStarFilter);
                    if(spnYear.getSelectedItemPosition() != 0) {
                        for (int j = 0; j < al.size(); j++) {
                            if (al.get(j).getYear() != Integer.parseInt(spnYear.getSelectedItem().toString())) {
                                alFilter.add(al.get(j));

                            }
                        } al.removeAll(alFilter);
                    }
                } else {
                    al.clear();
                    alStarFilter.clear();
                    al.addAll(dbh.getAllSongs());
                    if(spnYear.getSelectedItemPosition()!=0) {
                        for (int j = 0; j < al.size(); j++) {
                            if (al.get(j).getYear() != Integer.parseInt(spnYear.getSelectedItem().toString())) {
                                alFilter.add(al.get(j));

                            }
                        }
                        al.removeAll(alFilter);
                    }
                }
                caSong.notifyDataSetChanged();
            }
        });
        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alFilter.clear();
                al.clear();
                al.addAll(dbh.getAllSongs());
                if (i==0){
                    alYear.set(0, "Filter by Year");
                    aaYear.notifyDataSetChanged();
                    al.clear();
                    al.addAll(dbh.getAllSongs());

                } else {
                    alYear.set(0, "View all songs");
                    for (int k = 0; k < al.size(); k++) {
                        if (al.get(k).getYear() != Integer.parseInt(adapterView.getItemAtPosition(i).toString())) {
                            alFilter.add(al.get(k));

                        }
                    }
                    al.removeAll(alFilter);
                    ArrayList<Song> temp = al;
                    if(tbStar.isChecked()){
                        for (int s = 0; s < al.size(); s++) {
                            if (al.get(s).getStars() != 5) {
                                alStarFilter.add(al.get(s));
                            }
                        }
                        al.removeAll(alStarFilter);
                    } else{
                        al = temp;
                    }
                } caSong.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ListViewSongs.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        caSong.notifyDataSetChanged();

        alYear = dbh.getAllYears();
        alYear.add(0,"Filter by year");

        aaYear = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, alYear);

        spnYear.setAdapter(aaYear);
        tbStar.setChecked(false);
    }
}