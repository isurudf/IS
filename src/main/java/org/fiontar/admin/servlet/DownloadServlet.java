package org.fiontar.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ftp.FTPClient;
import org.fiontar.admin.algo.Assign;
import org.fiontar.api.Database.FTPTransfer;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class DownloadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InputStream inStream = null;
        FTPClient ftp = FTPTransfer.setup(true);
        response.setContentType("application/octet-stream");
        OutputStream outStream = response.getOutputStream();

        if (request.getParameter("name").equals("Resume")) {
            String value2 = null;
            Cookie cookies2[] = request.getCookies();
            if (cookies2 != null) {
                for (int i = 0; i < cookies2.length; i++) {
                    if (cookies2[i].getName().equals("user")) {
                        value2 = cookies2[i].getValue();
                        break;
                    }
                    else if (cookies2[i].getName().equals("admin")) {
                        value2 = cookies2[i].getValue();
                        break;
                    }
                }
            }
            if (value2 == null) {
                response.setHeader("Refresh", "0; URL=login.jsp?id=You're not logged in!");
                return;
            }
            String path;
            if (value2.equals("rur13admin")) {
                if(request.getParameter("path")!=null){
                    path = request.getParameter("path");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + path + "\"");
                    ftp.retrieveFile("CV" + File.separator + path, outStream);
                }
            } else {
                Undergrad u = UndergradDA.getUndergrad(value2.substring(0, 32), value2.substring(32, 39));
                path = u.getField() + File.separator + u.getIndex() + ".pdf";
                response.setHeader("Content-Disposition", "attachment; filename=\"" + u.getIndex() + ".pdf" + "\"");
                ftp.retrieveFile("CV" + File.separator + path, outStream);
            }
            
        } else {
            return;
        }

        if (inStream != null) {
            inStream.close();
        }
        outStream.close();
        FTPTransfer.disconnect(ftp);
        /*
         response.setContentType("text/html;charset=UTF-8");

         File file = new File(filePath);
         int length = 0;
         ServletOutputStream outStream = response.getOutputStream();
         ServletContext context = getServletConfig().getServletContext();
         String mimetype = context.getMimeType(filePath);

         // sets response content type
         if (mimetype == null) {
         mimetype = "application/octet-stream";
         }
         response.setContentType(mimetype);
       
         response.setContentLength((int) file.length());
         String fileName = (new File(filePath)).getName();

         // sets HTTP header
         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

         byte[] byteBuffer = new byte[BUFSIZE];
         //       DataInputStream in = new DataInputStream(new FileInputStream(file));
         DataInputStream in = new DataInputStream(fis);
        
         // reads the file's bytes and writes them to the response stream
         while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
         outStream.write(byteBuffer, 0, length);
         }

         in.close();
         outStream.close();*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
