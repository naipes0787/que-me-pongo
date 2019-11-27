package quemepongo.util;

import com.google.common.collect.Maps;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spark.Request;

import java.util.Map;

public class MultipartFormData {
    private static final ServletFileUpload parser = new ServletFileUpload(new DiskFileItemFactory());

    private Map<String, Object> data = Maps.newHashMap();

    public MultipartFormData(Request request) {
        try {
            parser.parseRequest(request.raw()).forEach(item -> {
                if (item.isFormField()) {
                    data.put(item.getFieldName(), item.getString());
                } else if (item.getSize() > 0) {
                    data.put(item.getFieldName(), item);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear form data");
        }
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }
}
