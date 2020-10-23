package com.example.bai9;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tx,tx2;
    ListView listView;
    Context context;
    ArrayList<CountriesModel> countriesData;
    CustomAdapter customAdapter;
    CountriesModel countriesModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        //getviews
        btn = (Button) findViewById(R.id.additem);
        tx = (TextView) findViewById(R.id.editTextTextPersonName);
        tx2 = (TextView) findViewById(R.id.editTextTextPersonName2);
        listView = findViewById(R.id.listview);
        countriesData = new ArrayList<>();

        //add Countries Data
        populateCountriesData();

        customAdapter = new CustomAdapter(context,countriesData);
        listView.setAdapter(customAdapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(view);
                return false;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countriesModel = new CountriesModel();
                countriesModel.setId(1);
                countriesModel.setName(tx.getText().toString());
                countriesModel.setImage(R.drawable.ute);
                countriesModel.setPopulation(tx2.getText().toString());
                countriesData.add(countriesModel);
                customAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xóa")
                        .setMessage("Bạn chắc chắc muốn xóa")
                        .setPositiveButton("có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                countriesData.remove(which_item);
                                customAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("không",null)
                        .show();
                return true;
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.example_menu, menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                Toast.makeText(this, "Thêm", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_2:
                Toast.makeText(this, "Sửa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_3:
                Toast.makeText(this, "Xóa", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void populateCountriesData() {
        //country 1
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Nguyễn Hữu Thắng");
        countriesModel.setImage(R.drawable.ute);
        countriesModel.setPopulation("1811505310339");
        countriesData.add(countriesModel);

        //country 2
        countriesModel = new CountriesModel();
        countriesModel.setId(2);
        countriesModel.setName("Tôn Ngộ Không");
        countriesModel.setImage(R.drawable.tnk);

        countriesModel.setPopulation("500");
        countriesData.add(countriesModel);

        //country 3
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Trư Bát Giới");
        countriesModel.setImage(R.drawable.tbg);
        countriesModel.setPopulation("123");
        countriesData.add(countriesModel);

        //country 4
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Sa Tăng");
        countriesModel.setImage(R.drawable.st);
        countriesModel.setPopulation("77");
        countriesData.add(countriesModel);
    }


}