package pl.tracz.tymoteusz.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class BuyCurrency extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    CurrencyCourseParsing currencyCourse = new CurrencyCourseParsing();
    BuyConverter buyConverter = new BuyConverter();
    Spinner sItems;
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        spinnerAdapter();

        Button button = (Button) findViewById(R.id.convert);
        button.setOnClickListener(this);
        sItems.setOnItemSelectedListener(this);
    }

    private void spinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, currencyCourse.getCurrencyList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
    }

    private float getSpinnerIndexSelected(){
        long selectedIndex = sItems.getSelectedItemId();
        float selectedIn = selectedIndex;
        return selectedIn;
    }

    private float getFirstCurrencyCount(){
        t1 = (TextView) findViewById(R.id.pln);
        String currencyAmount = t1.getText().toString().replaceAll(",",".");
        float count = Float.parseFloat(currencyAmount);
        return count;
    }

    @Override
    public void onClick(View v) {
        t2 = (TextView) findViewById(R.id.currency);
        t2.setText(Float.toString(buyConverter.convertCurrency(getFirstCurrencyCount(),getSpinnerIndexSelected()))+" PLN");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String currencyName = currencyCourse.currencyTextList.get(sItems.getSelectedItemPosition());
        TextView t3 = (TextView) findViewById(R.id.currencyName);
        t3.setText("("+currencyName+")");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
