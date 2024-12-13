import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {

    // Method to simulate fetching exchange rates
    private static HashMap<String, Double> getExchangeRates(String baseCurrency) {
        HashMap<String, Double> rates = new HashMap<>();
        switch (baseCurrency.toUpperCase()) {
            case "USD":
                rates.put("INR", 83.0);  // Example: 1 USD = 83 INR
                rates.put("EUR", 0.93); // Example: 1 USD = 0.93 EUR
                rates.put("JPY", 148.5); // Example: 1 USD = 148.5 JPY
                break;
            case "INR":
                rates.put("USD", 0.012); // Example: 1 INR = 0.012 USD
                rates.put("EUR", 0.011); // Example: 1 INR = 0.011 EUR
                rates.put("JPY", 1.79);  // Example: 1 INR = 1.79 JPY
                break;
            case "EUR":
                rates.put("USD", 1.07);  // Example: 1 EUR = 1.07 USD
                rates.put("INR", 89.5);  // Example: 1 EUR = 89.5 INR
                rates.put("JPY", 160.0); // Example: 1 EUR = 160 JPY
                break;
            default:
                System.out.println("Base currency not supported.");
        }
        return rates;
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Currency Converter ---");
        
        // Step 1: Select base currency
        System.out.print("Enter the base currency (e.g., USD, INR, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Fetch exchange rates for the selected base currency
        HashMap<String, Double> rates = getExchangeRates(baseCurrency);
        if (rates.isEmpty()) {
            System.out.println("No exchange rates available for the selected currency. Exiting...");
            return;
        }

        // Step 3: Select target currency
        System.out.println("Available target currencies: " + rates.keySet());
        System.out.print("Enter the target currency: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        if (!rates.containsKey(targetCurrency)) {
            System.out.println("Invalid target currency. Exiting...");
            return;
        }

        // Step 4: Enter amount to convert
        System.out.print("Enter the amount to convert: ");
        double amount;
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a numeric value: ");
            scanner.next();
        }
        amount = scanner.nextDouble();

        // Step 5: Perform currency conversion
        double exchangeRate = rates.get(targetCurrency);
        double convertedAmount = amount * exchangeRate;

        // Step 6: Display the result
        System.out.printf("Converted Amount: %.2f %s (Exchange Rate: %.2f)\n", convertedAmount, targetCurrency, exchangeRate);

        scanner.close();
    }
}
 