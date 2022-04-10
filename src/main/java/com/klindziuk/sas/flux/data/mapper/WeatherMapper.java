package com.klindziuk.sas.flux.data.mapper;

import com.klindziuk.sas.flux.data.model.request.StreamSetsWeatherRequest;
import com.klindziuk.sas.flux.data.model.response.openweather.OpenWeatherResponse;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface WeatherMapper {

  WeatherMapper MAPPER = Mappers.getMapper(WeatherMapper.class);

  @Mapping(target = "weather", expression = "java(openWeatherResponse.getWeather().get(0).getMain())")
  @Mapping(target = "description", expression = "java(openWeatherResponse.getWeather().get(0).getDescription())")
  @Mapping(target = "temperature", expression = "java(openWeatherResponse.getMain().getTemp() - 273.15F)")
  @Mapping(target = "feelsLike", expression = "java(openWeatherResponse.getMain().getFeelsLike() - 273.15F)")
  @Mapping(target = "windSpeed", source = "openWeatherResponse.wind.speed")
  @Mapping(target = "city", source = "openWeatherResponse.name")
  @Mapping(target = "country", source = "openWeatherResponse.sys.country")
  @Mapping(target = "duplicate", ignore = true)
  StreamSetsWeatherRequest toWeatherRequest(final OpenWeatherResponse openWeatherResponse);
}
