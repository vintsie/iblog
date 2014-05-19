package com.vint.iblog.command.service.interfaces;

import java.util.Map;

/**
 * 命令处理统一入口定义。每个具体的命令处理类需要实现这个接口。
 * Created by Vin on 14-5-17.
 */
public interface ICommandExe {

    /**
     * 命令处理统一入口定义。每个具体的命令处理类需要实现这个接口，比如
     * GitHub文章刷新工具{@link com.vint.iblog.command.service.impl.GitHubRefreshExe}
     *
     * @param params Servlet接收的所有参数。
     * @throws Exception
     */
    public void execute(Map params) throws Exception;
}
