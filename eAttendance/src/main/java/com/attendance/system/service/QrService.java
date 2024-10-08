package com.attendance.system.service;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.QrRequest;

public interface QrService {
	public String createQrCode(@NonNull String app,QrRequest qrRequest);
	
	public ResponseEntity<String> createQRCode(String qrCodeData);
}