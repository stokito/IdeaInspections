package org.github.stokito.RedunantPropertyGetting;

import com.intellij.codeInspection.InspectionToolProvider;

public class RedunantPropertyGettingProvider implements InspectionToolProvider {
  public Class[] getInspectionClasses() {
    return new Class[] { RedunantPropertyGetting.class};
  }
}
