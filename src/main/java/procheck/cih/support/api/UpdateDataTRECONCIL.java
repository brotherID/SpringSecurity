package procheck.cih.support.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UpdateDataTRECONCIL {
	
	@GetMapping("get/data")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getData()  {
		
		log.info("begin getData in UpdateDataTRECONCIL");

		log.info("end getData in UpdateDataTRECONCIL");
		return "ok";
	}
	
	
	@GetMapping("get/amount")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getAmount()  {
		
		log.info("begin getAmount in UpdateDataTRECONCIL");

		log.info("end getAmount in UpdateDataTRECONCIL");
		return "ok";
	}
}



