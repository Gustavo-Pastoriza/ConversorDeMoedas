package com.Conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;

public class ConversorDeMoedas {

    private static final String MODELO_URL_API = "https://v6.exchangerate-api.com/v6/6ac6142476d18f09e81912b1/latest/";
    private final HttpClient clienteHttp;
    private final Gson gson;

    public ConversorDeMoedas() {
        this.clienteHttp = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public TaxasCambio obterTaxasCambio(String base) throws Exception {
        String url = MODELO_URL_API + base;
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resposta = clienteHttp.send(requisicao, HttpResponse.BodyHandlers.ofString());

        if (resposta.statusCode() == 200) {
            return gson.fromJson(resposta.body(), TaxasCambio.class);
        } else {
            throw new RuntimeException("Falha na requisição: " + resposta.statusCode());
        }
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha a conversão de moeda:");
            System.out.println("1. Dólar para Real");
            System.out.println("2. Real para Dólar");
            System.out.println("3. Dólar para Euro");
            System.out.println("4. Euro para Dólar");
            System.out.println("5. Dólar para Yuan");
            System.out.println("6. Yuan para Dólar");
            System.out.println("7. Sair");
            int escolha = scanner.nextInt();

            if (escolha == 7) {
                break;
            }

            System.out.println("Digite a quantidade para conversão:");
            double quantidade = scanner.nextDouble();

            realizarConversao(escolha, quantidade);
        }
        scanner.close();
    }

    public void realizarConversao(int escolha, double quantidade) {
        try {
            TaxasCambio taxasUSD = obterTaxasCambio("USD");
            switch (escolha) {
                case 1:
                    System.out.println(quantidade + " Dólar(es) é igual a "
                            + (quantidade * taxasUSD.getTaxas().getBRL()) + " Real(is).");
                    break;
                case 2:
                    System.out.println(quantidade + " Real(is) é igual a "
                            + (quantidade / taxasUSD.getTaxas().getBRL()) + " Dólar(es).");
                    break;
                case 3:
                    System.out.println(quantidade + " Dólar(es) é igual a "
                            + (quantidade * taxasUSD.getTaxas().getEUR()) + " Euro(s).");
                    break;
                case 4:
                    TaxasCambio taxasEUR = obterTaxasCambio("EUR");
                    System.out.println(quantidade + " Euro(s) é igual a "
                            + (quantidade * taxasEUR.getTaxas().getUSD()) + " Dólar(es).");
                    break;
                case 5:
                    System.out.println(quantidade + " Dólar(es) é igual a "
                            + (quantidade * taxasUSD.getTaxas().getCNY()) + " Yuan.");
                    break;
                case 6:
                    TaxasCambio taxasCNY = obterTaxasCambio("CNY");
                    System.out.println(quantidade + " Yuan é igual a "
                            + (quantidade * taxasCNY.getTaxas().getUSD()) + " Dólar(es).");
                    break;
                default:
                    System.out.println("Escolha inválida.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConversorDeMoedas conversor = new ConversorDeMoedas();
        conversor.exibirMenu();
    }
}