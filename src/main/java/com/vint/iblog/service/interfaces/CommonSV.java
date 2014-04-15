package com.vint.iblog.service.interfaces;

/**
 * Common service
 *
 * Created by Vin on 14-4-14.
 */
public interface CommonSV {

    /**
     * 获取指定类型的序列当前值
     *
     * @param type  序列类型
     * @return  序列
     * @throws Exception
     */
    public String getSeqByType(String type) throws Exception;

    /**
     * 根据指定类型修改序列的当前值
     * @param type  序列类型
     * @param seq   序列
     * @throws Exception
     */
    public void modifySeqByType(String type, String seq) throws Exception;
}
