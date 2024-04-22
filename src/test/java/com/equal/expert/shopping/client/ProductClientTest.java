package com.equal.expert.shopping.client;

import com.equal.expert.shopping.exception.ExternalServiceException;
import com.equal.expert.shopping.model.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductClientTest {

    @InjectMocks
    private ProductClient productClient;

    @Mock
    private HttpClient httpClient;

    @Mock
    private ObjectMapper mapper;

    @Test
    void getProduct_fetchProduct_Success() throws Exception {
        Product product = Product.builder().title("cheerios").price(11.11).build();

        HttpResponse response = mock(HttpResponse.class);
        when(response.body()).thenReturn("");
        when(httpClient.send(any(), any())).thenReturn(response);
        when(mapper.readValue(anyString(), eq(Product.class))).thenReturn(product);

        Optional<Product> productOptional = productClient.getProduct("cheerios");

        assertFalse(productOptional.isEmpty());
    }

    @Test
    void getProduct_ServiceError_Failure() throws Exception {
        when(httpClient.send(any(), any())).thenThrow(IOException.class);

        assertThrows(ExternalServiceException.class, () -> productClient.getProduct("cheerios"));
    }

    @Test
    void getProduct_OtherError_Failure() throws Exception {
        when(httpClient.send(any(), any())).thenThrow(RuntimeException.class);

        assertThrows(Exception.class, () -> productClient.getProduct("cheerios"));
    }
}