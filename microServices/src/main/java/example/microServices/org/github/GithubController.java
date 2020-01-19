package example.microServices.org.github;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {
	
	@Autowired
	private GithubService githubService;
	
	@RequestMapping(value = "/users/{name}",method = RequestMethod.GET)
	public Github getGitDetails(@PathVariable String name) throws IOException {
		return githubService.getDetails(name);
	}
	
	@RequestMapping(value = "/users/data",method = RequestMethod.GET)
	List<Github> getAllGitDetails(){
		return githubService.getAllDetails();
	}
	
	@RequestMapping(value = "/users/data/{id}",method = RequestMethod.GET)
	Github getSpecificGitDetails(@PathVariable long id){
		return githubService.getSpecificDetails(id);
	}
	
	@RequestMapping(value = "/users/data/{id}",method = RequestMethod.DELETE)
	void deleteSpecificGitDetails(@PathVariable long id){
		 githubService.deleteSpecificDetails(id);
	}
	
	
}
