package br.com.renovar.web;


import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

@ManagedBean(name = "ErrorBean")
@ViewScoped
public class ErrorBean implements Serializable {
	
	private static final long serialVersionUID = 7737656155841283868L;
	
	private StringBuilder error;
	
    public ErrorBean() {}
 
    public String getStackTrace() {
        error = new StringBuilder();
        
        // Get the current JSF context
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
 
        // Fetch the exception
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
        
        // Create a writer for keeping the stacktrace of the exception
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
 
        // Fill the stack trace into the write
        fillStackTrace(ex, pw);
                
        return error.toString();
    }
    
    public String getAllStackTrace() {
        error = new StringBuilder();
        
        // Get the current JSF context
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
 
        // Fetch the exception
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
        
        // Create a writer for keeping the stacktrace of the exception
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
 
        // Fill the stack trace into the write
        fillStackTrace(ex, pw);
        
        return writer.toString();
    }
 
    /**
    * Write the stack trace from an exception into a writer.
    *
    * @param ex
    *         Exception for which to get the stack trace
    * @param pw
    *         PrintWriter to write the stack trace
    */
    private void fillStackTrace(Throwable ex, PrintWriter pw) {
        if (null == ex) {
            return;
        }


        error.append("<br/>");
        error.append(ex.getLocalizedMessage());
        error.append("<br/>");
 
        ex.printStackTrace(pw);
 
        // The first time fillStackTrace is called it will always
       //  be a ServletException
        if (ex instanceof ServletException) {
            Throwable cause = ((ServletException) ex).getRootCause();
            if (null != cause) {
                pw.println("Root Cause:");
                fillStackTrace(cause, pw);
            }
            } else {
            // Embedded cause inside the ServletException
            Throwable cause = ex.getCause();
 
            if (null != cause) {
                pw.println("Cause:");
                fillStackTrace(cause, pw);
            }
        }
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}