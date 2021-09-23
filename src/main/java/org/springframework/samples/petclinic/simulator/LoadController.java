package org.springframework.samples.petclinic.simulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulate")
public class LoadController {

	private final Logger LOG = LoggerFactory.getLogger(LoadController.class);

	private final LoadRepository loadRepository;

	private final ExecutorService LOAD_EXECUTOR = Executors.newSingleThreadExecutor();

	private Future loadRun;

	public static final int MAX_SEQ = 500000;

	public static final int MAX_LOOP = 5;

	public LoadController(final LoadRepository repository) {
		this.loadRepository = repository;
	}

	@GetMapping("/{seq}/{loop}")
	public void generateLoadInLoop(@PathVariable int seq, @PathVariable int loop) {
		for (int i = 1; i <= (loop > MAX_LOOP ? MAX_LOOP : loop); i++) {
			loadRepository.generateLoad(seq > MAX_SEQ ? MAX_SEQ : seq);
		}
	}

	@GetMapping("/{seq}")
	public void generateLoad(@PathVariable int seq) {
		if (loadRun == null) {
			synchronized (this) {
				loadRun = LOAD_EXECUTOR.submit(() -> {
					int i = 1;
					while (true) {
						int count = loadRepository.generateLoad(seq > MAX_SEQ ? MAX_SEQ : seq);
						LOG.info("Loaded " + count + " records in iteration " + i++);
						Thread.sleep(5000);
					}
				});
			}
		}
	}

	@GetMapping("/abort")
	public void generateLoad() {
		if (loadRun != null) {
			synchronized (loadRun) {
				loadRun.cancel(true);
				LOG.info("Load job termination status: " + loadRun.isCancelled());
				if (loadRun.isCancelled()) {
					loadRun = null;
				}
			}
		}
	}

}
