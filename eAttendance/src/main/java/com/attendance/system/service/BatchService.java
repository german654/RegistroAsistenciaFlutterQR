package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Batch;

public interface BatchService {

	public ResponseEntity<List<Batch>> getAllBatches();
	public ResponseEntity<String> addBatch(@NonNull Batch batch);
	public ResponseEntity<String> updateBatch(@NonNull Integer bid, String batchName);
	public ResponseEntity<Integer> deleteBatch(@NonNull Integer bid);
	public ResponseEntity<Batch> getBatch(@NonNull Integer bid);
}
