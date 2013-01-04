package com.github.stokito.IdeaFactoryMethodInspection;

import com.intellij.codeInspection.InspectionToolProvider;

public class FactoryMethodInspectionProvider implements InspectionToolProvider {
  public Class[] getInspectionClasses() {
    return new Class[] { SingletonInspection.class};
  }
}
