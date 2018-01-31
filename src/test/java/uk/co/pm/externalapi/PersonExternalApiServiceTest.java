package uk.co.pm.externalapi;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import uk.co.pm.FileTestUtils;
import uk.co.pm.SocketTestUtils;
import uk.co.pm.model.Person;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonExternalApiServiceTest {

    private static final int PORT = SocketTestUtils.findAvailablePort();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    private PersonExternalApiService personExternalApiService;


    @Before
    public void setUp() throws Exception {
        personExternalApiService = new PersonExternalApiService("http://localhost:" + PORT);
    }

    @Test
    public void getPerson() throws Exception{
        String body = FileTestUtils.readClasspathFile("/json/person.json");

        stubFor(get(urlEqualTo("/person"))
                        .willReturn(okJson(body)));

        Person person = personExternalApiService.getPerson();

        assertThat(person.getAge()).isEqualTo(35);
        assertThat(person.getName()).isEqualTo("Susan");
    }

}