package pipelineServer;

public interface CacheManager {

	public String getSolution(String gameString);
	public void setSolution(String gameString, String solution);
}
