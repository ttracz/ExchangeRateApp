package pl.tracz.tymoteusz.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRate extends AppCompatActivity {

    private ListView lv;
    private List<String> currencyList = new ArrayList<>();
    CurrencyCourseParsing courseParsing = new CurrencyCourseParsing();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);
        for (int i=0; i<courseParsing.sellRateList.size(); i++)
        {
            currencyList.add(courseParsing.currencyNameList.get(i)+":              "+courseParsing.buyRateList.get(i)+"              "+courseParsing.sellRateList.get(i));
        }
        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                currencyList );

        lv.setAdapter(arrayAdapter);
    }
}
