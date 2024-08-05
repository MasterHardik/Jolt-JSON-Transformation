import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test_jolt {
    public static void main(String[] args) throws IOException {
        // Read the JSON files
        List<Object> specs = JsonUtils.classpathToList("/spec.json");
        Object inputJSON = JsonUtils.classpathToObject("/input.json");

        // Create the Chainr
        Chainr chainr = Chainr.fromSpec(specs);

        // Transform the input JSON
        Object transformedOutput = chainr.transform(inputJSON);

        // Print the transformed JSON
        System.out.println(JsonUtils.toJsonString(transformedOutput));
    }
}
