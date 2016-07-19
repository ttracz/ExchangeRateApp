package pl.tracz.tymoteusz.wallet;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by tymoteusz.tracz on 19.07.2016.
 */
public class CurrencyCourseParsing {

    public List<String> currencyNameList = new ArrayList<>();
    public List<Float> buyRateList = new ArrayList<>();
    public List<Float> sellRateList = new ArrayList<>();
    public List<Integer> converterList = new ArrayList<>();
    public List<String> currencyTextList = new ArrayList<>();
    public String date = " ";
    public NodeList nodeList;
    public NodeList nodeListDate;

    public CurrencyCourseParsing() {
        try {

            URL url = new URL("http://www.nbp.pl/kursy/xml/LastC.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName("pozycja");


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element element = (Element) node;

                NodeList currencyCode = element.getElementsByTagName("kod_waluty");
                Element codeElement = (Element) currencyCode.item(0);
                currencyCode = codeElement.getChildNodes();
                currencyNameList.add(currencyCode.item(0).getNodeValue());

                NodeList buyRate = element.getElementsByTagName("kurs_kupna");
                Element buyRateElement = (Element) buyRate.item(0);
                buyRate = buyRateElement.getChildNodes();
                String buyRateString = buyRate.item(0).getNodeValue().toString();
                buyRateString = buyRateString.replaceAll(",",".");
                buyRateList.add(Float.parseFloat(buyRateString));

                NodeList sellRate = element.getElementsByTagName("kurs_sprzedazy");
                Element sellRateElement = (Element) sellRate.item(0);
                sellRate = sellRateElement.getChildNodes();
                String sellRateString = sellRate.item(0).getNodeValue().toString();
                sellRateString = sellRateString.replaceAll(",",".");
                sellRateList.add(Float.parseFloat(sellRateString));

                NodeList converter = element.getElementsByTagName("przelicznik");
                Element converterElement = (Element) converter.item(0);
                converter = converterElement.getChildNodes();
                converterList.add(Integer.parseInt(converter.item(0).getNodeValue()));

                NodeList currencyText = element.getElementsByTagName("nazwa_waluty");
                Element currencyElement = (Element) currencyText.item(0);
                currencyText = currencyElement.getChildNodes();
                currencyTextList.add(currencyText.item(0).getNodeValue());

            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List getCurrencyList()
    {
        return currencyNameList;
    }

    public List buyRateList()
    {
        return buyRateList;
    }

    public List sellRateList()
    {
        return sellRateList;
    }
}
