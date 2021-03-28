package validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import validation.error.*;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JsonValidator {

    private Report report;

    public Report diff(JsonNode a, JsonNode b) {
        report = new Report();
        compareTwoNodes("ROOT", a,b);
        return report;
    }

    private void compareTwoNodes(String path, JsonNode a, JsonNode b){
        if(a.getNodeType().equals(JsonNodeType.NULL) || b.getNodeType().equals(JsonNodeType.NULL)){
            report.addError(new NullValueValidationError(path));
            return;
        }

        if(!a.getNodeType().equals(b.getNodeType())){
            report.addError(new TypeMissMatchValidationError(path, a.getNodeType().toString(), b.getNodeType().toString()));
            return;
        }

        JsonNodeType type = a.getNodeType();
        if(type.equals(JsonNodeType.ARRAY)){
            compareTwoArrays(path, a,b);
        }else if(type.equals(JsonNodeType.OBJECT)){
            compareTwoObjects(path, a,b);
        } else if(type.equals(JsonNodeType.BOOLEAN)
                || type.equals(JsonNodeType.STRING)
                || type.equals(JsonNodeType.NUMBER)
        ){
            // final node.
        }else{
            throw  new IllegalArgumentException(path + ": Unsupported type : " + type.toString());
        }
    }

    private void compareTwoArrays(String path, JsonNode a, JsonNode b){
        if(!(a.getNodeType().equals(JsonNodeType.ARRAY) &&a.getNodeType().equals(JsonNodeType.ARRAY))){
            throw new IllegalArgumentException("Must provide two json arrays.");
        }

        if(a.size() == 0 || b.size() ==0){
            report.addError(new EmptyArrayValidationError(path));
            return;
        }
        compareTwoNodes(path + ".[*]" ,  a.get(0),  b.get(0));
    }

    private void compareTwoObjects(String path, JsonNode a, JsonNode b){
        Set<String> fields1 = new LinkedHashSet<>(), fields2 = new LinkedHashSet<>();
        a.fieldNames().forEachRemaining(fields1::add);
        b.fieldNames().forEachRemaining(fields2::add);
        List<String> toCompare = new LinkedList<>();

        for(String e : fields1){
            if(!fields2.contains(e)){
                report.addError(new MissingFieldValidationError(path, e));
                continue;
            }
            toCompare.add(e);
        }

        for(String e : fields2){
            if(!fields1.contains(e)){
                report.addError(new ExtraFieldValidationError(path, e));
            }
        }

        toCompare.forEach(key -> compareTwoNodes( path + "." + key ,a.get(key), b.get(key)));
    }

}
