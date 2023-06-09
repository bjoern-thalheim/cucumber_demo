package de.adesso.thalheim.gtd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CaptureStepDefinitions {

    @Value(value = "${local.server.port}")
    private int port;

    @When("Thought {string} is collected")
    public void thoughtIsCollected(String thought) throws IOException {
        // given
        HttpPost post = new HttpPost("http://localhost:%d/gtd/inbox".formatted(port));
        post.setEntity(new StringEntity(thought));
        // when
        HttpResponse postResponse = HttpClientBuilder.create().build().execute(post);
        // then
        Assertions.assertThat(postResponse.getStatusLine().getStatusCode()).isEqualTo(200);
    }

    @Then("Inbox contains {string}")
    public void inboxContains(String thought) throws IOException {
        // given
        HttpUriRequest get = new HttpGet("http://localhost:%d/gtd/inbox".formatted(port));
        // when
        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(get);
        // then
        String entity = EntityUtils.toString(response.getEntity());
        assertThat(StringUtils.strip(entity)).isEqualTo("[{\"description\":\"%s\"}]".formatted(thought));
    }
}
