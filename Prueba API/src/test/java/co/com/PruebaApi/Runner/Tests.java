package co.com.PruebaApi.Runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

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

    @Test
    public void getResponse(){
        Actor JuanPa = Actor.named("Juan")
                .whoCan(CallAnApi.at(environmentVariables.getProperty("UrlBase")));

        JuanPa.attemptsTo(
                Post.to("/university").with(request -> request.header("Content-Type", "application/json")
                        .body("[{\"name\": \"Juan\",\"LastName\": \"pitt\",\"university\": true}]")
                )
        );
        JuanPa.should(
                seeThatResponse("Validate the Name of studenst",
                        response -> response.statusCode(200)
                                .body("carrera", hasItems("medicina","medecina","ingenieria"))
                                .body("Sementre", hasItems("8","2","9"))
                )

        );

    }

    @Test
    public void getNumber(){

            Actor JuanPa = Actor.named("Juan")
                    .whoCan(CallAnApi.at(environmentVariables.getProperty("UrlBase")));

            JuanPa.attemptsTo(
                    Post.to("/university").with(request -> request.header("Content-Type", "application/json")
                            .body("[{\"name\": \"Juan\",\"LastName\": \"pitt\",\"university\": true}]")
                    )
            );

        List<String> names = SerenityRest.lastResponse().path("Carrera");
        assertThat(names).contains("Medecina","Medicina");

        List<Map<String,String>> users = SerenityRest.lastResponse()
                .jsonPath()
                .get("data");

        assertThat(users).hasSize(2);
    }

    @Test
    public void Validateheader(){
        Actor JuanPa = Actor.named("Juan")
                .whoCan(CallAnApi.at(environmentVariables.getProperty("UrlBase")));

        JuanPa.attemptsTo(
                Post.to("/university").with(request -> request.header("Content-Type", "application/json")
                        .body("[{\"name\": \"Juan\",\"LastName\": \"pitt\",\"university\": true}]")
                )
        );
        JuanPa.should(
                seeThatResponse("Validate Header",
                        response -> response.statusCode(200)
                                .header("X-TotalCount","3")
                )

        );
    }


    }



