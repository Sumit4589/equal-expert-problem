package com.equal.expert.shopping.client;

import com.equal.expert.shopping.exception.ExternalServiceException;
import com.equal.expert.shopping.exception.InvalidRequestException;
import com.equal.expert.shopping.model.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ProductClient {
    private static ProductClient productClient;

    public static synchronized ProductClient getInstance() {
        if (productClient == null) {
            productClient = new ProductClient();
        }
        return productClient;
    }

    private HttpClient client;

    private ObjectMapper mapper;

    private String uri = "https://equalexperts.github.io//backend-take-home-test-data/%s.json";

    private ProductClient() {
        client = HttpClient.newBuilder().build();
        mapper = new ObjectMapper();
    }

    public Optional<Product> getProduct(String productName) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri.formatted(productName)))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Product product = mapper.readValue(responseString, Product.class);
            return Optional.ofNullable(product);
        } catch (URISyntaxException ex) {
            throw new InvalidRequestException("invalid uri", ex);
        } catch (IOException | InterruptedException ex) {
            throw new ExternalServiceException("external service call failed", ex);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
