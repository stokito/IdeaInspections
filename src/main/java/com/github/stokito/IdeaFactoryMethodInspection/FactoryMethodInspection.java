package com.github.stokito.IdeaFactoryMethodInspection;

import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * User: stokito
 * Date: 3/21/11
 * Time: 21:30 PM
 */
public class FactoryMethodInspection extends BaseJavaLocalInspectionTool {

    @NotNull
    @Override
    public String getDisplayName() {
        return "Factory Method inspection";
    }

    @NotNull
    @Override
    public String getGroupDisplayName() {
        return GroupNames.BUGS_GROUP_NAME;
    }

    @NotNull
    @Override
    public String getShortName() {
        return "FactoryMethod";
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {

            @Override
            public void visitReferenceExpression(PsiReferenceExpression psiReferenceExpression) {
            }

            @Override
            public void visitClass(PsiClass aClass) {
                super.visitClass(aClass);
                if (isNotUsualClass(aClass) || !classIsSingleton(aClass)) {
                    return;
                }
                checkInstanceGetters(aClass, holder);
            }
        };
    }

    private void checkInstanceGetters(@NotNull final PsiClass aClass, @NotNull final ProblemsHolder holder) {
        @NotNull final PsiMethod[] instanceGetters = getInstanceGetters(aClass);
        for (PsiMethod instanceGetter : instanceGetters) {
            checkInstanceGetterModifiers(holder, instanceGetter);
            checkInstanceGetterReturnType(aClass, holder, instanceGetter);
        }
    }


}
