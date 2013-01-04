package org.github.stokito.RedunantPropertyGetting;

import com.intellij.codeInspection.*;
import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class RedunantPropertyGetting extends BaseJavaLocalInspectionTool {

    @NotNull
    public String getDisplayName() {
        return "getProperty() without using returning value";
    }

    @NotNull
    public String getGroupDisplayName() {
        return GroupNames.BUGS_GROUP_NAME;
    }

    @NotNull
    public String getShortName() {
        return "RedunantPropertyGetting";
    }


    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {

            @Override
            public void visitReferenceExpression(PsiReferenceExpression psiReferenceExpression) {
            }

            @Override
            public void visitExpressionStatement(PsiExpressionStatement statement) {
                super.visitExpressionStatement(statement);
                final PsiExpression expression = statement.getExpression();
                if(!(expression instanceof PsiMethodCallExpression)){
                    return;
                }
                final PsiMethodCallExpression call = (PsiMethodCallExpression) expression;
                final PsiMethod method = call.resolveMethod();
                if (method == null || method.isConstructor()) {
                    return;
                }
                final PsiType returnType = method.getReturnType();
                if(PsiType.VOID.equals(returnType)){
                    return;
                }
                final PsiClass aClass = method.getContainingClass();
                if (aClass == null){
                    return;
                }
                final PsiReferenceExpression methodExpression = call.getMethodExpression();
                final String methodName = methodExpression.getReferenceName();
                if(methodName == null){
                    return;
                }
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    holder.registerProblem(call, "not used returning value of getter");
                }
            }

        };
    }

    public boolean isEnabledByDefault() {
        return true;
    }
}
