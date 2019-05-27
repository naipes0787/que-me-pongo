package quemepongo.api.clientes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import quemepongo.exceptions.ClienteHttpException;

import java.io.IOException;

public abstract class Cliente {

    private HttpClient cliente;
    private String host;
    protected ObjectMapper mapper;

    public Cliente(String host){
        this.cliente = HttpClients.createDefault();
        this.host = host;
        this.mapper = new ObjectMapper();
    }

    public HttpResponse get(String path) {
        String url = host + path;
        HttpGet httpGetRequest = new HttpGet(url);
        try{
            return cliente.execute(httpGetRequest);
        }catch (IOException exc){
            throw new ClienteHttpException(url);
        }
    }

    protected Boolean terminoEnError(HttpResponse respuesta){
        int statusCode = respuesta.getStatusLine().getStatusCode();
        return 400 <= statusCode && statusCode <= 599;
    }
}
