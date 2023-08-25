package com.fueled.chatty.detekt.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRulesProvider : RuleSetProvider {

    override val ruleSetId: String = RULE_SET_ID

    override fun instance(config: Config): RuleSet = RuleSet(
        RULE_SET_ID,
        listOf(
            ViewModelScopeForbidden(config),
        )
    )


    private companion object {
        const val RULE_SET_ID = "CustomRules"
    }
}
