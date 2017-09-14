/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entersekt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author npilusa
 */
@Path("entersekt")
public class Entersekt {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Entersekt
     */
    public Entersekt() {
    }

    /**
     * Retrieves representation of an instance of entersekt.Entersekt
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() throws IOException {
        File curDir = new File(".");
        String files = getAllFiles(curDir);
        String dir = System.getProperty("user.dir");
        
        Files.list(Paths.get("."))
        .forEach(System.out::println);
        
        return "<html>"+
                "<style>\n" +
"table {\n" +
"    font-family: arial, sans-serif;\n" +
"    border-collapse: collapse;\n" +
"    width: 100%;\n" +
"}\n" +
"\n" +
"td, th {\n" +
"    border: 1px solid #dddddd;\n" +
"    text-align: left;\n" +
"    padding: 8px;\n" +
"}\n" +
"\n" +
"tr:nth-child(even) {\n" +
"    background-color: #dddddd;\n" +
"}\n" +
"</style>"+
                "<head>"+"<title>DirListing</title>"+"</head>"
                + "<body>"
                + "<h1>Index of "+dir+"</h1>"
                + "<table>"
                + "<tr> "
                +   "<th>Name</th>"
                +   "<th>Last Modified</th>"
                +   "<th>Size</th>"
                + "</tr>"+files+
                "</table>"
                + "<div>By: Norman Pilusa</div>"
                +"</body>"
                + "</html>";
    }

    /**
     * PUT method for updating or creating an instance of Entersekt
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }

    private static String getAllFiles(File curDir) {
        String dir = "";
        
        File[] filesList = curDir.listFiles();
        for (File f : filesList) {
            if (f.isDirectory()) {
                dir += "<tr>"+f.getAbsolutePath()+"\t"+f.length()+"</tr>";
            }
            if (f.isFile()) {
                dir += "<tr>"
                        +"<td>"+f.getName()+"</td>"
                        +"<td>"+new Date(f.lastModified())+"</td>"
                        +"<td>"+f.length()+"</td>"
                        +"</tr>";
            }
        }
        return dir;
    }
}
