package co.com.PruebaApi.Runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

@RunWith(SerenityRunner.class)
public class Tests {
    private EnvironmentVariables environmentVariables;
    @Test
    public void getInto(){
        Actor JuanPa = Actor.named("Juan")
                .whoCan(CallAnApi.at(environmentVariables.getProperty("UrlBase")));

        JuanPa.attemptsTo(
                Post.to("/university").with(request -> request.header("Content-Type", "application/json")
                        .body("[{\"name\": \"Juan\",\"LastName\": \"pitt\",\"university\": true}]")
                )
        );
        JuanPa.should(
                seeThatResponse("The request is valid",
                        response -> response.statusCode(200))
        );


    }

}
