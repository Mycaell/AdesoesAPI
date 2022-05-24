package com.klok.treinamento.adesoes.api.infrastructure.converter.custom;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService{

	@Autowired
	ModelMapper modelMapper;

	@Override
	public <T> T convert(Object data, Class<T> type) {
		return modelMapper.map(data, type);
	}
	
	@Override
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
}
