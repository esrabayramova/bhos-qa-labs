package sprinproj1;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Springproj1ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private HomeController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	public Springproj1ApplicationTests() {
	}

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void link_1() throws Exception{
		List<JSONObject> json = this.restTemplate.getForObject("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", List.class);
		assertThat(json).isNotEmpty();
		ResponseEntity<String> response = restTemplate.getForEntity("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", String.class);
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}

	@Test
	public void link_2() throws Exception{
		List<JSONObject> json = this.restTemplate.getForObject("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches", List.class);
		assertThat(json).isNotEmpty();
		ResponseEntity<String> response = restTemplate.getForEntity("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches", String.class);
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}


	@Test
	public void link_3() throws Exception{
		List<JSONObject> json = this.restTemplate.getForObject("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits", List.class);
		assertThat(json).isNotEmpty();
		ResponseEntity<String> response = restTemplate.getForEntity("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits", String.class);
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}

}
