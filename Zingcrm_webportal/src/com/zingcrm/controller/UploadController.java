package com.zingcrm.controller;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.exception.BusinessException;


@Controller
@RequestMapping(value = "/upload")
public class UploadController extends HttpServlet {


	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static String realPath;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView approvesMember() {

		return new ModelAndView("/upload", "command", "");
	}

	
	@RequestMapping(value = "/saveExcel", method = RequestMethod.POST)
	public void saveTrack(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException,
			ParseException, IOException {
		realPath = request.getSession().getServletContext().getRealPath("/");
	        PrintWriter writer = null;
	        InputStream is = null;
	        FileOutputStream fos = null;

		        try {
		            writer = response.getWriter();
		        } catch (IOException ex) {
		            log(UploadController.class.getName() + "has thrown an exception: " + ex.getMessage());
		        }

		        String filename =StringEscapeUtils.unescapeHtml(request.getHeader("X-File-Name")) ;
		        is = request.getInputStream();
		        try {
		         // to get the content type information from JSP Request Header
	                String contentType = request.getContentType();

	                // here we are checking the content type is not equal to Null
	                // and
	                // as well as the passed data from mulitpart/form-data is
	                // greater than or equal to 0
	                if ((contentType != null)
	                        && (contentType.indexOf("multipart/form-data") >= 0)) // If
	                                                                                // browser
	                                                                                // is
	                                                                                // IE
	                {
	                    DataInputStream in = new DataInputStream(is);

	                    // we are taking the length of Content type data
	                    int formDataLength = request.getContentLength();
	                    byte dataBytes[] = new byte[formDataLength + formDataLength];
	                    int byteRead = 0, totalBytesRead = 0;

	                    // this loop converting the uploaded file into byte code
	                    while (totalBytesRead < formDataLength) {
	                        byteRead = in.read(dataBytes, totalBytesRead,
	                                formDataLength);
	                        totalBytesRead += byteRead;
	                    }

	                    // for saving the file name
	                    String file = new String(dataBytes);
	                    String saveFile = file.substring(file.indexOf("filename=\"") + 10);
	                    saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
	                    saveFile = saveFile.substring(
	                            saveFile.lastIndexOf("\\") + 1,
	                            saveFile.indexOf("\""));
	                    int pos, lastIndex = contentType.lastIndexOf("=");
	                    String boundary = contentType.substring(lastIndex + 1,
	                            contentType.length());

	                    // extracting the index of file
	                    pos = file.indexOf("filename=\"");
	                    pos = file.indexOf("\n", pos) + 1;
	                    pos = file.indexOf("\n", pos) + 1;
	                    pos = file.indexOf("\n", pos) + 1;
	                    int boundaryLocation = file.indexOf(boundary, pos) - 4;
	                    int startPos = ((file.substring(0, pos)).getBytes()).length;
	                    int endPos = ((file.substring(0, boundaryLocation))
	                            .getBytes()).length;
	                    filename=saveFile;
	                    // upload using the given path and file name and then scale
	                    // to 300x200
	                    FileOutputStream fileOut = new FileOutputStream(realPath
	                            + filename.replaceAll(" ","-").replaceAll("%20", "-"));
	                    fileOut.write(dataBytes, startPos, (endPos - startPos));
	                    fileOut.flush();
	                    fileOut.close();
	                    in.close();
	                  
	                } else {
	                    filename=filename.replaceAll(" ","-").replaceAll("%20", "-");
    		            fos = new FileOutputStream(new File(realPath +filename.replaceAll(" ","-")));
    		            IOUtils.copy(is, fos);
    		            writer.print("{success: true}");
	                }
		        } catch (FileNotFoundException ex) {
		            writer.print("{success: false}");
		            log(UploadController.class.getName() + "has thrown an exception: " + ex.getMessage());
		        } catch (IOException ex) {
		            writer.print("{success: false}");
		            log(UploadController.class.getName() + "has thrown an exception: " + ex.getMessage());
		        } finally {
		            try {
		                fos.close();
		                is.close();
		            } catch (IOException ignored) {
		            }
		        }
		        

		        writer.flush();
		        writer.close();
		    }
	}
		