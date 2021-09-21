package org.springframework.samples.petclinic.simulator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulate")
public class LoadGenerateController {

	private final LoadRepository loadRepository;

	public static final int MAX_SEQ = 10000;

	public LoadGenerateController(final LoadRepository repository) {
		this.loadRepository = repository;
	}

	public void generateLoad(@PathVariable int seq) {
		loadRepository.generateLoad(seq > MAX_SEQ ? MAX_SEQ : seq);
	}

}
