/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import pojos.Actor;
import pojos.Director;
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_ACTORES;
import static utilidades.ConstantesClaves.CLAVE_OBJECT;
import static utilidades.ConstantesClaves.PARAMETRO_POST;

import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class DirectorDAO {

    public ArrayList<Director> getDirectorByMovie(CloseableHttpClient httpclient, int codRef) {
        ArrayList<Director> directores = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletDirector?op=getMovie&codRef=" + codRef);
            response = httpclient.execute(httpGet);
            //Si da OK 200  es que todo ha ido bien
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            directores = mapper.readValue(PasswordHash.descifra(b64p,CLAVE_OBJECT), new TypeReference<ArrayList<Director>>() {
            });
        } catch (Exception e) {
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return directores;
    }

    public ArrayList<Director> getAllDirectores(CloseableHttpClient httpclient) {
        ArrayList<Director> directores = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletDirector?op=get");
            response = httpclient.execute(httpGet);
            //Si da OK 200  es que todo ha ido bien
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            directores = mapper.readValue(PasswordHash.descifra(b64p, CLAVE_OBJECT), new TypeReference<ArrayList<Director>>() {
            });
        } catch (Exception e) {
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return directores;
    }

    public void updateDirectores(Director d, CloseableHttpClient httpclient) {
        try {

            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletDirector?op=update");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            /*
                Convertimos el objeto a String en formato JSON
             */
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(d);

            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_ACTORES);
            String mandar = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_POST, mandar));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity2 = response.getEntity();

                System.out.println(EntityUtils.toString(entity2, "UTF-8"));
                EntityUtils.consume(entity2);
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertDirectores(Director d, int n_referencia, CloseableHttpClient httpclient) {
        boolean inserted = false;
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletDirector?op=insert&id=" + n_referencia);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(d);
            //El método cifra, devuelve un array de Bytes con el usuario cifrado
            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_ACTORES);
            //Al codificarlo en Base64 devuelve un string "churro"
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_POST, usuarioCodificado));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                //comprobamos el mensaje correcto
                String ok = EntityUtils.toString(entity, "UTF-8");
                if (ok != null) {
                    inserted = true;
                } else {
                    inserted = false;
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    public void deleteDirectores(int p, CloseableHttpClient httpclient) {
        try {

            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletDirector?op=delete");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);

            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_ACTORES);
            String mandar = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_POST, mandar));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity2 = response.getEntity();

                System.out.println(EntityUtils.toString(entity2, "UTF-8"));
                EntityUtils.consume(entity2);
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DirectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
