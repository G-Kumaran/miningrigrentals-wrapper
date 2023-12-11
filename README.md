# MiningRigRentals v2 API Wrapper for Java Spring Boot
 - This wrapper was built to simplify usage of MRR API in Spring Boot Projects. 
 - It wraps [MRR API calls](https://www.miningrigrentals.com/apidocv2) in a custom Retrofit interface. 
 - API response models can be found in package `me.gkumaran.miningrigrentals.domain`
 - API calls configuration can be found in the Retrofit interface `me.gkumaran.miningrigrentals.ApiService`
 - Based on the success of reponse, either the unwrapped data or error is returned
```json
{
	"success": true,
	"data": {
	
	}
}
```


### Prerequisites
  * Java 17+
  * Spring Boot 3.2+
  * MiningRigRentals API Keys
  
### Generating API keys
  * Visit [MRR Keys page](https://www.miningrigrentals.com/account/apikey)
  * Create keys with "Add A Key"
 
### Setting up the wrapper
  * Download the latest release or Clone and build the package
  * Add the maven dependency to your project's `pom.xml`
```xml
<dependency>
	<groupId>me.gkumaran.wrapper</groupId>
	<artifactId>miningrigrentals</artifactId>
	<version>${miningrigrentals.version}</version>
</dependency>
```
  * Add to spring properties
```bash
mrr.key=
mrr.secret=
```
 * Inject the auto configured bean
```java
@Autowired
private me.gkumaran.miningrigrentals.ApiClient miningRigRentals;
```
 * Usage - Call methods from the class `me.gkumaran.miningrigrentals.ApiClient`
 * Sample - Test calls can be found in the `src/test/java/me/gkumaran/miningrigrentals/domain`

### Build
Alternatively, you can build the dependency yourself
```bash
mvn clean install
```

### Issues and Suggestions
 * Feel free to open a PR or issue
 * You can reach me on Discord @ GKumaran#9439

### Donations
To support this project, you can make a donation to its current maintainer: 
 - Bitcoin  : 35s4GNcVYYy4dqcggLumKCZeE5R7VgaFiB 
 - Ethereum : 0x7b6b6Cc1846F47B85e65BB655A7c19D3e4914877
 - Litecoin : MAHhgsvPBVbEFD5SNaJcbaGdLdZgoasJWx