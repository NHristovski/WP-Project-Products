package hristovski.nikola.product.service;

public interface FileSystemService {

    String saveFile(byte[] bytes, String productTitle);

    byte[] getFileBytes(String fullPath);
}
