package org.fiontar.api.Database;

import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPTransfer {

    public static FTPClient setup (boolean pvt) {
        try{
            FTPClient ftp = new FTPClient();
            int reply;
            ftp.connect(Constants.FTP_HOST);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new Exception("Exception in connecting to FTP Server");
            }
            ftp.login(Constants.FTP_USERNAME, Constants.FTP_PASSWORD);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            return ftp;
        }catch(Exception e){
            System.out.println("Exception in connecting to FTP Server");
            return null;
        }
        
    }
    
    public static void disconnect(FTPClient ftp) {
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                System.out.println("Logout and disconnection of FTP server failed");
            }
        }
    }
}
