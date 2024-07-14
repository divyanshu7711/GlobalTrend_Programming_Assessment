
import java.lang.annotation.*;
        import javax.annotation.processing.*;
        import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.*;
        import java.util.Set;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {
}

@SupportedAnnotationTypes("LogExecutionTime")
public class annotion extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(LogExecutionTime.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                processMethod((TypeElement) element.getEnclosingElement(), element);
            } else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Annotation can only be applied to methods", element);
            }
        }
        return true;
    }

    private void processMethod(TypeElement classElement, Element methodElement) {
        try {
            String className = classElement.getQualifiedName().toString();
            String methodName = methodElement.getSimpleName().toString();

            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(className + "LogExecutionTimeProcessor", classElement);
            try (PrintWriter out = new PrintWriter(jfo.openWriter())) {
                out.println("package " + classElement.getEnclosingElement() + ";");
                out.println("public class " + className + "LogExecutionTimeProcessor" + " {");
                out.println("    public static void " + methodName + "() {");
                out.println("        long startTime = System.nanoTime();");
                out.println("        " + className + "." + methodName + "();");
                out.println("        long endTime = System.nanoTime();");
                out.println("        long duration = (endTime - startTime);");
                out.println("        System.out.println(\"Execution time of " + methodName + ": \" + duration + \" nanoseconds\");");
                out.println("    }");
                out.println("}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
