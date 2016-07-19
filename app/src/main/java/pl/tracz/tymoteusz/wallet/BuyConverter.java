package pl.tracz.tymoteusz.wallet;

import android.widget.TextView;

/**
 * Created by tymoteusz.tracz on 19.07.2016.
 */
public class BuyConverter {

    CurrencyCourseParsing courseParsing = new CurrencyCourseParsing();

    public float convertCurrency(float amount, float currency_id)
    {
        int id = (int) currency_id;
        float wynik = amount * (courseParsing.sellRateList.get(id)) / courseParsing.converterList.get(id);
        return wynik;
    }
}
