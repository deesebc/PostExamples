package com.home.sample.mailsender.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;

public class InputStreamDataSource implements DataSource {

    byte[] bytes = null;
    private final String name;
    private final String contentType;

    public InputStreamDataSource(final InputStream inputStream, final String name, final String contentType) {
        try {
            bytes = IOUtils.toByteArray(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.name = name;
        this.contentType = contentType;

    }

    @Override
    public String getContentType() {
        return new MimetypesFileTypeMap().getContentType(contentType);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("Read-only data");
    }

}