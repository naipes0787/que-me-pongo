package quemepongo.api.clientes;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import quemepongo.exceptions.ClienteHttpException;

import java.io.IOException;

public class Cliente {

    private HttpClient cliente;
    private String host;

    public Cliente(String host){
        this.cliente = HttpClients.createDefault();
        this.host = host;
    }

    private HttpResponse get(String path){
        String url = host + path;
        HttpGet httpGetRequest = new HttpGet(url);
        try{
            return cliente.execute(httpGetRequest);
        }catch (IOException exc){
            throw new ClienteHttpException(path);
        }
    }

    public String getAsString(String path){
        HttpResponse respuesta = this.get(path);
        if (terminoEnError(respuesta)) throw new ClienteHttpException(path);
        try{
            return EntityUtils.toString(respuesta.getEntity());
        }catch (IOException exc){
            throw new ClienteHttpException(path);
        }
    }

    private Boolean terminoEnError(HttpResponse respuesta){
        int statusCode = respuesta.getStatusLine().getStatusCode();
        return 400 <= statusCode && statusCode <= 599;
    }
}
