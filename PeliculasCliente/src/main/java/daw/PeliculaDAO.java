/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import pojos.Pelicula;

/**
 *
 * @author Junior
 */
public class PeliculaDAO {

    public ArrayList<Pelicula> getAllPeliculas(CloseableHttpClient httpclient) {
        ArrayList<Pelicula> peliculas = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletPelicula?op=get");
            response = httpclient.execute(httpGet);
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();

            ObjectMapper mapper = new ObjectMapper();
            String c = EntityUtils.toString(entity);

            if (!c.equalsIgnoreCase("FALSE")) {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                peliculas = mapper.readValue(c, new TypeReference<ArrayList<Pelicula>>() {
                });
            }
        } catch (Exception e) {
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return peliculas;
    }

}
