package ru.fls.privateoffice.util.service.impl;

import liquibase.util.file.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 28.04.12
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */

public class UploadFileService {
    private static final Logger log = Logger.getLogger(UploadFileService.class);

    @Value("${file.storage.path}")
    private String path;

    @Value("${file.storage.maxsize}")
    private Long maxSize;

    @Value("${file.storage.pdf.maxsize}")
    private Long pdfMaxSize;

    @Value("${file.storage.css}")
    private String cssFileName;

    public String saveFile(DefaultMultipartHttpServletRequest request, String fieldName) throws IOException {
        return saveFile(request.getFile(fieldName));
    }

    public String saveFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename().contains(".pdf") && file.getSize() > pdfMaxSize * 1024 ||
                !file.getOriginalFilename().contains(".pdf") && file.getSize() > maxSize * 1024
                ) {
            log.error("File " + file.getOriginalFilename() + " can't be uploaded: max size =  " + maxSize * 1024 + "; pdf max size =  " + pdfMaxSize * 1024 + "; file size = " + file.getSize());
           throw new IOException();
        }
        if (file.getSize() > 0) {
           File pic = new File(path + file.getOriginalFilename());

            if (pic.exists()) {
                int i = 0;
                String baseName = FilenameUtils.getBaseName(file.getOriginalFilename());
                String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                String format = path + baseName + "(%d)." + ext;

                do {
                    i++;
                    pic = new File(String.format(format, i));
                } while (pic.exists());
            }

            try {
                file.transferTo(pic);
            } catch (IOException e) {
                log.error("File " + file.getOriginalFilename() + " can't be uploaded", e);
             return null;  // throw e;
            } catch (IllegalStateException e){
                log.error(e);
                return null;
            }
            log.info("File " + file.getOriginalFilename() + " was successfully uploaded");
            return pic.getName();
        } else {
            log.error("File " + file.getOriginalFilename() + " can't be uploaded: file size = 0");
            return null;
        }
    }

    public List<String> saveFiles(DefaultMultipartHttpServletRequest request, String fieldName) throws IOException {
        List<String> list = new ArrayList<String>();
        for (MultipartFile file : request.getFiles(fieldName + "[]")) {
            list.add(saveFile(file));
        }
        return list;
    }

    public boolean saveCssFile(String text) throws IOException {
        if (text != null) {
            StringBuilder filePath = new StringBuilder();
            filePath.append(path).append(cssFileName);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(filePath.toString()));
                writer.write(text);
            }
            catch (FileNotFoundException fnf) {
                log.error("File " + filePath.toString() + " not found", fnf) ;
                throw fnf;
            }
            catch (IOException e) {
                log.error("File " + filePath.toString() + " can't be saved", e);
               throw e;
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

            log.info("File " + filePath.toString() + " was successfully saved");
        }
        return true;
    }

    public String getCssFile() {
        int len;
        char[] chr = new char[4096];

        StringBuilder filePath = new StringBuilder();
        filePath.append(path).append(cssFileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath.toString()));
            StringBuffer buffer = new StringBuffer();
            while ((len = reader.read(chr)) > 0) {
                buffer.append(chr, 0, len);
            }
            return buffer.toString();

        } catch (IOException e) {
            log.error("File " + filePath.toString() + " can't be read", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }

        return null;
    }

    public List<String> getUploadedFileList() {
        File file = new File(path);
        List<String> files = new ArrayList<String>();

        if (!file.exists()) {
            log.error("Cannot access " + path + ": No such file or directory");
            return files;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                files.add(f.getName());
            }
        }
        return files;
    }

    public void removeFile(String filename){
        File file = new File(path);
        if (!file.exists()) {
            log.error("Cannot access " + path + ": No such file or directory");
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
              if(f.getName().equals(filename)){
                  f.delete();
                  break;
              }
            }
        }
    }




}
