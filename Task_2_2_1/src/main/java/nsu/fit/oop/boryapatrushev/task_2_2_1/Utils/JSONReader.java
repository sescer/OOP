package nsu.fit.oop.boryapatrushev.task_2_2_1.Utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Baker;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Courier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Class implementing file readers
 * Needed for reading configs and information about workers of service.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class JSONReader {

    private static final ObjectMapper MAPPER =
            new ObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

    /**
     * Method to get file from resource folder
     * @param fileName file name
     * @return file, otherwise IllegalArgumentException would be thrown
     */
    private static File getFileFromResources(String fileName) {

        ClassLoader classLoader = JSONReader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException(fileName + ": file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    /**
     * JSON file reader
     * @param configPath path to JSON file with couriers
     * @return List of created workers
     */
    public static List<Courier> readJsonCourierList(String configPath) {

        CollectionType listType = MAPPER.getTypeFactory().constructCollectionType(List.class, Courier.class);

        try {
            return MAPPER.readValue(getFileFromResources(configPath), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON file reader
     * @param configPath path to JSON file with bakers
     * @return List of created workers
     */
    public static List<Baker> readJsonBakerList(String configPath) {

        CollectionType listType = MAPPER.getTypeFactory().constructCollectionType(List.class, Baker.class);

        try {
            return MAPPER.readValue(getFileFromResources(configPath), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON file reader
     * @param configPath path to JSON file with warehouse config
     * @return created warehouse
     */
    public static Warehouse readJsonWarehouseConfig(String configPath) {

        JavaType type = MAPPER.getTypeFactory().constructType(Warehouse.class);

        try {
            return MAPPER.readValue(getFileFromResources(configPath), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
