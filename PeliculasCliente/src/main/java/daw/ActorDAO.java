/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import pojos.Actor;
import static utilidades.ConstantesClaves.CLAVE_ACTORES;

import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class ActorDAO {
    
    public ArrayList<Actor> getActorsByMovie(CloseableHttpClient httpclient,int codRef) {
        ArrayList<Actor> actores = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletActor?op=getMovie&codRef="+codRef);
            response = httpclient.execute(httpGet);
            //Si da OK 200  es que todo ha ido bien
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            actores = mapper.readValue(PasswordHash.descifra(b64p, CLAVE_ACTORES), new TypeReference<ArrayList<Actor>>() {
            });
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
        return actores;
    }
    
    public ArrayList<Actor> getAllActors(CloseableHttpClient httpclient) {
        ArrayList<Actor> actores = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletActor?op=get");
            response = httpclient.execute(httpGet);
            //Si da OK 200  es que todo ha ido bien
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            actores = mapper.readValue(PasswordHash.descifra(b64p, "claveActores"), new TypeReference<ArrayList<Actor>>() {
            });
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
        return actores;
    }
}
