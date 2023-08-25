package com.fueled.chatty.detekt.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.containingClass

class ViewModelScopeForbidden(
    config: Config,
) : Rule(config) {

    override val issue: Issue = Issue(
        javaClass.simpleName,
        Severity.Maintainability,
        "Forbids the use of `$VIEW_MODEL_SCOPE` within ViewModel implementations",
        Debt.FIVE_MINS,
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (function.containingClass()?.isFeatureViewModel() == true) {
            val bodyBlock = function.bodyBlockExpression ?: return
            val hasViewModelScope = bodyBlock.statements
                .map { statement -> statement.text }
                .any { text -> text.contains(VIEW_MODEL_SCOPE, ignoreCase = true) }

            if (hasViewModelScope) {
                reportIssue(function.containingClass()!!)
            }
        }
    }

    private fun reportIssue(
        klass :KtClass,
    ) {
        report(
            CodeSmell(
                issue,
                Entity.atPackageOrFirstDecl(klass.containingKtFile),
                message = "ViewModel (${klass.name}) is using `$VIEW_MODEL_SCOPE`. Prefer `launch {}` instead"
            )
        )
    }

    private fun KtClass.isFeatureViewModel(): Boolean {
        val isBaseViewModel = name?.contains("BaseViewModel") == true
        return superTypeListEntries.any { superType ->
            val superTypeName = superType.typeReference?.text.orEmpty()
            superTypeName.contains("ViewModel") && !isBaseViewModel
        }
    }

}

private const val VIEW_MODEL_SCOPE = "viewModelScope"
