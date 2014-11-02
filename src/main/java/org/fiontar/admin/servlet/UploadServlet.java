package org.fiontar.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;

import org.fiontar.api.Database.FTPTransfer;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    //   private static final String UPLOAD_DIRECTORY = "Uploads";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 10;  // 10MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // checks if the request actually contains upload file
        PrintWriter out = response.getWriter();
        String cookieName2 = "user", value2 = null;
        Cookie cookie = null;
        boolean admin = false;
        Cookie cookies2[] = request.getCookies();
        if (cookies2 != null) {
            for (int i = 0; i < cookies2.length; i++) {
                if (cookies2[i].getName().equals(cookieName2)) {
                    value2 = cookies2[i].getValue();
                    cookie = cookies2[i];
                    break;
                } else if (cookies2[i].getName().equals("admin")) {
                    if (cookies2[i].getValue().equals("rur13admin")) {
                        admin = true;
                    }
                    break;
                }
            }
        }
        Undergrad a = null;
        String path = null;

        if (!admin) {

            if (value2 == null || cookie == null) {
                response.setHeader("Refresh", "0; URL=login.jsp?id=You're not logged in!");
                return;
            }
            a = UndergradDA.getUndergrad(value2.substring(0, 32), value2.substring(32, 39));
            path = "CV" + File.separator;
            if (a == null) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
                return;
            }
        }
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            response.sendRedirect("message.jsp?message=" + "Error: Form must has enctype=multipart/form-data.");

            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();

        if (!admin) {
            // sets memory threshold - beyond which files are stored in disk 
            factory.setSizeThreshold(MEMORY_THRESHOLD);
        }
        // sets temporary location to store files
//        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        if (!admin) {
            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);

            // sets maximum size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);
        }
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        //       String uploadPath = getServletContext().getRealPath("")
        //             + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist

        try {
            // parses the request's content to extract file data

            List<FileItem> formItems = upload.parseRequest(request);
            FileItem fileItem = null;
            String fileType = null;
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        fileItem = item;
                    } else {
                        fileType = item.getString();
                    }
                }
            }
            //  String filePath = uploadPath + File.separator + fileName;
            String message = "";
            if (fileType.equals("Resume")) {
                String fileName = fileItem.getName();
                if (!fileName.equals(a.getIndex() + ".pdf")) {
                    message = "Resume file name is not in the specified format " + a.getIndex() + ".pdf!";
                } else {
                    FTPClient ftp = FTPTransfer.setup(true);
                    InputStream inputStream = fileItem.getInputStream();
                    if (a.getResume().length() > 5) {
                        ftp.deleteFile(path + a.getResume());
                    }
                    ftp.storeFile(path + a.getField() + File.separator + fileName, inputStream);
                    inputStream.close();
                    a.setResume(a.getField() + File.separator + fileName);
                    UndergradDA.updateResume(a);
                    message = "Resume Uploaded Successfully!";
                }
            } else if (admin) {
                FTPClient ftp = FTPTransfer.setup(true);
                InputStream inputStream = fileItem.getInputStream();
                ftp.storeFile(fileType, inputStream);
                inputStream.close();
                message = "File Uploaded Successfully!";
            }
            response.sendRedirect("message.jsp?message=" + message);

        } catch (Exception ex) {
            response.sendRedirect("message.jsp?message=There was an error uploading the Resume. Make sure the file size is below 5MB and try again."
                    + "If the problem persists, please contact us via admin@rotaractmora.org or the contact form.");

        }


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
