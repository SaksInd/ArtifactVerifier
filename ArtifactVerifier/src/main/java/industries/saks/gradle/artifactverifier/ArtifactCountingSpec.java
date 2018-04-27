package industries.saks.gradle.artifactverifier;

public class ArtifactCountingSpec extends ArtifactSpec {

	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Object propertyMissing(String name) {
		return "Access property " + name;
	}

}
