package RhemaApp.Rhema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class RhemaApplication {

	public static void main(String[] args) {

		System.out.println("connection success??");
		SpringApplication.run(RhemaApplication.class, args);
	}

	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Health Check OK.");
	}
}
