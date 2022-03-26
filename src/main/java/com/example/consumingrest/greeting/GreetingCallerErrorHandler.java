package com.example.consumingrest.greeting;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class GreetingCallerErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            if(clientHttpResponse.getStatusCode().is5xxServerError()){
                String errorMessage = getErrorMessage(clientHttpResponse.getBody());
                throw new GreetingCallerServerError(errorMessage);
            }

        throw new RuntimeException();
    }

    private String getErrorMessage(InputStream body) {
        String errorMessage = "";
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(body, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            errorMessage = jsonObject.optString("errorMessage", "Default Error Message");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }
}
