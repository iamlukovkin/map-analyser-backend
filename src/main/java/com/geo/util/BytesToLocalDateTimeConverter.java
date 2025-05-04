package com.geo.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@ReadingConverter
public class BytesToLocalDateTimeConverter implements Converter<byte[], LocalDateTime> {

    @Override
    public LocalDateTime convert(final byte[] source) {
        return LocalDateTime.parse(new String(source), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}