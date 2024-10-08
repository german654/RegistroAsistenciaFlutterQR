package com.attendance.system.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.attendance.system.model.QrRequest;
import com.attendance.system.service.QrService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


@Service
public class QrServiceImpl implements QrService {

    private static final Logger logger = LoggerFactory.getLogger(QrServiceImpl.class);

    private final int size = 450;

    public String createQrCode(@NonNull String app,QrRequest qrRequest) {
    	
        // Add the signature to the UPI deep link
        String qrCode = UriComponentsBuilder.fromUriString(app)
                .queryParam("course", qrRequest.getCourse())
                .queryParam("subject", qrRequest.getSubject())
                .queryParam("sem",qrRequest.getSem())
                .queryParam("division",qrRequest.getDividion())
                .queryParam("fid", qrRequest.getFaculty())
                .queryParam("duration", qrRequest.getDuration())
                .queryParam("createdAt", qrRequest.getCreatedAt())
                .build()
                .encode()
                .toUriString();

        logger.info("Generated UPI QR Code: {}", qrCode);
        return qrCode;
    }
    
    public ResponseEntity<String> createQRCode(String qrCodeData) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, size, size);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "png", baos);
            String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

            logger.info("Generated QR Code Image");
            return ResponseEntity.ok(base64Image);
        } catch (IOException | WriterException e) {
            logger.error("Error creating QR Code", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}