package com.vmanage.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vmanage.ajax.service.VendorAjaxService;

@EnableWebMvc
@RestController
@RequestMapping("/")
public class VendorAjaxRestController {

	@Autowired
	@Qualifier(value = "ajaxService")
	private VendorAjaxService ajaxService;

	public VendorAjaxRestController() {
		System.out.println("VendorAjaxRestController created.");
	}

	/* GST AJAX */
	@GetMapping(value = "/gstAjax/{gst}")
	public String gstAjax(@PathVariable String gst) {
		String byGstAjax = this.ajaxService.findByGstAjax(gst);
		System.out.println("gst ajax: " + gst);
		return byGstAjax;
	}

	/* MOBILE NUMBER AJAX */
	@GetMapping(value = "/mobileAjax/{mobile}")
	public String mobileAjax(@PathVariable Long mobile) {
		String byMobileAjax = this.ajaxService.findByMobileAjax(mobile);
		System.out.println("number ajax: " + mobile);
		return byMobileAjax;
	}

	/* EMAIL AJAX */
	@GetMapping(value = "/emailAjax/{email:.+}")
	public String emailAjax(@PathVariable String email) {
		String byEmail = this.ajaxService.findByEmail(email);
		System.out.println("ajax: " + email);
		return byEmail;
	}

	/* WEBSITE AJAX */
	@GetMapping(value = "/siteAjax/{website:.+}")
	public String websiteAjax(@PathVariable String website) {
		String byWebsiteAjax = this.ajaxService.findByWebsiteAjax(website);
		System.out.println("website ajax: " + website);
		return byWebsiteAjax;
	}

	/* EMAIL AJAX FOR LOGIN */
	@GetMapping(value = "/mailLogInAjax/{email:.+}")
	public String emailAjaxForLogIn(@PathVariable String email) {
		String emailLogInAjax = this.ajaxService.emailLogInAjax(email);
		System.out.println("mail login ajax: " + email);
		return emailLogInAjax;
	}

}
