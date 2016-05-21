/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import pojos.Usuario;
import static utilidades.ConstantesClaves.CLAVE_LOGIN;
import static utilidades.ConstantesClaves.CLAVE_REGISTRO;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class UsuariosDAO {

    public boolean insertUser(Usuario u) {
        boolean inserted = false;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletUsuario?op=registra");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(u);
            //El método cifra, devuelve un array de Bytes con el usuario cifrado
            byte[] bytes = PasswordHash.cifra(usuarioJson,CLAVE_REGISTRO);
            //Al codificarlo en Base64 devuelve un string "churro"
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_POST, usuarioCodificado));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                //comprobamos el mensaje correcto
                String ok = EntityUtils.toString(entity2, "UTF-8");
                if (ok != null) {
                    inserted = true;
                } else {
                    inserted = false;
                }
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    public boolean login(Usuario u) {
        boolean loginOK = false;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletUsuario?op=login");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            
            /*
                Convertimos el objeto a String en formato JSON
             */
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(u);

            byte[] bytes = PasswordHash.cifra(usuarioJson,CLAVE_LOGIN);
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_POST, usuarioCodificado));
            //Codigo Prueba
              //nvps.add(new BasicNameValuePair("condicion", "NO"));
            /*****************/
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();

                String response = EntityUtils.toString(entity2, "UTF-8");
                //Aqui recibe el TRUE
                loginOK = response.equals("TRUE");

                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginOK;
    }
}
