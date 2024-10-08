package com.attendance.system.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.CustomConfig;
import com.attendance.system.model.QrRequest;
import com.attendance.system.model.SiteUser;
import com.attendance.system.service.QrService;
import com.attendance.system.service.SessionService;
import com.attendance.system.service.UserService;
import com.attendance.system.service.UtilityService;

import ch.qos.logback.core.util.Duration;

@Controller
public class QrController {
	
	private static final Logger logger = LoggerFactory.getLogger(QrController.class);

	@Autowired
	private QrService qrService;

	@Autowired
	private UserService userService;

	@Autowired
	private UtilityService utility;
	
	@Autowired
	private SessionService sessionService;

	@PostMapping("/attendanceQR")
	public ModelAndView qr(@RequestParam("course") String course, @RequestParam("subject") String subject,
			@RequestParam("semester") String sem, @RequestParam("division") String division,
			@RequestParam("duration") String duration) {
		ModelAndView mv = new ModelAndView("qr-page");

		SiteUser user = userService.getUser(utility.getCurrentUsername()).getBody();

		if (user == null) {
			return new ModelAndView("login");
		}

		LocalTime currentTime = LocalTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		String formattedTime = currentTime.format(formatter);

		QrRequest request = QrRequest.builder().course(course).dividion(division).subject(subject).sem(sem)
				.duration(duration).createdAt(formattedTime).faculty(user.getUserId().toString()).build();

		String qrString = qrService.createQrCode(CustomConfig.ACCESS_POINT, request);

		mv.addObject("qr", qrService.createQRCode(qrString).getBody());
		mv.addObject("duration", duration);
		
		sessionService.createSession(course, subject, sem, division, user.getUserId().toString());
		
		
	    CompletableFuture.runAsync(() -> {
	        try {
	            // Delay for the specified duration
	            Thread.sleep(Duration.buildByMinutes(Double.parseDouble(duration)).getMilliseconds());

	            // Call the stopSession function
	            sessionService.stopSession(course, subject, sem, division, user.getUserId().toString());
	            
	            logger.info("Session Stopped");
	            
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            e.printStackTrace();
	        }
	    });
		
		return mv;
	}

}
