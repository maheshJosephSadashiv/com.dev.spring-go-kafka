package example.microServices.org.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubService {

	@Autowired
	private GithubRepository githubRepository;

	public Github getDetails(String userName) throws IOException {
		HttpURLConnection httpcon = null;
		try {
			httpcon = (HttpURLConnection) new URL("https://api.github.com/users/" + userName).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		StringBuilder responseSB = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			responseSB.append("\n" + line);
		}
		in.close();
		Github json = new Gson().fromJson(responseSB.toString(), Github.class);
		githubRepository.save(json);

		return json;
	}

	public List<Github> getAllDetails() {
		List<Github> githubList = new ArrayList<Github>();
		githubRepository.findAll().forEach(githubList::add);
		return githubList;
	}

	public Github getSpecificDetails(long id) {
		return githubRepository.findOne(id);
	}

	public void deleteSpecificDetails(long id) {
		githubRepository.delete(id);

	}

}
