package com.pramps.view;

import java.io.PrintWriter;

public class ViewUtils {
    public static void writeHeader(PrintWriter out, String title, String cssPath) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"pt-BR\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>" + title + "</title>");
        out.println("    <link rel=\"stylesheet\" href=\"" + cssPath + "\">");
        out.println("</head>");
        out.println("<body>");
    }

    public static void writeFooter(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
