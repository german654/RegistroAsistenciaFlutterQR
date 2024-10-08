package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.model.Batch;
import com.attendance.system.service.BatchService;

@RestController
@RequestMapping("batch")
public class BatchController {

	
	@Autowired
	private BatchService batchService;

	@RequestMapping
	public ModelAndView batch() {
		return new ModelAndView("batch").addObject("batchList", batchService.getAllBatches().getBody());
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addBatch(@NonNull Batch batch){
		return batchService.addBatch(batch);
	}
	
	@GetMapping("get")
	public ResponseEntity<List<Batch>> getAllBatches(){
		return batchService.getAllBatches();
	}
	
	@GetMapping("get/{bid}")
	public ResponseEntity<Batch> getBatch(@PathVariable @NonNull Integer bid){
		return batchService.getBatch(bid);
	}
	
	@PutMapping("update/{bid}")
	public ResponseEntity<String> updateBatch(@PathVariable @NonNull Integer bid,@RequestParam("updBatch") String batchName){
		return batchService.updateBatch(bid,batchName);
	}
	
	@DeleteMapping("delete/{bid}")
	public ResponseEntity<Integer> deleteBatch(@PathVariable @NonNull Integer bid){
		return batchService.deleteBatch(bid);
	}

}
