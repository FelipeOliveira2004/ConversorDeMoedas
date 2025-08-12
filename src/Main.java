import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao;
        double valor;
        String moeda;

        Scanner leitura = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (true) {
            System.out.println("""
                
                ********************************************
                Seja bem-vindo(a) ao Conversor de Moedas! =]
                1) Dólar --> Peso argentino
                2) Peso argentino --> Dólar
                3) Dólar --> Real brasileiro
                4) Real brasileiro --> Dólar
                5) Dólar --> Peso colombiano
                6) Peso colombiano --> Dólar
                7) Sair
                Escolha uma opção válida:
                ********************************************
                """);

            opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    moeda = "USD/ARS";
                    break;
                case 2:
                    moeda = "ARS/USD";
                    break;
                case 3:
                    moeda = "USD/BRL";
                    break;
                case 4:
                    moeda = "BRL/USD";
                    break;
                case 5:
                    moeda = "USD/COP";
                    break;
                case 6:
                    moeda = "COP/USD";
                    break;
                case 7:
                    System.out.println("\nSaindo...");
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida, tente novamente. ");
                    continue;
            }

            System.out.println("\nDigite o valor que deseja converter:");
            valor = leitura.nextDouble();

            String endereco = "https://v6.exchangerate-api.com/v6/dbb475a38300c838dfa4bb86/pair/" + moeda + "/" + valor;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                TaxaConversao taxa = gson.fromJson(json, TaxaConversao.class);

                Moeda taxaMoeda = new Moeda(taxa);
                System.out.println(taxaMoeda);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("\nNão foi possível realizar a conversão.");
            }
        }
    }
}