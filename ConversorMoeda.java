import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;

// Classe para mapear a resposta da API
class ExchangeRateResponse {
    String result;
    String base_code;
    Map<String, Double> conversion_rates;
}

public class ConversorMoeda {
    private static final String API_KEY = "";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== CONVERSOR DE MOEDAS =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. USD → BRL");
            System.out.println("2. BRL → USD");
            System.out.println("3. USD → ARS");
            System.out.println("4. ARS → USD");
            System.out.println("5. BRL → COP");
            System.out.println("6. COP → BRL");
            System.out.println("7. Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Programa encerrado.");
                break;
            }

            System.out.print("Digite o valor a converter: ");
            double valor = scanner.nextDouble();

            String from = "";
            String to = "";

            switch (opcao) {
                case 1: from = "USD"; to = "BRL"; break;
                case 2: from = "BRL"; to = "USD"; break;
                case 3: from = "USD"; to = "ARS"; break;
                case 4: from = "ARS"; to = "USD"; break;
                case 5: from = "BRL"; to = "COP"; break;
                case 6: from = "COP"; to = "BRL"; break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            double taxa = getTaxaCambio(from, to);
            if (taxa != 0) {
                double convertido = convert(valor, taxa);
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", valor, from, convertido, to);
            } else {
                System.out.println("Erro ao obter taxa de câmbio!");
            }
        }

        scanner.close();
    }

    private static double getTaxaCambio(String from, String to) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + from))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Erro na API. Código HTTP: " + response.statusCode());
                return 0;
            }

            Gson gson = new Gson();
            ExchangeRateResponse resposta = gson.fromJson(response.body(), ExchangeRateResponse.class);

            if (!"success".equals(resposta.result)) {
                System.err.println("Erro na resposta da API.");
                return 0;
            }

            return resposta.conversion_rates.get(to);

        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return 0;
        }
    }

    private static double convert(double valor, double taxa) {
        return valor * taxa;
    }
}
