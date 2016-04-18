/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import pojos.Director;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class DirectorDAO {

    public boolean insertDirector(Director d) {
        boolean inserted = false;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response1 = null;
        try {
            HttpPost httpPost1 = new HttpPost("http://localhost:8080/PeliculasServidor/ServletDirector?op=insert");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(d);

            byte[] bytes = PasswordHash.cifra(usuarioJson, "clave");
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));
            nvps.add(new BasicNameValuePair("userInsert", usuarioCodificado));
            httpPost1.setEntity(new UrlEncodedFormEntity(nvps));
            response1 = httpclient.execute(httpPost1);

            HttpEntity entity1 = response1.getEntity();
            String res = EntityUtils.toString(entity1);
            inserted = res.equalsIgnoreCase("TRUE");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (response1 != null) {
                try {
                    response1.close();
                } catch (IOException ex) {
                    Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return inserted;
    }
}
