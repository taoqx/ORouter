package com.example.apt_compiler;

import com.example.apt_annotation.ORouter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import net.ltgt.gradle.incap.IncrementalAnnotationProcessor;
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.apt_annotation.ORouter")
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.AGGREGATING)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ORouterProcessor extends AbstractProcessor {

    private Filer filer;
    private Map<String, String> map = new HashMap<>();
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE,"processing");

        if (set != null && set.size() != 0) {   //process函数会执行多次
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ORouter.class);
            mapElements(elements);

            TypeSpec.Builder oRouter$Map = TypeSpec.classBuilder("ORouter$Map");
            FieldSpec fieldSpec;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                fieldSpec = FieldSpec.builder(String.class, entry.getKey())
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", entry.getValue())
                        .build();
                oRouter$Map.addField(fieldSpec);
            }
            TypeSpec typeSpec = oRouter$Map.build();
            JavaFile javaFile = JavaFile.builder("com.example.operation", typeSpec).build();
            try {
                System.out.println("creating a file");
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    private void mapElements(Set<? extends Element> elements) {
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                TypeElement typeElement = (TypeElement) element;
                String path = typeElement.getAnnotation(ORouter.class).path();
                String className = typeElement.getQualifiedName().toString();
                map.put(path,className);
            }
        }
    }

}
