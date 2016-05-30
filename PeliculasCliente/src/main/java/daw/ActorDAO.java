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
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_ACTORES;
import static utilidades.ConstantesClaves.CLAVE_OBJECT;
import static utilidades.ConstantesClaves.PARAMETRO_ACTORES;
import static utilidades.ConstantesClaves.PARAMETRO_POST;

import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class ActorDAO {

    public Actor getActor(CloseableHttpClient httpclient, String name){
        Actor actor = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletActor?op=getActor&name=" + name);
            response = httpclient.execute(httpGet);
             System.out.println("ESTADOOOOO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
             ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            actor = mapper.readValue(PasswordHash.descifra(b64p,CLAVE_OBJECT), new TypeReference<Actor>() {
            });
        } catch (Exception e) {
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return actor;
    }
    public ArrayList<Actor> getActorsByMovie(CloseableHttpClient httpclient, int codRef) {
        ArrayList<Actor> actores = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/PeliculasServidor/ServletActor?op=getMovie&codRef=" + codRef);
            response = httpclient.execute(httpGet);
            //Si da OK 200  es que todo ha ido bien
            System.out.println("ESTADO: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String c = EntityUtils.toString(entity);
            //Desencriptando
            ObjectMapper mapper = new ObjectMapper();
            byte[] b64p = Base64.decodeBase64(c);
            actores = mapper.readValue(PasswordHash.descifra(b64p,CLAVE_OBJECT), new TypeReference<ArrayList<Actor>>() {
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
            actores = mapper.readValue(PasswordHash.descifra(b64p, CLAVE_OBJECT), new TypeReference<ArrayList<Actor>>() {
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

    public void updateActores(Actor a, CloseableHttpClient httpclient) {
        try {

            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletActor?op=update");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            /*
                Convertimos el objeto a String en formato JSON
             */
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(a);

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
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertActores(Actor p, CloseableHttpClient httpclient) {
        boolean inserted = false;
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletActor?op=insert");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);
            //El método cifra, devuelve un array de Bytes con el usuario cifrado
            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_OBJECT);
            //Al codificarlo en Base64 devuelve un string "churro"
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_ACTORES, usuarioCodificado));

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
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }
    
     public boolean insertActuan(Actor p, int idPelicula,CloseableHttpClient httpclient) {
        boolean inserted = false;
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletActor?op=insertActuan&id=" + idPelicula);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // Convertimos el objeto a String en formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(p);
            //El método cifra, devuelve un array de Bytes con el usuario cifrado
            byte[] bytes = PasswordHash.cifra(usuarioJson, CLAVE_OBJECT);
            //Al codificarlo en Base64 devuelve un string "churro"
            String usuarioCodificado = new String(Base64.encodeBase64(bytes));

            nvps.add(new BasicNameValuePair(PARAMETRO_ACTORES, usuarioCodificado));

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
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    public void deleteActores(int p, CloseableHttpClient httpclient) {
        try {
            //Hacemos una peticion HTTP mediante el metodo POST
            HttpPost httpPost = new HttpPost("http://localhost:8080/PeliculasServidor/ServletActor?op=delete");
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
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
