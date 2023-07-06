/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1960.framework.fileUpload;

/**
 *
 * @author Chalman
 */
public class FileUpload {
    String name;
    String path;
    byte[] bytes;
    
///encapsulation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
///Constructors
    public FileUpload(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }
    
}
