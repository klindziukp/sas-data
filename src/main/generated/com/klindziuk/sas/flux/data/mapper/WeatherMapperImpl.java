package com.klindziuk.sas.flux.data.mapper;

import com.klindziuk.sas.flux.data.model.request.StreamSetsWeatherRequest;
import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import com.klindziuk.sas.flux.data.model.response.openweather.Sys;
import com.klindziuk.sas.flux.data.model.response.openweather.Wind;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-07T18:55:35+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 12.0.2 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public StreamSetsWeatherRequest toWeatherRequest(OpenWeatherResponse openWeatherResponse) {
        if ( openWeatherResponse == null ) {
            return null;
        }

        StreamSetsWeatherRequest streamSetsWeatherRequest = new StreamSetsWeatherRequest();

        streamSetsWeatherRequest.setWindSpeed( openWeatherResponseWindSpeed( openWeatherResponse ) );
        streamSetsWeatherRequest.setCity( openWeatherResponse.getName() );
        streamSetsWeatherRequest.setCountry( openWeatherResponseSysCountry( openWeatherResponse ) );

        streamSetsWeatherRequest.setWeather( openWeatherResponse.getWeather().get(0).getMain() );
        streamSetsWeatherRequest.setDescription( openWeatherResponse.getWeather().get(0).getDescription() );
        streamSetsWeatherRequest.setTemperature( openWeatherResponse.getMain().getTemp() - 273.15F );
        streamSetsWeatherRequest.setFeelsLike( openWeatherResponse.getMain().getFeelsLike() - 273.15F );

        return streamSetsWeatherRequest;
    }

    private Float openWeatherResponseWindSpeed(OpenWeatherResponse openWeatherResponse) {
        if ( openWeatherResponse == null ) {
            return null;
        }
        Wind wind = openWeatherResponse.getWind();
        if ( wind == null ) {
            return null;
        }
        Float speed = wind.getSpeed();
        if ( speed == null ) {
            return null;
        }
        return speed;
    }

    private String openWeatherResponseSysCountry(OpenWeatherResponse openWeatherResponse) {
        if ( openWeatherResponse == null ) {
            return null;
        }
        Sys sys = openWeatherResponse.getSys();
        if ( sys == null ) {
            return null;
        }
        String country = sys.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }
}
