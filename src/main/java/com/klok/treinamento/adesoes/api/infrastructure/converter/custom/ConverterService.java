package com.klok.treinamento.adesoes.api.infrastructure.converter.custom;

import java.util.List;

public interface ConverterService {
	
	<T> T convert(Object data, Class<T> type);
	
	<S, T> List<T> mapList(List<S> source, Class<T> targetClass);
}
