package co.com.PruebaApi.Runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

@RunWith(SerenityRunner.class)
public class Example {

    private static final String restApiUrl = "http://university.net.api";

    @Test
    public void getIntoActiveFalse(){
        Actor JuanPa = Actor.named("Juan Navas")
                .whoCan(CallAnApi.at(restApiUrl));

        JuanPa.attemptsTo(
                Post.to("/university").with(request -> request.header("Content-Type", "application/json")
                        .body("[{\"name\": \"Juan\",\"LastName\": \"pitt\",\"university\": true}]")
                )
        );
        JuanPa.should(
                seeThatResponse("The field Active is false",
                        response -> response.statusCode(200))
        );


    }

}
