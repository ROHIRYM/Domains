package com.rohirym.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.rohirym.dto.DomainAndStatusDTO;
import com.rohirym.entity.Domain;
import com.rohirym.service.DomainStatusService;

@Service
public class DomainStatusServiceImpl implements DomainStatusService {

	@Override
	public List<DomainAndStatusDTO> lookup(List<Domain> domainList) {
		HttpsURLConnection con = makeHttpsURLConnection2GoogleSafebrowsing();
		try {
			con.setRequestMethod("POST");
			return checkAllDomains(con, domainList);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		return new ArrayList<DomainAndStatusDTO>();
	}

	private List<DomainAndStatusDTO> checkAllDomains(HttpsURLConnection con, List<Domain> domainList) {
		List<DomainAndStatusDTO> result = new ArrayList<>();
		int size = domainList.size();
		List<Domain> domainListPart = new ArrayList<>();
		int j = 0;
		for (int i = 0; i < size; i++) {
			domainListPart.add(domainList.get(i));
			j++;
			if (j == 500 || i == size - 1) {
				j = 0;
				result.addAll(check500Domains(con, domainListPart));
			}
		}
		return result;
	}

	private List<DomainAndStatusDTO> check500Domains(HttpsURLConnection con, List<Domain> domainListPart) {
		try {
			sendPostRequest2GoogleSafebrowsing(con, domainListPart);
			return fillDomainAndStatusList(con, domainListPart);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private void sendPostRequest2GoogleSafebrowsing(HttpsURLConnection con, List<Domain> domainListPart)
			throws IOException {
		StringBuffer urlParameters = new StringBuffer();
		urlParameters.append(domainListPart.size());
		urlParameters.append(System.getProperty("line.separator"));
		for (Domain domain : domainListPart) {
			urlParameters.append(domain.getDomainName());
			urlParameters.append(System.getProperty("line.separator"));
		}
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters.toString());
		wr.flush();
		wr.close();
	}

	private List<DomainAndStatusDTO> fillDomainAndStatusList(HttpsURLConnection con, List<Domain> domainListPart)
			throws IOException {
		List<DomainAndStatusDTO> result = new ArrayList<>();
		int responseCode = con.getResponseCode();
		if (responseCode == 204) {
			for (Domain domain : domainListPart) {
				result.add(new DomainAndStatusDTO(domain, "ok"));
			}
		} else if (responseCode == 200) {
			result = readResponseBodyFromGoogleSafebrowsing(con, domainListPart);
		} else {
			for (Domain domain : domainListPart) {
				result.add(new DomainAndStatusDTO(domain));
			}
		}
		return result;
	}

	private List<DomainAndStatusDTO> readResponseBodyFromGoogleSafebrowsing(HttpsURLConnection con,
			List<Domain> domainListPart) throws IOException {
		List<DomainAndStatusDTO> result = new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		int i = 0;
		while ((inputLine = in.readLine()) != null) {
			result.add(new DomainAndStatusDTO(domainListPart.get(i), inputLine));
			i++;
		}
		in.close();
		return result;
	}

	private HttpsURLConnection makeHttpsURLConnection2GoogleSafebrowsing() {
		String baseURL = "https://sb-ssl.google.com/safebrowsing/api/lookup";
		List<ArgumentPair> argumentPairs = new ArrayList<>();
		argumentPairs.add(new ArgumentPair("client", "api"));
		argumentPairs.add(new ArgumentPair("key", "AIzaSyDiXHIx_QFKr79OoG8y9UbQBEhyTFK8VAU"));
		argumentPairs.add(new ArgumentPair("appver", "1.5.2"));
		argumentPairs.add(new ArgumentPair("pver", "3.0"));
		return makeHttpsURLConnection(baseURL, argumentPairs);
	}

	private HttpsURLConnection makeHttpsURLConnection(String baseURL, List<ArgumentPair> argumentPairs) {
		try {
			URL url = makeUrl(baseURL, argumentPairs);
			return (HttpsURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private URL makeUrl(String baseURL, List<ArgumentPair> argumentPairs) throws MalformedURLException {
		String arguments = "";
		try {
			arguments = combineArguments(argumentPairs);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new URL(baseURL + "?" + arguments);
	}

	private String combineArguments(List<ArgumentPair> argumentPairs) throws UnsupportedEncodingException {
		StringBuffer arguments = new StringBuffer();
		for (ArgumentPair argumentPair : argumentPairs) {
			arguments.append(encodeArgumentWithUTF8(argumentPair.getVarName(), argumentPair.varValue));
			arguments.append('&');
		}
		return arguments.toString();
	}

	private String encodeArgumentWithUTF8(String varName, String varValue) throws UnsupportedEncodingException {
		return encodeArgument(varName, varValue, "UTF-8");
	}

	private String encodeArgument(String varName, String varValue, String encoding)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(varName, encoding) + "=" + URLEncoder.encode(varValue, encoding);
	}

	class ArgumentPair {

		private String varName;

		private String varValue;

		ArgumentPair(String varName, String varValue) {
			this.varName = varName;
			this.varValue = varValue;
		}

		String getVarName() {
			return varName;
		}

		String getVarValue() {
			return varValue;
		}
	}

}
