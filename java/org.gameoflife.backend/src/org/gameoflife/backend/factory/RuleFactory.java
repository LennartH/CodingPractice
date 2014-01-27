package org.gameoflife.backend.factory;

import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.impl.rule.StandardRuleApplier;

public class RuleFactory {
    
    public static RuleApplier createStandardRuleSet() {
        return new StandardRuleApplier();
    }

}
