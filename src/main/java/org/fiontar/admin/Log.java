/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fiontar.admin;

/**
 *
 * @author New
 */
public class Log {
    private static String log =null;


    public static String getLog() {
        return log;
    }

    public static void addLog(String lognew) {
        if(log!=null)
            log += "<br></br>"+lognew;
        else
            log=lognew;
    }
    
}
