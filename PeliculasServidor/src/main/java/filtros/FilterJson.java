/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import pojos.Actor;
import pojos.Genero;
import pojos.Pelicula;
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_OBJECT;
import static utilidades.ConstantesClaves.CLAVE_PELICULAS;
import static utilidades.ConstantesClaves.PARAMETRO_ACTORES;
import static utilidades.ConstantesClaves.PARAMETRO_DELETE_PELICULA;
import static utilidades.ConstantesClaves.PARAMETRO_GENERO;
import static utilidades.ConstantesClaves.PARAMETRO_PELICULAS;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class FilterJson implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterJson() {
    }

    public Pelicula descifrarPelicula(ObjectMapper mapper, String o, String k) {
        Pelicula p = null;
        byte[] b64;
        try {
            b64 = Base64.decodeBase64(o.getBytes("UTF-8"));
            p = mapper.readValue(PasswordHash.descifra(b64, k), new TypeReference<Pelicula>() {
            });
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    public Actor descifrarActor(ObjectMapper mapper, String o, String k) {
        Actor p = null;
        byte[] b64;
        try {
            b64 = Base64.decodeBase64(o.getBytes("UTF-8"));
            p = mapper.readValue(PasswordHash.descifra(b64, k), new TypeReference<Actor>() {
            });
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public Genero descifrarGenero(ObjectMapper mapper, String o, String k) {
        Genero g = null;
        byte[] b64;
        try {
            b64 = Base64.decodeBase64(o.getBytes("UTF-8"));
            g = mapper.readValue(PasswordHash.descifra(b64, k), new TypeReference<Genero>() {
            });
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
    public Integer descifrarIntPelicula(ObjectMapper mapper, String o, String k) {
        int p = 0;
        byte[] b64;
        try {
            b64 = Base64.decodeBase64(o.getBytes("UTF-8"));
            p = mapper.readValue(PasswordHash.descifra(b64, k), new TypeReference<Integer>() {
            });
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterJson:DoBeforeProcessing");
        }

        ObjectMapper mapper = new ObjectMapper();
        //PELICULAS
        if (request.getParameter(PARAMETRO_PELICULAS) != null) {
            request.setAttribute("request", descifrarPelicula(mapper, request.getParameter(PARAMETRO_PELICULAS), CLAVE_OBJECT));
        }
        if (request.getParameter(PARAMETRO_DELETE_PELICULA) != null) {
            request.setAttribute("requestdelete", descifrarIntPelicula(mapper, request.getParameter(PARAMETRO_DELETE_PELICULA), CLAVE_OBJECT));
        }
        //Actores
        
        if (request.getParameter(PARAMETRO_ACTORES) != null) {
            request.setAttribute("request", descifrarActor(mapper, request.getParameter(PARAMETRO_ACTORES), CLAVE_OBJECT));
        }
        if (request.getParameter(PARAMETRO_GENERO) != null) {
            request.setAttribute("request", descifrarGenero(mapper, request.getParameter(PARAMETRO_GENERO), CLAVE_OBJECT));
        }
        
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterJson:DoAfterProcessing");
        }
        try {
            Object o = null;
            if (request.getAttribute("send") != null) {
                o = request.getAttribute("send");
                response.getWriter().print(cifrar(o, CLAVE_OBJECT));
            }
            

        } catch (IOException ex) {
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String cifrar(Object o, String key) {
        String objeto = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String objetoJson = mapper.writeValueAsString(o);
            byte[] bytes = PasswordHash.cifra(objetoJson, key);
            objeto = new String(Base64.encodeBase64(bytes));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FilterJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("FilterJson:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            /*
                Para una sola llamada se usa request !
               - 
             */
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterJson:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterJson()");
        }
        StringBuffer sb = new StringBuffer("FilterJson(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
