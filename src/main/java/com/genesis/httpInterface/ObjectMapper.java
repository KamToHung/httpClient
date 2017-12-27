package com.genesis.httpInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.genesis.exception.MapperException;

import java.io.IOException;


/**
 * MAPPER对象
 */
public interface ObjectMapper {

    /**
     * 解析
     * @param content
     * @param cls
     * @param <T>
     * @return
     * @throws MapperException
     */
    <T> T parse(String content, Class<T> cls) throws MapperException;

    /**
     * 转换Json/Xml
     * @param object
     * @return
     */
    String toString(Object object) throws MapperException;



    ObjectMapper DEFAULT_MAPPER_JSON = new ObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        @Override
        public <T> T parse(String content, Class<T> cls) throws MapperException {
            try {
                return (T)objectMapper.readValue(content, cls);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data:" + content, e);
            }
        }

        @Override
        public String toString(Object object) throws MapperException{
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Throwable t) {
                throw new MapperException(t);
            }
        }
    };

    ObjectMapper DEFAULT_MAPPER_XML = new ObjectMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        @Override
        public <T> T parse(String content, Class<T> cls) throws MapperException {
            try {
                return (T)xmlMapper.readValue(content, cls);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data:" + content, e);
            }
        }

        @Override
        public String toString(Object object) throws MapperException {
            try {
                return xmlMapper.writeValueAsString(object);
            } catch (Throwable t) {
                throw new MapperException(t);
            }
        }
    };
}
