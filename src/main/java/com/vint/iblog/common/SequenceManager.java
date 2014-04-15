package com.vint.iblog.common;


import com.vint.iblog.common.exception.LRuntimeException;
import com.vint.iblog.service.interfaces.CommonSV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.util.HexTransManager;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列管理器
 * Created by Vin on 14-2-17.
 */
public class SequenceManager {

    private transient static Log log = LogFactory.getLog(SequenceManager.class);

    /*保存当前序列的最大值*/
    String curMaxSeq = "";
    /**/
    //private int step = 0;
    static int queueSize = 1000;

    static SequenceManager sm = new SequenceManager();

    final String lock = "Give me five";
    int reload_try_time = 0;
    int break_time = 3;
    //final ReentrantLock lock =

    // 根据序列类型存储序列的缓冲池
    private final Map<String, Queue<String>> sequence = new ConcurrentHashMap<String, Queue<String>>();

    /**
     * Get the singleton instance
     *
     * @return the singleton instance
     */
    public static SequenceManager getInstance() {
        return sm;
    }

    /**
     * Get new sequence
     *
     * @return <code>java.lang.String</code>
     */
    public String getNewSeq() {
        synchronized (lock) {
            String seq = sequence.get("C").poll();
            if (null == seq) {
                if (++reload_try_time >= break_time) {
                    throw new LRuntimeException("After trying to load sequence " + break_time + " times, "
                            + "queue is remaining empty, so application will be shut down.");
                }
                log.info("Sequence queue is empty, prepare to reload.");
                fill(curMaxSeq);
                if(sequence.get("C").size() > 0){
                    // reset retry times
                    reload_try_time = 0;
                }
                seq = getNewSeq();
            }
            return seq;
        }
    }

    private SequenceManager() {
        try {
            CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
            String startSeq = commonSV.getSeqByType("C");
            sequence.put("C", new ArrayBlockingQueue<String>(queueSize, true));
            fill(startSeq);
            log.info("Sequence manager initialed successfully!");
        } catch (Exception e) {
            throw new LRuntimeException("Failed to load sequence, application has been shut down.");
        }
    }

    private void fill(String startSeq) {
        HexTransManager htm = HexTransManager.getInstance(startSeq);
        while (sequence.get("C").size() < queueSize) {
            sequence.get("C").add(htm.getNext());
        }
        curMaxSeq = htm.getCurSeq();
        persistenceMaxSeq(htm.getCurSeq());
    }

    private void persistenceMaxSeq(String maxSeq) {
        try {
            CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
            commonSV.modifySeqByType("C", maxSeq);
            log.info("Sequence has increased to " + maxSeq);
        } catch (Exception e) {
            throw new LRuntimeException("Persistence sequence failed, application exited.");
        }
    }


}
