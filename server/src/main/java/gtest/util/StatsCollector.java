package gtest.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Vitalii Tymchyshyn
 */
public class StatsCollector implements Filter, ServletContextAware{
    private static final double NANO_FACTOR = 1e-9;
    private ServletContext servletContext;
    private long numQueries;
    private long minTime = Long.MAX_VALUE;
    private long maxTime = 0;
    private long totalTime = 0;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.nanoTime();
        try {
            chain.doFilter(request, response);
        } finally {
            long time = System.nanoTime() - start;
            accountRequest(time);
        }
    }

    private synchronized void accountRequest(long time) {
        numQueries++;
        totalTime += time;
        minTime = Math.min(time, minTime);
        maxTime = Math.max(time, maxTime);
    }

    @Scheduled(fixedRate = 60000)
    public synchronized void printStatistics() {
        if (numQueries == 0) {
            servletContext.log("Statistics: there was no queries during last minute");
        } else {
            servletContext.log(String.format("Statistics: %d queries processed, %15.5f seconds average," +
                    " %15.5f minimum, %15.5f maximum",
                    numQueries,
                    totalTime * NANO_FACTOR/ numQueries,
                    minTime * NANO_FACTOR,
                    maxTime * NANO_FACTOR
                    ));
            numQueries = 0;
            totalTime = maxTime = 0;
            minTime = Long.MAX_VALUE;
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
