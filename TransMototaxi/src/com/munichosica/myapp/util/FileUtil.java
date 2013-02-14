package com.munichosica.myapp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class FileUtil {
	public static String createTempFile(HttpServletRequest request,String name,byte[] archivo){
		FileOutputStream fop=null;
		File file=null;
		File folder=null;
		try {
			name=String.valueOf(new Double(Math.random()*10000).intValue())+name;
			folder=new File(request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+"temp");
			if(!folder.exists()){
				boolean results=folder.mkdir();
				if(results){
					System.out.println("DIR Created");
				}
			}
			
			file=new File(request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+"temp/"+name);
			//String absolutePath=file.getAbsolutePath();
			//System.out.println("File path : " + absolutePath);
			//String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
			//System.out.println("File path : " + filePath);
			//asociado.getFoto().setAdjnombreV(asociado.getFoto().getAdjnombreV());
			fop=new FileOutputStream(file);
			if(!file.exists()){
				file.createNewFile();
			}
			fop.write(archivo);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	public static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    public static byte[] compress(byte[] content){
    	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
        	GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        	gzipOutputStream.write(content);
        	gzipOutputStream.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        //System.out.printf("Compression ratio %f\n", (1.0f * content.length/byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }
    
    public static byte[] deCompress(byte[] input) throws UnsupportedEncodingException, IOException, DataFormatException
    {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
        	IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(input)), out);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }
    
    public static String getExtension(String fileName){
    	int mid= fileName.lastIndexOf(".");
        return fileName.substring(mid,fileName.length());//+".muni";
    }
}
