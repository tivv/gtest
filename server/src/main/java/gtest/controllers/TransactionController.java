package gtest.controllers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import gtest.dto.ClientData;
import gtest.dto.Player;
import gtest.dto.TransactionRequest;
import gtest.dto.TransactionResponse;
import gtest.dao.PlayerDao;
import gtest.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vitalii Tymchyshyn
 */
@Controller
@RequestMapping(value = "/session")
public class TransactionController extends ExceptionHandlingController implements ServletContextAware {
    private final AtomicLong clientCounter = new AtomicLong(System.currentTimeMillis());
    private Cache<Long, Long> clientTransactions;
    private PlayerDao playerDao;
    private ServletContext servletContext;

    @Autowired
    public TransactionController(@Value("${transaction.cache.size}") int cacheSize) {
        clientTransactions = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ClientData registerClient() {

        long clientNum = clientCounter.incrementAndGet();
        clientTransactions.put(clientNum, 0L);
        return new ClientData(clientNum, 1L);

    }

    @RequestMapping(value="{session}/transaction", method = RequestMethod.POST)
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public @ResponseBody TransactionResponse addTransaction(HttpServletResponse response,
                                                            @PathVariable long session,
                                                            @RequestBody TransactionRequest transactionRequest) throws IOException {

        Long previousTransaction = clientTransactions.getIfPresent(session);
        if (previousTransaction == null) {
            throw new ControllerException(HttpStatus.GONE, "There is not session " + session + ", please reregister");
        }
        if (previousTransaction > transactionRequest.getTransactionId()) {
            throw new ControllerException(HttpStatus.CONFLICT, "Invalid transaction id "
                    + transactionRequest.getTransactionId() + ", current id is " + previousTransaction);
        }
        if (previousTransaction < transactionRequest.getTransactionId()) {
            playerDao.addTransaction(transactionRequest.getUsername(), transactionRequest.getBalanceChange());
            clientTransactions.put(session, transactionRequest.getTransactionId());
            response.setStatus(HttpStatus.CREATED.value());
            servletContext.log("Created " + (transactionRequest.getBalanceChange().signum() < 0 ? "OUT" : "IN")
                    + " transaction for " + transactionRequest.getUsername() + " with sum "
                    + transactionRequest.getBalanceChange().abs());
        } else {
            response.setStatus(HttpStatus.OK.value());
        }
        Player player = playerDao.getPlayer(transactionRequest.getUsername());
        return new TransactionResponse(transactionRequest.getTransactionId(), player.getBalanceVersion(),
                transactionRequest.getBalanceChange(), player.getBalance());
    }

    @Required @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
