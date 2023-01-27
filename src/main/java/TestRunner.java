import annotation.AfterSuite;
import annotation.BeforeSuite;
import annotation.Test;
import exception.AnnotationException;
import tests.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {


    public static void start(Class<?> src) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, AnnotationException {
        Method[] declaredMethods = src.getDeclaredMethods();

        List<Method> testMethods;
        testMethods = Arrays.stream(declaredMethods)
                .filter(e -> e.getAnnotation(Test.class) != null)
                .sorted((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority())
                .collect(Collectors.toList());

        int count1 = 0, count2 = 0;
        for (Method method : declaredMethods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                count1++;
                if (count1 > 1) throw new AnnotationException("error two annotations 'BeforeSuite' in one class: " + src.getName());
                testMethods.add(0, method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                count2++;
                if (count2 > 1) throw new AnnotationException("error two annotations 'AfterSuite' in one class: " + src.getName());
                testMethods.add(method);
            }
        }

        Object obj = src.getDeclaredConstructor().newInstance();
        for (Method method : testMethods) {
            method.invoke(obj);
        }

    }

}
