package com.othex.reserveme.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.othex.reserveme.exceptions.TechnicalException;

@Service
public class FileStorageService {

    @Value("${image.upload.directory}")
    private String imageUploadDirectory;

    public String storeFile(MultipartFile file) {
        var fileName = generateHash(file);
        final var extension = getFileExtension(file);

        try {
            File targetFile = new File(imageUploadDirectory + fileName + "." + extension);
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName + "." + extension;
    }

    public String getFileExtension(MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                int lastDotIndex = originalFilename.lastIndexOf(".");
                if (lastDotIndex >= 0 && lastDotIndex < originalFilename.length() - 1) {
                    return originalFilename.substring(lastDotIndex + 1);
                }
            }
        }
        return null;
    }

    private String generateHash(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            byte[] hash = digest.digest();

            // Convert hash bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new TechnicalException("unable to generate hash for the file profided !");
        }
    }
}
