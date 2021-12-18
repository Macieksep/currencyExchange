import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class CurrencyExchange {

    public static void main(String[] args) {

        converter.setRefreshRateSeconds(60);

        CurrencyWindow win = new CurrencyWindow("Currency Exchange");

        JLabel ammoutLabel = new JLabel("Ammout:");
        ammoutLabel.setForeground(Color.white);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.white);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.white);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.white);

        JComboBox<Currency> currencyBox1 = new JComboBox<>();
            addCurrencies(currencyBox1);
            currencyBox1.setFocusable(false);
            currencyBox1.setSelectedItem(Currency.USD);

        JComboBox<Currency> currencyBox2 = new JComboBox<>();
            addCurrencies(currencyBox2);
            currencyBox2.setFocusable(false);
            currencyBox2.setSelectedItem(Currency.PLN);

        JButton convertButton = new JButton("Convert");
            convertButton.setBackground(Color.WHITE);
            convertButton.setFocusable(false);

        JLabel outcomeLabel = new JLabel("0");
            outcomeLabel.setForeground(Color.white);

        JTextField ammoutField = new JTextField();
            ammoutField.setText("1.00");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double moneyAmmout = 1;

                try {
                    moneyAmmout = Double.parseDouble(ammoutField.getText());
                } catch (Exception ignored){}

                Currency currency1 = (Currency)currencyBox1.getSelectedItem();
                Currency currency2 = (Currency)currencyBox2.getSelectedItem();

                try {
                    if (String.valueOf(converter.convertCurrency(new BigDecimal(moneyAmmout), currency1, currency2)).length()>=19){
                        outcomeLabel.setText("Oops! Too long!");
                    } else {
                        outcomeLabel.setText(String.valueOf(converter.convertCurrency(new BigDecimal(moneyAmmout), currency1, currency2)));
                    }
                } catch (Exception ignored){}

            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.blue);

        JPanel panel1 = new JPanel();
            panel1.setBackground(Color.blue);
            panel1.setLayout(new GridLayout(2,1));

        JPanel panel2 = new JPanel();
            panel2.setBackground(Color.blue);
            panel2.setLayout(new GridLayout(2,1));

        JPanel panel3 = new JPanel();
            panel3.setBackground(Color.blue);
            panel3.setLayout(new GridLayout(2,1));

        JPanel panel4 = new JPanel();
            panel4.setBackground(Color.blue);
            panel4.setLayout(new GridLayout(2,1));

        JPanel panel5 = new JPanel();
            panel5.setBackground(Color.blue);
            panel5.setLayout(new GridLayout(2,1));

        {

            panel1.add(ammoutLabel);
            panel1.add(ammoutField);

            panel2.add(fromLabel);
            panel2.add(currencyBox1);

            panel3.add(toLabel);
            panel3.add(currencyBox2);

            panel4.add(new JLabel(""));
            panel4.add(convertButton);

            panel5.add(resultLabel);
            panel5.add(outcomeLabel);

            mainPanel.add(panel1);
            mainPanel.add(panel2);
            mainPanel.add(panel3);
            mainPanel.add(panel4);
            mainPanel.add(panel5);

            win.add(mainPanel);
            win.setVisible(true);
        }

    }

    static void addCurrencies(JComboBox<Currency> comboBox){

        List<Currency> currencies = new ArrayList<>(Arrays.asList(Currency.values()));

        currencies.forEach(comboBox::addItem);

    }

    static String accessKey() throws IOException {
        File file = new File(".gitignore");

        Scanner scanner = new Scanner(file);

        return scanner.nextLine();

    }

    static CurrencyConverter converter;
    static {
        try {
            converter = new CurrencyConverterBuilder()
                    .strategy(Strategy.CURRENCY_LAYER_FILESTORE)
                    .accessKey(accessKey()) //https://currencylayer.com/signup/free << free accessKey here!
                    .buildConverter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
    Title: Currency Exchange
    Author: Maciej Sepeta
    Description: Currency Exchange GUI program with most of present currencies
    ver. 0.1.0
 */