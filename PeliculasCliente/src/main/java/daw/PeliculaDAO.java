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
import pojos.Pelicula;
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_OBJECT;
import static utilidades.ConstantesClaves.CLAVE_PELICULAS;
import static utilidades.ConstantesClaves.PARAMETRO_DELETE_PELICULA;
import static utilidades.ConstantesClaves.PARAMETRO_PELICULAS;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

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
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            peliculas = mapper.readValue(PasswordHash.descifra(b64p, CLAVE_OBJECT), new TypeReference<ArrayList<Pelicula>>() {
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
        return peliculas;
    }

    public void updatePeliculas(Pelicula p, CloseableHttpClient httpclient) {
        try {

            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletPelicula?op=update");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            /*
                Convertimos el objeto a String en formato JSON
             */
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);

            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_OBJECT);
            String mandar = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_PELICULAS, mandar));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity2 = response.getEntity();

                System.out.println("HOLI"+EntityUtils.toString(entity2, "UTF-8"));
                EntityUtils.consume(entity2);
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertPelicula(Pelicula p, CloseableHttpClient httpclient) {
        int lastID = 0;
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletPelicula?op=insert");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);
            //El método cifra, devuelve un array de Bytes con el usuario cifrado
            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_OBJECT);
            //Al codificarlo en Base64 devuelve un string "churro"
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_PELICULAS, usuarioCodificado));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                //comprobamos el mensaje correcto
                String ok = EntityUtils.toString(entity, "UTF-8");
                lastID = Integer.parseInt(ok);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }

    public void deletePeliculas(int p, CloseableHttpClient httpclient) {
        try {

            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletPelicula?op=delete");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);

            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_OBJECT);
            String mandar = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_DELETE_PELICULA, mandar));

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
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
