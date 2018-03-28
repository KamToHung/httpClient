package com.genesis.httpinterface;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.genesis.exception.MapperException;


/**
 * MAPPER对象
 *
 * @author Terry
 */
public interface ObjectMapper {

    /**
     * 解析
     *
     * @param content
     * @param cls
     * @param <T>
     * @return
     * @throws MapperException
     */
    <T> T parse(String content, Class<T> cls) throws MapperException;

    /**
     * 转换Json/Xml
     *
     * @param object
     * @return
     * @throws MapperException
     */
    String toString(Object object) throws MapperException;


    ObjectMapper DEFAULT_MAPPER_JSON = new ObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper OBJECT_MAPPER = new com.fasterxml.jackson.databind.ObjectMapper();

        @Override
        public <T> T parse(String content, Class<T> cls) throws MapperException {
            try {
                return (T) OBJECT_MAPPER.readValue(content, cls);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data:" + content, e);
            }
        }

        @Override
        public String toString(Object object) throws MapperException {
            try {
                return OBJECT_MAPPER.writeValueAsString(object);
            } catch (Throwable t) {
                throw new MapperException(t);
            }
        }
    };

    ObjectMapper DEFAULT_MAPPER_XML = new ObjectMapper() {
        XmlMapper XML_MAPPER = new XmlMapper();

        @Override
        public <T> T parse(String content, Class<T> cls) throws MapperException {
            try {
                return (T) XML_MAPPER.readValue(content, cls);
            } catch (Exception e) {
                throw new MapperException(e.getMessage() + ",data:" + content, e);
            }
        }

        @Override
        public String toString(Object object) throws MapperException {
            try {
                return XML_MAPPER.writeValueAsString(object);
            } catch (Throwable t) {
                throw new MapperException(t);
            }
        }
    };


}
