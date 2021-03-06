package org.shiftone.jrat.core.config;

import java.util.ArrayList;
import java.util.List;
import org.shiftone.jrat.core.criteria.IncludeExcludeMethodCriteria;
import org.shiftone.jrat.core.criteria.MatcherMethodCriteria;
import org.shiftone.jrat.core.criteria.MethodCriteria;
import org.shiftone.jrat.util.log.Logger;

/**
 * @author jeff@shiftone.org (Jeff Drost)
 */
public class Profile {

    private static final Logger LOG = Logger.getLogger(Profile.class);
    private final IncludeExcludeMethodCriteria methodCriteria = new IncludeExcludeMethodCriteria();
    private String name;
    private final List<Handler> factories = new ArrayList<Handler>();

    public MatcherMethodCriteria createInclude() {
        MatcherMethodCriteria newCriteria = new MatcherMethodCriteria();
        methodCriteria.addPositive(newCriteria);
        return newCriteria;
    }

    public MatcherMethodCriteria createExclude() {
        MatcherMethodCriteria newCriteria = new MatcherMethodCriteria();
        methodCriteria.addNegative(newCriteria);
        return newCriteria;
    }

    public Handler createFactory() {
        Handler handler = new Handler();
        factories.add(handler);
        return handler;
    }

    public MethodCriteria getMethodCriteria() {
        return methodCriteria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Handler> getFactories() {
        return factories;
    }
}
