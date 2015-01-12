package org.shiftone.jrat.core.jmx;

import org.shiftone.jrat.util.log.Logger;

/**
 * @author jeff@shiftone.org (Jeff Drost)
 */
public class NullJmxRegistry implements JmxRegistry {

    private static final Logger LOG = Logger.getLogger(NullJmxRegistry.class);
    public static final JmxRegistry INSTANCE = new NullJmxRegistry();

    private NullJmxRegistry() {
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void registerMBean(Object object, String name) {
        LOG.debug("registerMBean() doing nothing - jmx disabled");
    }
}