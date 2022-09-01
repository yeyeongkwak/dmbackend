package com.spring.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProfileController {
	
	private final Environment env;
	
	@GetMapping("/profile")
	public String profile() {
		List<String> profiles = Arrays.asList(env.getActiveProfiles());
		List<String> realProfiles = Arrays.asList("real","real1","real2");
		String defaultProfiles = profiles.isEmpty()? "default" : profiles.get(0);
		System.out.println(profiles);
		System.out.println(realProfiles);
		System.out.println(defaultProfiles);
		return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfiles);
	}
}
