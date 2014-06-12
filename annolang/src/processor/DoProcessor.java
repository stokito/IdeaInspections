package processor;

import annotation.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import javax.tools.Diagnostic;

/**
 * User: stokito
 * Date: 3/31/11
 * Time: 10:12 PM
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("annotation.Do")
public class DoProcessor extends AbstractProcessor {

    Messager messager;

    /**
     * Initializes the processor with the processing environment by
     * setting the {@code processingEnv} field to the value of the
     * {@code processingEnv} argument.  An {@code
     * IllegalStateException} will be thrown if this method is called
     * more than once on the same object.
     *
     * @param processingEnv environment to access facilities the tool framework
     *                      provides to the processor
     * @throws IllegalStateException if this method is called more than once.
     */
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getRootElements()) {
                Do annotation = e.getAnnotation(Do.class);
                if (annotation != null) {
                    String val = annotation.value();
                    AnnoCompiler compiler = new AnnoCompiler(val);
                    messager.printMessage(Diagnostic.Kind.WARNING, val, e);
                }
        }

        return false;
    }
}
