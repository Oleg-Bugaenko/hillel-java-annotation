import exception.AnnotationException;
import tests.MyTest;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, AnnotationException {

        TestRunner.start(MyTest.class);


    }
}
