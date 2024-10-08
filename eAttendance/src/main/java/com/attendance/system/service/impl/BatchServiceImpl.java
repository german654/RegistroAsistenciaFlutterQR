package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.BatchDao;
import com.attendance.system.model.Batch;
import com.attendance.system.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {
	@Autowired
	private BatchDao batchDao;

	@Override
	public ResponseEntity<List<Batch>> getAllBatches() {
		try {
			return new ResponseEntity<List<Batch>>(batchDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<List<Batch>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> addBatch(@NonNull Batch batch) {
		try {
			batchDao.save(batch);
			return new ResponseEntity<String>("<p class='text-success'>Batch added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<String> updateBatch(@NonNull Integer bid, String batchName) {
		try {
			Batch batch = new Batch(bid, batchName);
			Batch dbBatch = batchDao.findById(bid).get();

			if (Objects.nonNull(batch.getBatchName()) && !"".equalsIgnoreCase(batch.getBatchName())) {
				dbBatch.setBatchName(batch.getBatchName());
			}
			batchDao.save(dbBatch);
			return new ResponseEntity<String>("<p class='text-success'>Batch Updated SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Integer> deleteBatch(@NonNull Integer bid) {
		try {
			batchDao.deleteById(bid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Batch> getBatch(@NonNull Integer bid) {
		try {
			return new ResponseEntity<Batch>(batchDao.findById(bid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
