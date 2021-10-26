package com.example.springproj2;

import java.io.IOException;
import java.lang.*;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Springproj2ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private HomeController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	public Springproj2ApplicationTests() {
	}

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void check_list() throws Exception{
		List<JSONObject> json = this.restTemplate.getForObject("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", List.class);
		boolean isList = false;
		if (json instanceof List){
			isList = true;
		}
		assertEquals(isList, true);

//		ResponseEntity<String> response = restTemplate.getForEntity("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", String.class);
//		JSONArray jsonArray = new JSONArray(response.getBody());
//		boolean isList = false;
//		if (jsonArray instanceof List){
//			isList = true;
//		}
//		assertEquals(isList, true);



	}

	@Test
	public void check_unique_id_name() throws Exception{
		String json = this.restTemplate.getForObject("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", String.class);
		JSONArray jsonArray = new JSONArray(json);

		List names = IntStream.range(0, jsonArray.length())
				.mapToObj(index -> {
					try {
						return ((JSONObject)jsonArray.get(index)).optString("name");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());

		List ids = IntStream.range(0, jsonArray.length())
				.mapToObj(index -> {
					try {
						return ((JSONObject)jsonArray.get(index)).optString("id");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());

		//System.out.println(names);
		//System.out.println(ids);

		Set<String> names_u =
				new HashSet<String>(names);

		Set<String> ids_u =
				new HashSet<String>(ids);

		boolean unique_names = names_u.size() == names.size();
		boolean unique_ids = ids_u.size() == ids.size();

		boolean pass_test = false;

		if (unique_ids == true && unique_names == true){
			pass_test = true;
		}
		assertEquals(pass_test, true);
	}

	@Test
	public void check_ssl_cert() throws IOException {
		new URL ("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos") .openConnection() .connect();
	}

	@Test
	public void check_ssl() throws NoSuchAlgorithmException, IOException, CertificateException {
		URL url = new URL("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos");
		//System.out.println("URL Class Name: "+url.getClass().getName());

		URLConnection con = url.openConnection();
		//System.out.println("Connection Class Name: "+con.getClass().getName());

		HttpsURLConnection scon = (HttpsURLConnection) con;
		scon.connect();
		Certificate[] certs = scon.getServerCertificates();
		//System.out.println("Server Certificate: "+certs[0].toString());
		String cert_from_url = certs[0].toString();

		CertificateFactory fac = CertificateFactory.getInstance("X509");
		FileInputStream is = new FileInputStream("urlcert.cer");
		X509Certificate cert = (X509Certificate) fac.generateCertificate(is);
		//System.out.println("Server Certificate: "+cert.toString());
		String cert_from_file = cert.toString();

		boolean valid_cert = cert_from_url.equals(cert_from_file);
		assertEquals(valid_cert, true);
	}
}

