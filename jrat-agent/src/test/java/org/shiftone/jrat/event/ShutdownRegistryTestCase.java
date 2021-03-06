package org.shiftone.jrat.event;

import org.junit.Test;
import org.shiftone.jrat.core.output.FileOutputFactory;
import org.shiftone.jrat.core.output.FileOutputRegistry;
import org.shiftone.jrat.core.shutdown.ShutdownListener;
import org.shiftone.jrat.core.shutdown.ShutdownRegistry;
import org.shiftone.jrat.util.log.Logger;

/**
 * @author Jeff Drost
 */
public class ShutdownRegistryTestCase {

    private static final Logger LOG = Logger.getLogger(ShutdownRegistryTestCase.class);

    @Test
    public void testOne() {

        ShutdownRegistry shutdownRegistry = new ShutdownRegistry();
        FileOutputRegistry fileOutputRegistry = new FileOutputRegistry();
        FileOutputFactory fileOutputFactory = new FileOutputFactory(fileOutputRegistry, 0);

        shutdownRegistry.registerShutdownListener(fileOutputRegistry);
        for (int i = 0; i < 100; i++) {
            shutdownRegistry.registerShutdownListener(new TestShutdownListener(i));
        }
    }

    private class TestShutdownListener implements ShutdownListener {

        private final int i;

        public TestShutdownListener(int i) {
            this.i = i;
        }

        @Override
        public void shutdown() {
            LOG.info("shutdown " + i);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                LOG.error("sleep failed", e);
            }
        }

        @Override
        public String toString() {
            return "TestShutdownListener#" + i;
        }
    }
}
